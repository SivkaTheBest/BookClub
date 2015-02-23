# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                        bigint not null,
  name                      varchar(255),
  author                    varchar(255),
  constraint pk_book primary key (id))
;

create table rating (
  id                        bigint not null,
  rating                    integer,
  constraint pk_rating primary key (id))
;

create table user (
  id                        bigint not null,
  login                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence book_seq;

create sequence rating_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book;

drop table if exists rating;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_seq;

drop sequence if exists rating_seq;

drop sequence if exists user_seq;

