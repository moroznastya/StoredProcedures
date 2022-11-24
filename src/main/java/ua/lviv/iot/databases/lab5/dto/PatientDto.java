package ua.lviv.iot.databases.lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "", collectionRelation = "")
public class PatientDto extends RepresentationModel<PatientDto> {
    private int id;
    private String surname;
    private String name;
    private LocalDate registrationDate;
    private int hospital;
    private int data;
}
