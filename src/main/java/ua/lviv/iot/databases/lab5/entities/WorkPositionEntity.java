package ua.lviv.iot.databases.lab5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "work_position")
@Data
@RequiredArgsConstructor
public class WorkPositionEntity {
    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "positions")
    @JsonIgnore
    private List<DoctorEntity> doctors;
}
