package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.DoctorEntity;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {
    List<DoctorEntity> findDoctorEntitiesByPositionsName(String positionId);
    List<DoctorEntity> findDoctorEntitiesByHospitalId(int hospitalId);
    @Procedure
    int maxDoctorsSalary();

}
