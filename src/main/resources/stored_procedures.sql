-- Use database
use labs_db_triggers;


-- Parametrized insertion in Country
drop procedure if exists insertInCountry;
DELIMITER //
create procedure insertInCountry(
	in name_in varchar (50),
	out name_out varchar (50)
)
begin    
	insert into country(`name`) values (name_in);
    select `name` into name_out from country where `name`=name_in limit 1;
end //
DELIMITER ;


-- many-to-many for Patient-Medicine
drop procedure if exists insertInPatientMedicine;
DELIMITER //
create procedure insertInPatientMedicine(
	in patient_id_in int,
	in medicine_name_in varchar(70),
    in special_notes_in text
)
begin    
	insert into patient_medicine(patient_id, medicine_name, special_notes)
		values(patient_id_in, medicine_name_in, special_notes_in);      
	select * 
		-- into patient_id_out, medicine_name_out, special_notes_out
        from patient_medicine
        where id=last_insert_id() limit 1;
end //
DELIMITER ;


drop procedure if exists updatePatientMedicine;
DELIMITER //
create procedure updatePatientMedicine(
	in id_in int,
	in patient_id_in int,
	in medicine_name_in varchar(70),
    in special_notes_in text
)
begin   
	update patient_medicine set 
		patient_id=patient_id_in, medicine_name=medicine_name_in, special_notes=special_notes_in
        where id=id_in;
	select *
		-- into patient_id_out, medicine_name_out, special_notes_out
        from patient_medicine
        where id=id limit 1;
end //
DELIMITER ;


-- insert 10 rows in Country
drop procedure if exists insertTenRowsInCountry;
DELIMITER //
create procedure insertTenRowsInCountry()
begin   
	declare counter int;
    set counter = 1;
    loop_label: loop
		if counter = 11 then leave loop_label;
        end if;
        insert into country(`name`) values(concat("Noname", counter));
        set counter = counter + 1;
    end loop;    
end //
DELIMITER ;


-- for MAX function
drop procedure if exists maxDoctorsSalary;
DELIMITER //
create procedure maxDoctorsSalary(
	out max_v int
)
 begin
	set max_v = maxSalaryAmongDoctors();
end //
DELIMITER ;


-- cursor for Country (task №2)
drop procedure if exists createTablesCountriesNames;
DELIMITER //
create procedure createTablesCountriesNames()
begin

	declare done bool default false;
	declare  new_name varchar(50);
    
    declare counter int;
    declare random_n int;

	declare Country_Cursor cursor
	for select `name` from country;

	declare continue handler
	for not found set done = true;
	
	open Country_Cursor;   
    	
	country_loop: loop

		fetch Country_Cursor into new_name;
		if done then leave country_loop;
		end if;
		set @temp_query_drop=CONCAT('drop table if exists ', new_name, ';');
		set @temp_query=CONCAT('create table ', new_name, '(some_text varchar(50));');                   
        
		set counter = 1;
        set random_n = (select floor(rand()*10)+1);
            
        prepare my_query_drop from @temp_query_drop;
		execute my_query_drop;
		deallocate prepare my_query_drop;
	
		prepare my_query from @temp_query;
		execute my_query;
		deallocate prepare my_query;
        
        my_loop: loop

			set @temp_query_2=CONCAT('insert into ', new_name, ' values("', SUBSTR(MD5(RAND()),1,8), '");');

			prepare my_query_2 from @temp_query_2;
			execute my_query_2;
			deallocate prepare my_query_2;

			if counter = random_n then leave my_loop;
			end if;

			set counter = counter + 1;

		end loop;
        
	end loop;
    
	close Country_Cursor;

end //
DELIMITER ;



