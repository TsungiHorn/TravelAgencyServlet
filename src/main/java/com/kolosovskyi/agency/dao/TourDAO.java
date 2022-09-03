package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourType;
import com.kolosovskyi.agency.exception.DAOException;
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
            throw new DAOException();
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
            throw new DAOException();
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
            throw new DAOException();
        }
    }

    public void delete(Tour tour) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.DELETE_TOUR)) {
            statement.setLong(1, tour.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot delete tour ", e);
            throw new DAOException();
        }
    }

    public List<Tour> getAll(int offset)  {
        List<Tour> tours;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_ALL_TOURS)) {
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            tours = getListOfTours(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Cannot get all tours", e);
            throw new DAOException();
        }
        return tours;
    }

    public List<Tour> getAllToursToADMIN(int offset) {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_ALL_TOURS_ADMIN)) {
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            tours = getListOfTours(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Cannot get all admin tours", e);
            throw new DAOException();
        }
        return tours;
    }

    private List<Tour> getListOfTours(ResultSet resultSet) throws SQLException {
        List<Tour> tours = new ArrayList<>();
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
        return tours;
    }

    public int getAmountOfPages() {
        int result = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_PAGES_OF_TOUR)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("ceil");
            }
        } catch (
                SQLException e) {
            LOGGER.error("Cannot get all tours", e);
            throw new DAOException();
        }
        return result;
    }

    public List<Tour> getTourOrderByPrice(int offset) {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_ALL_TOURS_ORDER_BY_PRICE)) {
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            tours = getListOfTours(resultSet);
        } catch (SQLException e) {
            LOGGER.error("cannot get tours by price", e);
            throw new DAOException();
        }
        return tours;
    }

    public List<Tour> getTourOrderByStars(int offset) {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_ALL_TOURS_ORDER_BY_HOTEL_STARS)) {
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            tours = getListOfTours(resultSet);
        } catch (SQLException e) {
            LOGGER.error("cannot get tours by stars", e);
            throw new DAOException();
        }
        return tours;
    }

    public List<Tour> getTourOrderByCountOfPerson(int offset) {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SELECT_ALL_TOURS_ORDER_BY_PERSON_NUMBER)) {
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            tours = getListOfTours(resultSet);
        } catch (SQLException e) {
            LOGGER.error("cannot get tours by person number", e);
            throw new DAOException();
        }
        return tours;
    }
}
