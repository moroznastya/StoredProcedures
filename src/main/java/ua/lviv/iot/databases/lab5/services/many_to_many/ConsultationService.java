package ua.lviv.iot.databases.lab5.services.many_to_many;

import ua.lviv.iot.databases.lab5.entities.many_to_many.ConsultationEntity;
import ua.lviv.iot.databases.lab5.services.GeneralService;

import java.util.List;

public interface ConsultationService extends GeneralService<ConsultationEntity, Integer> {
    List<ConsultationEntity> getConsultationEntitiesByPatientId(int patientId);
    List<ConsultationEntity> getConsultationEntitiesByDoctorId(int doctorId);
}
