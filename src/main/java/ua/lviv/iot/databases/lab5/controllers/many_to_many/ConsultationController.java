package ua.lviv.iot.databases.lab5.controllers.many_to_many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.assemblers.many_to_many.ConsultationDtoAssembler;
import ua.lviv.iot.databases.lab5.dto.many_to_many.ConsultationDto;
import ua.lviv.iot.databases.lab5.entities.many_to_many.ConsultationEntity;
import ua.lviv.iot.databases.lab5.services.many_to_many.ConsultationService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/databases/lab5/consultations")
public class ConsultationController {
    private final ConsultationService consultationService;
    private final ConsultationDtoAssembler consultationDtoAssembler;

    @Autowired
    public ConsultationController(ConsultationService consultationService, ConsultationDtoAssembler consultationDtoAssembler) {
        this.consultationService = consultationService;
        this.consultationDtoAssembler = consultationDtoAssembler;
    }

    @GetMapping("/")
    public CollectionModel<ConsultationDto> getAllConsultations(){
        return consultationDtoAssembler.toCollectionModel(consultationService.getAll());
    }

    @GetMapping("/{id}")
    public ConsultationDto getConsultationById(@PathVariable int id){
        return consultationDtoAssembler.toModel(consultationService.getById(id));
    }

    @PostMapping("/")
    public ConsultationDto createConsultation(@RequestBody ConsultationEntity consultation){
        return consultationDtoAssembler.toModel(consultationService.create(consultation));
    }

    @PutMapping("/{id}")
    public ConsultationDto updateConsultation(@PathVariable int id, @RequestBody ConsultationEntity consultation){
        return consultationDtoAssembler.toModel(consultationService.updateById(id, consultation));
    }

    @DeleteMapping("/{id}")
    public void deleteConsultation(@PathVariable int id){
        consultationService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllConsultations(){
        consultationService.deleteAll();
    }

    @GetMapping("/patients/{id}")
    public CollectionModel<ConsultationDto> getConsultationEntitiesByPatientId(@PathVariable int id) {
        return consultationDtoAssembler.toCollectionModel(consultationService.getConsultationEntitiesByPatientId(id));
    }

    @GetMapping("/doctors/{id}")
    public CollectionModel<ConsultationDto> getConsultationEntitiesByDoctorId(@PathVariable int id) {
        return consultationDtoAssembler.toCollectionModel(consultationService.getConsultationEntitiesByDoctorId(id));
    }
}
