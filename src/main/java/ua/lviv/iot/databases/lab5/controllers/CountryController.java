package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.CountryDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.CountryDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.CountryEntity;
import ua.lviv.iot.databases.lab5.entities.DiagnosisEntity;
import ua.lviv.iot.databases.lab5.services.CountryService;

@RestController
@RequestMapping("/api/database/lab5/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryDtoAssembler countryDtoAssembler;

    @Autowired
    public CountryController(CountryService countryService, CountryDtoAssembler countryDtoAssembler) {
        this.countryService = countryService;
        this.countryDtoAssembler = countryDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<CountryDto> getAllCountries(){
        return countryDtoAssembler.toCollectionModel(countryService.getAll());
    }

    @GetMapping("/{id}")
    public CountryDto getCountryById(@PathVariable String id){
        return countryDtoAssembler.toModel(countryService.getById(id));
    }

    @PostMapping("/")
    public CountryDto createCountry(@RequestBody CountryEntity country){
        return countryDtoAssembler.toModel(countryService.create(country));
    }

//    @PutMapping("/{id}")
//    public CountryDto updateCountry(@PathVariable String id, @RequestBody CountryEntity country){
//        return countryDtoAssembler.toModel(countryService.updateById(id, country));
//    }

    @DeleteMapping("/{id}")
    public void deleteCountryById(@PathVariable String id){
        countryService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllCountries(){
        countryService.deleteAll();
    }

    @PostMapping("/insert-ten-random-countries")
    public void insertTenRowsInCountry(){
        countryService.insertTenRowsInCountry();
    }

    @PostMapping("/create-tables-with-countries-names")
    public void createTablesCountriesNames(){
        countryService.createTablesCountriesNames();
    }
}


