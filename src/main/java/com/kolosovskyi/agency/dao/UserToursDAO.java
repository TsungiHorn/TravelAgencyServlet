package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourStatus;
import com.kolosovskyi.agency.entity.User;
import com.kolosovskyi.agency.entity.UserTours;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserToursDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserToursDAO.class);
    private static final UserDAO USER_DAO = UserDAO.getInstance();
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();
    private final PostgreSQLConnectionPool pool;

    private UserToursDAO(){
        pool = PostgreSQLConnectionPool.getInstance();
    }
    private static class UserToursDAOHandler{
        private static final UserToursDAO INSTANCE = new UserToursDAO();
    }

    public static UserToursDAO getInstance(){
        return UserToursDAOHandler.INSTANCE;
    }

    public void create(UserTours userTours){
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLStatements.INSERT_INTO_USER_TOURS);
        ){
            statement.setLong(1, userTours.getUser().getId());
            statement.setLong(2, userTours.getTour().getId());
            statement.setLong(3, userTours.getDiscountPercent());
            statement.setBigDecimal(4, userTours.getFinalPrice());
            statement.setLong(5, userTours.getStatus().ordinal());
            statement.setDate(6, Date.valueOf(userTours.getOrderTime()));
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot create user tours", e);
        }
    }

    public List<UserTours> read(User user){
        ArrayList<UserTours> userTours = new ArrayList<>();
        UserTours temp;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLStatements.GET_USER_TOURS);
        ){
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                temp = new UserTours();
                temp.setUser(USER_DAO.read(resultSet.getLong("user_id")).orElse(new User()));
                temp.setTour(TOUR_DAO.read(resultSet.getLong("tour_id")).orElse(new Tour()));
                temp.setDiscountPercent(resultSet.getInt("discount_percent"));
                temp.setFinalPrice(resultSet.getBigDecimal("final_price"));
                temp.setOrderTime(resultSet.getDate("order_time").toLocalDate());
                temp.setStatus(TourStatus.values()[(int)resultSet.getLong("status_id")]);
                userTours.add(temp);
            }
        }catch (SQLException e){
            LOGGER.error("Cannot read user tours", e);
        }
        return userTours;
    }

    public Optional<UserTours>  readTourUser(Long userId, Long tourId){
        UserTours userTour = null;
       try(Connection connect = pool.getConnection();
           PreparedStatement statement = connect.prepareStatement(SQLStatements.GET_TOUR_USER)){
           statement.setLong(1, userId);
           statement.setLong(2, tourId);
           ResultSet resultSet = statement.executeQuery();
           if(resultSet.next()) {
            userTour = new UserTours(USER_DAO.read(userId).orElse(new User()),
                    TOUR_DAO.read(tourId).orElse(new Tour()),
                    resultSet.getDate("order_time").toLocalDate(),
                    TourStatus.values()[(int) resultSet.getLong("status_id")],
                    resultSet.getBigDecimal("final_price"),
                    resultSet.getInt("discount_percent")
                    );
           }
       }catch (SQLException e){
           LOGGER.error("Cannot find user`s tour", e);
       }
       return Optional.ofNullable(userTour);
    }
     public void update(UserTours userTours){
        try(Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLStatements.UPDATE_USER_TOURS)) {
            statement.setLong(1, userTours.getUser().getId());
            statement.setLong(2, userTours.getTour().getId());
            statement.setInt(3, userTours.getDiscountPercent());
            statement.setBigDecimal(4, userTours.getFinalPrice());
            statement.setLong(5, userTours.getStatus().ordinal());
            statement.setDate(6, Date.valueOf(userTours.getOrderTime()));
            statement.setLong(7, userTours.getUser().getId());
            statement.setLong(8, userTours.getTour().getId());
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot update user tours ", e);
        }
    }

    public void delete(Long userId, Long tourId){
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLStatements.DELETE_USER_TOURS)) {
            statement.setLong(1, userId);
            statement.setLong(2, tourId);
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot delete user tours ", e);
        }
    }
}
