package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class DiscountDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountDAO.class);
    private final PostgreSQLConnectionPool pool;

    private DiscountDAO(){
        pool = PostgreSQLConnectionPool.getInstance();
    }

    private static class DiscountDAOHandler{
        private static final DiscountDAO INSTANCE = new DiscountDAO();
    }

    public static DiscountDAO getDiscountDAO(){
        return DiscountDAOHandler.INSTANCE;
    }
    public void create(Discount discount) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.INSERT_INTO_DISCOUNT)){
            statement.setInt(1, discount.getStep());
            statement.setInt(2, discount.getMaxPercent());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                discount.setId(resultSet.getLong("id"));
            discount.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            LOGGER.error("Cannot create discount", e);
        }
    }

    public Optional<Discount> read(Long id) {
        Discount discount = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_FROM_DISCOUNT_BY_ID)) {
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                discount = new Discount(resultSet.getLong("id"),
                        resultSet.getInt("step"), resultSet.getInt("max_percent"));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot read discount", e);
            e.printStackTrace();
        }
        return Optional.ofNullable(discount);
    }

    public void update(Discount discount) {
        try(Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLConstance.UPDATE_DISCOUNT)){
        statement.setInt(1, discount.getStep());
        statement.setInt(2, discount.getMaxPercent());
        statement.setLong(3, discount.getId());
        statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot update discount", e);
        }
    }


    public void delete(Discount discount) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstance.DELETE_DISCOUNT)) {
            statement.setLong(1, discount.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot delete discount", e);
            e.printStackTrace();
        }
    }
}
