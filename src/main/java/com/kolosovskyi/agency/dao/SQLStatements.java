package com.kolosovskyi.agency.dao;

public final class SQLStatements {
    public static final String GET_FULL_USER_BY_EMAIL = "SELECT id, name, email, password, role_id, is_blocked FROM \"user\" WHERE email = ?";
    public static final String SELECT_HOT_TOURS = "SELECT * FROM tour WHERE is_hot = true AND is_hidden = false AND start_date >= 'today'";
    public static final String SELECT_SIMPLE_TOURS = "SELECT * FROM tour WHERE is_hot = false AND is_hidden = false AND start_date >= 'today'";
    public static final String SELECT_ALL_TOURS = "SELECT * FROM tour";
    public static final String SELECT_ALL_USERS = "SELECT * FROM \"user\"";
    public static final String INSERT_INTO_DISCOUNT = "INSERT INTO discount(step, max_percent) VALUES (?, ?) RETURNING id";
    public static final String GET_FROM_DISCOUNT_BY_ID = "SELECT id, step ,max_percent FROM discount WHERE id = ?";
    public static final String UPDATE_DISCOUNT = "UPDATE discount SET step = ?, max_percent = ? WHERE id = ?";
    public static final String DELETE_DISCOUNT = "DELETE FROM discount WHERE id = ?";
    public static final String INSERT_INTO_TOUR = "INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city, start_date) VALUES(?,?,?,?,?,?,?,?,?,?) RETURNING id";
    public static final String GET_USER_BY_EMAIL_PASSWORD = "SELECT email, password FROM \"user\" WHERE email=? AND password=?";
    public static final String GET_USER_BY_EMAIL = "SELECT email FROM \"user\" WHERE email=?";
    public static final String GET_INTO_TOUR = "SELECT id, title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city, start_date FROM tour WHERE id = ?";
    public static final String UPDATE_TOUR = "UPDATE tour SET title = ?, type_id = ?, person_number = ?, hotel_stars = ?, price = ?, is_hot = ?, is_hidden = ?, country = ?, city = ?, start_date = ? WHERE id = ?";
    public static final String DELETE_TOUR = "DELETE FROM tour WHERE id = ?";
    public static final String INSERT_INTO_USER = "INSERT INTO \"user\"(name, email, password, role_id, is_blocked) VALUES(?, ?, ?, ?, ?) RETURNING id";
    public static final String GET_FROM_USER = "SELECT id, name, email, password, role_id, is_blocked FROM \"user\" WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE \"user\" SET name = ?, email = ?, password = ?, role_id = ?, is_blocked = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM \"user\" WHERE id = ?";
    public static final String INSERT_INTO_USER_TOURS = "INSERT INTO user_tours(user_id, tour_id, discount_percent, final_price, status_id, order_time) VALUES(?,?,?,?,?,?)";
    public static final String GET_USER_TOURS = "SELECT user_id, tour_id, discount_percent, final_price, status_id, order_time FROM user_tours WHERE user_id = ?";
    public static final String UPDATE_USER_TOURS = "UPDATE user_tours SET user_id = ?, tour_id = ?, discount_percent = ?, final_price = ?, status_id = ?, order_time = ? WHERE user_id = ? AND tour_id = ?";
    public static final String DELETE_USER_TOURS = "DELETE FROM user_tours WHERE user_id = ? AND tour_id = ?";
    public static final String DROP_DISCOUNT = "DROP TABLE discount";
    public static final String DROP_USER = "DROP TABLE \"user\" CASCADE";
    public static final String DROP_TOUR = "DROP TABLE tour CASCADE";
    public static final String DROP_USER_TOURS = "DROP TABLE user_tours CASCADE";
    public static final String CREATE_DISCOUNT_TABLE = "CREATE TABLE \"discount\" (\"id\" serial NOT NULL, \"step\" bigint NOT NULL DEFAULT '0', \"max_percent\" bigint NOT NULL DEFAULT '0', CONSTRAINT \"discount_pk\" PRIMARY KEY (\"id\")) WITH (OIDS=FALSE);";
    public static final String CREATE_USER_TABLE = "CREATE TABLE \"user\" (\"id\" serial NOT NULL, \"name\" varchar(255) NOT NULL, \"email\" varchar(255) NOT NULL UNIQUE,\"password\" varchar(255), \"role_id\" bigint NOT NULL, \"is_blocked\" BOOLEAN NOT NULL DEFAULT 'false', CONSTRAINT \"user_pk\" PRIMARY KEY (\"id\")) WITH ( OIDS=FALSE);";
    public static final String USER_FK = "ALTER TABLE \"user\" ADD CONSTRAINT \"user_fk0\" FOREIGN KEY (\"role_id\") REFERENCES \"role\"(\"id\");";
    public static final String TOUR_FK = "ALTER TABLE \"tour\" ADD CONSTRAINT \"tour_fk0\" FOREIGN KEY (\"type_id\") REFERENCES \"tour_type\"(\"id\");";
    public static final String USER_TOURS_FK0 = "ALTER TABLE \"user_tours\" ADD CONSTRAINT \"user_tours_fk0\" FOREIGN KEY (\"user_id\") REFERENCES \"user\"(\"id\");";
    public static final String USER_TOURS_FK1 = "ALTER TABLE \"user_tours\" ADD CONSTRAINT \"user_tours_fk1\" FOREIGN KEY (\"tour_id\") REFERENCES \"tour\"(\"id\");";
    public static final String USER_TOURS_FK2 = "ALTER TABLE \"user_tours\" ADD CONSTRAINT \"user_tours_fk2\" FOREIGN KEY (\"status_id\") REFERENCES \"tour_status\"(\"id\");";
    public static final String CREATE_TOUR_TABLE = "CREATE TABLE \"tour\" (\"id\" serial NOT NULL, \"title\" varchar(255) NOT NULL, \"type_id\" bigint NOT NULL,  \"person_number\" bigint NOT NULL, \"hotel_stars\" bigint NOT NULL, \"price\" numeric NOT NULL, \"is_hot\" BOOLEAN NOT NULL DEFAULT 'false', \"is_hidden\" BOOLEAN NOT NULL DEFAULT 'false', \"country\" varchar(255) NOT NULL, \"city\" varchar(255) NOT NULL, \"start_date\" DATE, CONSTRAINT \"tour_pk\" PRIMARY KEY (\"id\")) WITH (OIDS=FALSE);";
    public static final String CREATE_USER_TOURS_TABLE = "CREATE TABLE \"user_tours\" (\"user_id\" bigint NOT NULL,\"tour_id\" bigint NOT NULL,\"discount_percent\" bigint NOT NULL,\"final_price\" numeric NOT NULL,\"status_id\" bigint NOT NULL,\"order_time\" TIMESTAMP NOT NULL, CONSTRAINT \"user_tours_pk\" PRIMARY KEY (\"user_id\",\"tour_id\")) WITH (OIDS=FALSE);";
    public static final String GET_TOUR_USER = "SELECT user_id, tour_id, discount_percent, final_price, status_id, order_time FROM user_tours WHERE user_id = ? AND tour_id = ?";

    private SQLStatements() {
    }
}
