package com.epam.expositions.dao.query;

public class UserQueries {
    public static final String SELECT_FROM_USER_BY_LOGIN = "SELECT u.id, u.login, u.password, u.email, r.id as role_id, r.name FROM user as u\n" +
            " join role as r on u.role_id = r.id \n" +
            " where u.login = ?";

    public static final String SELECT_FROM_USER_BY_ID = "SELECT u.id, u.login, u.password, u.email, r.id as role_id, r.name FROM user as u\n" +
            " join role as r on u.role_id = r.id \n" +
            " where u.id = ?";

    public static final String SELECT_FROM_USER = "SELECT u.id, u.login, u.password, u.email, r.id as role_id, r.name FROM user as u\n" +
            " join role as r on u.role_id = r.id";

    public static final String SELECT_FROM_USER_BY_EMAIL = "SELECT u.id, u.login, u.password, u.email, r.id as role_id, r.name FROM user as u\n" +
            " join role as r on u.role_id = r.id \n" +
            " where u.email = ?";
    public static final String SELECT_LAST_USER_INSERTED = "SELECT u.id, u.login, u.password, u.email, r.id as role_id, r.name FROM user as u\n" +
            " join role as r on u.role_id = r.id \n" +
            " ORDER BY ID DESC LIMIT 1";

    public static final String CREATE_USER = "INSERT INTO user (`login`, `password`, `email`, `role_id`) VALUES (?, ?, ?, ?);";

}
