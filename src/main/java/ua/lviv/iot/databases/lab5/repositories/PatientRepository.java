package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    PatientEntity findPatientEntityByDataId(int dataId);
    List<PatientEntity> findPatientEntitiesByDiagnosesName(String diagnosisName);
    List<PatientEntity> findPatientEntitiesByHospitalId(int hospitalId);


}
