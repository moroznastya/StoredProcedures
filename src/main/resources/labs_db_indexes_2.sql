-- Create database
create database if not exists labs_db_triggers;

-- Use database
use labs_db_triggers;



-- Drop tables if exist
drop table if exists patient_medicine;
drop table if exists patient_diagnosis;
drop table if exists doctor_position;
drop table if exists consultation;
drop table if exists patient;
drop table if exists doctor;
drop table if exists hospital;
drop table if exists country;
drop table if exists city;
drop table if exists medicine;
drop table if exists diagnosis;
drop table if exists region;
drop table if exists work_position;
drop table if exists `data`;
drop table if exists logs_for_country_deletion;



-- Create Tables
-- Table: city
create table city (
    `name` varchar(70) not null,
    region_name varchar(70) not null,
    constraint city_pk primary key (`name`)
);

create table country(
	`name` varchar(70) not null,
    constraint country_pk primary key (`name`)
);

-- Table: consultation
create table consultation (
	id int not null auto_increment,
    patient_id int not null,
    doctor_id int not null,
    `date` date not null,
    conclusion text not null,
    constraint consultation_pk primary key (id)
);

-- Table: data
create table `data` (
    id int NOT NULL auto_increment,
    temperature_in_celsius float not null,
    systolic_pressure int not null,
    diastolic_pressure int not null,
    heart_rate_in_minute int not null,
    special_notes text,
    constraint data_pk primary key (id)
);

-- Table: diagnosis
create table diagnosis (
    `name` varchar(90) not null,
    constraint diagnosis_pk primary key (`name`)
);

-- Table: doctor
create table doctor (
    id int NOT NULL auto_increment,
    surname varchar(50) not null,
    `name` varchar(50) not null,
    previous_experience_in_years int not null,
    hire_date date not null,
    hospital_id int not null,
    salary_in_hrn int not null,
    constraint doctor_pk primary key (id)
);

-- Table: doctor_position
create table doctor_position (
    doctor_id int not null,
    position_name varchar(90) not null,
    constraint doctor_position_pk primary key (doctor_id,position_name)
);

-- Table: hospital
create table hospital (
    id int NOT NULL auto_increment,
    `name` varchar(100) not null,
    address varchar(100) not null,
    city_name varchar(70) not null,
    constraint hospital_pk primary key (id)
);

-- Table: medicine
create table medicine (
    `name` varchar(100) not null,
    constraint medicine_pk primary key (`name`)
);

-- Table: patient
create table patient (
    id int NOT NULL auto_increment,
    surname varchar(50) not null,
    `name` varchar(50) not null,
    registration_date date not null,
    data_id int not null,
    hospital_id int not null,
    constraint patient_pk primary key (id)
);

-- Table: patient_diagnosis
create table patient_diagnosis (
    patient_id int not null,
    diagnosis_name varchar(90) not null,
    constraint patient_diagnosis_pk primary key (patient_id,diagnosis_name)
);

-- Table: patient_medicine
create table patient_medicine (
	id int not null auto_increment,
    patient_id int not null,
    medicine_name varchar(100) not null,
    special_notes text,
    constraint patient_medicine_pk primary key (id)
);

-- Table: region
create table region (
    `name` varchar(70) not null,
    country_name varchar(50) not null,
    constraint region_pk primary key (`name`)
);

-- Table: work_position
create table work_position (
    `name` varchar(90) not null,
    constraint work_position_pk primary key (`name`)
);

-- Table: logs_for_country_deletion
create table logs_for_country_deletion(
	country varchar(50) not null,
    `time` timestamp
);



-- Add foreign keys
-- Reference: city_region (table: city)
alter table city add constraint city_region foreign key city_region (region_name)
    references region (`name`) on update cascade on delete cascade;

-- Reference: consultation_doctor (table: consultation)
alter table consultation add constraint consultation_doctor foreign key consultation_doctor (doctor_id)
    references doctor (id) on update cascade on delete cascade;

-- Reference: consultation_patient (table: consultation)
alter table consultation add constraint consultation_patient foreign key consultation_patient (patient_id)
    references patient (id) on update cascade on delete cascade;

-- Reference: doctor_hospital (table: doctor)
alter table doctor add constraint doctor_hospital foreign key doctor_hospital (hospital_id)
    references hospital (id) on update cascade on delete cascade;

-- Reference: doctor_position_doctor (table: doctor_position)
alter table doctor_position add constraint doctor_position_doctor foreign key doctor_position_doctor (doctor_id)
    references doctor (id) on update cascade on delete cascade;

-- Reference: doctor_position_position (table: doctor_position)
alter table doctor_position add constraint doctor_position_position foreign key doctor_position_position (position_name)
    references work_position (name) on update cascade on delete cascade;

