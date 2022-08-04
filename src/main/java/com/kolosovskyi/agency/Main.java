package com.kolosovskyi.agency;
import com.kolosovskyi.agency.dao.DiscountDAO;
import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.*;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
     UserDAO dao = UserDAO.getUserDAO();
     User user = new User("dewcf", "eevr", Role.USER);
     dao.create(user);
        System.out.println(user.getId());
    }
}
