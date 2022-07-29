package com.kolosovskyi.agency.dao;

public class SQLConstance {
    public static final String INSERT_INTO_DISCOUNT = "INSERT INTO discount(step, max_percent) VALUES (?, ?)";
    public static final String GET_FROM_DISCOUNT_BY_ID = "SELECT id, step ,max_percent FROM discount WHERE id = ?";
    public static final String UPDATE_DISCOUNT = "UPDATE discount SET step = ?, max_percent = ? WHERE id = ?";
    public static final String DELETE_DISCOUNT = "DELETE FROM discount WHERE id = ?";
    public static final String INSERT_INTO_TOUR = "INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String GET_INTO_TOUR = "SELECT id, title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city FROM tour WHERE id = ?";
    public static final String UPDATE_TOUR = "UPDATE tour SET title = ?, type_id = ?, person_number = ?, hotel_stars = ?, price = ?, is_hot = ?, is_hidden = ?, country = ?, city = ? WHERE id = ?";
    public static final String DELETE_TOUR = "DELETE FROM tour WHERE id = ?";
    public static final String INSERT_INTO_USER = "INSERT INTO user(name, email, role_id) VALUES(?, ?, ?)";
    public static final String GET_FROM_USER = "SELECT id, name, email, role_id FROM user WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE user SET name = ?, email = ?, role_id = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
}
