package com.kolosovskyi.agency.connection;

import com.kolosovskyi.agency.Main;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSQLConnectionPool {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private final HikariDataSource dataSource;

    private PostgreSQLConnectionPool() {
        HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");
        dataSource = new HikariDataSource(config);
    }

    private static class ConnectionPoolHolder {
        private static final PostgreSQLConnectionPool INSTANCE = new PostgreSQLConnectionPool();
    }

    public static PostgreSQLConnectionPool getDBManager() {
        return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        logger.info("GETTING CONNECTION");
        return dataSource.getConnection();
    }
}
