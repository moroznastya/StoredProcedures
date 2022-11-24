package ua.lviv.iot.databases.lab5.services;

import org.springframework.data.jpa.repository.query.Procedure;
import ua.lviv.iot.databases.lab5.entities.DoctorEntity;

import java.util.List;

public interface DoctorService extends GeneralService<DoctorEntity, Integer> {
    List<DoctorEntity> getDoctorEntitiesByPositionsName(String positionId);
    List<DoctorEntity> getDoctorEntitiesByHospitalId(int hospitalId);

    int maxDoctorsSalary();
}
