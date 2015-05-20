delete from AD_COMPLIANCE_LOG;
delete from AD_COMPLIANCE;
delete from MAINTENANCE_TYPE;
delete from LOCATION;
delete from AIRCRAFT;
delete from MODEL;
delete from APP_USER;
delete from RATING;
delete from OPERATION;
delete from FLIGHT_LOG;
delete from MAINTENANCE_LOG;
delete from REPAIR_LOG;

--drop table flight_log;
--drop table aircraft;
--drop table maintenance_type;
--drop table model;

--ALTER TABLE AD_COMPLIANCE_LOG ADD COLUMN AIRCRAFT_ID BIGINT;
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

INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('1111', 'John', 'Doe', (select id from RATING where name='Commercial'), 'doejohn', 'password1', 'ADMIN');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('2222', 'Jack', 'Smith', (select id from RATING where name='Private'), 'smithjack', 'password2', 'PILOT');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('3333', 'Sally', 'Johnson', (select id from RATING where name='Commercial'), 'johnsonsally', 'password3', 'PILOT');
INSERT INTO APP_USER (user_id, first_name, last_name, rating_id, username, password, role) values('4444', 'Paul', 'Adams', (select id from RATING where name='CFI'), 'adamspaul', 'password4', 'MECHANIC');

INSERT INTO MODEL(NAME, SHOW_STARTS) values('Robinson R44', true);
INSERT INTO MODEL(NAME, SHOW_STARTS) values('Bell 47G2A1', false);
INSERT INTO MODEL(NAME, SHOW_STARTS) values('Bell 47G3B1', false);
INSERT INTO MODEL(NAME, SHOW_STARTS) values('Bell 206', true);

INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('N175KM','N121AH', (select id from MODEL where name='Robinson R44'), '12106', 'N175KM.png', 1, 99.0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('N441ML','N710MY', (select id from MODEL where name='Robinson R44'), '10465', 'N441ML.png', 2, 265.0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('N4344J','N4344J', (select id from MODEL where name='Robinson R44'), '12818', 'N4344J.png', 3, 34.0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('N86SL','N241AU', (select id from MODEL where name='Robinson R44'), '10375', 'N86SL.png', 4, 42.0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('47 G3-B1','N32FG', (select id from MODEL where name='Bell 47G3B1'), '3838', 'N32FG.png', 5, 897.0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('N118TV','N118TV', (select id from MODEL where name='Bell 206'), '2844', 'N118TV.png', 6, 543.0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('47 G2-A1','N8505F', (select id from MODEL where name='Bell 47G2A1'), '', 'N8505F.png', 7, 29.0);
INSERT INTO AIRCRAFT (aircraft_number, name, model_id, serial_num, image_path, ordering, hobbs) values('N586SC','N586SC', (select id from MODEL where name='Bell 206'), '2033', 'N586SC.png', 8, 18.0);

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

INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='doejohn'), TIMESTAMP('2015-02-02 00:00:00'), 2, (select id from LOCATION where name='BHAA'), 127.4, 131.2, (select id from OPERATION where name='Tours'));
INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='smithjack'), TIMESTAMP('2015-04-11 00:00:00'), 0, (select id from LOCATION where name='Badlands'), 125.0, 127.4, (select id from OPERATION where name='Power line Patrol'));
INSERT INTO FLIGHT_LOG (AIRCRAFT_ID, USER_ID, DATE, STARTS, LOCATION_ID, HOBBS_BEGIN, HOBBS_END, OPERATION_ID) values((select id from AIRCRAFT where name='N121AH'), (select id from APP_USER where username='doejohn'), TIMESTAMP('2015-04-12 00:00:00'), 0, (select id from LOCATION where name='Other'), 133.2, 137.8, (select id from OPERATION where name='Video/Movie'));

INSERT INTO MAINTENANCE_LOG(AIRCRAFT_ID, MAINTENANCE_TYPE_ID, COMPLY_WITH_HOBBS, COMPLY_WITH_DATE) values ((select id from AIRCRAFT where name='N121AH'), (select id from MAINTENANCE_TYPE where name='Replace hydraulic filter per Section 1.170.' and MODEL_ID=(select id from MODEL where name='Robinson R44')), 601.5, null);
INSERT INTO MAINTENANCE_LOG(AIRCRAFT_ID, MAINTENANCE_TYPE_ID, COMPLY_WITH_HOBBS, COMPLY_WITH_DATE) values ((select id from AIRCRAFT where name='N121AH'), (select id from MAINTENANCE_TYPE where name='Overhaul Lycoming Engine.' and MODEL_ID=(select id from MODEL where name='Robinson R44')), null, TIMESTAMP('2015-02-02 00:00:00'));
INSERT INTO MAINTENANCE_LOG(AIRCRAFT_ID, MAINTENANCE_TYPE_ID, COMPLY_WITH_HOBBS, COMPLY_WITH_DATE) values ((select id from AIRCRAFT where name='N121AH'), (select id from MAINTENANCE_TYPE where name='50HR' and MODEL_ID=(select id from MODEL where name='Robinson R44')), 250.0, null);

INSERT INTO REPAIR_LOG(REPORT_DATE, PILOT_ID, AIRCRAFT_ID, ISSUE_NOTES, REPAIR_DATE, MECHANIC_ID, REPAIR_NOTES) values (TIMESTAMP('2015-04-27 00:00:00'), (select ID from APP_USER where username='johnsonsally'), (select id from AIRCRAFT where name='N121AH'), 'Passenger tail light is out', TIMESTAMP('2015-02-02 00:00:00'), (select ID from APP_USER where username='adamspaul'), 'Replace passenger tail light');
INSERT INTO REPAIR_LOG(REPORT_DATE, PILOT_ID, AIRCRAFT_ID, ISSUE_NOTES, REPAIR_DATE, MECHANIC_ID, REPAIR_NOTES) values (TIMESTAMP('2015-04-05 00:00:00'), (select ID from APP_USER where username='doejohn'), (select id from AIRCRAFT where name='N4344J'), 'Dinging noise', null, null, null);
INSERT INTO REPAIR_LOG(REPORT_DATE, PILOT_ID, AIRCRAFT_ID, ISSUE_NOTES, REPAIR_DATE, MECHANIC_ID, REPAIR_NOTES) values (TIMESTAMP('2015-04-08 00:00:00'), (select ID from APP_USER where username='smithjack'), (select id from AIRCRAFT where name='N4344J'), 'Funny smell', null, null, null);

INSERT INTO AD_COMPLIANCE(NAME, DESCRIPTION, MODEL_ID, TIME_BEFORE_ACTION, DAILY) values ('Main Rotor Blade Part No. C016-5', 'Per AD 2011-12-10, Amendment 39-16717 issued 2 June, 2011, paragraph (a), I have visually checked for any exposed (bare metal) skin-to-skin joint area on the lower surface of each main rotor blade.  (This check must be performed prior to the first flight of each day and may be performed by the owner/operator (pilot) holding at least a private pilot certificate.  Any exposed skin-to-spar joint area must be refinished before further flight.)', (select id from MODEL where name='Robinson R44'), null, true);
INSERT INTO AD_COMPLIANCE(NAME, DESCRIPTION, MODEL_ID, TIME_BEFORE_ACTION, DAILY) values ('Secondary Rotor Blade Part No. C022-9', 'Per Secondary AD 2011-12-10, Amendment 39-16717 issued 2 June, 2011, paragraph (a), I have visually checked for any exposed (bare metal) skin-to-skin joint area on the lower surface of each main rotor blade.  (This check must be performed prior to the first flight of each day and may be performed by the owner/operator (pilot) holding at least a private pilot certificate.  Any exposed skin-to-spar joint area must be refinished before further flight.)', (select id from MODEL where name='Robinson R44'), null, true);
INSERT INTO AD_COMPLIANCE(NAME, DESCRIPTION, MODEL_ID, TIME_BEFORE_ACTION, DAILY) values ('Stabilizer Tube', 'Per AD 74-8-2, Move stabilizer tube assambly fore and aft in the horizontal plane by hand visually check the tube assambly for cracks in area extending outboard six inches from the tube retaining nut P/N 47-104-114-1', (select id from MODEL where name='Bell 47G2A1'), null, true);
INSERT INTO AD_COMPLIANCE(NAME, DESCRIPTION, MODEL_ID, TIME_BEFORE_ACTION, DAILY) values ('Mast thrust bearing AD 62-23-02 (N/A)', '', (select id from MODEL where name='Bell 47G3B1'), 25, false);
INSERT INTO AD_COMPLIANCE(NAME, DESCRIPTION, MODEL_ID, TIME_BEFORE_ACTION, DAILY) values ('Stabilizer Bar AD 74-08-02', '', (select id from MODEL where name='Robinson R44'), 100, false);
INSERT INTO AD_COMPLIANCE(NAME, DESCRIPTION, MODEL_ID, TIME_BEFORE_ACTION, DAILY) values ('Crankshaft Flange Bolt AD80-04-04 R1', '', (select id from MODEL where name='Robinson R44'), 600, false);

INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-04-08 00:00:00'), null, (select ID from APP_USER where username='doejohn'), (select id from AIRCRAFT where name='N121AH'), (select ID from AD_COMPLIANCE where NAME='Main Rotor Blade Part No. C016-5'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-04-09 00:00:00'), null, (select ID from APP_USER where username='johnsonsally'), (select id from AIRCRAFT where name='N121AH'), (select ID from AD_COMPLIANCE where NAME='Main Rotor Blade Part No. C016-5'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-04-10 00:00:00'), null, (select ID from APP_USER where username='doejohn'), (select id from AIRCRAFT where name='N121AH'), (select ID from AD_COMPLIANCE where NAME='Main Rotor Blade Part No. C016-5'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-04-10 00:00:00'), null, (select ID from APP_USER where username='adamspaul'), (select id from AIRCRAFT where name='N121AH'), (select ID from AD_COMPLIANCE where NAME='Secondary Rotor Blade Part No. C022-9'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-04-11 00:00:00'), null, (select ID from APP_USER where username='adamspaul'), (select id from AIRCRAFT where name='N121AH'), (select ID from AD_COMPLIANCE where NAME='Secondary Rotor Blade Part No. C022-9'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-04-11 00:00:00'), 120, (select ID from APP_USER where username='smithjack'), (select id from AIRCRAFT where name='N32FG'), (select ID from AD_COMPLIANCE where NAME='Mast thrust bearing AD 62-23-02 (N/A)'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-04-25 00:00:00'), 111, (select ID from APP_USER where username='smithjack'), (select id from AIRCRAFT where name='N118TV'), (select ID from AD_COMPLIANCE where NAME='Mast thrust bearing AD 62-23-02 (N/A)'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2015-03-05 00:00:00'), 15.00, (select ID from APP_USER where username='adamspaul'), (select id from AIRCRAFT where name='N4344J'), (select ID from AD_COMPLIANCE where NAME='Stabilizer Bar AD 74-08-02'));
INSERT INTO AD_COMPLIANCE_LOG(COMPLY_WITH_DATE, COMPLY_WITH_HOBBS, PILOT_ID, AIRCRAFT_ID, AD_COMPLIANCE_ID) values (TIMESTAMP('2014-04-01 00:00:00'), 63.00, (select ID from APP_USER where username='adamspaul'), (select id from AIRCRAFT where name='N4344J'), (select ID from AD_COMPLIANCE where NAME='Stabilizer Bar AD 74-08-02'));

