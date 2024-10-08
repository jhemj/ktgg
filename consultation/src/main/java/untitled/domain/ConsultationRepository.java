package untitled.domain;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "consultations", path = "consultations")
public interface ConsultationRepository extends PagingAndSortingRepository<Consultation, Long> {

    // phone으로 검색
    List<Consultation> findByPhone(String phone);

    // matchedsalesman으로 검색
    List<Consultation> findByMatchedsalesman(String matchedsalesman);

    // projectname으로 검색
    List<Consultation> findByProjectname(String projectname);
}