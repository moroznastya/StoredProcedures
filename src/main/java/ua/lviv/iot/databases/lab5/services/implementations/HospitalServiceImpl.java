package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.HospitalEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.CityRepository;
import ua.lviv.iot.databases.lab5.repositories.HospitalRepository;
import ua.lviv.iot.databases.lab5.services.HospitalService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    private final CityRepository cityRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository, CityRepository cityRepository) {
        this.hospitalRepository = hospitalRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<HospitalEntity> getAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public HospitalEntity getById(Integer id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital doesn't exist!"));
    }

    @Override
    @Transactional
    public HospitalEntity create(HospitalEntity item) {
        return hospitalRepository.save(item);
    }

    @Override
    @Transactional
    public HospitalEntity updateById(Integer id, HospitalEntity item) {
        HospitalEntity hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital doesn't exist!"));

        hospital.setName(item.getName());
        hospital.setAddress(item.getAddress());
        hospital.setCity(item.getCity());

        return hospitalRepository.save(hospital);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital doesn't exist!"));

        hospitalRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        hospitalRepository.deleteAll();
    }

    @Override
    public List<HospitalEntity> getHospitalEntitiesByCityName(String cityName) {
        cityRepository.findById(cityName)
                .orElseThrow(() -> new ResourceNotFoundException("City doesn't exist!"));

        return hospitalRepository.findHospitalEntitiesByCityName(cityName);
    }
}
