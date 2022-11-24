package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.MedicineEntity;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, String> {
}
