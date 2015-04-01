delete from MAINTENANCE;
delete from MAINTENANCE_TYPE;
delete from MAINTENANCE_CATEGORY;
delete from ACTION;
delete from LOCATION;
delete from AIRCRAFT;
delete from MODEL;
delete from APP_USER;
delete from RATING;

INSERT INTO RATING (name) values ('Private');
INSERT INTO RATING (name) values ('Commercial');
INSERT INTO RATING (name) values ('CFI');

INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('1111', 'jeff', 'byrd', (select id from RATING where name='Commercial'), 'byrdje', 'password1');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('2222', 'megan', 'byrd', (select id from RATING where name='Private'), 'byrdm', 'password2');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('3333', 'juliana', 'byrd', (select id from RATING where name='Commercial'), 'byrdju', 'password3');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password) values('4444', 'derek', 'byrd', (select id from RATING where name='CFI'), 'byrdd', 'password4');

INSERT INTO MODEL(NAME) values('Robinson R44');
INSERT INTO MODEL(NAME) values('Bell 47G2A1');
INSERT INTO MODEL(NAME) values('Bell 47G3B1');
INSERT INTO MODEL(NAME) values('Bell 206');

INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N175KM','N121AH', (select id from MODEL where name='Robinson R44'), '12106', 'C:/Users/byrdj/Pictures/image.png', '?', false, 1);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N441ML','N710MY', (select id from MODEL where name='Robinson R44'), '10465', '', '?', false, 2);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N4344J','N4344J', (select id from MODEL where name='Robinson R44'), '12818', 'C:/Users/byrdj/Pictures/image2.png', '?', false, 3);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N86SL','N241AU', (select id from MODEL where name='Robinson R44'), '10375', 'C:/Users/byrdj/Pictures/image3.png', '?', false, 4);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('47 G3-B1','N32FG', (select id from MODEL where name='Bell 47G3B1'), '3838', '', '?', true, 5);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N118TV','N118TV', (select id from MODEL where name='Bell 206'), '2844', '', 'RR  250-C20B (400SHP)', true, 6);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('47 G2-A1','N8505F', (select id from MODEL where name='Bell 47G2A1'), '', '', '?', true, 7);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, engine, show_starts, ordering) values('N586SC','N586SC', (select id from MODEL where name='Bell 206'), '2033', '', 'RR  250-C20B (400SHP)', false, 8);

INSERT INTO LOCATION(NAME) values('BHAA');
INSERT INTO LOCATION(NAME) values('Badlands');
INSERT INTO LOCATION(NAME) values('Rushmore');
INSERT INTO LOCATION(NAME) values('Rushmore/BHAA');
INSERT INTO LOCATION(NAME) values('Other');

INSERT INTO OPERATION(NAME) values('Tours');
INSERT INTO OPERATION(NAME) values('No Flight');
INSERT INTO OPERATION(NAME) values('Ferry');
INSERT INTO OPERATION(NAME) values('Maintenance');
INSERT INTO OPERATION(NAME) values('135');
INSERT INTO OPERATION(NAME) values('Pipe line Patrol');
INSERT INTO OPERATION(NAME) values('Power line Patrol');
INSERT INTO OPERATION(NAME) values('Spec'' Ops');
INSERT INTO OPERATION(NAME) values('Tour + MX');
INSERT INTO OPERATION(NAME) values('Aerial Photo');
INSERT INTO OPERATION(NAME) values('Video/Movie');
INSERT INTO OPERATION(NAME) values('Tour + Ferry');
INSERT INTO OPERATION(NAME) values('Training');

--INSERT INTO ACTION(NAME) values ('Overhaul');
--INSERT INTO ACTION(NAME) values ('Retire');

INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION) values('50HR', 'MONTHS', (select id from MODEL where name='Robinson R44'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL');
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION) values('100HR', 'MONTHS', (select id from MODEL where name='Robinson R44'), 100, 'manual-456', 'pn-456', 'sn-456', 'OVERHAUL');
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION) values('Replace hydraulic filter per Section 1.170.', 'FLIGHT_HOURS', (select id from MODEL where name='Robinson R44'), 300, 'manual-001', 'pn-001', 'sn-001', 'RETIRE');
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION) values('50HR', 'FLIGHT_HOURS', (select id from MODEL where name='Bell 47G2A1'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL');
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION) values('50HR', 'FLIGHT_HOURS', (select id from MODEL where name='Bell 47G3B1'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL');
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION) values('50HR', 'FLIGHT_HOURS', (select id from MODEL where name='Bell 206'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL');

INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='byrdje'), current_timestamp, 2, (select id from LOCATION where name='BHAA'), 127.4, 131.2, (select id from OPERATION where name='Tours'));
INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='byrdm'), current_timestamp, 0, (select id from LOCATION where name='Badlands'), 125.0, 127.4, (select id from OPERATION where name='Power line Patrol'));
INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='byrdje'), current_timestamp, 0, (select id from LOCATION where name='Other'), 133.2, 137.8, (select id from OPERATION where name='Video/Movie'));