package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBManager.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM role");
        prepareStatement.executeQuery();
        ResultSet resultSet = prepareStatement.getResultSet();
        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }
}
