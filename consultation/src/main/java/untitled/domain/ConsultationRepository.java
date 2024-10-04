package untitled.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "consultations",
    path = "consultations"
)
public interface ConsultationRepository
    extends PagingAndSortingRepository<Consultation, Long> {}
