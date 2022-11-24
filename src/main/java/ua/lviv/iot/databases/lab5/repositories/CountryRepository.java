package ua.lviv.iot.databases.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.databases.lab5.entities.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, String> {
    @Procedure
    String insertInCountry(String countryName);

    @Procedure
    void insertTenRowsInCountry();

    @Procedure
    void createTablesCountriesNames();
}
