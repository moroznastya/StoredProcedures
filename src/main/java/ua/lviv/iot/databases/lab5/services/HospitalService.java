package ua.lviv.iot.databases.lab5.services;

import ua.lviv.iot.databases.lab5.entities.HospitalEntity;

import java.util.List;

public interface HospitalService extends GeneralService<HospitalEntity, Integer> {
    List<HospitalEntity> getHospitalEntitiesByCityName(String cityName);
}
