package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.DoctorDto;
import ua.lviv.iot.databases.lab5.dto.HospitalDto;
import ua.lviv.iot.databases.lab5.dto.PatientDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.DoctorDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.HospitalDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.PatientDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.HospitalEntity;
import ua.lviv.iot.databases.lab5.services.DoctorService;
import ua.lviv.iot.databases.lab5.services.HospitalService;
import ua.lviv.iot.databases.lab5.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/databases/lab5/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final HospitalDtoAssembler hospitalDtoAssembler;
    private final PatientDtoAssembler patientDtoAssembler;
    private final DoctorDtoAssembler doctorDtoAssembler;

    @Autowired
    public HospitalController(HospitalService hospitalService, DoctorService doctorService, PatientService patientService, HospitalDtoAssembler hospitalDtoAssembler, PatientDtoAssembler patientDtoAssembler, DoctorDtoAssembler doctorDtoAssembler) {
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.hospitalDtoAssembler = hospitalDtoAssembler;
        this.patientDtoAssembler = patientDtoAssembler;
        this.doctorDtoAssembler = doctorDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<HospitalDto> getAllHospitals(){
        return hospitalDtoAssembler.toCollectionModel(hospitalService.getAll());
    }

    @GetMapping("/{id}")
    public HospitalDto getHospitalById(@PathVariable int id){
        return hospitalDtoAssembler.toModel(hospitalService.getById(id));
    }

    @PostMapping("/")
    public HospitalDto createHospital(@RequestBody HospitalEntity hospital){
        return hospitalDtoAssembler.toModel(hospitalService.create(hospital));
    }

    @PutMapping("/{id}")
    public HospitalDto updateHospital(@PathVariable int id, @RequestBody HospitalEntity hospital){
        return hospitalDtoAssembler.toModel(hospitalService.updateById(id, hospital));
    }

    @DeleteMapping("/{id}")
    public void deleteHospitalById(@PathVariable int id){
        hospitalService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllHospitals(){
        hospitalService.deleteAll();
    }

    @GetMapping("/{id}/patients")
    public CollectionModel<PatientDto> getPatientEntitiesByHospitalId(@PathVariable int id){
        return patientDtoAssembler.toCollectionModel(patientService.getPatientEntitiesByHospitalId(id));
    }

    @GetMapping("/{id}/doctors")
    public CollectionModel<DoctorDto> getDoctorEntitiesByHospitalId(@PathVariable int id){
        return doctorDtoAssembler.toCollectionModel(doctorService.getDoctorEntitiesByHospitalId(id));
    }


}
