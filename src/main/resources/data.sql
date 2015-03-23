insert into reservation( family_name, date_and_time, group_size) values ('Longs',CURRENT TIMESTAMP,2);
insert into reservation( family_name, date_and_time, group_size) values ('Changs',CURRENT TIMESTAMP,5);
insert into reservation( family_name, date_and_time, group_size) values ('Grays',CURRENT TIMESTAMP,2);

INSERT INTO RATING (name) values ('Private');
INSERT INTO RATING (name) values ('Commercial');
INSERT INTO RATING (name) values ('CFI');

INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('1111', 'jeff', 'byrd', 2, 'byrdje', 'password1');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('2222', 'megan', 'byrd', 1, 'byrdm', 'password2');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('3333', 'juliana', 'byrd', 3, 'byrdju', 'password3');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('4444', 'derek', 'byrd', 2, 'byrdd', 'password4');

INSERT INTO MODEL(NAME) values('Robinson R44');
INSERT INTO MODEL(NAME) values('Bell 47G2A1');
INSERT INTO MODEL(NAME) values('Bell 47G3B1');
INSERT INTO MODEL(NAME) values('Bell 206');

INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N175KM','N121AH', 1, '12106', 'C:/Users/byrdj/Pictures/image.png', '?', false, 1);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N441ML','N710MY', 1, '10465', '', '?', false, 2);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N4344J','N4344J', 1, '12818', 'C:/Users/byrdj/Pictures/image2.png', '?', false, 3);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N86SL','N241AU', 1, '10375', 'C:/Users/byrdj/Pictures/image3.png', '?', false, 4);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('47 G3-B1','N32FG', 3, '3838', '', '?', true, 5);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N118TV','N118TV', 4, '2844', '', 'RR  250-C20B (400SHP)', true, 6);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('47 G2-A1','N8505F', 2, '', '', '?', true, 7);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N586SC','N586SC', 4, '2033', '', 'RR  250-C20B (400SHP)', false, 8);

INSERT INTO LOCATION(NAME) values('BHAA');
INSERT INTO LOCATION(NAME) values('Badlands');
INSERT INTO LOCATION(NAME) values('Rushmore');
INSERT INTO LOCATION(NAME) values('Rushmore/BHAA');
INSERT INTO LOCATION(NAME) values('Other');

INSERT INTO MAINTENANCE_TYPE(NAME) values('Flight Hours');
INSERT INTO MAINTENANCE_TYPE(NAME) values('Months');

INSERT INTO MAINTENANCE(NAME, MAINTENANCE_TYPE_ID) values('50HR', 1);
INSERT INTO MAINTENANCE(NAME, MAINTENANCE_TYPE_ID) values('100HR', 1);