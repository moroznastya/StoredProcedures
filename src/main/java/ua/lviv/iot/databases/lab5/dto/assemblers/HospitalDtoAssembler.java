package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.DataController;
import ua.lviv.iot.databases.lab5.controllers.HospitalController;
import ua.lviv.iot.databases.lab5.controllers.PatientController;
import ua.lviv.iot.databases.lab5.dto.HospitalDto;
import ua.lviv.iot.databases.lab5.dto.PatientDto;
import ua.lviv.iot.databases.lab5.entities.HospitalEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HospitalDtoAssembler implements RepresentationModelAssembler<HospitalEntity, HospitalDto> {
    @Override
    public HospitalDto toModel(HospitalEntity entity) {
        HospitalDto hospitalDto = HospitalDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .city(entity.getCity().getName())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatientController.class)
                .getPatientById(hospitalDto.getId())).withSelfRel();
        Link patients = linkTo(methodOn(HospitalController.class)
                .getPatientEntitiesByHospitalId(entity.getId())).withRel("patients");
        Link doctors = linkTo(methodOn(HospitalController.class)
                .getDoctorEntitiesByHospitalId(entity.getId())).withRel("doctors");

        hospitalDto.add(selfLink);
        hospitalDto.add(patients);
        hospitalDto.add(doctors);

        return hospitalDto;
    }

    @Override
    public CollectionModel<HospitalDto> toCollectionModel(Iterable<? extends HospitalEntity> entities) {
        CollectionModel<HospitalDto> hospitalDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(HospitalController.class).getAllHospitals()).withSelfRel();
        hospitalDtos.add(selfLink);
        return hospitalDtos;
    }
}
