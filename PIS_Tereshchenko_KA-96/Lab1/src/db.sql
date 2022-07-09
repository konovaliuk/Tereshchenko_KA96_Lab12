CREATE TYPE orderStatus AS ENUM ('onboard', 'paid', 'onway', 'delivered', 'trouble');
CREATE TYPE productStatus AS ENUM ('onstock', 'na', 'suspended');

CREATE TABLE IF NOT EXISTS users (
            userId serial primary key,
            login varchar,
            passHash varchar,
            registeredTime timestamp,
);

create table if not exists user_order{
            orderId serial primary key,
            userId int,
            FOREIGN KEY(userId)
                REFERENCES users(userId)
                ON DELETE CASCADE,
            deliveryInfo varchar,
            status orderStatus,
            createdTime timestamp
};

create table if not exists product{
            productId serial primary key,
            model varchar,
            stock int,
            price int,
            status productStatus
}

create table if not exists order_item{
            itemId serial primary key,
            orderId int,
            prodoctId int,
            FOREIGN KEY(orderId)
                REFERENCES user_order(orderId)
                ON DELETE CASCADE,
                        FOREIGN KEY(prodoctId)
                REFERENCES product(prodoctId)
                ON DELETE CASCADE,
            price int,
            amount int,
            aux VARCHAR,
};