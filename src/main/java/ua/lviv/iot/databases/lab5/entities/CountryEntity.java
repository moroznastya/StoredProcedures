package ua.lviv.iot.databases.lab5.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@Data
@RequiredArgsConstructor
public class CountryEntity {
    @Id
    @Column(name = "name")
    private String name;
}
