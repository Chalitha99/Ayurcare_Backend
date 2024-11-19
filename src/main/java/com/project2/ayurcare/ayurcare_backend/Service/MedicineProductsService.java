package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.MedicaluserDTO;
import com.project2.ayurcare.ayurcare_backend.DTO.MedicineProductsDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
import com.project2.ayurcare.ayurcare_backend.entity.MedicineProducts;
import com.project2.ayurcare.ayurcare_backend.exceptions.ResourceNotFoundException;
import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
import com.project2.ayurcare.ayurcare_backend.repository.MedicineProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineProductsService {

    @Autowired
    private MedicineProductsRepository medicineProductsRepository;

    @Autowired
    private ModelMapper modelMapperConfig;

    public MedicineProductsDTO createProduct(MedicineProductsDTO medicineProductsDTO) {
        MedicineProducts medicineProducts = modelMapperConfig.map(medicineProductsDTO, MedicineProducts.class);
        // Ensure the ID is null for new entries
        medicineProducts.setProductId(null); // Corrected method name
        MedicineProducts savedProduct = medicineProductsRepository.save(medicineProducts);
        return modelMapperConfig.map(savedProduct, MedicineProductsDTO.class);
    }

    public List<MedicineProductsDTO> getAllProducts() {
        return medicineProductsRepository.findAll().stream()
                .map(products -> modelMapperConfig.map(products, MedicineProductsDTO.class))
                .collect(Collectors.toList());
    }


    public MedicineProductsDTO updateProduct(String productId, MedicineProductsDTO medicineProductsDTO) {
        MedicineProducts existingProduct = medicineProductsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found with id: " + productId));

        // Update only the fields of the existing user without changing the ID
        existingProduct.setProductName(medicineProductsDTO.getProductName());
        existingProduct.setProductType(medicineProductsDTO.getProductType());
        existingProduct.setProductUnits(medicineProductsDTO.getProductUnits());
        existingProduct.setLastUpdate(medicineProductsDTO.getLastUpdate());


        // Save the updated user
        MedicineProducts updatedProduct = medicineProductsRepository.save(existingProduct);
        return modelMapperConfig.map(updatedProduct, MedicineProductsDTO.class);
    }

    public void deleteProduct(String productId) {
        medicineProductsRepository.deleteById(productId);
    }
}
