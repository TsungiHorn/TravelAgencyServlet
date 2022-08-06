CREATE TABLE "user" (
    "id" serial NOT NULL,
     "name" varchar(255) NOT NULL,
      "email" varchar(255) NOT NULL UNIQUE,
       "role_id" bigint NOT NULL,
        "is_blocked" BOOLEAN NOT NULL DEFAULT 'false',
         CONSTRAINT "user_pk" PRIMARY KEY ("id")) WITH ( OIDS=FALSE);
CREATE TABLE "tour" (
                        "id" serial NOT NULL,
                        "title" varchar(255) NOT NULL,
                        "type_id" bigint NOT NULL,
                        "person_number" bigint NOT NULL,
                        "hotel_stars" bigint NOT NULL,
                        "price" numeric NOT NULL,
                        "is_hot" BOOLEAN NOT NULL DEFAULT 'false',
                        "is_hidden" BOOLEAN NOT NULL DEFAULT 'false',
                        CONSTRAINT "tour_pk" PRIMARY KEY ("id")
);



CREATE TABLE "tour_type" (
                                    "id" serial NOT NULL,
                                    "type" varchar(255) NOT NULL,
                                    CONSTRAINT "tour_type_pk" PRIMARY KEY ("id")
);



CREATE TABLE "user_tours" (
                                     "user_id" bigint NOT NULL,
                                     "tour_id" bigint NOT NULL,
                                     "discount_percent" bigint NOT NULL,
                                     "final_price" numeric NOT NULL,
                                     "status_id" bigint NOT NULL,
                                     "order_time" TIMESTAMP NOT NULL,
                                     CONSTRAINT "user_tours_pk" PRIMARY KEY ("user_id","tour_id")
);



CREATE TABLE "tour_status" (
                                      "id" serial NOT NULL,
                                      "status" varchar(255) NOT NULL,
                                      CONSTRAINT "tour_status_pk" PRIMARY KEY ("id")
);



CREATE TABLE "discount" (
                                   "id" serial NOT NULL,
                                   "step" bigint NOT NULL DEFAULT '0',
                                   "max_percent" bigint NOT NULL DEFAULT '0',
                                   CONSTRAINT "discount_pk" PRIMARY KEY ("id")
);



ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("id");


ALTER TABLE "tour" ADD CONSTRAINT "tour_fk0" FOREIGN KEY ("type_id") REFERENCES "tour_type"("id");


ALTER TABLE "user_tours" ADD CONSTRAINT "user_tours_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "user_tours" ADD CONSTRAINT "user_tours_fk1" FOREIGN KEY ("tour_id") REFERENCES "tour"("id");
ALTER TABLE "user_tours" ADD CONSTRAINT "user_tours_fk2" FOREIGN KEY ("status_id") REFERENCES "tour_status"("id");