INSERT INTO RESET_LOG(DATE, RESET_ITEM, HOBBS, AIRCRAFT_ID) values (TIMESTAMP('2014-02-19 00:00:00'), 'HOBBS', 11.22, (select id from AIRCRAFT where name='N121AH'));
INSERT INTO RESET_LOG(DATE, RESET_ITEM, HOBBS, AIRCRAFT_ID) values (TIMESTAMP('2015-05-19 00:00:00'), 'HOBBS', 77.16, (select id from AIRCRAFT where name='N121AH'));
INSERT INTO RESET_LOG(DATE, RESET_ITEM, HOBBS, AIRCRAFT_ID) values (TIMESTAMP('2015-01-03 00:00:00'), 'ENGINE', 55.75, (select id from AIRCRAFT where name='N121AH'));
INSERT INTO RESET_LOG(DATE, RESET_ITEM, HOBBS, AIRCRAFT_ID) values (TIMESTAMP('2014-05-03 00:00:00'), 'HOBBS', 14.33, (select id from AIRCRAFT where name='N710MY'));
INSERT INTO RESET_LOG(DATE, RESET_ITEM, HOBBS, AIRCRAFT_ID) values (TIMESTAMP('2015-02-07 00:00:00'), 'ENGINE', 42.30, (select id from AIRCRAFT where name='N4344J'));