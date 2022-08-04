import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.dao.SQLConstance;
import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserToursDAOTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserToursDAOTest.class);
    private static final UserDAO userDAO = UserDAO.getUserDAO();
    private static final TourDAO tourDAO = TourDAO.getTourDAO();
    private static final UserToursDAO userToursDAO = UserToursDAO.getInstance();
    private static final User user1 = new User("Aoaoao", "qazxswedc@gmail.com", Role.USER);
    private static final User user2 = new User("Sosa", "sosa@gmail.com", Role.USER);
    private static final Tour tour1 = new Tour("Jubaba", TourType.REST, 99L,4, new BigDecimal(1999), true,
            false, "Egypt", "Alexandria");
    private static final Tour tour2 = new Tour("Don-Huyandon", TourType.SHOPPING, 789L,5, new BigDecimal(3000), true,
            false, "UAE", "Dubai");
    private static final Tour tour3 = new Tour("Shibuya", TourType.EXCURSION, 500L,5, new BigDecimal(2600), true,
            true, "Japan", "Kyoto");

    private static final UserTours userTours1 = new UserTours(user1, tour1, new Date(12), TourStatus.REGISTERED, new BigDecimal(2500), 20);
    private static final UserTours userTours2 = new UserTours(user2, tour2, new Date(11), TourStatus.PAID, new BigDecimal(3000), 30);
    private static final UserTours userTours3 = new UserTours(user2, tour3, new Date(13), TourStatus.REGISTERED, new BigDecimal(1700), 20);
    @BeforeEach
    void start() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(SQLConstance.CREATE_USER_TABLE);
            PreparedStatement statement2 = connection.prepareStatement(SQLConstance.USER_FK);
            statement1.executeUpdate();
            statement2.executeUpdate();
            PreparedStatement statement3 = connection.prepareStatement(SQLConstance.CREATE_TOUR_TABLE);
            PreparedStatement statement4 = connection.prepareStatement(SQLConstance.TOUR_FK);
            statement3.executeUpdate();
            statement4.executeUpdate();
            PreparedStatement statement5 = connection.prepareStatement(SQLConstance.CREATE_USER_TOURS_TABLE);
            statement5.executeUpdate();
            PreparedStatement statement6 = connection.prepareStatement(SQLConstance.USER_TOURS_FK0);
            PreparedStatement statement7 = connection.prepareStatement(SQLConstance.USER_TOURS_FK1);
            PreparedStatement statement8 = connection.prepareStatement(SQLConstance.USER_TOURS_FK2);
            statement6.executeUpdate();
            statement7.executeUpdate();
            statement8.executeUpdate();

            userDAO.create(user1);
            userDAO.create(user2);
                                                                //FIX CREATE METHOD IN UserToursDAO
            tourDAO.crate(tour1);
            tourDAO.crate(tour2);
            tourDAO.crate(tour3);

        } catch (SQLException e) {
            LOGGER.error("cannot start UserToursDAOTest", e);
        }
    }

    @Test
    void createAndReadTest() {
        userToursDAO.create(userTours1);
        userToursDAO.create(userTours2);
        userToursDAO.create(userTours3);

        assertEquals(userTours1, userToursDAO.read(user1).get().get(0));
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
            LOGGER.error("cannot finished UserToursDAOTest", e);
        }
    }
}
