package ua.lviv.iot.databases.lab5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "data")
@Data
@RequiredArgsConstructor
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "temperature_in_celsius")
    private float temperature;

    @Column(name = "systolic_pressure")
    private int systPressure;

    @Column(name = "diastolic_pressure")
    private int diastPressure;

    @Column(name = "heart_rate_in_minute")
    private int heartRate;

    @Column(name = "specialNotes")
    private String specialNotes;

    @OneToOne(mappedBy = "data")
    private PatientEntity patient;
}
