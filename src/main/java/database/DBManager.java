package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private DBManager() {
    }

    private static class DBMHolder {
        private static final HikariDataSource dataSource;
        private static final HikariConfig config = new HikariConfig();

        static {
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/TravelAgencyDataBase");
            config.setUsername("postgres");
            config.setPassword("2298");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            dataSource = new HikariDataSource(config);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DBMHolder.dataSource.getConnection();
    }
}