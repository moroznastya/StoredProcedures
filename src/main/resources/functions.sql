-- Use database
use labs_db_triggers;


set global log_bin_trust_function_creators = 1;


-- count MAX for any column in Patient
drop function if exists maxSalaryAmongDoctors;
DELIMITER //
create function maxSalaryAmongDoctors() 
returns int
begin
    return (select max(salary_in_hrn) from doctor);
end //
DELIMITER ;


