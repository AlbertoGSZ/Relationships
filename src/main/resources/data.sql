insert into page_user(id, name, password) values(1, 'Cristian', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into page_user(id, name, password) values(2, 'Alberto', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');

insert into authority(id, name) values(1, 'ROLE_ADMIN');
insert into authority(id, name) values(2, 'ROLE_USER');

insert into page_user_authorities(page_users_id, authorities_id) values(1, 1);
insert into page_user_authorities(page_users_id, authorities_id) values(2, 2);

insert into person(age, name, nationality, surname, father_id) values(5, 'Juan', 'España', 'Gonzalez', NULL);
insert into person(age, name, nationality, surname, father_id) values(45, 'Pedro', 'España', 'Gonzalez', 1);
insert into person(age, name, nationality, surname, father_id) values(55, 'Juan', 'España', 'Gonzalez', 2);
insert into person(age, name, nationality, surname, father_id) values(65, 'Luis', 'España', 'Gonzalez', 3);
insert into person(age, name, nationality, surname, father_id) values(75, 'Mariano', 'España', 'Gonzalez', 4);
insert into person(age, name, nationality, surname, father_id) values(28, 'Alberto', 'España', 'García', NULL);
insert into person(age, name, nationality, surname, father_id) values(9, 'Gerard', 'España', 'García', 6);
insert into person(age, name, nationality, surname, father_id) values(30, 'Alan', 'España', 'Gutierrez', NULL);
insert into person(age, name, nationality, surname, father_id) values(48, 'Javián', 'España', 'Gutierrez', 8);
insert into person(age, name, nationality, surname, father_id) values(33, 'Oliver', 'España', 'Gutierrez', 9);
insert into person(age, name, nationality, surname, father_id) values(13, 'Michel', 'España', 'Gutierrez', 10);

-- insert into person_children(person_id, children_id) values(1, 2);
-- insert into person_children(person_id, children_id) values(2, 3);
-- insert into person_children(person_id, children_id) values(3, 4);
-- insert into person_children(person_id, children_id) values(4, 5);
-- insert into person_children(person_id, children_id) values(6, 7);
-- insert into person_children(person_id, children_id) values(8, 9);
-- insert into person_children(person_id, children_id) values(9, 10);
-- insert into person_children(person_id, children_id) values(10, 11);

