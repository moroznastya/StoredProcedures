package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.CityEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.CityRepository;
import ua.lviv.iot.databases.lab5.repositories.RegionRepository;
import ua.lviv.iot.databases.lab5.services.CityService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, RegionRepository regionRepository) {
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public List<CityEntity> getCityEntitiesByRegionName(String regionName) {
        regionRepository.findById(regionName)
                .orElseThrow(() -> new ResourceNotFoundException("Region doesn't exist!"));

        return cityRepository.findCityEntitiesByRegionName(regionName);
    }

    @Override
    public List<CityEntity> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public CityEntity getById(String s) {
        return cityRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("City doesn't exist!"));
    }

    @Override
    @Transactional
    public CityEntity create(CityEntity item) {
        return cityRepository.save(item);
    }

    @Override
    @Transactional
    public CityEntity updateById(String s, CityEntity item) {
        CityEntity city = cityRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("City doesn't exist!"));

        city.setName(item.getName());
        city.setRegion(item.getRegion());

        return cityRepository.save(city);
    }

    @Override
    @Transactional
    public void deleteById(String s) {
        cityRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("City doesn't exist!"));

        cityRepository.deleteById(s);
    }

    @Override
    @Transactional
    public void deleteAll() {
        cityRepository.deleteAll();
    }
}
