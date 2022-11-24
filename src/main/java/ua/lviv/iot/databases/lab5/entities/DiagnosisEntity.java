package ua.lviv.iot.databases.lab5.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diagnosis")
@Data
@RequiredArgsConstructor
public class DiagnosisEntity {
    @Id
    @Column(name = "name")
    private String name;

//    @JsonBackReference
    @ManyToMany(mappedBy = "diagnoses")
    private List<PatientEntity> patients;

    public DiagnosisEntity(String name) {
        this.name = name;
    }
}
