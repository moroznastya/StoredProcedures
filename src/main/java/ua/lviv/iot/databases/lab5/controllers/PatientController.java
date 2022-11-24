package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.DataDto;
import ua.lviv.iot.databases.lab5.dto.DiagnosisDto;
import ua.lviv.iot.databases.lab5.dto.PatientDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.DataDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.DiagnosisDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.PatientDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;
import ua.lviv.iot.databases.lab5.services.DataService;
import ua.lviv.iot.databases.lab5.services.DiagnosisService;
import ua.lviv.iot.databases.lab5.services.PatientService;

@RestController
@RequestMapping("/api/databases/lab5/patients")
public class PatientController {
    private final PatientService patientService;
    private final DiagnosisService diagnosisService;
    private final DataService dataService;
    private final PatientDtoAssembler patientDtoAssembler;
    private final DiagnosisDtoAssembler diagnosisDtoAssembler;
    private final DataDtoAssembler dataDtoAssembler;

    @Autowired
    public PatientController(PatientService patientService, DiagnosisService diagnosisService, DataService dataService, PatientDtoAssembler patientDtoAssembler, DiagnosisDtoAssembler diagnosisDtoAssembler, DataDtoAssembler dataDtoAssembler) {
        this.patientService = patientService;
        this.diagnosisService = diagnosisService;
        this.dataService = dataService;
        this.patientDtoAssembler = patientDtoAssembler;
        this.diagnosisDtoAssembler = diagnosisDtoAssembler;
        this.dataDtoAssembler = dataDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<PatientDto> getAllPatients(){
        return patientDtoAssembler.toCollectionModel(patientService.getAll());
    }

    @GetMapping("/{id}")
    public PatientDto getPatientById(@PathVariable int id){
        return patientDtoAssembler.toModel(patientService.getById(id));
    }

    @PostMapping("/")
    public PatientDto createPatient(@RequestBody PatientEntity patient){
        return patientDtoAssembler.toModel(patientService.create(patient));
    }

    @PutMapping("/{id}")
    public PatientDto updatePatient(@PathVariable int id, @RequestBody PatientEntity patient){
        return patientDtoAssembler.toModel(patientService.updateById(id, patient));
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable int id){
        patientService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllPatients(){
        patientService.deleteAll();
    }

    @GetMapping("/{id}/diagnoses")
    public CollectionModel<DiagnosisDto> getDiagnosisEntitiesByPatientsId(@PathVariable int id){
        return diagnosisDtoAssembler.toCollectionModel(diagnosisService.getDiagnosisEntitiesByPatientsId(id));
    }

    @GetMapping("/{id}/data")
    public DataDto getPatientEntitiesByDataId(@PathVariable int id){
        return dataDtoAssembler.toModel(dataService.getDataEntityByPatientId(id));
    }



}
