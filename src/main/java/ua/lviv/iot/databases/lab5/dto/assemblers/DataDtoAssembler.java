package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.DataController;
import ua.lviv.iot.databases.lab5.controllers.PatientController;
import ua.lviv.iot.databases.lab5.controllers.RegionController;
import ua.lviv.iot.databases.lab5.dto.DataDto;
import ua.lviv.iot.databases.lab5.dto.RegionDto;
import ua.lviv.iot.databases.lab5.entities.DataEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DataDtoAssembler implements RepresentationModelAssembler<DataEntity, DataDto> {

    @Override
    public DataDto toModel(DataEntity entity) {
        DataDto dataDto = DataDto.builder()
                .id(entity.getId())
                .temperature(entity.getTemperature())
                .systolicPressure(entity.getDiastPressure())
                .systolicPressure(entity.getSystPressure())
                .heartRate(entity.getHeartRate())
                .specialNotes(entity.getSpecialNotes())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DataController.class)
                .getDataById(dataDto.getId())).withSelfRel();
        Link patientLink = linkTo(methodOn(PatientController.class)
                .getPatientById(entity.getId())).withRel("patient");

        dataDto.add(selfLink);
        dataDto.add(patientLink);

        return dataDto;
    }

    @Override
    public CollectionModel<DataDto> toCollectionModel(Iterable<? extends DataEntity> entities) {
        CollectionModel<DataDto> dataDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DataController.class).getAllData()).withSelfRel();
        dataDtos.add(selfLink);
        return dataDtos;
    }
}
