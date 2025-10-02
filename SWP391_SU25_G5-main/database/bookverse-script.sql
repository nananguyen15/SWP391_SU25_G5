create table authors
(
    id     bigint auto_increment
        primary key,
    image  varchar(50)  null,
    name   varchar(100) not null,
    bio    text         null,
    active tinyint      not null
);

create table promotions
(
    id            bigint auto_increment
        primary key,
    content       text        not null,
    percentage    int         null,
    type          varchar(50) not null,
    promotion_day date        not null
);

create table publishers
(
    id     bigint auto_increment
        primary key,
    name   varchar(100) not null,
    active tinyint      not null
);

create table series
(
    id          bigint auto_increment
        primary key,
    name        varchar(200) not null,
    description text         null,
    image       varchar(50)  null,
    active      tinyint      not null
);

create table sup_catergories
(
    id     int          not null
        primary key,
    name   varchar(100) null,
    active tinyint(1)   not null
);

create table sub_categories
(
    id          bigint auto_increment
        primary key,
    sup_cat_id  int          null,
    name        varchar(100) not null,
    description text         null,
    discount_id bigint       not null,
    active      tinyint(1)   not null,
    constraint sub_categories_promotions_id_fk
        foreign key (discount_id) references promotions (id),
    constraint sub_categories_sup_catergories_id_fk
        foreign key (sup_cat_id) references sup_catergories (id)
);

create table books
(
    id             bigint auto_increment
        primary key,
    title          varchar(200)  not null,
    description    text          null,
    price          double        not null,
    author_id      bigint        null,
    publisher_id   bigint        null,
    category_id    bigint        null,
    stock_quantity int default 0 null,
    published_date date          null,
    image          varchar(50)   null,
    active         tinyint       null,
    constraint books_author_fk
        foreign key (author_id) references authors (id),
    constraint books_ibfk_1
        foreign key (category_id) references sub_categories (id)
            on delete set null,
    constraint books_publisher_fk
        foreign key (publisher_id) references publishers (id)
);

create index category_id
    on books (category_id);

create table series_books
(
    series_id bigint not null,
    book_id   bigint not null,
    primary key (series_id, book_id),
    constraint series_books_ibfk_1
        foreign key (series_id) references series (id),
    constraint series_books_ibfk_2
        foreign key (book_id) references books (id)
);

create table users
(
    id       varchar(36) charset utf8mb3 not null
        primary key,
    username varchar(50)                 not null,
    password varchar(255)                not null,
    email    varchar(100)                not null,
    fullname varchar(100)                null,
    phone    varchar(20)                 null,
    address  varchar(255)                null,
    image    varchar(50)                 null,
    active   tinyint(1)                  not null comment 'Active flag for the user (soft delete when set tinyint(0))',
    constraint email
        unique (email),
    constraint username
        unique (username)
);

create table carts
(
    id         bigint auto_increment
        primary key,
    user_id    varchar(36) charset utf8mb3         not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    active     tinyint(1)                          not null,
    constraint carts_user_fk
        foreign key (user_id) references users (id)
);

create table cart_items
(
    id         bigint auto_increment
        primary key,
    cart_id    bigint                              not null,
    book_id    bigint                              null,
    quantity   int                                 not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    deleted_at timestamp                           null,
    constraint cart_items_cart_fk
        foreign key (cart_id) references carts (id),
    constraint cart_items_ibfk_2
        foreign key (book_id) references books (id)
);

create index book_id
    on cart_items (book_id);

create table notifications
(
    id         bigint auto_increment
        primary key,
    user_id    varchar(36) charset utf8mb3          not null,
    content    text                                 not null,
    type       varchar(50)                          null,
    is_read    tinyint(1) default 0                 not null,
    created_at timestamp  default CURRENT_TIMESTAMP null,
    constraint notifications_user_fk
        foreign key (user_id) references users (id)
);

create table orders
(
    id           bigint auto_increment
        primary key,
    user_id      varchar(36) charset utf8mb3                                                null,
    status       enum ('PENDING', 'PAID', 'SHIPPED', 'CANCELLED') default 'PENDING'         null,
    total_amount double                                                                     not null,
    address      varchar(50) charset utf8mb3                                                null,
    created_at   timestamp                                        default CURRENT_TIMESTAMP null,
    active       tinyint                                                                    not null,
    constraint orders_ibfk_1
        foreign key (user_id) references users (id)
);

create table order_items
(
    id       bigint auto_increment
        primary key,
    order_id bigint null,
    book_id  bigint null,
    quantity int    not null,
    price    double null,
    constraint order_items_ibfk_1
        foreign key (order_id) references orders (id),
    constraint order_items_ibfk_2
        foreign key (book_id) references books (id)
);

create index book_id
    on order_items (book_id);

create index order_id
    on order_items (order_id);

create table payments
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
    constraint payments_ibfk_1
        foreign key (order_id) references orders (id)
);

create table reviews
(
    id         bigint auto_increment
        primary key,
    user_id    varchar(36) charset utf8mb3         null,
    book_id    bigint                              null,
    rating     int                                 null,
    comment    text                                null,
    created_at timestamp default CURRENT_TIMESTAMP null comment 'thay cho active',
    constraint reviews_ibfk_1
        foreign key (user_id) references users (id),
    constraint reviews_ibfk_2
        foreign key (book_id) references books (id),
    check (`rating` between 1 and 5)
);

create index book_id
    on reviews (book_id);

create table user_roles
(
    user_id varchar(36) charset utf8mb3 not null,
    role    varchar(10)                 not null,
    primary key (user_id, role),
    constraint user_roles_ibfk_1
        foreign key (user_id) references users (id)
);


