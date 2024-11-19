//package com.project2.ayurcare.ayurcare_backend.jpaResource;
//
//import java.util.List;
//
//import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.project2.ayurcare.ayurcare_backend.DTO.MedicaluserDTO;
//import com.project2.ayurcare.ayurcare_backend.Service.MedicaluserService;
//
//@RestController
//@RequestMapping("/users")
//public class medicaluserJpaResource {
//
//	 @Autowired
//	    private MedicaluserService medicaluserService;
//
//	    @GetMapping
//	    public List<MedicaluserDTO> retrieveUsers() {
//	        return medicaluserService.getAllUsers();
//	    }
//
//	    @GetMapping("/id/{medical_id}")
//	    public MedicaluserDTO retrieveUser(@PathVariable String medical_id) {
//	        return medicaluserService.getUserById(medical_id);
//	    }
//
//	    @GetMapping("/email/{medicaluserEmail}")
//	    public MedicaluserDTO retrieveUserByEmail(@PathVariable String medicaluserEmail) {
//	        return medicaluserService.getUserByEmail(medicaluserEmail);
//	    }
//
//	    @DeleteMapping("/delete/{medical_id}")
//	    public ResponseEntity<Void> deleteUser(@PathVariable String medical_id) {
//	        medicaluserService.deleteUser(medical_id);
//	        return ResponseEntity.noContent().build();
//	    }
//
//	    @PostMapping("/create")
//	    public MedicaluserDTO createUser(@RequestBody MedicaluserDTO medicaluserDTO) {
//	        return medicaluserService.createUser(medicaluserDTO);
//	    }
//
//	    @PutMapping("/update/{medical_id}")
//	    public MedicaluserDTO updateUser(@PathVariable String medical_id, @RequestBody MedicaluserDTO medicaluserDTO) {
//	        return medicaluserService.updateUser(medical_id, medicaluserDTO);
//	    }
//
//		@GetMapping("/registered/USERcount")
//		public int registeredUserCount() {
//			return medicaluserService.registeredPatientCount();
//		}
//
//		@GetMapping("/lastFiveUsers")
//		public List<MedicaluserDTO> lastFiveUsers() throws Exception {
//			return medicaluserService.lastFiveUsers();
//		}
//
//
//	@GetMapping("/patient/{patientId}")
//	public ResponseEntity<MedicaluserDTO> getMedicaluserByPatientId(@PathVariable String patientId) {
//		MedicaluserDTO medicaluserDTO = medicaluserService.getMedicaluserByPatientId(patientId);
//		return ResponseEntity.ok(medicaluserDTO);
//	}
//}




package com.project2.ayurcare.ayurcare_backend.jpaResource;

import java.util.List;

import com.project2.ayurcare.ayurcare_backend.DTO.MedicaluserDTO;
import com.project2.ayurcare.ayurcare_backend.Service.MedicaluserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class medicaluserJpaResource {

	@Autowired
	private MedicaluserService medicaluserService;

	@GetMapping
	public List<MedicaluserDTO> retrieveUsers() {
		return medicaluserService.getAllUsers();
	}

	@GetMapping("/id/{medical_id}")
	public ResponseEntity<MedicaluserDTO> retrieveUser(@PathVariable String medical_id) {
		MedicaluserDTO user = medicaluserService.getUserById(medical_id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/email/{medicaluserEmail}")
	public ResponseEntity<MedicaluserDTO> retrieveUserByEmail(@PathVariable String medicaluserEmail) {
		MedicaluserDTO user = medicaluserService.getUserByEmail(medicaluserEmail);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/delete/{medical_id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String medical_id) {
		medicaluserService.deleteUser(medical_id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/create")
	public ResponseEntity<MedicaluserDTO> createUser(@RequestBody MedicaluserDTO medicaluserDTO) {
		MedicaluserDTO createdUser = medicaluserService.createUser(medicaluserDTO);
		return ResponseEntity.ok(createdUser);
	}

	@PutMapping("/update/{medical_id}")
	public ResponseEntity<MedicaluserDTO> updateUser(@PathVariable String medical_id, @RequestBody MedicaluserDTO medicaluserDTO) {
		MedicaluserDTO updatedUser = medicaluserService.updateUser(medical_id, medicaluserDTO);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping("/registered/USERcount")
	public ResponseEntity<Integer> registeredUserCount() {
		int count = medicaluserService.registeredPatientCount();
		return ResponseEntity.ok(count);
	}

	@GetMapping("/lastFiveUsers")
	public ResponseEntity<List<MedicaluserDTO>> lastFiveUsers() throws Exception {
		List<MedicaluserDTO> users = medicaluserService.lastFiveUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<MedicaluserDTO> getMedicaluserByPatientId(@PathVariable String patientId) {
		MedicaluserDTO medicaluserDTO = medicaluserService.getMedicaluserByPatientId(patientId);
		return ResponseEntity.ok(medicaluserDTO);
	}
}
