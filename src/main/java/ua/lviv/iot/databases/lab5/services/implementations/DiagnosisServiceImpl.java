package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.DiagnosisEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.DiagnosisRepository;
import ua.lviv.iot.databases.lab5.repositories.PatientRepository;
import ua.lviv.iot.databases.lab5.services.DiagnosisService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, PatientRepository patientRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<DiagnosisEntity> getDiagnosisEntitiesByPatientsId(int patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient doesn't exist!"));

       return diagnosisRepository.findDiagnosisEntitiesByPatientsId(patientId);
    }

    @Override
    public List<DiagnosisEntity> getAll() {
        return diagnosisRepository.findAll();
    }

    @Override
    public DiagnosisEntity getById(String s) {
        return diagnosisRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnosis doesn't exist!"));
    }

    @Override
    @Transactional
    public DiagnosisEntity create(DiagnosisEntity item) {
        return diagnosisRepository.save(item);
    }

    @Override
    @Transactional
    public DiagnosisEntity updateById(String s, DiagnosisEntity item) {
        DiagnosisEntity diagnosis = diagnosisRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnosis doesn't exist!"));

        diagnosis.setName(item.getName());

        return diagnosisRepository.save(diagnosis);
    }

    @Override
    @Transactional
    public void deleteById(String s) {
        diagnosisRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnosis doesn't exist!"));

        diagnosisRepository.deleteById(s);
    }

    @Override
    @Transactional
    public void deleteAll() {
        diagnosisRepository.deleteAll();
    }
}
