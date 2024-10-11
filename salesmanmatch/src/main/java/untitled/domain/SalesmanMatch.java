package untitled.domain;

import javax.persistence.*;
import lombok.Data;
import untitled.SalesmanmatchApplication;
import untitled.domain.SalesmanMatched;

@Entity
@Table(name = "SalesmanMatch_table")
@Data
public class SalesmanMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "origin_id")
    private Long originId;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "matched_salesman", nullable = false)
    private String matchedsalesman;

    @Column(name = "source_type") // 추가된 필드
    private String sourceType;

    @PostPersist
    public void onPostPersist() {
        // SalesmanMatched 이벤트에 consultationId 추가
        SalesmanMatched salesmanMatched = new SalesmanMatched(this, this.originId);
        salesmanMatched.publishAfterCommit();
    }

    public static SalesmanMatchRepository repository() {
        return SalesmanmatchApplication.applicationContext.getBean(SalesmanMatchRepository.class);
    }

    // InterestCreated 이벤트 처리
    public static void matchSalesman(InterestCreated interestCreated) {
        String phone = interestCreated.getPhone();
        String matchedSalesman = determineSalesman(phone);

        SalesmanMatch salesmanMatch = new SalesmanMatch();
        salesmanMatch.setPhone(phone);
        salesmanMatch.setMatchedsalesman(matchedSalesman);
        salesmanMatch.setOriginId(interestCreated.getId());
        salesmanMatch.setSourceType("InterestCreated"); // 이벤트 출처 설정

        // 저장 및 이벤트 발행
        repository().save(salesmanMatch);
    }

    // ConsultationCreated 이벤트 처리
    public static void matchSalesman(ConsultationCreated consultationCreated) {
        String phone = consultationCreated.getPhone();
        String matchedSalesman = determineSalesman(phone);

        SalesmanMatch salesmanMatch = new SalesmanMatch();
        salesmanMatch.setPhone(phone);
        salesmanMatch.setMatchedsalesman(matchedSalesman);
        salesmanMatch.setOriginId(consultationCreated.getId());
        salesmanMatch.setSourceType("ConsultationCreated"); // 이벤트 출처 설정

        // 저장 및 이벤트 발행
        repository().save(salesmanMatch);
    }

    // 전화번호를 통해 세일즈맨을 결정하는 로직
    private static String determineSalesman(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone number is null or empty");
        }

        char lastChar = phone.charAt(phone.length() - 1);
        if (!Character.isDigit(lastChar)) {
            throw new IllegalArgumentException("Phone number does not end with a digit");
        }

        int lastDigit = Character.getNumericValue(lastChar);
        switch (lastDigit) {
            case 0: return "이진혁";
            case 1: return "오예성";
            case 2: return "김민수";
            case 3: return "박철수";
            case 4: return "최영업";
            case 5: return "최민주";
            case 6: return "진정하";
            case 7: return "김수완";
            case 8: return "이주은";
            case 9: return "최효원";
            default:
                throw new IllegalArgumentException("Invalid last digit in phone number");
        }
    }
}