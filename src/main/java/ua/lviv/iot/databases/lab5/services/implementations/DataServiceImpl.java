package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.DataEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.DataRepository;
import ua.lviv.iot.databases.lab5.repositories.PatientRepository;
import ua.lviv.iot.databases.lab5.services.DataService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    private final DataRepository dataRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DataServiceImpl(DataRepository dataRepository, PatientRepository patientRepository) {
        this.dataRepository = dataRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<DataEntity> getAll() {
        return dataRepository.findAll();
    }

    @Override
    public DataEntity getById(Integer id) {
        return dataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data doesn't exist!"));
    }

    @Override
    @Transactional
    public DataEntity create(DataEntity item) {
        return dataRepository.save(item);
    }

    @Override
    @Transactional
    public DataEntity updateById(Integer id, DataEntity item) {
        DataEntity data = dataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data doesn't exist!"));

        data.setTemperature(item.getTemperature());
        data.setSystPressure(item.getSystPressure());
        data.setDiastPressure(item.getDiastPressure());
        data.setHeartRate(item.getHeartRate());
        data.setSpecialNotes(item.getSpecialNotes());

        return dataRepository.save(data);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        dataRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        dataRepository.deleteAll();
    }

    @Override
    public DataEntity getDataEntityByPatientId(int patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient doesn't exist!"));

        return dataRepository.findDataEntityByPatientId(patientId);
    }
}
