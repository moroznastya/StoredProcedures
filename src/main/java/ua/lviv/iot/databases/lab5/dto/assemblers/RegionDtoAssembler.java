package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.CountryController;
import ua.lviv.iot.databases.lab5.controllers.DataController;
import ua.lviv.iot.databases.lab5.controllers.RegionController;
import ua.lviv.iot.databases.lab5.controllers.WorkPositionController;
import ua.lviv.iot.databases.lab5.dto.RegionDto;
import ua.lviv.iot.databases.lab5.dto.WorkPositionDto;
import ua.lviv.iot.databases.lab5.entities.RegionEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionDtoAssembler implements RepresentationModelAssembler<RegionEntity, RegionDto> {
    @Override
    public RegionDto toModel(RegionEntity entity) {
        RegionDto regionDto = RegionDto.builder()
                .name(entity.getName())
                .country(entity.getCountry())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RegionController.class)
                .getRegionById(regionDto.getName())).withSelfRel();
        Link countryLink = linkTo(methodOn(CountryController.class)
                .getCountryById(entity.getCountry())).withRel("country");

        regionDto.add(selfLink);
        regionDto.add(countryLink);

        return regionDto;
    }

    @Override
    public CollectionModel<RegionDto> toCollectionModel(Iterable<? extends RegionEntity> entities) {
        CollectionModel<RegionDto> regionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RegionController.class).getAllRegions()).withSelfRel();
        regionDtos.add(selfLink);
        return regionDtos;
    }
}
