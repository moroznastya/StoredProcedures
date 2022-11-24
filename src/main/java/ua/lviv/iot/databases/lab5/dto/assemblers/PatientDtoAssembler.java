package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.DataController;
import ua.lviv.iot.databases.lab5.controllers.DiagnosisController;
import ua.lviv.iot.databases.lab5.controllers.PatientController;
import ua.lviv.iot.databases.lab5.controllers.RegionController;
import ua.lviv.iot.databases.lab5.dto.PatientDto;
import ua.lviv.iot.databases.lab5.dto.RegionDto;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PatientDtoAssembler implements RepresentationModelAssembler<PatientEntity, PatientDto> {
    @Override
    public PatientDto toModel(PatientEntity entity) {
        PatientDto patientDto = PatientDto.builder()
                .id(entity.getId())
                .surname(entity.getSurname())
                .name(entity.getName())
                .registrationDate(entity.getRegDate())
                .data(entity.getData().getId())
                .hospital(entity.getHospital().getId())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatientController.class)
                .getPatientById(patientDto.getId())).withSelfRel();
        Link patientData = linkTo(methodOn(DataController.class)
                .getDataById(entity.getId())).withRel("patientData");

        patientDto.add(selfLink);
        patientDto.add(patientData);

        return patientDto;
    }

    @Override
    public CollectionModel<PatientDto> toCollectionModel(Iterable<? extends PatientEntity> entities) {
        CollectionModel<PatientDto> patientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PatientController.class).getAllPatients()).withSelfRel();
        patientDtos.add(selfLink);
        return patientDtos;
    }
}
