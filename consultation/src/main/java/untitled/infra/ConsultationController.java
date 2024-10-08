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

    // 기존의 생성, 수정, 진행 단계 변경 메서드는 그대로 유지합니다.
}