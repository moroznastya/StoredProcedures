package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.DiagnosisController;
import ua.lviv.iot.databases.lab5.controllers.RegionController;
import ua.lviv.iot.databases.lab5.dto.DiagnosisDto;
import ua.lviv.iot.databases.lab5.dto.RegionDto;
import ua.lviv.iot.databases.lab5.entities.DiagnosisEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiagnosisDtoAssembler implements RepresentationModelAssembler<DiagnosisEntity, DiagnosisDto> {
    @Override
    public DiagnosisDto toModel(DiagnosisEntity entity) {
        DiagnosisDto diagnosisDto = DiagnosisDto.builder()
                .name(entity.getName())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DiagnosisController.class)
                .getDiagnosisById(diagnosisDto.getName())).withSelfRel();

        diagnosisDto.add(selfLink);

        return diagnosisDto;
    }

    @Override
    public CollectionModel<DiagnosisDto> toCollectionModel(Iterable<? extends DiagnosisEntity> entities) {
        CollectionModel<DiagnosisDto> diagnosisDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DiagnosisController.class).getAllDiagnoses()).withSelfRel();
        diagnosisDtos.add(selfLink);
        return diagnosisDtos;
    }
}
