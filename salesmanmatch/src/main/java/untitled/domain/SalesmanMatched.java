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
    private String sourceType; // 추가된 필드

    public SalesmanMatched(SalesmanMatch aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.originId = aggregate.getOriginId();
        this.phone = aggregate.getPhone();
        this.matchedsalesman = aggregate.getMatchedsalesman();
        this.sourceType = aggregate.getSourceType(); // 설정
    }
}