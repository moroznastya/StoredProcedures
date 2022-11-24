package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.RegionEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.RegionRepository;
import ua.lviv.iot.databases.lab5.services.RegionService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<RegionEntity> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public RegionEntity getById(String s) {
        return regionRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Region doesn't exist!"));
    }

    @Override
    @Transactional
    public RegionEntity create(RegionEntity item) {
        return regionRepository.save(item);
    }

    @Override
    @Transactional
    public RegionEntity updateById(String s, RegionEntity item) {
        RegionEntity region = regionRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Region doesn't exist!"));

        region.setName(item.getName());

        return regionRepository.save(region);
    }

    @Override
    @Transactional
    public void deleteById(String s) {
        regionRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Region doesn't exist!"));

        regionRepository.deleteById(s);
    }

    @Override
    @Transactional
    public void deleteAll() {
        regionRepository.deleteAll();
    }
}
