package untitled.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import untitled.domain.Interest;
import untitled.domain.InterestRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interests")
public class InterestController {

    @Autowired
    private InterestRepository interestRepository;

    // 모든 Interest 조회
    @GetMapping
    public ResponseEntity<List<Interest>> getAllInterests() {
        List<Interest> interests = (List<Interest>) interestRepository.findAll();
        return ResponseEntity.ok(interests);
    }

    // phone으로 Interest 조회
    @GetMapping(params = "phone")
    public ResponseEntity<List<Interest>> getInterestsByPhone(@RequestParam String phone) {
        List<Interest> interests = interestRepository.findByPhone(phone);
        return ResponseEntity.ok(interests);
    }

    // matchedsalesman으로 Interest 조회
    @GetMapping(params = "matchedsalesman")
    public ResponseEntity<List<Interest>> getInterestsByMatchedSalesman(@RequestParam String matchedsalesman) {
        List<Interest> interests = interestRepository.findByMatchedsalesman(matchedsalesman);
        return ResponseEntity.ok(interests);
    }

    // projectname으로 Interest 조회
    @GetMapping(params = "projectname")
    public ResponseEntity<List<Interest>> getInterestsByProjectName(@RequestParam String projectname) {
        List<Interest> interests = interestRepository.findByProjectname(projectname);
        return ResponseEntity.ok(interests);
    }

    // ID로 Interest 조회
    @GetMapping("/{id}")
    public ResponseEntity<Interest> getInterestById(@PathVariable Long id) {
        Optional<Interest> interest = interestRepository.findById(id);
        if (interest.isPresent()) {
            return ResponseEntity.ok(interest.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 새로운 Interest 생성 (추가된 POST 메서드)
    @PostMapping
    public ResponseEntity<Interest> createInterest(@RequestBody Interest interest) {
        Interest savedInterest = interestRepository.save(interest);
        return ResponseEntity.ok(savedInterest);
    }
}
