package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourType;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TourDAOTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(TourDAOTest.class);
    private static final TourDAO dao = TourDAO.getTourDAO();
    private static  Tour tour1;
    private static  Tour tour2;
    private static  Tour tour3;
    @BeforeEach
    void start() {
        tour1 = new Tour("Jubaba",
                TourType.REST,
                99L,
                4,
                new BigDecimal(1999),
                true,
                false,
                "Egypt",
                "Alexandria");

        tour2 = new Tour("Don-Huyandon",
                TourType.SHOPPING,
                789L,
                5,
                new BigDecimal(3000),
                true,
                false,
                "UAE",
                "Dubai");

        tour3 = new Tour("Shibuya",
                TourType.EXCURSION,
                500L,
                5,
                new BigDecimal(2600),
                true,
                true,
                "Japan",
                "Kyoto");

        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(SQLConstance.CREATE_TOUR_TABLE);
            PreparedStatement statement2 = connection.prepareStatement(SQLConstance.TOUR_FK);
            statement1.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot start com.kolosovskyi.agency.dao.TourDAOTest", e);
        }
    }

    @Test
    void createAndReadTest() {
        dao.crate(tour1);
        dao.crate(tour2);
        dao.crate(tour3);

        assertEquals(tour1, dao.read(1L).orElse(new Tour()));
        assertEquals(tour2, dao.read(2L).orElse(new Tour()));
        assertEquals(tour3, dao.read(3L).orElse(new Tour()));
    }

    @Test
    void updateTest(){
        dao.crate(tour1);
        dao.crate(tour2);
        dao.crate(tour3);

        tour1.setTitle("Jubaba");
        tour1.setType(TourType.REST);
        tour1.setPersonNumber(200L);
        tour1.setHotelStars(4);
        tour1.setPrice(new BigDecimal(2500));
        tour1.setHot(false);
        tour1.setHidden(false);
        tour1.setCountry("Egypt");
        tour1.setCity("Alexandria");


        tour2.setTitle("Don-Huyandon");
        tour2.setType(TourType.REST);
        tour2.setPersonNumber(789L);
        tour2.setHotelStars(5);
        tour2.setPrice(new BigDecimal(3000));
        tour2.setHot(false);
        tour2.setHidden(true);
        tour2.setCountry("Egypt");
        tour2.setCity("Alexandria");


        tour3.setTitle("Shibuya 2.0");
        tour3.setType(TourType.EXCURSION);
        tour3.setPersonNumber(500L);
        tour3.setHotelStars(5);
        tour3.setPrice(new BigDecimal(2600));
        tour3.setHot(true);
        tour3.setHidden(true);
        tour3.setCountry("Japan");
        tour3.setCity("Osaka");

        dao.update(tour1);
        dao.update(tour2);
        dao.update(tour3);

        assertEquals(tour1, dao.read(1L).orElse(new Tour()));
        assertEquals(tour2, dao.read(2L).orElse(new Tour()));
        assertEquals(tour3, dao.read(3L).orElse(new Tour()));
    }

    @Test
    void deleteTest(){
        dao.crate(tour1);
        dao.crate(tour2);
        dao.crate(tour3);

        dao.delete(tour1);
        dao.delete(tour2);
        dao.delete(tour3);

        assertFalse(dao.read(1L).isPresent());
        assertFalse(dao.read(2L).isPresent());
        assertFalse(dao.read(3L).isPresent());
    }
    @AfterEach
     void finish() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLConstance.DROP_TOUR);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot finished com.kolosovskyi.agency.dao.TourDAOTest", e);
        }
    }
}
