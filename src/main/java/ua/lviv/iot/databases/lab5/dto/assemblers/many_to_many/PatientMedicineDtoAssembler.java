package ua.lviv.iot.databases.lab5.dto.assemblers.many_to_many;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.CityController;
import ua.lviv.iot.databases.lab5.controllers.many_to_many.PatientMedicineController;
import ua.lviv.iot.databases.lab5.dto.CityDto;
import ua.lviv.iot.databases.lab5.dto.PatientDto;
import ua.lviv.iot.databases.lab5.dto.many_to_many.PatientMedicineDto;
import ua.lviv.iot.databases.lab5.entities.many_to_many.PatientMedicineEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PatientMedicineDtoAssembler implements RepresentationModelAssembler<PatientMedicineEntity, PatientMedicineDto> {
    @Override
    public PatientMedicineDto toModel(PatientMedicineEntity entity) {
        PatientMedicineDto patientMedicineDto = PatientMedicineDto.builder()
                .id(entity.getId())
                .patient(entity.getPatient().getId())
                .medicine(entity.getMedicine().getName())
                .specialNotes(entity.getSpecialNotes())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatientMedicineController.class)
                .getPatientMedicineById(entity.getId())).withSelfRel();

        patientMedicineDto.add(selfLink);

        return patientMedicineDto;
    }

    @Override
    public CollectionModel<PatientMedicineDto> toCollectionModel(Iterable<? extends PatientMedicineEntity> entities) {
        CollectionModel<PatientMedicineDto> patientMedicineDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PatientMedicineController.class).getAllPatientMedicine()).withSelfRel();
        patientMedicineDtos.add(selfLink);
        return patientMedicineDtos;
    }
}
