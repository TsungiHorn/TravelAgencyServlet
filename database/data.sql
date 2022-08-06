INSERT INTO discount(step, max_percent) VALUES (?, ?) RETURNING id
SELECT id, step ,max_percent FROM discount WHERE id = ?
UPDATE discount SET step = ?, max_percent = ? WHERE id = ?
DELETE FROM discount WHERE id = ?
INSERT INTO tour(title, type_id, person_number,
                 hotel_stars, price, is_hot, is_hidden,
                 country, city) VALUES(?,?,?,?,?,?,?,?,?) RETURNING id
SELECT id, title, type_id, person_number, hotel_stars, price,
       is_hot, is_hidden, country, city FROM tour WHERE id = ?
UPDATE tour SET title = ?, type_id = ?, person_number = ?, hotel_stars = ?,
 price = ?, is_hot = ?, is_hidden = ?, country = ?, city = ? WHERE id = ?

DELETE FROM tour WHERE id = ?

INSERT INTO "user"(name, email, role_id) VALUES(?, ?, ?) RETURNING id
SELECT id, name, email, role_id FROM "user" WHERE id = ?
UPDATE "user" SET name = ?, email = ?, role_id = ? WHERE id = ?
DELETE FROM "user" WHERE id = ?
INSERT INTO user_tours(user_id, tour_id, discount_percent,
                       final_price, status_id, order_time) VALUES(?,?,?,?,?,?)
SELECT user_id, tour_id, discount_percent, final_price, status_id,
       order_time FROM user_tours WHERE user_id = ?
UPDATE user_tours SET user_id = ?, tour_id = ?, discount_percent = ?,
                      final_price = ?, status_id = ?, order_time = ? WHERE user_id = ?
DELETE FROM user_tours WHERE user_id = ?, tour_id = ?
DROP TABLE discount
DROP TABLE "user" CASCADE
DROP TABLE tour CASCADE
DROP TABLE user_tours CASCADE
CREATE TABLE "discount" ("id" serial NOT NULL, "step" bigint NOT NULL DEFAULT '0',
 "max_percent" bigint NOT NULL DEFAULT '0', CONSTRAINT "discount_pk" PRIMARY KEY ("id")) WITH (OIDS=FALSE)
CREATE TABLE "user" ("id" serial NOT NULL, "name" varchar(255) NOT NULL,
"email" varchar(255) NOT NULL UNIQUE, "role_id" bigint NOT NULL,
 "is_blocked" BOOLEAN NOT NULL DEFAULT 'false', CONSTRAINT "user_pk" PRIMARY KEY ("id")) WITH ( OIDS=FALSE);
ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("id");
ALTER TABLE "tour" ADD CONSTRAINT "tour_fk0" FOREIGN KEY ("type_id") REFERENCES "tour_type"("id")
ALTER TABLE "user_tours" ADD CONSTRAINT "user_tours_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id")
ALTER TABLE "user_tours" ADD CONSTRAINT "user_tours_fk1" FOREIGN KEY ("tour_id") REFERENCES "tour"("id")
ALTER TABLE "user_tours" ADD CONSTRAINT "user_tours_fk2" FOREIGN KEY ("status_id") REFERENCES "tour_status"("id");
CREATE TABLE "tour" ("id" serial NOT NULL,
    "title" varchar(255) NOT NULL, "type_id" bigint NOT NULL,  "person_number" bigint NOT NULL,
     "hotel_stars" bigint NOT NULL, "price" numeric NOT NULL, "is_hot" BOOLEAN NOT NULL DEFAULT 'false',
      "is_hidden" BOOLEAN NOT NULL DEFAULT 'false', "country" varchar(255) NOT NULL, "city" varchar(255) NOT NULL,
       CONSTRAINT "tour_pk" PRIMARY KEY ("id")) WITH (OIDS=FALSE)
CREATE TABLE "user_tours" ("user_id" bigint NOT NULL,"tour_id" bigint NOT NULL,"discount_percent" bigint NOT NULL,
"final_price" numeric NOT NULL,"status_id" bigint NOT NULL,"order_time" TIMESTAMP NOT NULL,
 CONSTRAINT "user_tours_pk" PRIMARY KEY ("user_id","tour_id")) WITH (OIDS=FALSE)
