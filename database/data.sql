для создания рабочей бд для ментора
INSERT INTO tour_type(id, "type") VALUES (0, REST);
INSERT INTO tour_type(id, "type") VALUES (1, SHOPPING);
INSERT INTO tour_type(id, "type") VALUES (2, EXCURSION);

INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Safari, 1, 12, 4, 2000, true, true, Namibia, Shoshomosho);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Hongo, 2, 22, 5, 2660, false, true, Jumanji, Jumanji);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Shimagaga, 1, 2, 4, 1000, true, false, Madagascar, Juliano);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (MarleyBob, 0, 50, 3, 1100, true, false, Jamaica, "Port Antonio");
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES ("Aloha Oi", 0, 25, 4, 1900, true, true, Hawaii, Gonalulu);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Arabia, 1, 10, 5, 4000, true, false, UAE, "Abu-Dhabi");
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Motadorro, 2, 42, 4, 2000, false, true, Spain, BVarcelona);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES ("Palma-de-Mayorka", 0, 23, 5, 3000, true, true, Spain, Madrid);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES ("Camps Dream", 0, 15, 2, 999, false, true, "New Zealand", Welington);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Jojoms, 1, 33, 5, 2400, false, false, Egipt, Alexandria);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES ("Magic Island", 2, 10, 5, 3340, true, true, "Canari Islands", "Puerto Rico");
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES ("Muay Thai", 2, 50, 3, 1000, true, false, Thailand, Bangkok);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Baunti, 0, 7, 5, 3200, true, true, Bali, "Amet and Lovina");
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Borabora, 1, 30, 4, 1700, false, false, France, Korsica);
INSERT INTO tour(title, type_id, person_number, hotel_stars, price, is_hot, is_hidden, country, city)
VALUES (Muchachos, 0, 34, 3, 1200, true, true, Brasil, Porcos);

INSERT INTO user(name, email, role_id,  password, is_blocked) VALUES (dyushapook, kolosovskiyandr@gmail.com,
                                                                      1, qwerty123, false);

INSERT INTO role(id, role) VALUES (0, ADMIN);
INSERT INTO role(id, role) VALUES (1, USER);
INSERT INTO role(id, role) VALUES (2, MANAGER);

INSERT INTO tour_status(id, status) VALUES (0, REGISTERED);
INSERT INTO tour_status(id, status) VALUES (1, PAID);
INSERT INTO tour_status(id, status) VALUES (2, CANCELED);