-- Reference: hospital_city (table: hospital)
alter table hospital add constraint hospital_city foreign key hospital_city (city_name)
    references city (`name`) on update cascade on delete cascade;

-- Reference: patient_data (table: patient)
alter table patient add constraint patient_data foreign key patient_data (data_id)
    references `data` (id) on update cascade on delete cascade;

-- Reference: patient_diagnosis_diagnosis (table: patient_diagnosis)
alter table patient_diagnosis add constraint patient_diagnosis_diagnosis foreign key patient_diagnosis_diagnosis (diagnosis_name)
    references diagnosis (`name`) on update cascade on delete cascade;

-- Reference: patient_diagnosis_patient (table: patient_diagnosis)
alter table patient_diagnosis add constraint patient_diagnosis_patient foreign key patient_diagnosis_patient (patient_id)
    references patient (id) on update cascade on delete cascade;

-- Reference: patient_hospital (table: patient)
alter table patient add constraint patient_hospital foreign key patient_hospital (hospital_id)
    references hospital (id) on update cascade on delete cascade;

-- Reference: patient_medicine_medicine (table: patient_medicine)
alter table patient_medicine add constraint patient_medicine_medicine foreign key patient_medicine_medicine (medicine_name)
    references medicine (`name`) on update cascade on delete cascade;

-- Reference: patient_medicine_patient (table: patient_medicine)
alter table patient_medicine add constraint patient_medicine_patient foreign key patient_medicine_patient (patient_id)
    references patient (id) on update cascade on delete cascade;           
    
    
    
-- Create indexes  
create index city_name_idx on city(`name`);   
create index hospital_name_idx on hospital(`name`); 
create index patient_surname_idx on patient(surname);  
create index doctor_surname_idx on doctor(surname);        
    
    
    
-- Insert values into tables
-- Country
insert into country(`name`) values('Ukraine');
insert into country(`name`) values('Poland');
insert into country(`name`) values('Slovakia');

-- Region
insert into region(`name`, country_name) values('Kyiv region', "Ukraine"); 
insert into region(`name`, country_name) values('Lviv region', "Ukraine");
insert into region(`name`, country_name) values('Rivne region', "Ukraine");
insert into region(`name`, country_name) values('Odesa region', "Ukraine");
insert into region(`name`, country_name) values('Volyn region', "Ukraine");
insert into region(`name`, country_name) values('Ternopil region', "Ukraine");
insert into region(`name`, country_name) values('Ivano-Frankivsk region', "Ukraine");
insert into region(`name`, country_name) values('Kropyvnytskyi region', "Ukraine");
insert into region(`name`, country_name) values('Zhytomyr region', "Ukraine");
insert into region(`name`, country_name) values('Mykolaiv region', "Ukraine");
insert into region(`name`, country_name) values('Zakarpattia region', "Ukraine");
insert into region(`name`, country_name) values('Kherson region', "Ukraine");

-- Diagnosis
insert into diagnosis(`name`) values('pneumonia');
insert into diagnosis(`name`) values('bronchitis');
insert into diagnosis(`name`) values('flu');
insert into diagnosis(`name`) values('poisoning');
insert into diagnosis(`name`) values('allergy');
insert into diagnosis(`name`) values('acetonemia');
insert into diagnosis(`name`) values('cystitic');
insert into diagnosis(`name`) values('candidiasis');
insert into diagnosis(`name`) values('stomatitis');

-- City
insert into city(`name`, region_name) values('Rivne', 'Rivne region');
insert into city(`name`, region_name) values('Korets', 'Rivne region');
insert into city(`name`, region_name) values('Kyiv', 'Kyiv region');
insert into city(`name`, region_name) values('Bila Tserkva', 'Kyiv region');
insert into city(`name`, region_name) values('Kalush', 'Ivano-Frankivsk region');
insert into city(`name`, region_name) values('Ivano-Frankivsk', 'Ivano-Frankivsk region');
insert into city(`name`, region_name) values('Lutsk', 'Volyn region');
insert into city(`name`, region_name) values('Kherson', 'Kherson region');
insert into city(`name`, region_name) values('Zhytomyr', 'Zhytomyr region');
insert into city(`name`, region_name) values('Ternopil', 'Ternopil region');
insert into city(`name`, region_name) values('Pochaiv', 'Ternopil region');
insert into city(`name`, region_name) values('Mykolaiv', 'Mykolaiv region');

-- Work_Position
insert into work_position(`name`) values('virologist');
insert into work_position(`name`) values('therapist');
insert into work_position(`name`) values('bacteriologist');
insert into work_position(`name`) values('urologist');
insert into work_position(`name`) values('dentist');
insert into work_position(`name`) values('pediatrician');
insert into work_position(`name`) values('allergist');
insert into work_position(`name`) values('pulmonologist');
insert into work_position(`name`) values('ENT doctor');
insert into work_position(`name`) values('infectious disease specialist');

