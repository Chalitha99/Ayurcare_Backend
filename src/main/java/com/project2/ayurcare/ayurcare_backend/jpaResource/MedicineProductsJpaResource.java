package com.project2.ayurcare.ayurcare_backend.jpaResource;


import com.project2.ayurcare.ayurcare_backend.DTO.MedicaluserDTO;
import com.project2.ayurcare.ayurcare_backend.DTO.MedicineProductsDTO;
import com.project2.ayurcare.ayurcare_backend.Service.MedicineProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineProductsJpaResource {

    @Autowired
    private MedicineProductsService medicineProductsService;


    @PostMapping("/create")
    public MedicineProductsDTO createProduct(@RequestBody MedicineProductsDTO medicineProductsDTO) {
        return medicineProductsService.createProduct(medicineProductsDTO);
    }

    @GetMapping("/list")
    public List<MedicineProductsDTO> retrieveProducts() {

        return medicineProductsService.getAllProducts();
    }

//    @PutMapping("/update/{productId}")
//    public MedicineProductsDTO updateProduct(@PathVariable String productId, @RequestBody MedicineProductsDTO medicineProductsDTO) {
//        return medicineProductsService.updateProduct(productId, medicineProductsDTO);
//    }

    @PutMapping("/update/{productId}")
    public MedicineProductsDTO updateProduct(@PathVariable("productId") String productId, @RequestBody MedicineProductsDTO medicineProductsDTO) {
        System.out.println("Updating Product: " + productId + " with data: " + medicineProductsDTO);
        return medicineProductsService.updateProduct(productId, medicineProductsDTO);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        medicineProductsService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
