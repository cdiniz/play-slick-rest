# --- !Ups

create table "SUPPLIERS" ("ID" BIGSERIAL NOT NULL PRIMARY KEY,"name" VARCHAR(254) NOT NULL,"desc" VARCHAR(254) NOT NULL);

# --- !Downs
;
drop table "SUPPLIERS";