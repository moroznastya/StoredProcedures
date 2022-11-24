package ua.lviv.iot.databases.lab5.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hospital")
@Data
@RequiredArgsConstructor
public class HospitalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_name")
    private CityEntity city;

    @OneToMany(mappedBy = "hospital")
    private List<PatientEntity> patients = new ArrayList<>();

    @OneToMany(mappedBy = "hospital")
    private List<DoctorEntity> doctors = new ArrayList<>();
}
