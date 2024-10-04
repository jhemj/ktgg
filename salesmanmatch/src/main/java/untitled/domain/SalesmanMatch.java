package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.SalesmanmatchApplication;
import untitled.domain.SalesmanMatched;

@Entity
@Table(name = "SalesmanMatch_table")
@Data
//<<< DDD / Aggregate Root
public class SalesmanMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        SalesmanMatched salesmanMatched = new SalesmanMatched(this);
        salesmanMatched.publishAfterCommit();
    }

    public static SalesmanMatchRepository repository() {
        SalesmanMatchRepository salesmanMatchRepository = SalesmanmatchApplication.applicationContext.getBean(
            SalesmanMatchRepository.class
        );
        return salesmanMatchRepository;
    }

    //<<< Clean Arch / Port Method
    public static void matchSalesman(InterestCreated interestCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        SalesmanMatch salesmanMatch = new SalesmanMatch();
        repository().save(salesmanMatch);

        */

        /** Example 2:  finding and process
        
        repository().findById(interestCreated.get???()).ifPresent(salesmanMatch->{
            
            salesmanMatch // do something
            repository().save(salesmanMatch);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void matchSalesman(ConsultationCreated consultationCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        SalesmanMatch salesmanMatch = new SalesmanMatch();
        repository().save(salesmanMatch);

        */

        /** Example 2:  finding and process
        
        repository().findById(consultationCreated.get???()).ifPresent(salesmanMatch->{
            
            salesmanMatch // do something
            repository().save(salesmanMatch);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
