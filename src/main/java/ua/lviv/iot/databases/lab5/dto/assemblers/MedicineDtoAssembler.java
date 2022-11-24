package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.MedicineController;
import ua.lviv.iot.databases.lab5.controllers.RegionController;
import ua.lviv.iot.databases.lab5.dto.MedicineDto;
import ua.lviv.iot.databases.lab5.dto.RegionDto;
import ua.lviv.iot.databases.lab5.entities.MedicineEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MedicineDtoAssembler implements RepresentationModelAssembler<MedicineEntity, MedicineDto> {
    @Override
    public MedicineDto toModel(MedicineEntity entity) {
        MedicineDto medicineDto = MedicineDto.builder()
                .name(entity.getName())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicineController.class)
                .getMedicineById(medicineDto.getName())).withSelfRel();

        medicineDto.add(selfLink);

        return medicineDto;
    }

    @Override
    public CollectionModel<MedicineDto> toCollectionModel(Iterable<? extends MedicineEntity> entities) {
        CollectionModel<MedicineDto> medicineDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(MedicineController.class).getAllMedicine()).withSelfRel();
        medicineDtos.add(selfLink);
        return medicineDtos;
    }
}
