package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.DoctorDto;
import ua.lviv.iot.databases.lab5.dto.WorkPositionDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.DoctorDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.WorkPositionDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.DoctorEntity;
import ua.lviv.iot.databases.lab5.services.DoctorService;
import ua.lviv.iot.databases.lab5.services.WorkPositionService;

@RestController
@RequestMapping("/api/databases/lab5/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final WorkPositionService workPositionService;
    private final DoctorDtoAssembler doctorDtoAssembler;
    private final WorkPositionDtoAssembler workPositionDtoAssembler;

    @Autowired
    public DoctorController(DoctorService doctorService, WorkPositionService workPositionService, DoctorDtoAssembler doctorDtoAssembler, WorkPositionDtoAssembler workPositionDtoAssembler) {
        this.doctorService = doctorService;
        this.workPositionService = workPositionService;
        this.doctorDtoAssembler = doctorDtoAssembler;
        this.workPositionDtoAssembler = workPositionDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<DoctorDto> getAllDoctors(){
        return doctorDtoAssembler.toCollectionModel(doctorService.getAll());
    }

    @GetMapping("/{id}")
    public DoctorDto getDoctorById(@PathVariable int id){
        return doctorDtoAssembler.toModel(doctorService.getById(id));
    }

    @PostMapping("/")
    public DoctorDto createDoctor(@RequestBody DoctorEntity doctor){
        return doctorDtoAssembler.toModel(doctorService.create(doctor));
    }

    @PutMapping("/{id}")
    public DoctorDto updateDoctor(@PathVariable int id, @RequestBody DoctorEntity doctor){
        return doctorDtoAssembler.toModel(doctorService.updateById(id, doctor));
    }

    @DeleteMapping("/{id}")
    public void deleteDoctorById(@PathVariable int id){
        doctorService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllDoctors(){
        doctorService.deleteAll();
    }

    @GetMapping("/{id}/positions")
    public CollectionModel<WorkPositionDto> getAllPositionsByDoctorId(@PathVariable int id){
        return workPositionDtoAssembler.toCollectionModel(workPositionService.getWorkPositionEntitiesByDoctorsId(id));
    }

    @GetMapping("/count-max-salary")
    public int countMaxSalaryAmongDoctors(){
        return doctorService.maxDoctorsSalary();
    }


}
