package ua.lviv.iot.databases.lab5.dto.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.databases.lab5.controllers.CountryController;
import ua.lviv.iot.databases.lab5.controllers.DataController;
import ua.lviv.iot.databases.lab5.controllers.DiagnosisController;
import ua.lviv.iot.databases.lab5.controllers.PatientController;
import ua.lviv.iot.databases.lab5.dto.CountryDto;
import ua.lviv.iot.databases.lab5.dto.DataDto;
import ua.lviv.iot.databases.lab5.dto.DiagnosisDto;
import ua.lviv.iot.databases.lab5.entities.CountryEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CountryDtoAssembler implements RepresentationModelAssembler<CountryEntity, CountryDto> {
    @Override
    public CountryDto toModel(CountryEntity entity) {
        CountryDto countryDto = CountryDto.builder()
                .name(entity.getName())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CountryController.class)
                .getCountryById(countryDto.getName())).withSelfRel();

        countryDto.add(selfLink);

        return countryDto;
    }

    @Override
    public CollectionModel<CountryDto> toCollectionModel(Iterable<? extends CountryEntity> entities) {
        CollectionModel<CountryDto> countryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();
        countryDtos.add(selfLink);
        return countryDtos;
    }
}
