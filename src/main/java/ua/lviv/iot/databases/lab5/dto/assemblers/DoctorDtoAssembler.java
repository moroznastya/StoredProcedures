package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.DoctorController;
import ua.lviv.iot.databases.lab5.controllers.MedicineController;
import ua.lviv.iot.databases.lab5.controllers.PatientController;
import ua.lviv.iot.databases.lab5.dto.DoctorDto;
import ua.lviv.iot.databases.lab5.dto.MedicineDto;
import ua.lviv.iot.databases.lab5.dto.PatientDto;
import ua.lviv.iot.databases.lab5.entities.DoctorEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DoctorDtoAssembler implements RepresentationModelAssembler<DoctorEntity, DoctorDto> {
    @Override
    public DoctorDto toModel(DoctorEntity entity) {
        DoctorDto doctorDto = DoctorDto.builder()
                .id(entity.getId())
                .surname(entity.getSurname())
                .name(entity.getName())
                .hireDate(entity.getHireDate())
                .yearsOfExperience(entity.getExperience())
                .salary(entity.getSalary())
                .hospital(entity.getHospital().getId())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoctorController.class)
                .getDoctorById(entity.getId())).withSelfRel();

        doctorDto.add(selfLink);

        return doctorDto;
    }

    @Override
    public CollectionModel<DoctorDto> toCollectionModel(Iterable<? extends DoctorEntity> entities) {
        CollectionModel<DoctorDto> doctorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DoctorController.class).getAllDoctors()).withSelfRel();
        doctorDtos.add(selfLink);
        return doctorDtos;
    }
}
