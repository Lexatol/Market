CREATE TABLE USERS(
    id                      bigserial primary key,
    username                varchar(50) not null,
    password                varchar(100) not null,
    email                   varchar (50) unique
);

CREATE TABLE ROLES(
    id                      serial primary key,
    name                    varchar(100) not null
);

CREATE TABLE USERS_ROLES(
    user_id                 bigint not null,
    role_id                int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles(id)
);

CREATE TABLE PROFILES(
    id                      bigserial primary key,
    user_id                 bigint,
    hobbies                 varchar(1000),
    foreign key (user_id) references users(id)
);

CREATE TABLE CATEGORIES(
    id                      bigserial primary key,
    title                   varchar(100)
);

CREATE TABLE products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    category_id             bigint references categories(id),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

INSERT INTO ROLES (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN'), ('MANAGER');

INSERT INTO USERS(username, password, email)
VALUES
('user', '$2a$10$bHEU5neym35Z4dxIh6R.leRUe7pfRnvzC/iuvJWA1UoNUsXAnO3xm', 'user@gmail.com');

INSERT INTO USERS_ROLES (user_id, role_id)
VALUES
(1, 1), (1, 2);

INSERT INTO PROFILES(user_id, hobbies)
VALUES
(1, 'reading book');

INSERT INTO CATEGORIES(title)
VALUES
('Bread'), ('Milk'), ('Cheese');


INSERT INTO products (title, price, category_id)
VALUES
    ('Bread1', 24, 1), ('Milk1', 65, 2), ('Cheese1', 320, 3),
    ('Bread2', 25, 1), ('Milk2', 66, 2), ('Cheese2', 330, 3),
    ('Bread3', 26, 1), ('Milk3', 67, 2), ('Cheese3', 340, 3),
    ('Bread4', 27, 1), ('Milk4', 68, 2), ('Cheese4', 350, 3),
    ('Bread5', 28, 1), ('Milk5', 69, 2), ('Cheese5', 360, 3),
    ('Bread6', 29, 1), ('Milk6', 70, 2), ('Cheese6', 370, 3),
    ('Bread7', 30, 1), ('Milk7', 71, 2), ('Cheese7', 380, 3),
    ('Bread8', 31, 1), ('Milk8', 72, 2), ('Cheese8', 390, 3);

CREATE TABLE orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(500)
);

CREATE TABLE order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price_per_product       int,
    price                   int,
    quantity                int
);


