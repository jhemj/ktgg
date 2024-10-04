package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ConsultationProgressed extends AbstractEvent {

    private Long id;
}
