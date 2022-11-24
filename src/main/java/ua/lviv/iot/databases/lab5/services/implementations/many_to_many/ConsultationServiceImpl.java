package ua.lviv.iot.databases.lab5.services.implementations.many_to_many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.many_to_many.ConsultationEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.DoctorRepository;
import ua.lviv.iot.databases.lab5.repositories.PatientRepository;
import ua.lviv.iot.databases.lab5.repositories.many_to_many.ConsultationRepository;
import ua.lviv.iot.databases.lab5.services.many_to_many.ConsultationService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<ConsultationEntity> getAll() {
        return consultationRepository.findAll();
    }

    @Override
    public ConsultationEntity getById(Integer id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation doesn't exist!"));
    }

    @Override
    @Transactional
    public ConsultationEntity create(ConsultationEntity item) {
        return consultationRepository.save(item);
    }

    @Override
    @Transactional
    public ConsultationEntity updateById(Integer id, ConsultationEntity item) {
        ConsultationEntity consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation doesn't exist!"));

        consultation.setPatient(item.getPatient());
        consultation.setDoctor(item.getDoctor());
        consultation.setDate(item.getDate());
        consultation.setConclusion(item.getConclusion());

        return consultationRepository.save(consultation);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation doesn't exist!"));

        consultationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        consultationRepository.deleteAll();
    }

    @Override
    public List<ConsultationEntity> getConsultationEntitiesByPatientId(int patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient doesn't exist!"));

        return consultationRepository.findConsultationEntitiesByPatientId(patientId);
    }

    @Override
    public List<ConsultationEntity> getConsultationEntitiesByDoctorId(int doctorId) {
        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exist!"));

        return consultationRepository.findConsultationEntitiesByDoctorId(doctorId);
    }
}
