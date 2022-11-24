package ua.lviv.iot.databases.lab5.services;

import ua.lviv.iot.databases.lab5.entities.DiagnosisEntity;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;

import java.util.List;

public interface DiagnosisService extends GeneralService<DiagnosisEntity, String>{
    List<DiagnosisEntity> getDiagnosisEntitiesByPatientsId(int patientId);
}