-- Hospital
insert into hospital(`name`, address, city_name) values('Rivne Regional Hospital', 'Makarova street, 10', 'Rivne');
insert into hospital(`name`, address, city_name) values('Rivne Health Centre', 'Kyivska street, 4', 'Rivne');
insert into hospital(`name`, address, city_name) values('Kyiv City Clinical Hospital', 'Khreshchatyk street, 154', 'Kyiv');
insert into hospital(`name`, address, city_name) values('Zhytomyr Military hospital', 'Military street, 15', 'Zhytomyr');
insert into hospital(`name`, address, city_name) values('Ternopil Hospital №1', 'Shpytalna street, 9', 'Ternopil');
insert into hospital(`name`, address, city_name) values('Pochaiv District Komunalna Likarnya', 'Hospitalna street, 6', 'Pochaiv');
insert into hospital(`name`, address, city_name) values('Mykolaiv Hospital for War Veterans', 'Kyivska street, 1', 'Mykolaiv');
insert into hospital(`name`, address, city_name) values('Korets Tsentralna Rayonna Likarnya', 'Volodymirska street, 14a', 'Korets');
insert into hospital(`name`, address, city_name) values('Bilotserkivsʹkyy Viysʹkovyy Hospital', 'Bulvar Oleksandriivskii, 109', 'Bila Tserkva');
insert into hospital(`name`, address, city_name) values('Lutsk Municipal Clinical Hospital', 'Vidrodzennia avenue, 13', 'Lutsk');
insert into hospital(`name`, address, city_name) values('Kalush Central District Hospital', 'Medychna street, 6', 'Kalush');
insert into hospital(`name`, address, city_name) values('Ivano-Frankivsk Regional Clinical Hospital', 'Fedkovicha street, 91', 'Ivano-Frankivsk');
insert into hospital(`name`, address, city_name) values('Kherson City Clinical Hospital', 'Krymska street, 138', 'Kherson');

-- Doctor
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Vasylkovskii', 'Roman', 4, '2010-01-04', 2, 16500);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Kozak', 'Denys', 2, '2018-11-05', 7, 11200);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Lyulka', 'Vladyslav', 17, '2017-12-12', 4, 27700);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Borylyuk', 'Vladyslav', 7, '2016-10-10', 4, 20000);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Samanchuk', 'Iryna', 3, '2020-02-17', 7, 10000);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Ivanenko', 'Oleksandr', 0, '2022-11-11', 2, 7500);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Dovbenko', 'Ilarion', 1, '2021-07-21', 2, 12500);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Zenkivskyi', 'Bohdan', 8, '2019-09-19', 4, 13000);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Pobozhna', 'Oksana', 2, '2019-09-24', 7, 8000);
insert into doctor(surname, `name`, previous_experience_in_years, hire_date, hospital_id, salary_in_hrn) values('Shokalo', 'Natalia', 2, '2014-04-09', 7, 14400);

-- Doctor_Position
insert into doctor_position(doctor_id, position_name) values(1, 'virologist');
insert into doctor_position(doctor_id, position_name) values(1, 'therapist');
insert into doctor_position(doctor_id, position_name) values(2, 'therapist');
insert into doctor_position(doctor_id, position_name) values(3, 'bacteriologist');
insert into doctor_position(doctor_id, position_name) values(4, 'urologist');
insert into doctor_position(doctor_id, position_name) values(5, 'dentist');
insert into doctor_position(doctor_id, position_name) values(6, 'bacteriologist');
insert into doctor_position(doctor_id, position_name) values(6, 'pediatrician');
insert into doctor_position(doctor_id, position_name) values(7, 'pulmonologist');
insert into doctor_position(doctor_id, position_name) values(7, 'ENT doctor');
insert into doctor_position(doctor_id, position_name) values(8, 'ENT doctor');
insert into doctor_position(doctor_id, position_name) values(9, 'infectious disease specialist');
insert into doctor_position(doctor_id, position_name) values(10, 'allergist');

-- Data
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(38.6, 130, 75, 74);
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute, special_notes) values(40.4, 150, 90, 89, 'patient needs urgent medical intervention');
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(38.0, 124, 70, 65);
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(39.0, 140, 80, 77);
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(37.8, 140, 98, 65);
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(36.9, 95, 69, 59);
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(38.0, 145, 70, 81);
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute, special_notes) values(41.5, 165, 92, 90, 'patient needs urgent medical intervention');
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(38.0, 140, 80, 77);
insert into data(temperature_in_celsius, systolic_pressure, diastolic_pressure, heart_rate_in_minute) values(39.4, 140, 80, 69);

