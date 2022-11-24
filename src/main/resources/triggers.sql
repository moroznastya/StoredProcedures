-- Use database
use labs_db_triggers;


-- Region
drop trigger if exists onCreateRegion;
DELIMITER // 
create trigger onCreateRegion
before insert
on region
for each row
begin
	if (new.country_name not in (select `name` from country)) then
		signal sqlstate '45000'
		set message_text = 'FK error: no such data found';
	end if;
end//
DELIMITER ;


-- Country
drop trigger if exists onCreateCountry;
DELIMITER // 
create trigger onCreateCountry
before insert
on country
for each row
begin
	if (new.`name` in (select `name` from country)) then
		signal sqlstate '45000'
		set message_text = 'PK error: such country already exists';
	end if;
end//
DELIMITER ;


drop trigger if exists onDeleteCountry;
DELIMITER // 
create trigger onDeleteCountry
before delete
on country
for each row
begin
	delete from region where country_name=old.`name`;
end//
DELIMITER ;


-- PatientMedicine
drop trigger if exists onCreatePatientMedicine;
DELIMITER // 
create trigger onCreatePatientMedicine
before insert
on patient_medicine
for each row
begin
	if (new.patient_id not in (select id from patient) or new.medicine_name not in (select `name` from medicine)) then
		signal sqlstate '45000'
		set message_text = 'FK error: no such data found';
	end if;    
end//
DELIMITER ;


drop trigger if exists onUpdatePatientMedicine;
DELIMITER // 
create trigger onUpdatePatientMedicine
before update
on patient_medicine
for each row
begin
	if (new.id not in (select id from patient_medicine) or 
		new.patient_id not in (select id from patient) or 
		new.medicine_name not in (select `name` from medicine)) then
		signal sqlstate '45000'
		set message_text = 'error';
	end if; 
end//
DELIMITER ;


-- Task 3: trigger (a)
drop trigger if exists doctorsSalaryCannotEndsWithTwoZeros;
DELIMITER // 
create trigger doctorsSalaryCannotEndsWithTwoZeros
before insert
on doctor
for each row
begin
	if (
		substring(new.salary_in_hrn, -2) = '00'
    ) then
		signal sqlstate '45000'
		set message_text = 'salary cannot end with "00"';
	end if; 
end//
DELIMITER ;


-- Task 3: trigger (f)
drop trigger if exists logsOnCountryDeletion;
DELIMITER // 
create trigger logsOnCountryDeletion
before delete
on country
for each row
begin
	insert into logs_for_country_deletion(country, `time`) values(old.`name`, current_timestamp());
end//
DELIMITER ;


-- Task 3: trigger (j)
drop trigger if exists onlySpecialCountriesAreAllowed;
DELIMITER // 
create trigger onlySpecialCountriesAreAllowed
before insert
on country
for each row
begin
	if (
		new.`name` not in ("Ukraine", "Poland", "Germany", "USA", "France", "Sweden", "Spain", "Italy", "Portugal")
	)
    then 
		signal sqlstate '45000'
		set message_text = 'unknown country';
	end if; 
end//
DELIMITER ;









