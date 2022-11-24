package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.DoctorDto;
import ua.lviv.iot.databases.lab5.dto.WorkPositionDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.DoctorDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.WorkPositionDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.WorkPositionEntity;
import ua.lviv.iot.databases.lab5.services.DoctorService;
import ua.lviv.iot.databases.lab5.services.WorkPositionService;

@RestController
@RequestMapping("/api/databases/lab5/positions")
public class WorkPositionController {
    private final WorkPositionService workPositionService;
    private final DoctorService doctorService;
    private final WorkPositionDtoAssembler workPositionDtoAssembler;
    private final DoctorDtoAssembler doctorDtoAssembler;

    @Autowired
    public WorkPositionController(WorkPositionService workPositionService, DoctorService doctorService, WorkPositionDtoAssembler workPositionDtoAssembler, DoctorDtoAssembler doctorDtoAssembler) {
        this.workPositionService = workPositionService;
        this.doctorService = doctorService;
        this.workPositionDtoAssembler = workPositionDtoAssembler;
        this.doctorDtoAssembler = doctorDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<WorkPositionDto> getAllWorkPositions(){
        return workPositionDtoAssembler.toCollectionModel(workPositionService.getAll());
    }

    @GetMapping("/{id}")
    public WorkPositionDto getWorkPositionById(@PathVariable String id){
        return workPositionDtoAssembler.toModel(workPositionService.getById(id));
    }

    @PostMapping("/")
    public WorkPositionDto createWorkPosition(@RequestBody WorkPositionEntity workPosition){
        return workPositionDtoAssembler.toModel(workPositionService.create(workPosition));
    }

//    @PutMapping("/{id}")
//    public WorkPositionEntity updateWorkPosition(@PathVariable String id, @RequestBody WorkPositionEntity workPosition){
//        return workPositionService.updateById(id, workPosition);
//    }

    @DeleteMapping("/{id}")
    public void deleteWorkPosition(@PathVariable String id){
        workPositionService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllPositions(){
        workPositionService.deleteAll();
    }

    @GetMapping("/{id}/doctors")
    public CollectionModel<DoctorDto> getDoctorEntitiesByPositionsName(@PathVariable String id){
        return doctorDtoAssembler.toCollectionModel(doctorService.getDoctorEntitiesByPositionsName(id));
    }


}
