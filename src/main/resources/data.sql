delete from MAINTENANCE_TYPE;
delete from LOCATION;
delete from AIRCRAFT;
delete from MODEL;
delete from APP_USER;
delete from RATING;
delete from OPERATION;
delete from FLIGHT_LOG;
delete from MAINTENANCE_LOG;


--drop table flight_log;
--drop table aircraft;
--drop table maintenance_type;
--drop table model;

--ALTER TABLE MAINTENANCE_TYPE ADD COLUMN SHOW_ON_DASHBOARD BOOLEAN;
--ALTER TABLE MAINTENANCE_LOG DROP COLUMN COMPLY_WITH_DATE;
--ALTER TABLE MAINTENANCE_LOG ADD COLUMN COMPLY_WITH_DATE DATE;
--ALTER TABLE AIRCRAFT ADD COLUMN hobbs DECIMAL;
--ALTER TABLE AIRCRAFT ADD COLUMN hobbs_offset DECIMAL;
--ALTER TABLE AIRCRAFT ADD COLUMN engine_total_time DECIMAL;
--ALTER TABLE AIRCRAFT ADD COLUMN engine_total_time_offset DECIMAL;
--ALTER TABLE AIRCRAFT DROP COLUMN SHOW_STARTS;

INSERT INTO RATING (name) values ('Private');
INSERT INTO RATING (name) values ('Commercial');
INSERT INTO RATING (name) values ('CFI');

INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('1111', 'jeff', 'byrd', (select id from RATING where name='Commercial'), 'byrdje', 'password1', 'ADMIN');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('2222', 'megan', 'byrd', (select id from RATING where name='Private'), 'byrdm', 'password2', 'PILOT');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('3333', 'juliana', 'byrd', (select id from RATING where name='Commercial'), 'byrdju', 'password3', 'PILOT');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('4444', 'derek', 'byrd', (select id from RATING where name='CFI'), 'byrdd', 'password4', 'MECHANIC');

INSERT INTO MODEL(NAME, SHOW_STARTS) values('Robinson R44', true);
INSERT INTO MODEL(NAME, SHOW_STARTS) values('Bell 47G2A1', false);
INSERT INTO MODEL(NAME, SHOW_STARTS) values('Bell 47G3B1', false);
INSERT INTO MODEL(NAME, SHOW_STARTS) values('Bell 206', true);

INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('N175KM','N121AH', (select id from MODEL where name='Robinson R44'), '12106', 'N175KM.png', 1, 99.0, 0, 99.0, 0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('N441ML','N710MY', (select id from MODEL where name='Robinson R44'), '10465', 'N441ML.png', 2, 15.0, 76, 124, 130);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('N4344J','N4344J', (select id from MODEL where name='Robinson R44'), '12818', 'N4344J.png', 3, 15.0, 76, 124, 130);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('N86SL','N241AU', (select id from MODEL where name='Robinson R44'), '10375', 'N86SL.png', 4, 15.0, 76, 124, 130);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('47 G3-B1','N32FG', (select id from MODEL where name='Bell 47G3B1'), '3838', 'N32FG.png', 5, 15.0, 76, 124, 130);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('N118TV','N118TV', (select id from MODEL where name='Bell 206'), '2844', 'N118TV.png', 6, 15.0, 76, 124, 130);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('47 G2-A1','N8505F', (select id from MODEL where name='Bell 47G2A1'), '', 'N8505F.png', 7, 15.0, 76, 124, 130);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs, hobbs_offset, engine_total_time, engine_total_time_offset) values('N586SC','N586SC', (select id from MODEL where name='Bell 206'), '2033', 'N586SC.png', 8, 15.0, 76, 124, 130);

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

INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION, SHOW_ON_DASHBOARD) values('50HR', 'FLIGHT_HOURS', (select id from MODEL where name='Robinson R44'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL', true);
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION, SHOW_ON_DASHBOARD) values('100HR', 'FLIGHT_HOURS', (select id from MODEL where name='Robinson R44'), 100, 'manual-222', 'pn-222', 'sn-222', 'OVERHAUL', true);
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION, SHOW_ON_DASHBOARD) values('Overhaul Lycoming Engine.', 'MONTHS', (select id from MODEL where name='Robinson R44'), 144, 'manual-456', 'pn-456', 'sn-456', 'OVERHAUL', true);
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION, SHOW_ON_DASHBOARD) values('Replace hydraulic filter per Section 1.170.', 'FLIGHT_HOURS', (select id from MODEL where name='Robinson R44'), 300, 'manual-001', 'pn-001', 'sn-001', 'RETIRE', false);
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION, SHOW_ON_DASHBOARD) values('50HR', 'FLIGHT_HOURS', (select id from MODEL where name='Bell 47G2A1'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL', true);
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION, SHOW_ON_DASHBOARD) values('50HR', 'FLIGHT_HOURS', (select id from MODEL where name='Bell 47G3B1'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL', true);
INSERT INTO MAINTENANCE_TYPE(NAME, MAINTENANCE_CATEGORY, MODEL_ID, TIME_BEFORE_ACTION, MANUAL_NUMBER, PART_NUMBER, SERIAL_NUMBER, ACTION, SHOW_ON_DASHBOARD) values('50HR', 'FLIGHT_HOURS', (select id from MODEL where name='Bell 206'), 50, 'manual-123', 'pn-123', 'sn-123', 'OVERHAUL', true);

INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='byrdje'), TIMESTAMP('2015-02-02 00:00:00'), 2, (select id from LOCATION where name='BHAA'), 127.4, 131.2, (select id from OPERATION where name='Tours'));
INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='byrdm'), TIMESTAMP('2015-04-11 00:00:00'), 0, (select id from LOCATION where name='Badlands'), 125.0, 127.4, (select id from OPERATION where name='Power line Patrol'));
INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='byrdje'), TIMESTAMP('2015-04-12 00:00:00'), 0, (select id from LOCATION where name='Other'), 133.2, 137.8, (select id from OPERATION where name='Video/Movie'));

INSERT INTO MAINTENANCE_LOG(AIRCRAFT_ID, MAINTENANCE_TYPE_ID, COMPLY_WITH_HOBBS, COMPLY_WITH_DATE) values ((select id from AIRCRAFT where name='N121AH'), (select id from MAINTENANCE_TYPE where name='Replace hydraulic filter per Section 1.170.' and MODEL_ID=(select id from MODEL where name='Robinson R44')), 601.5, null);
INSERT INTO MAINTENANCE_LOG(AIRCRAFT_ID, MAINTENANCE_TYPE_ID, COMPLY_WITH_HOBBS, COMPLY_WITH_DATE) values ((select id from AIRCRAFT where name='N121AH'), (select id from MAINTENANCE_TYPE where name='Overhaul Lycoming Engine.' and MODEL_ID=(select id from MODEL where name='Robinson R44')), null, TIMESTAMP('2015-02-02 00:00:00'));
INSERT INTO MAINTENANCE_LOG(AIRCRAFT_ID, MAINTENANCE_TYPE_ID, COMPLY_WITH_HOBBS, COMPLY_WITH_DATE) values ((select id from AIRCRAFT where name='N121AH'), (select id from MAINTENANCE_TYPE where name='50HR' and MODEL_ID=(select id from MODEL where name='Robinson R44')), 250.0, null);