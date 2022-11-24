package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.DiagnosisDto;
import ua.lviv.iot.databases.lab5.dto.PatientDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.DiagnosisDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.assemblers.PatientDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.DiagnosisEntity;
import ua.lviv.iot.databases.lab5.services.DiagnosisService;
import ua.lviv.iot.databases.lab5.services.PatientService;

@RestController
@RequestMapping("api/databases/lab5/diagnoses")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;
    private final PatientService patientService;
    private final DiagnosisDtoAssembler diagnosisDtoAssembler;
    private final PatientDtoAssembler patientDtoAssembler;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService, PatientService patientService, DiagnosisDtoAssembler diagnosisDtoAssembler, PatientDtoAssembler patientDtoAssembler) {
        this.diagnosisService = diagnosisService;
        this.patientService = patientService;
        this.diagnosisDtoAssembler = diagnosisDtoAssembler;
        this.patientDtoAssembler = patientDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<DiagnosisDto> getAllDiagnoses(){
        return diagnosisDtoAssembler.toCollectionModel(diagnosisService.getAll());
    }

    @GetMapping("/{id}")
    public DiagnosisDto getDiagnosisById(@PathVariable String id){
        return diagnosisDtoAssembler.toModel(diagnosisService.getById(id));
    }

    @PostMapping("/")
    public DiagnosisDto createDiagnosis(@RequestBody DiagnosisEntity diagnosis){
        return diagnosisDtoAssembler.toModel(diagnosisService.create(diagnosis));
    }

    @PutMapping("/{id}")
    public DiagnosisDto updateDiagnosis(@PathVariable String id, @RequestBody DiagnosisEntity diagnosis){
        return diagnosisDtoAssembler.toModel(diagnosisService.updateById(id, diagnosis));
    }

    @DeleteMapping("/{id}")
    public void deleteDiagnosis(@PathVariable String id){
        diagnosisService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllDiagnoses(){
        diagnosisService.deleteAll();
    }


    @GetMapping("/{id}/patients")
    public CollectionModel<PatientDto> getPatientEntitiesByDiagnosesName(@PathVariable String id){
        return patientDtoAssembler.toCollectionModel(patientService.getPatientEntitiesByDiagnosesName(id));
    }
}
