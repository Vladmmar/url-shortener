package com.vladm.dao;

import com.vladm.dto.UserDto;
import com.vladm.entity.Role;
import com.vladm.entity.User;
import com.vladm.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access=PRIVATE)
public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SELECT_ALL = """
            SELECT *
            FROM users
            """;
    private static final String SELECT_BY_ID = SELECT_ALL + """
            WHERE id = ?
            """;
    private static final String SELECT_BY_EMAIL = SELECT_ALL + """
            WHERE email = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO users(first_name, last_name, email, password, profile_image, birthday)
            VALUES(?, ?, ?, ?, ?, ?)
            """;

    @SneakyThrows
    @Override
    public List<User> findAll() {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            var resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }

            return users;
        }
    }

    @SneakyThrows
    @Override
    public Optional<User> findById(Integer id) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }

            return Optional.ofNullable(user);
        }
    }

    @SneakyThrows
    public Optional<User> findByEmail(String email) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }

            return Optional.ofNullable(user);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @SneakyThrows
    @Override
    public User save(User entity) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getProfileImage());
            preparedStatement.setDate(6, Date.valueOf(entity.getBirthday()));

            preparedStatement.executeUpdate();
            var resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            entity.setId(resultSet.getInt("id"));
            entity.setRegistrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime());

            return entity;
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .profileImage(resultSet.getString("profile_image"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                .role(Role.valueOf(resultSet.getString("role")))
                .build();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
