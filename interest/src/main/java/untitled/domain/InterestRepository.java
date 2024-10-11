package untitled.domain;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "interests", path = "interests")
public interface InterestRepository extends PagingAndSortingRepository<Interest, Long> {
    
    // phone으로 검색
    List<Interest> findByPhone(String phone);
    
    // matchedsalesman으로 검색
    List<Interest> findByMatchedsalesman(String matchedsalesman);
    
    // projectname으로 검색
    List<Interest> findByProjectname(String projectname);
}