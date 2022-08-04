package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class TourDAO {
    private final PostgreSQLConnectionPool pool;
    private static final Logger LOGGER = LoggerFactory.getLogger(TourDAO.class);

    private TourDAO(){
        pool = PostgreSQLConnectionPool.getInstance();
    }

    private static class TourDAOHandler{
        private static final TourDAO INSTANCE = new TourDAO();
    }
     public static TourDAO getTourDAO(){
        return TourDAOHandler.INSTANCE;
     }

    public void crate(Tour tour){
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConstance.INSERT_INTO_TOUR)){
            statement.setString(1, tour.getTitle());
            statement.setInt(2, tour.getType().ordinal());
            statement.setLong(3, tour.getPersonNumber());
            statement.setInt(4, tour.getHotelStars());
            statement.setBigDecimal(5, tour.getPrice());
            statement.setBoolean(6, tour.getHot());
            statement.setBoolean(7, tour.getHidden());
            statement.setString(8, tour.getCountry());
            statement.setString(9, tour.getCity());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                tour.setId(resultSet.getLong("id"));
        }catch (SQLException e){
            LOGGER.error("Cannot create tour ", e);
        }
    }

    public Optional<Tour> read(Long id){
        Tour tour = null;
        try(Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_INTO_TOUR)){
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
                tour = new Tour(id, resultSet.getString("title"), TourType.values()[(int) resultSet.getLong("type_id")], resultSet.getLong("person_number"), resultSet.getInt("hotel_stars"), resultSet.getBigDecimal("price"), resultSet.getBoolean("is_hot"), resultSet.getBoolean("is_hidden"), resultSet.getString("country"), resultSet.getString("city"));
            }
        }catch(SQLException e){
            LOGGER.error("Cannot read tour ", e);
        }
        return Optional.ofNullable(tour);
    }

    public void update(Tour tour, String title, TourType tourType, Long personNumber, Integer hotelStars, BigDecimal price, Boolean isHot,
                       Boolean isHidden, String country, String city){
        try(Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLConstance.UPDATE_TOUR)){
            statement.setString(1, title);
            statement.setLong(2, tourType.ordinal());
            statement.setLong(3, personNumber);
            statement.setInt(4, hotelStars);
            statement.setBigDecimal(5, price);
            statement.setBoolean(6, isHot);
            statement.setBoolean(7, isHidden);
            statement.setString(8, country);
            statement.setString(9, city);
            statement.setLong(10, tour.getId());
            statement.executeUpdate();
        }catch(SQLException e){
            LOGGER.error("Cannot update tour ", e);
        }
    }
    public void delete(Tour tour){
        try(Connection connection = pool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstance.DELETE_TOUR)){
            statement.setLong(1, tour.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("Cannot delete tour ", e);
        }
    }
}
