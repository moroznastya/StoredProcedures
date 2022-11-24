package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.MedicineEntity;
import ua.lviv.iot.databases.lab5.entities.RegionEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.MedicineRepository;
import ua.lviv.iot.databases.lab5.services.MedicineService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<MedicineEntity> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public MedicineEntity getById(String s) {
        return medicineRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine doesn't exist!"));
    }

    @Override
    @Transactional
    public MedicineEntity create(MedicineEntity item) {
        return medicineRepository.save(item);
    }

    @Override
    @Transactional
    public MedicineEntity updateById(String s, MedicineEntity item) {
        MedicineEntity medicine = medicineRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine doesn't exist!"));

        medicine.setName(item.getName());

        return medicineRepository.save(medicine);
    }

    @Override
    @Transactional
    public void deleteById(String s) {
        medicineRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine doesn't exist!"));

        medicineRepository.deleteById(s);
    }

    @Override
    @Transactional
    public void deleteAll() {
        medicineRepository.deleteAll();
    }
}
