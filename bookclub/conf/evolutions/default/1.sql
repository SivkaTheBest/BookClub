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

create sequence rating_seq;

create sequence user_seq;

alter table rating add constraint fk_rating_user_1 foreign key (user_login) references user (login) on delete restrict on update restrict;
create index ix_rating_user_1 on rating (user_login);
alter table rating add constraint fk_rating_book_2 foreign key (book_id) references book (id) on delete restrict on update restrict;
create index ix_rating_book_2 on rating (book_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book;

drop table if exists rating;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_seq;

drop sequence if exists rating_seq;

drop sequence if exists user_seq;

