package com.epam.expositions.query;

public class UserQueries {
    public static final String SELECT_FROM_USER_BY_LOGIN = "SELECT u.id, u.login, u.password, u.email, r.id as role_id, r.name FROM user as u\n" +
            " join role as r on u.role_id = r.id \n" +
            " where u.login = ?";

    public static final String SELECT_FROM_USER_BY_ID = "SELECT u.id, u.login, u.password, u.email, r.id as role_id, r.name FROM user as u\n" +
            " join role as r on u.role_id = r.id \n" +
            " where u.id = ?";

}
