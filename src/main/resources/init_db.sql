create table if not exists device_service (
                                id int not null auto_increment,
                                owner_name text not null,
                                status int not null,
                                decision int,
                                primary key (id),
                                unique key (id)
);
