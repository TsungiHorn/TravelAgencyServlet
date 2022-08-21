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

    public static UserDAO getInstance(){
        return UserDAOHandler.INSTANCE;
    }

    public void create(User user){
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.INSERT_INTO_USER)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getRole().ordinal());
            statement.setBoolean(5, user.getBlocked());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                user.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            LOGGER.error("Cannot create user", e);
        }
    }

    public Optional<User> read(Long id){
        User user = null;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_FROM_USER)){
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
                user = new User(id, resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
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
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getRole().ordinal());
            statement.setBoolean(5, user.getBlocked());
            statement.setLong(6, user.getId());
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

    public Optional<User> getUserByEmail(String email){
        User user = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_FULL_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                user=new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        Role.values()[(int) resultSet.getLong("role_id")],
                        resultSet.getBoolean("is_blocked"));
        } catch (SQLException e) {
            LOGGER.error("Cannot get user", e);
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public boolean isExistingLogin(String email, String password){
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_USER_BY_EMAIL_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            LOGGER.error("Cannot find user in db", e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExistingCreateAccount(String email){
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            LOGGER.error("Cannot find user in db", e);
            e.printStackTrace();
        }
        return false;
    }
}
