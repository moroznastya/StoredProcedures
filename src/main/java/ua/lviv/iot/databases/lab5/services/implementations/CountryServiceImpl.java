package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.CountryEntity;
import ua.lviv.iot.databases.lab5.entities.DataEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.CountryRepository;
import ua.lviv.iot.databases.lab5.services.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryEntity> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public CountryEntity getById(String s) {
        return countryRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Country doesn't exist!"));
    }

    @Override
    public CountryEntity create(CountryEntity item) {
        String countryName = countryRepository.insertInCountry(item.getName());

        CountryEntity country = new CountryEntity();
        country.setName(countryName);

        return country;
    }

    @Override
    public CountryEntity updateById(String s, CountryEntity item) {
        CountryEntity country = countryRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Country doesn't exist!"));

        country.setName(item.getName());

        return countryRepository.save(country);
    }

    @Override
    public void deleteById(String s) {
        countryRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Country doesn't exist!"));

        countryRepository.deleteById(s);
    }

    @Override
    public void deleteAll() {
        countryRepository.deleteAll();
    }


    @Override
    public void insertTenRowsInCountry() {
        countryRepository.insertTenRowsInCountry();
    }

    @Override
    public void createTablesCountriesNames() {
        countryRepository.createTablesCountriesNames();
    }
}
