package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
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
            PreparedStatement statement = connection.prepareStatement(SQLConstance.INSERT_INTO_USER_TOURS);
        ){
            statement.setLong(1, userTours.getUser().getId());
            statement.setLong(2, userTours.getTour().getId());
            statement.setLong(3, userTours.getDiscountPercent());
            statement.setBigDecimal(4, userTours.getFinalPrice());
            statement.setLong(5, userTours.getStatus().ordinal());
            statement.setDate(6, userTours.getOrderTime());
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot create user tours", e);
        }
    }

    public Optional<List<UserTours>> read(User user){
        ArrayList<UserTours> userTours = new ArrayList<>();
        UserTours temp = null;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConstance.GET_USER_TOURS);
        ){
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                temp = new UserTours();
                temp.setUser(UserDAO.getUserDAO().read(resultSet.getLong("user_id")).get());
                temp.setTour(TourDAO.getTourDAO().read(resultSet.getLong("tour_id")).get());
                temp.setDiscountPercent(resultSet.getInt("discount_percent"));
                temp.setFinalPrice(resultSet.getBigDecimal("final_price"));
                temp.setOrderTime(resultSet.getDate("order_time"));
                temp.setStatus(TourStatus.values()[(int)resultSet.getLong("status_id")]);
                userTours.add(temp);
            }
        }catch (SQLException e){
            LOGGER.error("Cannot create user tours", e);
        }
        return Optional.of(userTours);
    }
     public void update(UserTours userTours){
        try(Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLConstance.UPDATE_USER_TOURS)) {
            statement.setLong(1, userTours.getTour().getId());
            statement.setInt(2, userTours.getDiscountPercent());
            statement.setBigDecimal(3, userTours.getFinalPrice());
            statement.setLong(4, userTours.getStatus().ordinal());
            statement.setDate(5, userTours.getOrderTime());
            statement.setLong(6, userTours.getUser().getId());

            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot update user tours ", e);
        }
    }

    public void delete(UserTours userTours){
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConstance.DELETE_USER_TOURS)) {
            statement.setLong(1, userTours.getUser().getId());
            statement.setLong(2, userTours.getTour().getId());
            statement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Cannot delete user tours ", e);
        }
    }
}
