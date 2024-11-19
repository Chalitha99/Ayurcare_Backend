package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.PaymentDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
import com.project2.ayurcare.ayurcare_backend.entity.Payment;
import com.project2.ayurcare.ayurcare_backend.entity.TransactionDetails;
import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
import com.project2.ayurcare.ayurcare_backend.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MedicalUserRepository medicaluserRepository;

    @Autowired
    private ModelMapper modelMapperConfig;

    private static final String KEY = "rzp_test_Sz3BaYEiUZGzdL";
    private static final String KEY_SECRET = "DYG8scAfeuzag1734ApKYxTa";
    private static final String CURRENCY = "LKR";


    public TransactionDetails createTransaction(Double amount){

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", amount * 100);
            jsonObject.put("currency", CURRENCY);

            RazorpayClient razorpayClient = new RazorpayClient(KEY,KEY_SECRET);

            Order order = razorpayClient.orders.create(jsonObject);

            return prepareTransactionDetails(order);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    private TransactionDetails prepareTransactionDetails(Order order){
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");

        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);
        return transactionDetails;
    }

    public void depositTransactionDetails(PaymentDTO paymentDTO) {
        try {
            // Fetch the medical user entity based on the provided medical user ID
            Optional<Medicaluser> optionalMedicaluser = medicaluserRepository.findById(paymentDTO.getMedicaluserId());
            if (!optionalMedicaluser.isPresent()) {
                throw new IllegalArgumentException("Medical user not found");
            }

            Medicaluser medicaluser = optionalMedicaluser.get();

            // Map PaymentDTO to Payment entity
            Payment payment = modelMapperConfig.map(paymentDTO, Payment.class);
            payment.setMedicaluser(medicaluser);

            // Save the payment entity to the database
            paymentRepository.save(payment);

            System.out.println("Payment saved successfully: " + payment);

        } catch (Exception e) {
            System.out.println("Error saving payment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int countAllPayment() {
        return paymentRepository.countAllPayment();
    }

}
