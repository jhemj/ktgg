package untitled.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import untitled.domain.Consultation;
import untitled.domain.ConsultationRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationRepository consultationRepository;

    // 모든 Consultation 조회
    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultations = (List<Consultation>) consultationRepository.findAll();
        return ResponseEntity.ok(consultations);
    }

    // phone으로 Consultation 조회
    @GetMapping(params = "phone")
    public ResponseEntity<List<Consultation>> getConsultationsByPhone(@RequestParam String phone) {
        List<Consultation> consultations = consultationRepository.findByPhone(phone);
        return ResponseEntity.ok(consultations);
    }

    // matchedsalesman으로 Consultation 조회
    @GetMapping(params = "matchedsalesman")
    public ResponseEntity<List<Consultation>> getConsultationsByMatchedSalesman(@RequestParam String matchedsalesman) {
        List<Consultation> consultations = consultationRepository.findByMatchedsalesman(matchedsalesman);
        return ResponseEntity.ok(consultations);
    }

    // projectname으로 Consultation 조회
    @GetMapping(params = "projectname")
    public ResponseEntity<List<Consultation>> getConsultationsByProjectName(@RequestParam String projectname) {
        List<Consultation> consultations = consultationRepository.findByProjectname(projectname);
        return ResponseEntity.ok(consultations);
    }

    // ID로 Consultation 조회 (기존 기능)
    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            return ResponseEntity.ok(consultation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 새로운 Consultation 생성
    @PostMapping
    public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultation) {
        Consultation savedConsultation = consultationRepository.save(consultation);
        return ResponseEntity.ok(savedConsultation);
    }

    // Consultation 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id, @RequestBody Consultation updatedConsultation) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            Consultation existingConsultation = consultation.get();
            existingConsultation.setConsultationdate(updatedConsultation.getConsultationdate());
            existingConsultation.setMemo(updatedConsultation.getMemo());
            existingConsultation.setStep(updatedConsultation.getStep());
            Consultation savedConsultation = consultationRepository.save(existingConsultation);
            return ResponseEntity.ok(savedConsultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Consultation 상태 변경 (단계별로)
    @PatchMapping("/{id}/step")
    public ResponseEntity<Consultation> updateConsultationStep(@PathVariable Long id, @RequestParam String step) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            Consultation existingConsultation = consultation.get();
            existingConsultation.setStep(step);
            Consultation savedConsultation = consultationRepository.save(existingConsultation);
            return ResponseEntity.ok(savedConsultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}