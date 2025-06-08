package com.vladm;


public final class JspHelper {

    private static final String FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String getPath(String JspName) {
        return String.format(FORMAT, JspName);
    }
}
