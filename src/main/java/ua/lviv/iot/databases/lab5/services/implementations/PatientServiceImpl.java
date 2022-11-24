package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.PatientEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.DataRepository;
import ua.lviv.iot.databases.lab5.repositories.DiagnosisRepository;
import ua.lviv.iot.databases.lab5.repositories.HospitalRepository;
import ua.lviv.iot.databases.lab5.repositories.PatientRepository;
import ua.lviv.iot.databases.lab5.repositories.many_to_many.PatientMedicineRepository;
import ua.lviv.iot.databases.lab5.services.PatientService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DataRepository dataRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMedicineRepository patientMedicineRepository, DiagnosisRepository diagnosisRepository, DataRepository dataRepository, HospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.dataRepository = dataRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<PatientEntity> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public PatientEntity getById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient doesn't exist!"));
    }

    @Override
    @Transactional
    public PatientEntity create(PatientEntity item) {
        return patientRepository.save(item);
    }

    @Override
    @Transactional
    public PatientEntity updateById(Integer id, PatientEntity item) {
        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient doesn't exist!"));

        patient.setSurname(item.getSurname());
        patient.setName(item.getName());
        patient.setRegDate(item.getRegDate());
        patient.setData(item.getData());
        patient.setHospital(item.getHospital());

        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient doesn't exist!"));

        patientRepository.deleteById(id);
    }

    @Override@Transactional

    public void deleteAll() {
        patientRepository.deleteAll();
    }


    @Override
    public PatientEntity getPatientEntityByDataId(int dataId) {
        dataRepository.findById(dataId)
                .orElseThrow(() -> new ResourceNotFoundException("Data doesn't exist!"));

        return patientRepository.findPatientEntityByDataId(dataId);
    }

    @Override
    public List<PatientEntity> getPatientEntitiesByDiagnosesName(String diagnosisName) {
        diagnosisRepository.findById(diagnosisName)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnosis doesn't exist!"));

        return patientRepository.findPatientEntitiesByDiagnosesName(diagnosisName);
    }

    @Override
    public List<PatientEntity> getPatientEntitiesByHospitalId(int hospitalId) {
        hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital doesn't exist!"));

        return patientRepository.findPatientEntitiesByHospitalId(hospitalId);
    }




}
