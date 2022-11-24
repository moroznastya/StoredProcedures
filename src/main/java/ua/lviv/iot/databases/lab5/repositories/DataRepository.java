package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.DataEntity;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Integer> {
    DataEntity findDataEntityByPatientId(int patientId);
}
