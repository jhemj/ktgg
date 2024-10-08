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
    private String matchedSalesman;
    private SourceType sourceType;

    public SalesmanMatched(AbstractEvent aggregate) {
        super(aggregate);
    }
}