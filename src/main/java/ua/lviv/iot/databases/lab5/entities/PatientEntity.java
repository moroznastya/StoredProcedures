package ua.lviv.iot.databases.lab5.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ua.lviv.iot.databases.lab5.entities.many_to_many.ConsultationEntity;
import ua.lviv.iot.databases.lab5.entities.many_to_many.PatientMedicineEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patient")
@Data
@RequiredArgsConstructor
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "registration_date")
    private LocalDate regDate;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalEntity hospital;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data_id")
    private DataEntity data;

    @ManyToMany
    @JoinTable(
            name = "patient_diagnosis",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_name")
    )
    private List<DiagnosisEntity> diagnoses;
}
