create table author
(
    id     bigint auto_increment
        primary key,
    image  varchar(255) null,
    name   varchar(255) not null,
    bio    longtext     null,
    active bit          not null
);

create table promotion
(
    id            bigint auto_increment
        primary key,
    content       tinytext     not null,
    percentage    int          null,
    promotion_day date         not null,
    type          varchar(255) not null
);

create table publisher
(
    id      bigint auto_increment
        primary key,
    name    varchar(255) not null,
    active  bit          not null,
    address varchar(255) null,
    image   varchar(255) null
);

create table series
(
    id          bigint auto_increment
        primary key,
    name        varchar(255) not null,
    description longtext     null,
    image       varchar(255) null,
    active      bit          not null,
    author      varchar(255) null
);

create table sup_category
(
    id     int          not null
        primary key,
    name   varchar(255) null,
    active tinyint(1)   not null
);

create table sub_category
(
    id          bigint auto_increment
        primary key,
    sup_cat_id  int          null,
    name        varchar(255) not null,
    description longtext     null,
    discount_id bigint       not null,
    active      tinyint(1)   not null,
    constraint FK8wpb5yj29ehdvislyp5vugp4v
        foreign key (sup_cat_id) references sup_category (id)
);

create table book
(
    id             bigint auto_increment
        primary key,
    title          varchar(255)  not null,
    description    longtext      null,
    price          double        not null,
    author_id      bigint        null,
    publisher_id   bigint        null,
    category_id    bigint        null,
    stock_quantity int default 0 null,
    published_date date          null,
    image          varchar(255)  null,
    active         bit           null,
    constraint book_ibfk_1
        foreign key (category_id) references sub_category (id)
            on delete set null,
    constraint books_author_fk
        foreign key (author_id) references author (id),
    constraint books_publisher_fk
        foreign key (publisher_id) references publisher (id)
);

create index category_id
    on book (category_id);

create table series_book
(
    series_id bigint not null,
    book_id   bigint not null,
    primary key (series_id, book_id),
    constraint series_book_ibfk_1
        foreign key (series_id) references series (id),
    constraint series_book_ibfk_2
        foreign key (book_id) references book (id)
);

create index series_books_ibfk_2
    on series_book (book_id);

create index sub_categories_promotions_id_fk
    on sub_category (discount_id);

create table user
(
    id       varchar(36) charset utf8mb3 not null
        primary key,
    username varchar(255)                null,
    password varchar(255)                not null,
    email    varchar(255)                null,
    fullname varchar(100)                null,
    phone    varchar(255)                null,
    address  varchar(255)                null,
    image    varchar(255)                null,
    active   tinyint(1)                  not null comment 'Active flag for the user (soft delete when set tinyint(0))',
    name     varchar(255)                null,
    constraint email
        unique (email),
    constraint username
        unique (username)
);

create table cart
(
    id         bigint auto_increment
        primary key,
    user_id    varchar(36) charset utf8mb3         not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    active     tinyint(1)                          not null,
    constraint carts_user_fk
        foreign key (user_id) references user (id)
);

create table cart_item
(
    id         bigint auto_increment
        primary key,
    cart_id    bigint                              not null,
    book_id    bigint                              null,
    quantity   int                                 not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    deleted_at timestamp                           null,
    constraint cart_item_ibfk_2
        foreign key (book_id) references book (id),
    constraint cart_items_cart_fk
        foreign key (cart_id) references cart (id)
);

create index book_id
    on cart_item (book_id);

create table notification
(
    id         bigint auto_increment
        primary key,
    user_id    varchar(36) charset utf8mb3          not null,
    content    text                                 not null,
    type       varchar(50)                          null,
    is_read    tinyint(1) default 0                 not null,
    created_at timestamp  default CURRENT_TIMESTAMP null,
    constraint notifications_user_fk
        foreign key (user_id) references user (id)
);

create table `order`
(
    id           bigint auto_increment
        primary key,
    user_id      varchar(36) charset utf8mb3                                                null,
    status       enum ('PENDING', 'PAID', 'SHIPPED', 'CANCELLED') default 'PENDING'         null,
    total_amount double                                                                     not null,
    address      varchar(50) charset utf8mb3                                                null,
    created_at   timestamp                                        default CURRENT_TIMESTAMP null,
    active       tinyint                                                                    not null,
    constraint order_ibfk_1
        foreign key (user_id) references user (id)
);

create index orders_ibfk_1
    on `order` (user_id);

create table order_item
(
    id       bigint auto_increment
        primary key,
    order_id bigint null,
    book_id  bigint null,
    quantity int    not null,
    price    double null,
    constraint order_item_ibfk_1
        foreign key (order_id) references `order` (id),
    constraint order_item_ibfk_2
        foreign key (book_id) references book (id)
);

create index book_id
    on order_item (book_id);

create index order_id
    on order_item (order_id);

create table payment
(
    id         bigint auto_increment
        primary key,
    order_id   bigint                                                          null,
    method     enum ('COD', 'MOMO', 'VNPAY', 'BANK_TRANSFER')                  null,
    status     enum ('PENDING', 'SUCCESS', 'FAILED') default 'PENDING'         null,
    paid_at    timestamp                                                       null,
    created_at timestamp                             default CURRENT_TIMESTAMP null,
    deleted_at timestamp                                                       null,
    constraint order_id
        unique (order_id),
    constraint payment_ibfk_1
        foreign key (order_id) references `order` (id)
);

create table review
(
    id         bigint auto_increment
        primary key,
    user_id    varchar(36) charset utf8mb3         null,
    book_id    bigint                              null,
    rating     int                                 null,
    comment    text                                null,
    created_at timestamp default CURRENT_TIMESTAMP null comment 'thay cho active',
    constraint review_ibfk_1
        foreign key (user_id) references user (id),
    constraint review_ibfk_2
        foreign key (book_id) references book (id),
    check (`rating` between 1 and 5)
);

create index book_id
    on review (book_id);

create index reviews_ibfk_1
    on review (user_id);

create table user_role
(
    user_id varchar(36) charset utf8mb3 not null,
    role    varchar(255)                not null,
    primary key (user_id, role),
    constraint user_role_ibfk_1
        foreign key (user_id) references user (id)
);


