package com.kolosovskyi.agency.connection;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class PostgreSQLConnectionPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreSQLConnectionPool.class);
    private final HikariDataSource dataSource;

    private PostgreSQLConnectionPool() {
        HikariConfig config = new HikariConfig(readProperties());
        dataSource = new HikariDataSource(config);
    }

    private static Properties readProperties() {
        Properties properties = readPropertiesInTestEnvironment();
        if (properties.isEmpty()) {
            properties = readPropertiesInTomcatContainer();
        }
        return properties;
    }

    private static Properties readPropertiesInTestEnvironment() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/hikari.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.info("Can not load properties from test folder, application running in tomcat", e);
        }
        return properties;
    }

    private static Properties readPropertiesInTomcatContainer() {
        Properties properties;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resource = loader.getResourceAsStream("hikari.properties")) {
            properties = new Properties();
            properties.load(resource);
        } catch (IOException e) {
            LOGGER.error("Can not read properties for pool", e);
            properties = new Properties();
        }
        return properties;
    }

    private static class ConnectionPoolHolder {
        private static final PostgreSQLConnectionPool INSTANCE = new PostgreSQLConnectionPool();
    }

    public static PostgreSQLConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        LOGGER.info("GETTING CONNECTION");
        return dataSource.getConnection();
    }
}
