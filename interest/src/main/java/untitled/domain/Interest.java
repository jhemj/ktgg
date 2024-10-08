package untitled.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import untitled.InterestApplication;
import untitled.domain.InterestCreated;
import untitled.domain.InterestUpdated;

@Entity
@Table(name = "Interest_table")
@Data
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectname;

    private Date date;

    private String phone;

    private String customername;

    private String matchedsalesman;

    private Boolean interest;

    @PostPersist
    public void onPostPersist() {
        InterestCreated interestCreated = new InterestCreated(this);
        interestCreated.publishAfterCommit();

        System.out.println("InterestCreated 이벤트 발행");
    }

    @PostUpdate
    public void onPostUpdate() {
        InterestUpdated interestUpdated = new InterestUpdated(this);
        interestUpdated.publishAfterCommit();

        System.out.println("InterestUpdated 이벤트 발행");
    }

    public static InterestRepository repository() {
        InterestRepository interestRepository = InterestApplication.applicationContext.getBean(
            InterestRepository.class
        );
        return interestRepository;
    }

    /**
     * SalesmanMatched 이벤트를 처리하여 matchedsalesman 필드 업데이트
     */
    public static void editSalesman(SalesmanMatched salesmanMatched) {
        if ("INTEREST_CREATED".equals(salesmanMatched.getSourceType())) {
            // originId를 사용하여 Interest 엔티티를 조회합니다.
            repository().findById(salesmanMatched.getOriginId()).ifPresent(interest -> {
                interest.setMatchedsalesman(salesmanMatched.getMatchedsalesman());
                repository().save(interest);

                InterestUpdated interestUpdated = new InterestUpdated(interest);
                interestUpdated.publishAfterCommit();
            });
        }
    }
}