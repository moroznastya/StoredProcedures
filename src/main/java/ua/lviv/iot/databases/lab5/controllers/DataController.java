package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.DataDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.DataDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.DataEntity;
import ua.lviv.iot.databases.lab5.services.DataService;

import java.util.List;

@RestController
@RequestMapping("/api/databases/lab5/data")
public class DataController {
    private final DataService dataService;
    private final DataDtoAssembler dataDtoAssembler;

    @Autowired
    public DataController(DataService dataService, DataDtoAssembler dataDtoAssembler) {
        this.dataService = dataService;
        this.dataDtoAssembler = dataDtoAssembler;
    }
    @GetMapping("/")
    public CollectionModel<DataDto> getAllData(){
        return dataDtoAssembler.toCollectionModel(dataService.getAll());
    }

    @GetMapping("/{id}")
    public DataDto getDataById(@PathVariable int id){
        return dataDtoAssembler.toModel(dataService.getById(id));
    }

//    @PostMapping("/")
//    public DataEntity createData(@RequestBody DataEntity data){
//        return dataService.create(data);
//    }

    @PutMapping("/{id}")
    public DataDto updateData(@PathVariable int id, @RequestBody DataEntity data){
        return dataDtoAssembler.toModel(dataService.updateById(id, data));
    }

//    @DeleteMapping("/{id}")
//    public void deleteDataById(@PathVariable int id){
//        dataService.deleteById(id);
//    }
//
//    @DeleteMapping("/")
//    public void deleteAllData(){
//        dataService.deleteAll();
//    }

}