-- Medicine
insert into medicine(`name`) values('penicillin');
insert into medicine(`name`) values('molnupiravir');
insert into medicine(`name`) values('flixotide');
insert into medicine(`name`) values('agropyron');
insert into medicine(`name`) values('enterosgel');
insert into medicine(`name`) values('cetirizine');
insert into medicine(`name`) values('subalin');
insert into medicine(`name`) values('furamag');
insert into medicine(`name`) values('clotrimazole');
insert into medicine(`name`) values('chlorophyllipt');

-- Patient
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Shevchenko', 'Bohdan', '2022-08-12', 1, 2);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Dovzhenko', 'Ostap', '2022-08-13', 2, 2);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Starchuk', 'Oleksandra', '2022-07-11', 3, 7);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Zhuk', 'Ivan', '2022-06-01', 4, 7);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Sashenko', 'Ignatiy', '2022-08-25', 5, 4);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Ukrainets', 'Oksana', '2022-09-02', 6, 4);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Polonska', 'Margaryta', '2022-09-03', 7, 4);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Zhabchak', 'Roman', '2022-08-29', 8, 2);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Golovinskyi', 'Igor', '2022-08-27', 9, 7);
insert into patient(surname, `name`, registration_date, data_id, hospital_id) values('Ignatenko', 'Ksenia', '2022-09-01', 10, 7);

-- Patient_Medicine
insert into patient_medicine(patient_id, medicine_name) values(1, 'penicillin');
insert into patient_medicine(patient_id, medicine_name) values(1, 'molnupiravir');
insert into patient_medicine(patient_id, medicine_name) values(2, 'molnupiravir');
insert into patient_medicine(patient_id, medicine_name) values(2, 'flixotide');
insert into patient_medicine(patient_id, medicine_name, special_notes) values(3, 'agropyron', 'extend the course for 3 days');
insert into patient_medicine(patient_id, medicine_name) values(4, 'enterosgel');
insert into patient_medicine(patient_id, medicine_name) values(4, 'agropyron');
insert into patient_medicine(patient_id, medicine_name) values(5, 'agropyron');
insert into patient_medicine(patient_id, medicine_name) values(6, 'cetirizine');
insert into patient_medicine(patient_id, medicine_name, special_notes) values(6, 'subalin', 'extend the course for 10 days');
insert into patient_medicine(patient_id, medicine_name, special_notes) values(7, 'molnupiravir', 'extend the course for 1 day');
insert into patient_medicine(patient_id, medicine_name, special_notes) values(8, 'furamag', 'extend the course for 21 days');
insert into patient_medicine(patient_id, medicine_name) values(9, 'chlorophyllipt');
insert into patient_medicine(patient_id, medicine_name, special_notes) values(10, 'subalin', 'extend the course for 4 days');

-- Patient_Diagnosis
insert into patient_diagnosis(patient_id, diagnosis_name) values(1, 'pneumonia');
insert into patient_diagnosis(patient_id, diagnosis_name) values(2, 'bronchitis');
insert into patient_diagnosis(patient_id, diagnosis_name) values(3, 'pneumonia');
insert into patient_diagnosis(patient_id, diagnosis_name) values(4, 'poisoning');
insert into patient_diagnosis(patient_id, diagnosis_name) values(5, 'stomatitis');
insert into patient_diagnosis(patient_id, diagnosis_name) values(6, 'acetonemia');
insert into patient_diagnosis(patient_id, diagnosis_name) values(7, 'acetonemia');
insert into patient_diagnosis(patient_id, diagnosis_name) values(8, 'acetonemia');
insert into patient_diagnosis(patient_id, diagnosis_name) values(9, 'cystitic');
insert into patient_diagnosis(patient_id, diagnosis_name) values(10, 'poisoning');

-- Consultation
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(1, 1, '2022-08-12', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(1, 6, '2022-08-13', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(2, 6, '2022-08-13', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(2, 7, '2022-08-17', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(3, 2, '2022-07-11', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(3, 5, '2022-08-24', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(4, 9, '2022-06-01', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(4, 10, '2022-06-30', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(4, 5, '2022-07-21', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(5, 3, '2022-08-25', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(5, 8, '2022-08-30', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(6, 4, '2022-09-02', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(6, 8, '2022-09-03', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(7, 4, '2022-09-03', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(7, 4, '2022-09-05', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(8, 6, '2022-08-29', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(8, 1, '2022-09-01', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(9, 9, '2022-08-28', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(9, 2, '2022-08-29', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(10, 10, '2022-09-01', 'treatment was prescribed');
insert into consultation(patient_id, doctor_id, `date`, conclusion) values(10, 5, '2022-09-04', 'treatment was prescribed');
