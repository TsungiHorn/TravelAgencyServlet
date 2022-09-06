package com.kolosovskyi.agency.exception.service;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddTourValidator {
    private static final TourDAO tourDAO = TourDAO.getInstance();
    public static Tour checkingTourValidity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Tour tour = new Tour();
        if(request.getParameter("title")!=null && !request.getParameter("title").isBlank()){
            tour.setTitle(request.getParameter("title"));
            if(request.getParameter("person")!=null && !request.getParameter("person").isBlank()){
                tour.setPersonNumber(Long.valueOf(request.getParameter("person")));
                if(request.getParameter("stars")!=null && !request.getParameter("stars").isBlank()){
                    tour.setHotelStars(Integer.valueOf(request.getParameter("stars")));
                    if(request.getParameter("price")!=null && !request.getParameter("price").isBlank()){
                        tour.setPrice(BigDecimal.valueOf(Long.parseLong(request.getParameter("price"))));
                        if(request.getParameter("hot")!=null && !request.getParameter("hot").isBlank()){
                            tour.setHot(Boolean.valueOf(request.getParameter("hot")));
                            if(request.getParameter("hidden")!=null && !request.getParameter("hidden").isBlank()){
                                tour.setHidden(Boolean.valueOf(request.getParameter("hidden")));
                                if(request.getParameter("country")!=null && !request.getParameter("country").isBlank()){
                                    tour.setCountry(request.getParameter("country"));
                                   if(request.getParameter("city")!=null && !request.getParameter("city").isBlank()) {
                                       tour.setCity(request.getParameter("city"));
                                       if(request.getParameter("date")!=null && !request.getParameter("date").isBlank()){
                                           tour.setTourType(TourType.valueOf(request.getParameter("type")));
                                           tour.setStartDate(LocalDate.parse(request.getParameter("date")));
                                           tourDAO.create(tour);
                                           response.sendRedirect("/4admin-catalog");
                                       }else{
                                         response.sendRedirect("/AddTourServlet?fail_date");
                                       }
                                   }else{
                                       response.sendRedirect("/AddTourServlet?fail_city");
                                   }
                                }else{
                                    response.sendRedirect("/AddTourServlet?fail_country");
                                }
                            }else{
                                response.sendRedirect("/AddTourServlet?fail_hidden");
                            }
                        }else{
                            response.sendRedirect("/AddTourServlet?fail_hot");
                        }
                    }else{
                        response.sendRedirect("/AddTourServlet?fail_price");
                    }
                }else{
                    response.sendRedirect("/AddTourServlet?fail_stars");
                }
            }else{
                response.sendRedirect("/AddTourServlet?fail_person");
            }
        }else{
            response.sendRedirect("/AddTourServlet?fail_title");
        }
        return tour;
    }
}
