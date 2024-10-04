package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.InterestApplication;
import untitled.domain.InterestCreated;
import untitled.domain.InterestUpdated;

@Entity
@Table(name = "Interest_table")
@Data
//<<< DDD / Aggregate Root
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectname;

    private Date date;

    private String phone;

    private String customername;

    private String matchedsalesman;

    private Boolean interest;

    @PostPersist
    public void onPostPersist() {
        InterestUpdated interestUpdated = new InterestUpdated(this);
        interestUpdated.publishAfterCommit();

        InterestCreated interestCreated = new InterestCreated(this);
        interestCreated.publishAfterCommit();
    }

    public static InterestRepository repository() {
        InterestRepository interestRepository = InterestApplication.applicationContext.getBean(
            InterestRepository.class
        );
        return interestRepository;
    }

    public void updateInterest() {
        //implement business logic here:

        InterestUpdated interestUpdated = new InterestUpdated(this);
        interestUpdated.publishAfterCommit();
    }

    public void createInterest() {
        //implement business logic here:

        InterestCreated interestCreated = new InterestCreated(this);
        interestCreated.publishAfterCommit();
    }

    //<<< Clean Arch / Port Method
    public static void editSalesman(SalesmanMatched salesmanMatched) {
        //implement business logic here:

        /** Example 1:  new item 
        Interest interest = new Interest();
        repository().save(interest);

        InterestUpdated interestUpdated = new InterestUpdated(interest);
        interestUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(salesmanMatched.get???()).ifPresent(interest->{
            
            interest // do something
            repository().save(interest);

            InterestUpdated interestUpdated = new InterestUpdated(interest);
            interestUpdated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
