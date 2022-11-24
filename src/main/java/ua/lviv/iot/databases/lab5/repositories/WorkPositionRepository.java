package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.DoctorEntity;
import ua.lviv.iot.databases.lab5.entities.WorkPositionEntity;

import java.util.List;

@Repository
public interface WorkPositionRepository extends JpaRepository<WorkPositionEntity, String> {
    List<WorkPositionEntity> findWorkPositionEntitiesByDoctorsId(int doctorId);
}
