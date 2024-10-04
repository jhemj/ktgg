package untitled.domain;

import untitled.domain.ConsultationCreated;
import untitled.domain.ConsultationEdited;
import untitled.domain.ConsultationProgressed;
import untitled.ConsultationApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Consultation_table")
@Data

//<<< DDD / Aggregate Root
public class Consultation  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String projectname;
    
    
    
    
    private String phone;
    
    
    
    
    private String customername;
    
    
    
    
    private String matchedsalesman;
    
    
    
    
    private Date consultationdate;
    
    
    
    
    private Long memo;
    
    
    
    
    private String step;

    @PostPersist
    public void onPostPersist(){


        ConsultationCreated consultationCreated = new ConsultationCreated(this);
        consultationCreated.publishAfterCommit();



        ConsultationEdited consultationEdited = new ConsultationEdited(this);
        consultationEdited.publishAfterCommit();



        ConsultationProgressed consultationProgressed = new ConsultationProgressed(this);
        consultationProgressed.publishAfterCommit();

    
    }

    public static ConsultationRepository repository(){
        ConsultationRepository consultationRepository = ConsultationApplication.applicationContext.getBean(ConsultationRepository.class);
        return consultationRepository;
    }



    public void createConsultation(){
        //implement business logic here:
        
        ConsultationCreated consultationCreated = new ConsultationCreated(this);
        consultationCreated.publishAfterCommit();
        
        
    }
    public void editConsultation(){
        //implement business logic here:
        
        ConsultationEdited consultationEdited = new ConsultationEdited(this);
        consultationEdited.publishAfterCommit();
        
        
    }
    public void progressConsultation(){
        //implement business logic here:
        
        ConsultationProgressed consultationProgressed = new ConsultationProgressed(this);
        consultationProgressed.publishAfterCommit();
        
        
        untitled.external.ConsultationQuery consultationQuery = new untitled.external.ConsultationQuery();
        ConsultationApplication.applicationContext
            .getBean(untitled.external.Service.class)
            .( consultationQuery);
    }

//<<< Clean Arch / Port Method
    public static void editSalesman(SalesmanMatched salesmanMatched){
        
        //implement business logic here:

        /** Example 1:  new item 
        Consultation consultation = new Consultation();
        repository().save(consultation);

        ConsultationEdited consultationEdited = new ConsultationEdited(consultation);
        consultationEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(salesmanMatched.get???()).ifPresent(consultation->{
            
            consultation // do something
            repository().save(consultation);

            ConsultationEdited consultationEdited = new ConsultationEdited(consultation);
            consultationEdited.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
