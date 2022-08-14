package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserToursDAOTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserToursDAOTest.class);
    private static final UserDAO userDAO = UserDAO.getUserDAO();
    private static final TourDAO tourDAO = TourDAO.getTourDAO();
    private static final UserToursDAO userToursDAO = UserToursDAO.getInstance();
    private static  User user1;
    private static  User user2;
    private static UserTours userTours1;
    private static UserTours userTours2;
    private static UserTours userTours3;

    @BeforeEach
    void start() {
        user1 = new User("Aoaoao",
                "qazxswedc@gmail.com",
                "qwesc5",
                Role.USER,
                false);
        user2 = new User("Sosa",
                "sosa@gmail.com",
                "q12wesc5",
                Role.USER,
                false);
        Tour tour1 = new Tour("Jubaba",
                TourType.REST,
                99L,
                4,
                new BigDecimal(1999),
                true,
                false,
                "Egypt",
                "Alexandria");
        Tour tour2 = new Tour("Don-Huyandon",
                TourType.SHOPPING,
                789L,
                5,
                new BigDecimal(3000),
                true,
                false,
                "UAE",
                "Dubai");
        Tour tour3 = new Tour("Shibuya",
                TourType.EXCURSION,
                500L,
                5,
                new BigDecimal(2600),
                true,
                true,
                "Japan",
                "Kyoto");

        userTours1 = new UserTours(user1,
                tour1,
                LocalDate.of(2022, 5, 8),
                TourStatus.REGISTERED,
                new BigDecimal(2500),
                20);
         userTours2 = new UserTours(user2,
                 tour2,
                 LocalDate.of(2023, 7, 28),
                TourStatus.PAID,
                 new BigDecimal(3000),
                 30);
         userTours3 = new UserTours(user2,
                 tour3,
                 LocalDate.of(2022, 1, 17),
                TourStatus.REGISTERED,
                 new BigDecimal(1700),
                 20);

        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(SQLConstance.CREATE_USER_TABLE);
            statement1.executeUpdate();

            PreparedStatement statement2 = connection.prepareStatement(SQLConstance.USER_FK);
            statement2.executeUpdate();

            PreparedStatement statement3 = connection.prepareStatement(SQLConstance.CREATE_TOUR_TABLE);
            statement3.executeUpdate();

            PreparedStatement statement4 = connection.prepareStatement(SQLConstance.TOUR_FK);
            statement4.executeUpdate();

            PreparedStatement statement5 = connection.prepareStatement(SQLConstance.CREATE_USER_TOURS_TABLE);
            statement5.executeUpdate();

            PreparedStatement statement6 = connection.prepareStatement(SQLConstance.USER_TOURS_FK0);
            statement6.executeUpdate();

            PreparedStatement statement7 = connection.prepareStatement(SQLConstance.USER_TOURS_FK1);
            statement7.executeUpdate();

            PreparedStatement statement8 = connection.prepareStatement(SQLConstance.USER_TOURS_FK2);
            statement8.executeUpdate();

            userDAO.create(user1);
            userDAO.create(user2);

            tourDAO.create(tour1);
            tourDAO.create(tour2);
            tourDAO.create(tour3);

        } catch (SQLException e) {
            LOGGER.error("cannot start com.kolosovskyi.agency.dao.UserToursDAOTest", e);
        }
    }

    @Test
    void createAndReadTest() {
        userToursDAO.create(userTours1);
        userToursDAO.create(userTours2);
        userToursDAO.create(userTours3);

        assertEquals(userTours1, userToursDAO.read(user1).get(0));
        assertEquals(userTours2, userToursDAO.read(user2).get(0));
        assertEquals(userTours3, userToursDAO.read(user2).get(1));

        assertNotEquals(userTours2, userToursDAO.read(user2).get(1));
        assertNotEquals(userTours1, userToursDAO.read(user2).get(1));
        assertNotEquals(userTours3, userToursDAO.read(user2).get(0));
    }
//    @Test
//    void updateTest(){
//        userToursDAO.create(userTours1);
//        userToursDAO.create(userTours2);
//        userToursDAO.create(userTours3);
//
//        user1.setName("Andrey");
//        user1.setEmail("qwert@gmail.com");
//        user1.setRole(Role.MANAGER);
//
//        user2.setName("Andrew");
//        user2.setEmail("shamashi@gmail.com");
//        user2.setRole(Role.ADMIN);
//
//        assertNotEquals(userTours1, userToursDAO.read(user1).get(0));
//        assertNotEquals(userTours2, userToursDAO.read(user2).get(0));
//        assertNotEquals(userTours3, userToursDAO.read(user2).get(1));
//
//        userDAO.update(user1);
//        userDAO.update(user2);
//
//        assertEquals(userTours1, userToursDAO.read(user1).get(0));
//        assertEquals(userTours2, userToursDAO.read(user2).get(0));
//        assertEquals(userTours3, userToursDAO.read(user2).get(1));
//
//        userTours1.setUser(user2);
//        userToursDAO.update(userTours1);
//        assertEquals(userTours1, userToursDAO.read(user2).get(0));
//
////        userTours2.setUser(user1);
////        userToursDAO.update(userTours2);
////        assertEquals(userTours2, userToursDAO.read(user1).get().get(0));
//
//        userTours3.setUser(user2);
//        userToursDAO.update(userTours1);
//        assertEquals(userTours1, userToursDAO.read(user1).get(0));
//
//    }
    @Test
    void deleteTest(){
        userToursDAO.create(userTours1);
        userToursDAO.create(userTours2);
        userToursDAO.create(userTours3);

        userToursDAO.delete(userTours1);
        userToursDAO.delete(userTours2);
        userToursDAO.delete(userTours3);

        assertFalse(userToursDAO.read(user1).isEmpty());
        assertFalse(userToursDAO.read(user2).isEmpty());
    }
    @AfterEach
    void finish() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(SQLConstance.DROP_USER_TOURS);
            statement1.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(SQLConstance.DROP_USER);
            statement2.executeUpdate();
            PreparedStatement statement3 = connection.prepareStatement(SQLConstance.DROP_TOUR);
            statement3.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot finished com.kolosovskyi.agency.dao.UserToursDAOTest", e);
        }
    }
}
