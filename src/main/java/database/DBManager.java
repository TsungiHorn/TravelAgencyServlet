package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
   private final HikariDataSource dataSource;

   public DBManager(){
       HikariConfig config = new HikariConfig();
       config.setJdbcUrl("jdbc:postgresql://localhost:5432/TravelAgencyDataBase");
       config.setUsername("postgres");
       config.setPassword("2298");
       config.addDataSourceProperty("cachePrepStmts", "true");
       config.addDataSourceProperty("prepStmtCacheSize", "250");
       config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

       dataSource = new HikariDataSource(config);
   }

    public  Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
