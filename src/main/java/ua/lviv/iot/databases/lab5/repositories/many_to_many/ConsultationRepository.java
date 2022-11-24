package ua.lviv.iot.databases.lab5.repositories.many_to_many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.many_to_many.ConsultationEntity;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Integer> {
    List<ConsultationEntity> findConsultationEntitiesByPatientId(int patientId);
    List<ConsultationEntity> findConsultationEntitiesByDoctorId(int doctorId);
}
