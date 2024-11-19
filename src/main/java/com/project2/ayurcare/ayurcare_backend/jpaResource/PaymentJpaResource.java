package com.project2.ayurcare.ayurcare_backend.jpaResource;

import com.project2.ayurcare.ayurcare_backend.DTO.PaymentDTO;
import com.project2.ayurcare.ayurcare_backend.Service.PaymentService;
import com.project2.ayurcare.ayurcare_backend.entity.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentJpaResource {

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/createTransaction/{amount}"})
    public TransactionDetails createTransaction(@PathVariable(name="amount") Double amount){
        return paymentService.createTransaction(amount);
    }

    @PostMapping("/payment/placeeOrder/true")
    public PaymentDTO Deposite(@RequestBody PaymentDTO paymentDTO){
        paymentService.depositTransactionDetails(paymentDTO);
        return paymentDTO;
    }

    @GetMapping("payment/count")
    public int getPatientCount() {
        return paymentService.countAllPayment();
    }

}
