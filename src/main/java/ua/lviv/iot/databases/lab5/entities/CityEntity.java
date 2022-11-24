package ua.lviv.iot.databases.lab5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
@Data
@RequiredArgsConstructor
public class CityEntity {
    @Id
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_name")
    private RegionEntity region;
}
