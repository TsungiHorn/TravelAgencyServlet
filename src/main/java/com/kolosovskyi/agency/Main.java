package com.kolosovskyi.agency;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = PostgreSQLConnectionPool.getDBManager().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM role");
        prepareStatement.executeQuery();
        ResultSet resultSet = prepareStatement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }
}