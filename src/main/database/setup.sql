create table users(
                      id serial primary key,
                      first_name VARCHAR(50) not null,
                      last_name VARCHAR(50) not null,
                      email VARCHAR(256) not null,
                      password VARCHAR(72) not null,
                      profile_image VARCHAR(50),
                      birthday date not null,
                      registration_date timestamp not null DEFAULT now(),
                      role VARCHAR(15) not null DEFAULT 'USER',
                      check (role in ('USER', 'ADMIN'))
);