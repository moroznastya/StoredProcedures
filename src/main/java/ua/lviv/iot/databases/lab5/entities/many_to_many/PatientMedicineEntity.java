package ua.lviv.iot.databases.lab5.entities.many_to_many;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ua.lviv.iot.databases.lab5.entities.MedicineEntity;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;

import javax.persistence.*;

@Entity
@Table(name = "patient_medicine")
@Data
@RequiredArgsConstructor
public class PatientMedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "medicine_name")
    private MedicineEntity medicine;

    @Column(name = "special_notes")
    private String specialNotes;
}
