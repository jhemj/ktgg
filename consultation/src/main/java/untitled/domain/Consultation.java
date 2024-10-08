package untitled.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import untitled.ConsultationApplication;
import untitled.domain.ConsultationCreated;
import untitled.domain.ConsultationUpdated;

@Entity
@Table(name = "Consultation_table")
@Data
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectname;
    private Date consultationdate;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private String memo;
    private String step;

    @PostPersist
    public void onPostPersist() {
        ConsultationCreated consultationCreated = new ConsultationCreated(this);
        consultationCreated.publishAfterCommit();

        System.out.println("ConsultationCreated 이벤트 발행");
    }

    @PostUpdate
    public void onPostUpdate() {
        ConsultationUpdated consultationUpdated = new ConsultationUpdated(this);
        consultationUpdated.publishAfterCommit();

        System.out.println("ConsultationUpdated 이벤트 발행");
    }

    public static ConsultationRepository repository() {
        ConsultationRepository consultationRepository = ConsultationApplication.applicationContext.getBean(
            ConsultationRepository.class
        );
        return consultationRepository;
    }

    /**
     * SalesmanMatched 이벤트를 처리하여 matchedsalesman 필드 업데이트
     */
    public static void editSalesman(SalesmanMatched salesmanMatched) {
        if (salesmanMatched.getSourceType() == SourceType.CONSULTATION_CREATED) {
            repository().findById(salesmanMatched.getOriginId()).ifPresent(consultation -> {
                consultation.setMatchedsalesman(salesmanMatched.getMatchedSalesman());
                repository().save(consultation);

                ConsultationUpdated consultationUpdated = new ConsultationUpdated(consultation);
                consultationUpdated.publishAfterCommit();
            });
        }
    }
}