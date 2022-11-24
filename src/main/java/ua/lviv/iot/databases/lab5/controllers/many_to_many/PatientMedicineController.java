package ua.lviv.iot.databases.lab5.controllers.many_to_many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.assemblers.many_to_many.PatientMedicineDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.many_to_many.PatientMedicineDto;
import ua.lviv.iot.databases.lab5.entities.many_to_many.PatientMedicineEntity;
import ua.lviv.iot.databases.lab5.services.many_to_many.PatientMedicineService;

import java.util.List;

@RestController
@RequestMapping("/api/databases/lab5/treatment")
public class PatientMedicineController {
    private final PatientMedicineService patientMedicineService;
    private final PatientMedicineDtoAssembler patientMedicineDtoAssembler;

    @Autowired
    public PatientMedicineController(PatientMedicineService patientMedicineService, PatientMedicineDtoAssembler patientMedicineDtoAssembler) {
        this.patientMedicineService = patientMedicineService;
        this.patientMedicineDtoAssembler = patientMedicineDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<PatientMedicineDto> getAllPatientMedicine(){
        return patientMedicineDtoAssembler.toCollectionModel(patientMedicineService.getAll());
    }

    @GetMapping("/{id}")
    public PatientMedicineDto getPatientMedicineById(@PathVariable int id){
        return patientMedicineDtoAssembler.toModel(patientMedicineService.getById(id));
    }

    @PostMapping("/")
    public PatientMedicineDto createPatientMedicine(@RequestBody PatientMedicineEntity patientMedicine){
        return patientMedicineDtoAssembler.toModel(patientMedicineService.create(patientMedicine));
    }

    @PutMapping("/{id}")
    public PatientMedicineDto updatePatientMedicine(@PathVariable int id, @RequestBody PatientMedicineEntity patientMedicine){
        return patientMedicineDtoAssembler.toModel(patientMedicineService.updateById(id, patientMedicine));
    }

    @DeleteMapping("/{id}")
    public void deletePatientMedicine(@PathVariable int id){
        patientMedicineService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllPatientMedicine(){
        patientMedicineService.deleteAll();
    }

    @GetMapping("/patients/{id}")
    public CollectionModel<PatientMedicineDto> getPatientMedicineEntitiesByPatientId(@PathVariable int id){
        return patientMedicineDtoAssembler.toCollectionModel(patientMedicineService.getPatientMedicineEntitiesByPatientId(id));
    }

    @GetMapping("/medicine/{id}")
    public CollectionModel<PatientMedicineDto> getPatientMedicineEntitiesByMedicineName(@PathVariable String id){
        return patientMedicineDtoAssembler.toCollectionModel(patientMedicineService.getPatientMedicineEntitiesByMedicineName(id));
    }
}
