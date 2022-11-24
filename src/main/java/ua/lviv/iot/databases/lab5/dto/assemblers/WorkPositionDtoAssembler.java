package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.WorkPositionController;
import ua.lviv.iot.databases.lab5.dto.WorkPositionDto;
import ua.lviv.iot.databases.lab5.entities.WorkPositionEntity;

import java.awt.print.Book;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WorkPositionDtoAssembler implements RepresentationModelAssembler<WorkPositionEntity, WorkPositionDto> {

    @Override
    public WorkPositionDto toModel(WorkPositionEntity entity) {
        WorkPositionDto workPositionDto = WorkPositionDto.builder()
                .name(entity.getName())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WorkPositionController.class)
                .getWorkPositionById(workPositionDto.getName())).withSelfRel();

        workPositionDto.add(selfLink);

        return workPositionDto;
    }

    @Override
    public CollectionModel<WorkPositionDto> toCollectionModel(Iterable<? extends WorkPositionEntity> entities) {
        CollectionModel<WorkPositionDto> workPositionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(WorkPositionController.class).getAllWorkPositions()).withSelfRel();
        workPositionDtos.add(selfLink);
        return workPositionDtos;
    }

//    public CollectionModel<WorkPositionDto> toCollectionModel(Iterable<? extends WorkPositionEntity> entities, Link link) {
//        CollectionModel<WorkPositionDto> workPositionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
//        workPositionDtos.add(link);
//        return workPositionDtos;
//    }

}
