# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                        bigint not null,
  name                      varchar(255),
  author                    varchar(255),
  constraint pk_book primary key (id))
;

create table book_list (
  id                        bigint not null,
  start_date                timestamp,
  end_date                  timestamp,
  constraint pk_book_list primary key (id))
;

create table book_list_book (
  id                        bigint not null,
  bookList_id               bigint,
  book_id                   bigint,
  constraint pk_book_list_book primary key (id))
;

create table rating (
  id                        bigint not null,
  user_login                varchar(255),
  book_id                   bigint,
  rating                    integer,
  constraint pk_rating primary key (id))
;

create table user (
  login                     varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (login))
;

create sequence book_seq;

create sequence book_list_seq;

create sequence book_list_book_seq;

create sequence rating_seq;

create sequence user_seq;

alter table book_list_book add constraint fk_book_list_book_bookList_1 foreign key (bookList_id) references book_list (id) on delete restrict on update restrict;
create index ix_book_list_book_bookList_1 on book_list_book (bookList_id);
alter table book_list_book add constraint fk_book_list_book_book_2 foreign key (book_id) references book (id) on delete restrict on update restrict;
create index ix_book_list_book_book_2 on book_list_book (book_id);
alter table rating add constraint fk_rating_user_3 foreign key (user_login) references user (login) on delete restrict on update restrict;
create index ix_rating_user_3 on rating (user_login);
alter table rating add constraint fk_rating_book_4 foreign key (book_id) references book (id) on delete restrict on update restrict;
create index ix_rating_book_4 on rating (book_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book;

drop table if exists book_list;

drop table if exists book_list_book;

drop table if exists rating;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_seq;

drop sequence if exists book_list_seq;

drop sequence if exists book_list_book_seq;

drop sequence if exists rating_seq;

drop sequence if exists user_seq;

