create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into products (title, price)
values
    ('Bread', 24), ('Milk', 65), ('Cheese', 320),
    ('Bread', 25), ('Milk', 66), ('Cheese', 330),
    ('Bread', 26), ('Milk', 67), ('Cheese', 340),
    ('Bread', 27), ('Milk', 68), ('Cheese', 350),
    ('Bread', 28), ('Milk', 69), ('Cheese', 360),
    ('Bread', 29), ('Milk', 70), ('Cheese', 370),
    ('Bread', 30), ('Milk', 71), ('Cheese', 380),
    ('Bread', 31), ('Milk', 72), ('Cheese', 390);

create table carts (
    id                      bigserial primary key,
    users                   varchar(50),
    product_id              bigint,
    created_at              timestamp default current_timestamp,
    foreign key (product_id) references products(id)
);

create table order_items(
    id                      bigserial primary key,
    title                   varchar(255),
    quantity                int,
    price_per_item          int,
    price                   int
)
