package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.CityEntity;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String> {
    List<CityEntity> findCityEntitiesByRegionName(String regionName);
}
