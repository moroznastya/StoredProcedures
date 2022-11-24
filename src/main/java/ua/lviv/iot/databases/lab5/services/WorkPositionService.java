package ua.lviv.iot.databases.lab5.services;

import ua.lviv.iot.databases.lab5.entities.WorkPositionEntity;

import java.util.List;

public interface WorkPositionService extends GeneralService<WorkPositionEntity, String> {
    List<WorkPositionEntity> getWorkPositionEntitiesByDoctorsId(int doctorId);
}
