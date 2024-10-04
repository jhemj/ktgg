package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ConsultationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Consultation>> {

    @Override
    public EntityModel<Consultation> process(EntityModel<Consultation> model) {
        return model;
    }
}
