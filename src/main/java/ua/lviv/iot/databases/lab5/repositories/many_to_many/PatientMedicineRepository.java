package ua.lviv.iot.databases.lab5.repositories.many_to_many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.many_to_many.PatientMedicineEntity;

import java.util.List;

@Repository
public interface PatientMedicineRepository extends JpaRepository<PatientMedicineEntity, Integer> {

    @Query(value = "call insertInPatientMedicine(:patient_id_in, :medicine_name_in, :special_notes_in)", nativeQuery = true)
    PatientMedicineEntity insertInPatientMedicine(
            @Param("patient_id_in") int patientIdIn,
            @Param("medicine_name_in") String medicineNameIn,
            @Param("special_notes_in") String specialNotesIn);

    @Query(value = "call updatePatientMedicine(:id, :patient_id_in, :medicine_name_in, :special_notes_in)", nativeQuery = true)
    PatientMedicineEntity updatePatientMedicine(
            @Param("id") int id,
            @Param("patient_id_in") int patientIdIn,
            @Param("medicine_name_in") String medicineNameIn,
            @Param("special_notes_in") String specialNotesIn);

    List<PatientMedicineEntity> findPatientMedicineEntitiesByPatientId(int patientId);
    List<PatientMedicineEntity> findPatientMedicineEntitiesByMedicineName(String medicineName);
}
