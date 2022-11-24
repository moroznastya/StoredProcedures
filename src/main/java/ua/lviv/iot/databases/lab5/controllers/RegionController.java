package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.CityDto;
import ua.lviv.iot.databases.lab5.dto.RegionDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.CityDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.RegionDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.RegionEntity;
import ua.lviv.iot.databases.lab5.services.CityService;
import ua.lviv.iot.databases.lab5.services.RegionService;

@RestController
@RequestMapping("api/databases/lab5/regions")
public class RegionController {
    private final RegionService regionService;
    private final CityService cityService;
    private final RegionDtoAssembler regionDtoAssembler;
    private final CityDtoAssembler cityDtoAssembler;

    @Autowired
    public RegionController(RegionService regionService, CityService cityService, RegionDtoAssembler regionDtoAssembler, CityDtoAssembler cityDtoAssembler) {
        this.regionService = regionService;
        this.cityService = cityService;
        this.regionDtoAssembler = regionDtoAssembler;
        this.cityDtoAssembler = cityDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<RegionDto> getAllRegions(){
        return regionDtoAssembler.toCollectionModel(regionService.getAll());
    }

    @GetMapping("/{id}")
    public RegionDto getRegionById(@PathVariable String id){
        return regionDtoAssembler.toModel(regionService.getById(id));
    }

    @PostMapping("/")
    public RegionDto createRegion(@RequestBody RegionEntity region){
        return regionDtoAssembler.toModel(regionService.create(region));
    }

//    @PutMapping("/{id}")
//    public RegionEntity updateRegion(@PathVariable String id, @RequestBody RegionEntity region){
//        return regionService.updateById(id, region);
//    }

    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable String id){
        regionService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllRegions(){
        regionService.deleteAll();
    }

    @GetMapping("/{id}/cities")
    public CollectionModel<CityDto> getAllCitiesByRegionId(@PathVariable String id){
        return cityDtoAssembler.toCollectionModel(cityService.getCityEntitiesByRegionName(id));
    }
}
