package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Discount;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountDAOTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountDAOTest.class);
    private static final DiscountDAO dao = DiscountDAO.getDiscountDAO();
    private static Discount discount1;// = new Discount(1, 1);
    private static Discount discount2;// = new Discount(20, 10);
    private static Discount discount3;// = new Discount(0, 0);



    @BeforeEach
    void start() {
        discount1 = new Discount(1, 1);
        discount2 = new Discount(20, 10);
        discount3 = new Discount(0, 0);
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLConstance.CREATE_DISCOUNT_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot start com.kolosovskyi.agency.dao.DiscountDAOTest", e);
        }
    }
    @AfterEach
    void finish() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLConstance.DROP_DISCOUNT);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot finished com.kolosovskyi.agency.dao.DiscountDAOTest", e);
        }
    }

    @Test
    void createAndReadTest() {
        dao.create(discount1);
        dao.create(discount2);
        dao.create(discount3);

        assertEquals(discount1, dao.read(1L).orElse(new Discount()));
        assertEquals(discount2, dao.read(2L).orElse(new Discount()));
        assertEquals(discount3, dao.read(3L).orElse(new Discount()));
        assertNotEquals(discount1, dao.read(2L).orElse(new Discount()));
        assertNotEquals(discount2, dao.read(3L).orElse(new Discount()));
        assertNotEquals(discount3, dao.read(1L).orElse(new Discount()));
    }

    @Test
    void updateTest(){
        dao.create(discount1);
        dao.create(discount2);
        dao.create(discount3);

        discount1.setStep(3);
        discount1.setMaxPercent(7);
        dao.update(discount1);

        discount2.setStep(27);
        discount2.setMaxPercent(3);
        dao.update(discount2);

        discount3.setStep(15);
        discount3.setMaxPercent(25);
        dao.update(discount3);

        assertEquals(new Discount(1L, 3, 7), dao.read(1L).orElse(new Discount()));
        assertEquals(new Discount(2L, 27, 3), dao.read(2L).orElse(new Discount()));
        assertEquals(new Discount(3L, 15, 25), dao.read(3L).orElse(new Discount()));
    }

    @Test
    void deleteTest(){
        dao.create(discount1);
        dao.create(discount2);
        dao.create(discount3);

        dao.delete(discount1);
        dao.delete(discount2);
        dao.delete(discount3);

        assertFalse(dao.read(discount1.getId()).isPresent());
        assertFalse(dao.read(discount2.getId()).isPresent());
        assertFalse(dao.read(discount3.getId()).isPresent());
    }
}
