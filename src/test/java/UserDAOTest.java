import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.dao.DiscountDAO;
import com.kolosovskyi.agency.dao.SQLConstance;
import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Discount;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserDAOTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOTest.class);
    private static final UserDAO dao = UserDAO.getUserDAO();
    private static final User user1 = new User("Andrey", "kolos@gmail.com", Role.USER);
    private static final User user2 = new User("Andrey", "shamashi@gmail.com", Role.ADMIN);
    private static final User user3 = new User("Alex", "lolashipan@gmil.com", Role.USER);

    @BeforeEach
    void start() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(SQLConstance.CREATE_USER_TABLE);
            PreparedStatement statement2 = connection.prepareStatement(SQLConstance.USER_FK);
            statement1.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot start UserDAOTest", e);
        }
    }

    @Test
    void createAndReadTest() {
       dao.create(user1);
       dao.create(user2);
       dao.create(user3);

       assertEquals(user1, dao.read(1L).get());
       assertEquals(user2, dao.read(2L).get());
       assertEquals(user3, dao.read(3L).get());
    }

    @Test
    void updateTest(){
        dao.create(user1);
        dao.create(user2);
        dao.create(user3);

        dao.update(user1, "Andrey", "qwert@gmail.com", Role.MANAGER);
        dao.update(user2, "Andrew", "shamashi@gmail.com", Role.ADMIN);
        dao.update(user3, "Alex", "zxc@dmail.com", Role.USER);

        assertEquals(new User(1L,"Andrey", "qwert@gmail.com", Role.MANAGER), dao.read(1L).get());
        assertEquals(new User(2L,"Andrew", "shamashi@gmail.com", Role.ADMIN), dao.read(2L).get());
        assertEquals(new User(3L,"Alex", "zxc@dmail.com", Role.USER), dao.read(3L).get());
    }

    @AfterEach
    void finish() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLConstance.DROP_USER);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot finished UserDAOTest", e);
        }
    }
}
