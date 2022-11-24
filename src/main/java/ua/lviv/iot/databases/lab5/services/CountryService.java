package ua.lviv.iot.databases.lab5.services;

import ua.lviv.iot.databases.lab5.entities.CountryEntity;

public interface CountryService extends GeneralService<CountryEntity, String> {
    void insertTenRowsInCountry();

    void createTablesCountriesNames();
}
