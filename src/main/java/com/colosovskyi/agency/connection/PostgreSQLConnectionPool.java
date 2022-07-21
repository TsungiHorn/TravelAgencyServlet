package com.colosovskyi.agency.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQLConnectionPool {
    private final HikariDataSource dataSource;

    private PostgreSQLConnectionPool() {
        HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");
        dataSource = new HikariDataSource(config);
    }

    private static class ConnectionPoolHolder {
        private static final PostgreSQLConnectionPool INSTANCE = new PostgreSQLConnectionPool();
    }

    public static PostgreSQLConnectionPool getDBManager() {
        return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static class Main {
        public static void main(String[] args) throws SQLException {

            Connection connection = getDBManager().getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM role");
            prepareStatement.executeQuery();
            ResultSet resultSet = prepareStatement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        }
    }
}
