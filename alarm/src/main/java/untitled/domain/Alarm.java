package untitled.domain;

import untitled.domain.AlarmCreated;
import untitled.domain.StaffAlarmed;
import untitled.domain.AlarmEdited;
import untitled.domain.CustomerAlarmed;
import untitled.domain.SalesmanAlarmed;
import untitled.AlarmApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Alarm_table")
@Data

//<<< DDD / Aggregate Root
public class Alarm  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String eventType;
    
    
    
    
    private String eventData;
    
    
    
    
    private String salesmanId;
    
    
    
    @Embedded
    private Timestamp timestamp;

    @PostPersist
    public void onPostPersist(){


        AlarmCreated alarmCreated = new AlarmCreated(this);
        alarmCreated.publishAfterCommit();



        StaffAlarmed staffAlarmed = new StaffAlarmed(this);
        staffAlarmed.publishAfterCommit();



        AlarmEdited alarmEdited = new AlarmEdited(this);
        alarmEdited.publishAfterCommit();



        CustomerAlarmed customerAlarmed = new CustomerAlarmed(this);
        customerAlarmed.publishAfterCommit();



        SalesmanAlarmed salesmanAlarmed = new SalesmanAlarmed(this);
        salesmanAlarmed.publishAfterCommit();

    
    }

    public static AlarmRepository repository(){
        AlarmRepository alarmRepository = AlarmApplication.applicationContext.getBean(AlarmRepository.class);
        return alarmRepository;
    }



    public void getAlarmByStaff(){
        //implement business logic here:
        
        StaffAlarmed staffAlarmed = new StaffAlarmed(this);
        staffAlarmed.publishAfterCommit();
        
        
    }
    public void getAlarmByCustomer(){
        //implement business logic here:
        
        CustomerAlarmed customerAlarmed = new CustomerAlarmed(this);
        customerAlarmed.publishAfterCommit();
        
        
        untitled.external.AlarmQuery alarmQuery = new untitled.external.AlarmQuery();
        AlarmApplication.applicationContext
            .getBean(untitled.external.Service.class)
            .( alarmQuery);
    }
    public void getAlarmBySalesman(){
        //implement business logic here:
        
        SalesmanAlarmed salesmanAlarmed = new SalesmanAlarmed(this);
        salesmanAlarmed.publishAfterCommit();
        
        
    }

//<<< Clean Arch / Port Method
    public static void createAlarm(ConsultationCreated consultationCreated){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmCreated alarmCreated = new AlarmCreated(alarm);
        alarmCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(consultationCreated.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmCreated alarmCreated = new AlarmCreated(alarm);
            alarmCreated.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void createAlarm(ProjectCreated projectCreated){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmCreated alarmCreated = new AlarmCreated(alarm);
        alarmCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(projectCreated.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmCreated alarmCreated = new AlarmCreated(alarm);
            alarmCreated.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void createAlarm(InterestCreated interestCreated){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmCreated alarmCreated = new AlarmCreated(alarm);
        alarmCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(interestCreated.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmCreated alarmCreated = new AlarmCreated(alarm);
            alarmCreated.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void editAlarm(ProjectStarted projectStarted){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmEdited alarmEdited = new AlarmEdited(alarm);
        alarmEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(projectStarted.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmEdited alarmEdited = new AlarmEdited(alarm);
            alarmEdited.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void editAlarm(ProjectEnded projectEnded){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmEdited alarmEdited = new AlarmEdited(alarm);
        alarmEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(projectEnded.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmEdited alarmEdited = new AlarmEdited(alarm);
            alarmEdited.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void editAlarm(ProjectEdited projectEdited){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmEdited alarmEdited = new AlarmEdited(alarm);
        alarmEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(projectEdited.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmEdited alarmEdited = new AlarmEdited(alarm);
            alarmEdited.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void editAlarm(InterestUpdated interestUpdated){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmEdited alarmEdited = new AlarmEdited(alarm);
        alarmEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(interestUpdated.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmEdited alarmEdited = new AlarmEdited(alarm);
            alarmEdited.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void editAlarm(ConsultationEdited consultationEdited){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmEdited alarmEdited = new AlarmEdited(alarm);
        alarmEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(consultationEdited.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmEdited alarmEdited = new AlarmEdited(alarm);
            alarmEdited.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void editAlarm(ConsultationProgressed consultationProgressed){
        
        //implement business logic here:

        /** Example 1:  new item 
        Alarm alarm = new Alarm();
        repository().save(alarm);

        AlarmEdited alarmEdited = new AlarmEdited(alarm);
        alarmEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(consultationProgressed.get???()).ifPresent(alarm->{
            
            alarm // do something
            repository().save(alarm);

            AlarmEdited alarmEdited = new AlarmEdited(alarm);
            alarmEdited.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
