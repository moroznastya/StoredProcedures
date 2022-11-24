package ua.lviv.iot.databases.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.databases.lab5.dto.MedicineDto;
import ua.lviv.iot.databases.lab5.dto.assemblers.MedicineDtoAssembler;
import ua.lviv.iot.databases.lab5.entities.MedicineEntity;
import ua.lviv.iot.databases.lab5.services.MedicineService;

import java.util.List;

@RestController
@RequestMapping("/api/databases/lab5/medicine")
public class MedicineController {
    private final MedicineService medicineService;
    private final MedicineDtoAssembler medicineDtoAssembler;

    @Autowired
    public MedicineController(MedicineService medicineService, MedicineDtoAssembler medicineDtoAssembler) {
        this.medicineService = medicineService;
        this.medicineDtoAssembler = medicineDtoAssembler;
    }
    @GetMapping("/")
    public CollectionModel<MedicineDto> getAllMedicine(){
        return medicineDtoAssembler.toCollectionModel(medicineService.getAll());
    }

    @GetMapping("/{id}")
    public MedicineDto getMedicineById(@PathVariable String id){
        return medicineDtoAssembler.toModel(medicineService.getById(id));
    }

    @PostMapping("/")
    public MedicineDto createMedicine(@RequestBody MedicineEntity medicine){
        return medicineDtoAssembler.toModel(medicineService.create(medicine));
    }

//    @PutMapping("/{id}")
//    public MedicineEntity updateMedicine(@PathVariable String id, @RequestBody MedicineEntity medicine){
//        return medicineService.updateById(id, medicine);
//    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable String id){
        medicineService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteAllMedicine(){
        medicineService.deleteAll();
    }

}
