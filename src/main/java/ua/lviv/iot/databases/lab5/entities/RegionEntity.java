package ua.lviv.iot.databases.lab5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
@Data
@RequiredArgsConstructor
public class RegionEntity {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "country_name")
    private String country;
}
