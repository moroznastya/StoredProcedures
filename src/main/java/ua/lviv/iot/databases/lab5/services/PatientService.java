package ua.lviv.iot.databases.lab5.services;

import ua.lviv.iot.databases.lab5.entities.PatientEntity;

import java.util.List;

public interface PatientService extends GeneralService<PatientEntity, Integer> {
    PatientEntity getPatientEntityByDataId(int dataId);
    List<PatientEntity> getPatientEntitiesByDiagnosesName(String diagnosisName);
    List<PatientEntity> getPatientEntitiesByHospitalId(int hospitalId);


}
