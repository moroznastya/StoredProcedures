package ua.lviv.iot.databases.lab5.services;

import ua.lviv.iot.databases.lab5.entities.DataEntity;

public interface DataService extends GeneralService<DataEntity, Integer> {
    DataEntity getDataEntityByPatientId(int patientId);
}
