package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
    private final PostgreSQLConnectionPool pool;

    private UserDAO(){
        pool = PostgreSQLConnectionPool.getInstance();
    }

    private static class UserDAOHandler{
        private static final UserDAO INSTANCE = new UserDAO();
    }

    public static UserDAO getUserDAO(){
        return UserDAOHandler.INSTANCE;
    }

    public void create(User user){
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.INSERT_INTO_USER)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getRole().ordinal());
            statement.setBoolean(4, user.getBlocked());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                user.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            LOGGER.error("Cannot create user", e);
        }
    }

    public Optional<User> read(Long id){ //Optional
        User user = null;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_FROM_USER)){
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
                user = new User(id, resultSet.getString("name"),
                        resultSet.getString("email"),
                        Role.values()[(int) resultSet.getLong("role_id")],
                        resultSet.getBoolean("is_blocked"));
            }
        }catch(SQLException e){
            LOGGER.error("Cannot read user ", e);
        }
        return Optional.ofNullable(user);
    }

    public void update(User user) {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConstance.UPDATE_USER)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getRole().ordinal());
            statement.setBoolean(4, user.getBlocked());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot update user", e);
        }
    }


    public void delete(User user) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.DELETE_USER)) {
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot delete user", e);
            e.printStackTrace();
        }
    }
}
