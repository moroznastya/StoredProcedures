package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.CityDto;
import ua.lviv.iot.databases.lab5.dto.HospitalDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.CityDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.HospitalDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.CityEntity;
import ua.lviv.iot.databases.lab5.entities.HospitalEntity;
import ua.lviv.iot.databases.lab5.services.CityService;
import ua.lviv.iot.databases.lab5.services.HospitalService;

@RestController
@RequestMapping("/api/databases/lab5/cities")
public class CityController {
    private final CityService cityService;
    private final HospitalService hospitalService;
    private final CityDtoAssembler cityDtoAssembler;
    private final HospitalDtoAssembler hospitalDtoAssembler;

    @Autowired
    public CityController(CityService cityService, HospitalService hospitalService, CityDtoAssembler cityDtoAssembler, HospitalDtoAssembler hospitalDtoAssembler) {
        this.cityService = cityService;
        this.hospitalService = hospitalService;
        this.cityDtoAssembler = cityDtoAssembler;
        this.hospitalDtoAssembler = hospitalDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<CityDto> getAllCities(){
        return cityDtoAssembler.toCollectionModel(cityService.getAll());
    }

    @GetMapping("/{id}")
    public CityDto getCityById(@PathVariable String id){
        return cityDtoAssembler.toModel(cityService.getById(id));
    }

    @PostMapping("/")
    public CityDto createCity(@RequestBody CityEntity city){
        return cityDtoAssembler.toModel(cityService.create(city));
    }

//    @PutMapping("/{id}")
//    public CityEntity updateCity(@PathVariable String id, @RequestBody CityEntity city){
//        return cityService.updateById(id, city);
//    }

    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable String id){
        cityService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllCities(){
        cityService.deleteAll();
    }

    @GetMapping("/{id}/hospital")
    public CollectionModel<HospitalDto> getHospitalEntitiesByCityName(@PathVariable String id){
        return hospitalDtoAssembler.toCollectionModel(hospitalService.getHospitalEntitiesByCityName(id));
    }


}
