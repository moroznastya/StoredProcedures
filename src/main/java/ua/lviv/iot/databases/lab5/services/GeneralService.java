package ua.lviv.iot.databases.lab5.services;

import java.util.List;

public interface GeneralService<T, ID> {
    List<T> getAll();
    T getById(ID id);
    T create(T item);
    T updateById(ID id, T item);
    void deleteById(ID id);
    void deleteAll();
}
