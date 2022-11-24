package ua.lviv.iot.databases.lab5.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.databases.lab5.entities.DoctorEntity;
import ua.lviv.iot.databases.lab5.exceptions.ResourceNotFoundException;
import ua.lviv.iot.databases.lab5.repositories.DoctorRepository;
import ua.lviv.iot.databases.lab5.repositories.HospitalRepository;
import ua.lviv.iot.databases.lab5.repositories.WorkPositionRepository;
import ua.lviv.iot.databases.lab5.services.DoctorService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final WorkPositionRepository workPositionRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, WorkPositionRepository workPositionRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.workPositionRepository = workPositionRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<DoctorEntity> getDoctorEntitiesByPositionsName(String positionId) {
        workPositionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position doesn't exist!"));

        return doctorRepository.findDoctorEntitiesByPositionsName(positionId);
    }

    @Override
    public List<DoctorEntity> getDoctorEntitiesByHospitalId(int hospitalId) {
        hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital doesn't exist!"));

        return doctorRepository.findDoctorEntitiesByHospitalId(hospitalId);
    }

    @Override
    public int maxDoctorsSalary() {
        return doctorRepository.maxDoctorsSalary();
    }

    @Override
    public List<DoctorEntity> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorEntity getById(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exist!"));
    }

    @Override
    @Transactional
    public DoctorEntity create(DoctorEntity item) {
        return doctorRepository.save(item);
    }

    @Override
    @Transactional
    public DoctorEntity updateById(Integer id, DoctorEntity item) {
        DoctorEntity doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exist!"));

        doctor.setSurname(item.getSurname());
        doctor.setName(item.getName());
        doctor.setExperience(item.getExperience());
        doctor.setHireDate(item.getHireDate());
        doctor.setHospital(item.getHospital());
        doctor.setSalary(item.getSalary());

        return doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exist!"));

        doctorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        doctorRepository.deleteAll();
    }
}
