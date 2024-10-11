package untitled.domain;

import lombok.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
@NoArgsConstructor
public class SalesmanMatched extends AbstractEvent {

    private Long id;
    private Long originId;
    private String phone;
    private String matchedsalesman;
    private String sourceType;
    private Long consultationId;  // 추가: 특정 Consultation 객체의 ID

    public SalesmanMatched(SalesmanMatch aggregate, Long consultationId) {
        super(aggregate);
        this.id = aggregate.getId();
        this.originId = aggregate.getOriginId();
        this.phone = aggregate.getPhone();
        this.matchedsalesman = aggregate.getMatchedsalesman();
        this.sourceType = aggregate.getSourceType();
        this.consultationId = consultationId;  // 매칭된 Consultation의 ID 설정
    }
}