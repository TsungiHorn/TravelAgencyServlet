import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.dao.SQLConstance;
import com.kolosovskyi.agency.dao.TourDAO;
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
    private static final Tour tour1 = new Tour("Jubaba", TourType.REST, 99L,4, new BigDecimal(1999), true,
            false, "Egypt", "Alexandria");
    private static final Tour tour2 = new Tour("Don-Huyandon", TourType.SHOPPING, 789L,5, new BigDecimal(3000), true,
            false, "UAE", "Dubai");
    private static final Tour tour3 = new Tour("Shibuya", TourType.EXCURSION, 500L,5, new BigDecimal(2600), true,
            true, "Japan", "Kyoto");

    @BeforeEach
    void start() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(SQLConstance.CREATE_TOUR_TABLE);
            PreparedStatement statement2 = connection.prepareStatement(SQLConstance.TOUR_FK);
            statement1.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot start TourDAOTest", e);
        }
    }

    @Test
    void createAndReadTest() {
        dao.crate(tour1);
        dao.crate(tour2);
        dao.crate(tour3);

        assertEquals(tour1, dao.read(1L).get());
        assertEquals(tour2, dao.read(2L).get());
        assertEquals(tour3, dao.read(3L).get());
    }

    @Test
    void updateTest(){
        dao.crate(tour1);
        dao.crate(tour2);
        dao.crate(tour3);

        dao.update(tour1, "Jubaba", TourType.REST, 200L,4, new BigDecimal(2450), false,
                false, "Egypt", "Alexandria");
        dao.update(tour2, "Don-Huyandon", TourType.REST, 789L,5, new BigDecimal(3000), false,
                true, "UAE", "Dubai");
        dao.update(tour3, "Shibuya 2.0", TourType.EXCURSION, 500L,5, new BigDecimal(2600), true,
                true, "Japan", "Osaka");

        assertEquals(new Tour(1L,"Jubaba", TourType.REST, 200L,4, new BigDecimal(2450), false,
                false, "Egypt", "Alexandria"), dao.read(1L).get());
        assertEquals(new Tour(2L,"Don-Huyandon", TourType.REST, 789L,5, new BigDecimal(3000), false,
                true, "UAE", "Dubai"), dao.read(2L).get());
        assertEquals(new Tour(3L,"Shibuya 2.0", TourType.EXCURSION, 500L,5, new BigDecimal(2600), true,
                true, "Japan", "Osaka"), dao.read(3L).get());
    }

    @AfterEach
     void finish() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLConstance.DROP_TOUR);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot finished TourDAOTest", e);
        }
    }
}
