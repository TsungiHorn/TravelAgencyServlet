package service;


import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.dao.SQLStatements;
import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import com.kolosovskyi.agency.service.CredentialService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CredentialFreeTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialFreeTest.class);
    private static final UserDAO dao = UserDAO.getInstance();
    private static User user1;
    private static User user2;

    private final CredentialService service = CredentialService.getInstance();

    @BeforeEach
    void start() {
        user1 = new User("Andrey", "kolos@gmail.com", "qwser32", Role.USER, false);
        user2 = new User("Andrey", "shamashi@gmail.com", "asxz34f", Role.ADMIN, false);

        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(SQLStatements.CREATE_USER_TABLE);
            PreparedStatement statement2 = connection.prepareStatement(SQLStatements.USER_FK);
            statement1.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot start com.kolosovskyi.agency.dao.UserDAOTest", e);
        }
    }

    @Test
    void credentialFreeTest() {
        dao.create(user1);
        assertFalse(service.isCredentialFree("asdfghj", "kolos@gmail.com"));

        dao.create(user2);
        assertFalse(service.isCredentialFree("Andrey", "shamashi@gmail.com"));
        assertTrue(service.isCredentialFree("Andrey", "sham1ashi@gmail.com"));

    }


    @AfterEach
    void finish() {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatements.DROP_USER);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("cannot finished com.kolosovskyi.agency.dao.UserDAOTest", e);
        }
    }

}
