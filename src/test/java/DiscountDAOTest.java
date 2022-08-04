import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.dao.DiscountDAO;
import com.kolosovskyi.agency.dao.SQLConstance;
import com.kolosovskyi.agency.entity.Discount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DiscountDAOTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountDAOTest.class);
    private static final DiscountDAO dao = DiscountDAO.getDiscountDAO();
    private static final Discount discount1 = new Discount(1, 1);
    private static final Discount discount2 = new Discount(20, 10);
    private static final Discount discount3 = new Discount(0, 0);

    @BeforeEach
    void start() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLConstance.CREATE_DISCOUNT_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot start DiscountDAOTest", e);
        }
    }
    @AfterEach
    void finish() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLConstance.DROP_DISCOUNT);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot finished DiscountDAOTest", e);
        }
    }

    @Test
    void createAndReadTest() {
        dao.create(discount1);
        dao.create(discount2);
        dao.create(discount3);

        assertEquals(discount1, dao.read(1L).get());
        assertEquals(discount2, dao.read(2L).get());
        assertEquals(discount3, dao.read(3L).get());
        assertNotEquals(discount1, dao.read(2L).get());
        assertNotEquals(discount2, dao.read(3L).get());
        assertNotEquals(discount3, dao.read(1L).get());
    }

    @Test
    void updateTest(){
        dao.create(discount1);
        dao.create(discount2);
        dao.create(discount3);

        dao.update(discount1, 3, 7);
        dao.update(discount2, 27, 3);
        dao.update(discount3, 15, 25);

        assertEquals(new Discount(1L, 3, 7), dao.read(1L).get());
        assertEquals(new Discount(2L, 27, 3), dao.read(2L).get());
        assertEquals(new Discount(3L, 15, 25), dao.read(3L).get());
    }

    @Test
    void deleteTest(){
        dao.create(discount1);
        dao.create(discount2);
        dao.create(discount3);

        dao.delete(discount1);
        dao.delete(discount2);
        dao.delete(discount3);

//        assertThrows(Exception.class, (Executable) dao.read(1L).get());
//        //java.util.NoSuchElementException.class, (Executable) dao.read(1L).get()
//        //assertEquals(dao.read(1L).get(), discount1);
    }
}
