package ua.lviv.iot.databases.lab5.dto.assemblers.many_to_many;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.CityController;
import ua.lviv.iot.databases.lab5.controllers.many_to_many.ConsultationController;
import ua.lviv.iot.databases.lab5.dto.CityDto;
import ua.lviv.iot.databases.lab5.dto.many_to_many.ConsultationDto;
import ua.lviv.iot.databases.lab5.entities.many_to_many.ConsultationEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConsultationDtoAssembler implements RepresentationModelAssembler<ConsultationEntity, ConsultationDto> {
    @Override
    public ConsultationDto toModel(ConsultationEntity entity) {
        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(entity.getId())
                .patient(entity.getPatient().getId())
                .doctor(entity.getDoctor().getId())
                .date(entity.getDate())
                .conclusion(entity.getConclusion())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultationController.class)
                .getConsultationById(entity.getId())).withSelfRel();

        consultationDto.add(selfLink);

        return consultationDto;
    }

    @Override
    public CollectionModel<ConsultationDto> toCollectionModel(Iterable<? extends ConsultationEntity> entities) {
        CollectionModel<ConsultationDto> consultationDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ConsultationController.class).getAllConsultations()).withSelfRel();
        consultationDtos.add(selfLink);
        return consultationDtos;
    }
}
