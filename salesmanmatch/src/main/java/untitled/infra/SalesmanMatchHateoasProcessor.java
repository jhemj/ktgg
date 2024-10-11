package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class SalesmanMatchHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<SalesmanMatch>> {

    @Override
    public EntityModel<SalesmanMatch> process(
        EntityModel<SalesmanMatch> model
    ) {
        return model;
    }
}
