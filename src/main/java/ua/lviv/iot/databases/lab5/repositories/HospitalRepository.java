package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.HospitalEntity;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Integer> {
    List<HospitalEntity> findHospitalEntitiesByCityName(String cityName);
}
