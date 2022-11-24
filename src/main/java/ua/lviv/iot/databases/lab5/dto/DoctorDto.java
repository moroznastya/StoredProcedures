package ua.lviv.iot.databases.lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ua.lviv.iot.databases.lab5.entities.HospitalEntity;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "", collectionRelation = "")
public class DoctorDto extends RepresentationModel<DoctorDto> {
    private int id;
    private String surname;
    private String name;
    private int yearsOfExperience;
    private LocalDate hireDate;
    private int hospital;
    private int salary;
}
