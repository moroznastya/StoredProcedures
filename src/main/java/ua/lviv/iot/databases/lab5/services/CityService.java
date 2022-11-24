package ua.lviv.iot.databases.lab5.services;

import ua.lviv.iot.databases.lab5.entities.CityEntity;

import java.util.List;

public interface CityService extends GeneralService<CityEntity, String> {
    List<CityEntity> getCityEntitiesByRegionName(String regionName);
}
