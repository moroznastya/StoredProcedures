package ua.lviv.iot.databases.lab5.entities.many_to_many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ua.lviv.iot.databases.lab5.entities.DoctorEntity;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consultation")
@Data
@RequiredArgsConstructor
public class ConsultationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "conclusion")
    private String conclusion;
}
