CREATE TABLE `cart` (
  `customer_id` varchar(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
);

create table `user`(
id varchar(11) primary key not null,
name varchar(50) not null,
password varchar(255) not null,
admin tinyint(1) not null DEFAULT 0
);

create table `item`(
item_id int(11) not null primary key AUTO_INCREMENT,
item_name varchar(50) not null,
item_price int(11) not null,
stock int(11) not null,
category varchar(50) not null,
image_url varchar(255) null DEFAULT NULL,
image blob null DEFAULT NULL
);

create table `orders`(
order_id int(11) not null primary key AUTO_INCREMENT,
customer_id varchar(11) not null,
paid tinyint(1) not null DEFAULT 0
);

create table `print`(
    document_id int(11) not null primary key AUTO_INCREMENT,
    document blob not null,
    order_id int(11) not null
);

create table `order_item`(
order_id int(11) not null,
item_id int(11) not null,
quantity int(11) not null
);


alter table cart
add primary key(customer_id, item_id),
add foreign key(customer_id) references user(id),
add foreign key(item_id) references item(item_id);



alter table orders
add foreign key(customer_id) references user(id);

alter table order_item
add foreign key(order_id) references orders(order_id),
add foreign key(item_id) references item(item_id);

alter table print
add foreign key(order_id) references orders(order_id);


insert into user(id, name, password, admin) values
('admin', 'admin', 'admin', 1);

insert into item(item_name, item_price, stock, category) values
('Lord of the Flies', 100, 10, 'Book'),
('Wuthering Heights', 120,10,'Book'),
('Matador Pen', 10,100,'Stationery'),
('Sharpener',20,100,'Stationery'),
('Pencil',5,100,'Stationery'),
('Eraser',10,100,'Stationery'),
('Ruler',10,100,'Stationery'),
('A4 Paper',10,100,'Stationery'),
('A3 Paper',20,100,'Stationery'),
('A2 Paper',30,100,'Stationery'),
('A1 Paper',40,100,'Stationery'),
('A0 Paper',50,100,'Stationery'),
('Pygmalion',200,5,'Book'),
('Calculator',1000,10,'Electronics');