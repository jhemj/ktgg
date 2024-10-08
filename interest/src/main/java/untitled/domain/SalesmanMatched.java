package untitled.domain;

import lombok.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
@NoArgsConstructor
public class SalesmanMatched extends AbstractEvent {

    private Long id;                 // SalesmanMatch 엔티티의 id
    private Long originId;           // 원본 이벤트의 id (Interest의 id)
    private String phone;            // 고객의 전화번호
    private String matchedsalesman;  // 매칭된 영업사원 이름
    private String sourceType;       // 이벤트 출처 (e.g., "InterestCreated", "ConsultationCreated")

    public SalesmanMatched(Long id, Long originId, String phone, String matchedSalesman, String sourceType) {
        super();
        this.id = id;
        this.originId = originId;
        this.phone = phone;
        this.matchedsalesman = matchedsalesman;
        this.sourceType = sourceType;
    }


}