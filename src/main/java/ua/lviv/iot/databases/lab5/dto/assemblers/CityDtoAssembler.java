package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.CityController;
import ua.lviv.iot.databases.lab5.controllers.RegionController;
import ua.lviv.iot.databases.lab5.dto.CityDto;
import ua.lviv.iot.databases.lab5.dto.RegionDto;
import ua.lviv.iot.databases.lab5.entities.CityEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityDtoAssembler implements RepresentationModelAssembler<CityEntity, CityDto> {
    @Override
    public CityDto toModel(CityEntity entity) {
        CityDto cityDto = CityDto.builder()
                .name(entity.getName())
                .region(entity.getRegion().getName())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CityController.class)
                .getCityById(cityDto.getName())).withSelfRel();
        Link regionLink = linkTo(methodOn(CityController.class).getAllCities()).withRel("allCities");

        cityDto.add(selfLink);
        cityDto.add(regionLink);

        return cityDto;
    }

    @Override
    public CollectionModel<CityDto> toCollectionModel(Iterable<? extends CityEntity> entities) {
        CollectionModel<CityDto> cityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CityController.class).getAllCities()).withSelfRel();
        cityDtos.add(selfLink);
        return cityDtos;
    }
}
