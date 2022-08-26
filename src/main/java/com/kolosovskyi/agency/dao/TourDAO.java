package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TourDAO {
    private final PostgreSQLConnectionPool pool;
    private static final Logger LOGGER = LoggerFactory.getLogger(TourDAO.class);

    private TourDAO() {
        pool = PostgreSQLConnectionPool.getInstance();
    }

    private static class TourDAOHandler {
        private static final TourDAO INSTANCE = new TourDAO();
    }

    public static TourDAO getInstance() {
        return TourDAOHandler.INSTANCE;
    }

    public void create(Tour tour) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.INSERT_INTO_TOUR)) {
            statement.setString(1, tour.getTitle());
            statement.setInt(2, tour.getTourType().ordinal());
            statement.setLong(3, tour.getPersonNumber());
            statement.setInt(4, tour.getHotelStars());
            statement.setBigDecimal(5, tour.getPrice());
            statement.setBoolean(6, tour.getHot());
            statement.setBoolean(7, tour.getHidden());
            statement.setString(8, tour.getCountry());
            statement.setString(9, tour.getCity());
            statement.setDate(10, Date.valueOf(tour.getStartDate()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                tour.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            LOGGER.error("Cannot create tour ", e);
        }
    }

    public Optional<Tour> read(Long id) {
        Tour tour = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.GET_INTO_TOUR)) {
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                tour = new Tour(id, resultSet.getString("title"),
                        TourType.values()[(int) resultSet.getLong("type_id")],
                        resultSet.getLong("person_number"), resultSet.getInt("hotel_stars"),
                        resultSet.getBigDecimal("price"), resultSet.getBoolean("is_hot"),
                        resultSet.getBoolean("is_hidden"), resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getDate("start_date").toLocalDate());
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot read tour ", e);
        }
        return Optional.ofNullable(tour);
    }

    public void update(Tour tour) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.UPDATE_TOUR)) {
            statement.setString(1, tour.getTitle());
            statement.setLong(2, tour.getTourType().ordinal());
            statement.setLong(3, tour.getPersonNumber());
            statement.setInt(4, tour.getHotelStars());
            statement.setBigDecimal(5, tour.getPrice());
            statement.setBoolean(6, tour.getHot());
            statement.setBoolean(7, tour.getHidden());
            statement.setString(8, tour.getCountry());
            statement.setString(9, tour.getCity());
            statement.setDate(10, Date.valueOf(tour.getStartDate()));
            statement.setLong(11, tour.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot update tour ", e);
        }
    }

    public void delete(Tour tour) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.DELETE_TOUR)) {
            statement.setLong(1, tour.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot delete tour ", e);
        }
    }

    public List<Tour> getHotTours() {
        List<Tour> hotTours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_HOT_TOURS)) {
            ResultSet resultSetHot = statement.executeQuery();
            while (resultSetHot.next()) {
                //if(resultSetHot.getDate("start_date").toLocalDate().compareTo(LocalDate.now()) >= 0) { //req on date!!!
                hotTours.add(new Tour(resultSetHot.getLong("id"),
                        resultSetHot.getString("title"),
                        TourType.values()[(int) resultSetHot.getLong("type_id")],
                        resultSetHot.getLong("person_number"),
                        resultSetHot.getInt("hotel_stars"),
                        resultSetHot.getBigDecimal("price"),
                        resultSetHot.getBoolean("is_hot"),
                        resultSetHot.getBoolean("is_hidden"),
                        resultSetHot.getString("country"),
                        resultSetHot.getString("city"),
                        resultSetHot.getDate("start_date").toLocalDate()));
                //}
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get all tours", e);
        }
        return hotTours;
    }

    public List<Tour> getSimpleTours() {
        List<Tour> simpleTours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_SIMPLE_TOURS)) {
            ResultSet resultSetSimple = statement.executeQuery();
            while (resultSetSimple.next()) {
                //if(resultSetSimple.getDate("start_date").toLocalDate().compareTo(LocalDate.now()) >= 0) {
                simpleTours.add(new Tour(resultSetSimple.getLong("id"),
                        resultSetSimple.getString("title"),
                        TourType.values()[(int) resultSetSimple.getLong("type_id")],
                        resultSetSimple.getLong("person_number"),
                        resultSetSimple.getInt("hotel_stars"),
                        resultSetSimple.getBigDecimal("price"),
                        resultSetSimple.getBoolean("is_hot"),
                        resultSetSimple.getBoolean("is_hidden"),
                        resultSetSimple.getString("country"),
                        resultSetSimple.getString("city"),
                        resultSetSimple.getDate("start_date").toLocalDate()));
                // }
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get all tours", e);
        }
        return simpleTours;
    }

    public List<Tour> getAll() {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_ALL_TOURS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tours.add(new Tour(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        TourType.values()[(int) resultSet.getLong("type_id")],
                        resultSet.getLong("person_number"),
                        resultSet.getInt("hotel_stars"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getBoolean("is_hot"),
                        resultSet.getBoolean("is_hidden"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getDate("start_date").toLocalDate()));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get all tours", e);
        }
        return tours;
    }

    public List<Tour> getTourOrderByPrice() {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_ALL_TOURS_ORDER_BY_PRICE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tours.add(new Tour(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        TourType.values()[(int) resultSet.getLong("type_id")],
                        resultSet.getLong("person_number"),
                        resultSet.getInt("hotel_stars"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getBoolean("is_hot"),
                        resultSet.getBoolean("is_hidden"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getDate("start_date").toLocalDate()));
            }
        } catch (SQLException e) {
            LOGGER.error("cannot get tours by price", e);
        }
        return tours;
    }
}
