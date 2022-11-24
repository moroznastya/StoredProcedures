package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.DiagnosisEntity;
import ua.lviv.iot.databases.lab5.entities.MedicineEntity;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<DiagnosisEntity, String> {
    List<DiagnosisEntity> findDiagnosisEntitiesByPatientsId(int patientId);
}
