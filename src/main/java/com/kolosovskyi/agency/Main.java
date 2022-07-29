package com.kolosovskyi.agency;

import com.kolosovskyi.agency.dao.DiscountDAO;
import com.kolosovskyi.agency.entity.Discount;

public class Main {
    public static void main(String[] args) {
        DiscountDAO discount = DiscountDAO.getDiscountDAO();
        discount.create(new Discount(17, 17));
    }
}
