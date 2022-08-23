package com.kolosovskyi.agency.dao;

import com.kolosovskyi.agency.connection.PostgreSQLConnectionPool;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class UserDAOTest {
    private static final PostgreSQLConnectionPool POOL = PostgreSQLConnectionPool.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOTest.class);
    private static final UserDAO dao = UserDAO.getInstance();
    private static User user1;
    private static User user2;
    private static User user3;

    @BeforeEach
    void start() {
        user1 = new User("Andrey", "kolos@gmail.com", "qwser32", Role.USER, false);
        user2 = new User("Andrey", "shamashi@gmail.com", "asxz34f", Role.ADMIN, false);
        user3 = new User("Alex", "lolashipan@gmil.com","asxz34f", Role.USER, false);

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
    void createAndReadTest() {
       dao.create(user1);
       dao.create(user2);
       dao.create(user3);

       assertEquals(user1, dao.read(1L).orElse(new User()));
       assertEquals(user2, dao.read(2L).orElse(new User()));
       assertEquals(user3, dao.read(3L).orElse(new User()));
    }

    @Test
    void updateTest(){
        dao.create(user1);
        dao.create(user2);
        dao.create(user3);

        user1.setName("Andrey");
        user1.setEmail("qwert@gmail.com");
        user1.setPassword("asdfgh");
        user1.setRole(Role.MANAGER);
        user2.setName("Andrew");
        user2.setEmail("shamashi@gmail.com");
        user2.setRole(Role.ADMIN);
        user3.setName("Alex");
        user3.setEmail("zxc@dmail.com");
        user3.setRole(Role.USER);

        dao.update(user1);
        dao.update(user2);
        dao.update(user3);

        assertEquals(user1, dao.read(1L).orElse(new User()));
        assertEquals(user2, dao.read(2L).orElse(new User()));
        assertEquals(user3, dao.read(3L).orElse(new User()));
    }

    @Test
    void deleteTest(){
        dao.create(user1);
        dao.create(user2);
        dao.create(user3);

        dao.delete(user1);
        dao.delete(user2);
        dao.delete(user3);

        assertFalse(dao.read(1L).isPresent());
        assertFalse(dao.read(2L).isPresent());
        assertFalse(dao.read(3L).isPresent());
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
