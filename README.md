# KT.GG

**KT.GG**는 현장에서 영업활동을 하는 동안 고객이 정부 지원사업에 대한 관심이 높다는 점에 착안하여 개발된 서비스입니다. 지원사업에 대한 체계적인 관리가 이루어지지 않아 지원사업을 놓치는 경우가 많고, 고객도 사업 진행을 원하지만 못하는 상황이 발생하고 있습니다. 또한 신규 고객을 발굴하거나 기존 미관리 고객을 방문할 때, 영업대표들이 적절한 화젯거리가 없어 어려움을 겪는 경우가 많았습니다.

**KT.GG**는 이러한 문제를 해결하기 위해 지원사업 정보를 체계적으로 관리하고, 고객이 관심 있는 사업을 저장하여 시작 시점에 알림을 받도록 하며, 최적의 영업대표와 컨설턴트를 매칭하여 상담을 진행할 수 있도록 하는 서비스입니다. 이를 통해 고객 만족도를 높이고, 영업대표들의 업무 효율성을 향상시키며, 신규 고객 발굴에 도움이 되는 **Door Open Tool**로 활용될 것입니다.

[서비스 설계 모델링은 이곳에서 확인할 수 있습니다.](https://www.msaez.io/#/180518912/storming/project)

# 목차

- [서비스 시나리오](#서비스-시나리오)
- [체크포인트](#체크포인트)
- [분석/설계](#분석설계)
- [구현](#구현)
- [운영](#운영)
- [신규 개발 조직의 추가](#신규-개발-조직의-추가)


## 기능적 요구사항

1. **Staff**는 StaffPage에서 “CreateProject”, “StartProject”, “EndProject”, “EditProject” 네 가지 기능을 수행할 수 있다.
2. “EndProject”에 의해 “ProjectEnded” 이벤트가 발생하면, “AnnualProjectCreate” 정책이 실행된다: projectname, scale, target, host, summary, link를 복사하고, year=year+1, expStartDate=StartDate를 갖는 객체를 생성한다.
3. **Customer**는 CustomerPage에서 “CreateInterest”, “UpdateInterest” 명령을 실행할 수 있다.
4. **Customer**는 ConsultationPage에서 “CreateConsultation”, “EditConsultation” 명령을 실행할 수 있다.
5. “ProjectCreated”, “ProjectStarted”, “ProjectEnded”, “ProjectEdited”, “InterestCreated”, “InterestUpdated”, “ConsultationCreated”, “ConsultationEdited” 이벤트 발생 시, **Salesman**이 접근 가능한 SalesmanPage에 알람이 전달된다. 기존 알람을 시간순으로 조회 가능하다. 단, “InterestUpdated” 및 “InterestCreated”, “ConsultationEdited”, “ConsultationCreated” 이벤트의 경우, matchedsalesman이 접속한 Salesman과 같을 경우에 확인 가능하다.
6. “ProjectStarted” 이벤트의 경우, 해당 projectname에 대해 관심 설정(interest=1) 해놓은 고객(phone)에게 알림이 전달된다.
7. **Staff**의 경우, StaffPage에서 Project별 관심 고객 인원수, 진행 중인 컨설팅 등 통계를 파악할 수 있다.
8. “ConsultationCreated” 발생 시, **Salesman**은 수락 혹은 거절할 수 있고, 수락 시 step=“accepted”, 거절 시 step=“canceled”로 변경된다. 이후 Salesman은 SalesmanPage에서 step을 “in progress”, “success”, “fail”로 수정할 수 있다.
9. Spring, PostgreSQL, 쿠버네티스, Azure 환경에서 구현한다.

## 비기능적 요구사항

1. **트랜잭션**
   - 상담 신청 시 영업대표 매칭 및 알림은 동시에 이루어져야 한다. (Sync 호출)
2. **장애격리**
   - 알람(Alarm) 기능이 수행되지 않더라도 상담 신청은 365일 24시간 받을 수 있어야 한다. (Async (event-driven), Eventual Consistency)
3. **성능**
   - 고객은 자신이 등록한 관심 사업의 진행 상황을 조회할 수 있어야 한다. (CQRS)
   - 사업이 시작되면 관심 고객에게 알림을 줄 수 있어야 한다. (Event driven)

# 체크포인트

- **분석 설계**
  - 이벤트스토밍
    - 스티커 색상별 객체의 의미를 이해하고 헥사고날 아키텍처와의 연계 설계에 적절히 반영하였는가?
    - 각 도메인 이벤트가 의미 있는 수준으로 정의되었는가?
    - 어그리게잇: Command와 Event들을 ACID 트랜잭션 단위의 Aggregate로 제대로 묶었는가?
    - 기능적 요구사항과 비기능적 요구사항을 누락 없이 반영하였는가?
  - 서브 도메인, 바운디드 컨텍스트 분리
    - 팀별 KPI와 관심사, 상이한 배포 주기 등에 따른 Sub-domain이나 Bounded Context를 적절히 분리하였고 그 분리 기준의 합리성이 충분히 설명되는가?
      - 적어도 3개 이상 서비스 분리
    - 폴리글랏 설계: 각 마이크로서비스들의 구현 목표와 기능 특성에 따른 각자의 기술 Stack과 저장소 구조를 다양하게 채택하여 설계하였는가?
    - 서비스 시나리오 중 ACID 트랜잭션이 크리티컬한 Use Case에 대하여 무리하게 서비스가 과다하게 조밀히 분리되지 않았는가?
  - 컨텍스트 매핑 / 이벤트 드리븐 아키텍처
    - 업무 중요성과 도메인 간 서열을 구분할 수 있는가? (Core, Supporting, General Domain)
    - Request-Response 방식과 이벤트 드리븐 방식을 구분하여 설계할 수 있는가?
    - 장애격리: 서포팅 서비스를 제거하여도 기존 서비스에 영향이 없도록 설계하였는가?
    - 신규 서비스를 추가하였을 때 기존 서비스의 데이터베이스에 영향이 없도록 설계(열려있는 아키텍처)할 수 있는가?
    - 이벤트와 폴리시를 연결하기 위한 Correlation-key 연결을 제대로 설계하였는가?

- **구현**
  - [DDD] 분석 단계에서의 스티커별 색상과 헥사고날 아키텍처에 따라 구현체가 매핑되게 개발되었는가?
    - Entity Pattern과 Repository Pattern을 적용하여 JPA를 통하여 데이터 접근 어댑터를 개발하였는가?
    - [헥사고날 아키텍처] REST Inbound Adaptor 이외에 gRPC 등의 Inbound Adaptor를 추가함에 있어서 도메인 모델의 손상을 주지 않고 새로운 프로토콜에 기존 구현체를 적응시킬 수 있는가?
    - 분석 단계에서의 유비쿼터스 랭귀지(업무현장에서 쓰는 용어)를 사용하여 소스코드가 서술되었는가?
  - Request-Response 방식의 서비스 중심 아키텍처 구현
    - 마이크로서비스 간 Request-Response 호출에 있어 대상 서비스를 어떠한 방식으로 찾아서 호출하였는가? (Service Discovery, REST, FeignClient)
    - 서킷 브레이커를 통하여 장애를 격리시킬 수 있는가?
  - 이벤트 드리븐 아키텍처의 구현
    - 카프카를 이용하여 Pub/Sub으로 하나 이상의 서비스가 연동되었는가?
    - Correlation-key: 각 이벤트 건(메시지)이 어떠한 폴리시를 처리할 때 어떤 건에 연결된 처리건인지를 구별하기 위한 Correlation-key 연결을 제대로 구현하였는가?
    - Message Consumer 마이크로서비스가 장애 상황에서 수신받지 못했던 기존 이벤트들을 다시 수신받아 처리하는가?
    - Scaling-out: Message Consumer 마이크로서비스의 Replica를 추가했을 때 중복 없이 이벤트를 수신할 수 있는가?
    - CQRS: Materialized View를 구현하여, 타 마이크로서비스의 데이터 원본에 접근 없이(Composite 서비스나 조인 SQL 등 없이)도 내 서비스의 화면 구성과 잦은 조회가 가능한가?
  - 폴리글랏 프로그래밍
    - 각 마이크로서비스들이 하나 이상의 각자의 기술 Stack으로 구성되었는가?
    - 각 마이크로서비스들이 각자의 저장소 구조를 자율적으로 채택하고 각자의 저장소 유형(RDB, NoSQL, File System 등)을 선택하여 구현하였는가?
  - API 게이트웨이
    - API 게이트웨이를 통하여 마이크로서비스들의 진입점을 통일할 수 있는가?

- **운영**
  - SLA 준수
    - 셀프 힐링: Liveness Probe를 통하여 어떠한 서비스의 health 상태가 지속적으로 저하됨에 따라 어떠한 임계치에서 pod가 재생되는 것을 증명할 수 있는가?
    - 서킷 브레이커, 레이트 리밋 등을 통한 장애 격리와 성능 효율을 높일 수 있는가?
    - 오토스케일러(HPA)를 설정하여 확장적 운영이 가능한가?
    - 모니터링, 알림:
  - 무정지 운영 CI/CD
    - Readiness Probe의 설정과 Rolling Update를 통하여 신규 버전이 완전히 서비스를 받을 수 있는 상태일 때 신규 버전의 서비스로 전환됨을 siege 등으로 증명
    - Contract Test: 자동화된 경계 테스트를 통하여 구현 오류나 API 계약 위반을 미리 차단 가능한가?

# 분석/설계

## AS-IS 조직 (기능별로 수직적 조직)

현재 조직은 기능별로 분리되어 있어, 프로젝트 관리, 고객 관심 관리, 상담 관리, 영업대표 매칭, 알람 관리 등이 각각 독립적으로 운영되고 있습니다. 이러한 분산된 관리로 인해 지원사업 정보를 체계적으로 관리하기 어렵고, 고객과의 효과적인 커뮤니케이션에 한계가 있습니다.

## TO-BE 조직 (도메인별로 수평적 조직)

향후 조직은 도메인별로 통합하여 운영될 예정입니다. 프로젝트 관리, 고객 관리, 영업대표 관리 등을 통합하여 효율적인 업무 처리를 목표로 합니다. 이를 통해 지원사업 정보를 체계적으로 관리하고, 고객과의 소통을 강화하여 영업 기회를 극대화할 수 있습니다.

## Event Storming 결과

- MSAEz로 모델링한 이벤트 스토밍 결과: [이벤트 스토밍 모델링](https://www.msaez.io/#/180518912/storming/project)

### 이벤트 도출

- 주요 이벤트:
  - **ProjectCreated**
  - **ProjectStarted**
  - **ProjectEnded**
  - **ProjectEdited**
  - **InterestCreated**
  - **InterestUpdated**
  - **ConsultationCreated**
  - **ConsultationEdited**
  - **SalesmanMatched**

### 부적격 이벤트 탈락

- "ProjectViewed": UI 이벤트이므로 제외
- "CustomerLoggedIn": 인증 관련 이벤트로 본 서비스의 핵심 도메인과 무관하여 제외

### 액터, 커맨드 부착하여 읽기 좋게

- 액터:
  - **Staff**
  - **Customer**
  - **Salesman**
- 커맨드:
  - **CreateProject**
  - **StartProject**
  - **EndProject**
  - **EditProject**
  - **CreateInterest**
  - **UpdateInterest**
  - **CreateConsultation**
  - **EditConsultation**
  - **MatchSalesman**


### 어그리게잇으로 묶기

- **Project Aggregate**
  - Entity: Project
  - 이벤트: ProjectCreated, ProjectStarted, ProjectEnded, ProjectEdited
- **Interest Aggregate**
  - Entity: Interest
  - 이벤트: InterestCreated, InterestUpdated
- **Consultation Aggregate**
  - Entity: Consultation
  - 이벤트: ConsultationCreated, ConsultationEdited, ConsultationProgressed
- **SalesmanMatch Aggregate**
  - Entity: SalesmanMatch
  - 이벤트: SalesmanMatched


### 폴리시 부착 및 컨텍스트 매핑

- **AnnualProjectCreate Policy**: ProjectEnded 이벤트 발생 시 실행(내년 사업 대비)
- **MatchSalesman**: InterestCreated, ConsultationCreated 이벤트 발생 시 실행하여 적절한 영업대표 매칭
- **EditSalesman**: SalesmanMatched 이벤트 발생 시 실행하여 담당 영업대표 저장


### 완성된 1차 모형

모든 요구사항을 커버하도록 도메인 모델을 완성하였습니다.

### 비기능 요구사항에 대한 검증

- **트랜잭션 처리**: 상담 신청 시 영업대표 매칭 및 알림은 동기식 호출로 처리
- **장애 격리**: 알람 서비스는 비동기적으로 처리하여 알람 서비스의 장애가 상담 신청에 영향을 주지 않도록 설계
- **성능**: CQRS 패턴 적용하여 고객이 관심 사업의 진행 상황을 조회할 수 있도록 설계

# 구현

분석/설계 단계에서 도출된 헥사고날 아키텍처에 따라, 각 바운디드 컨텍스트별로 마이크로서비스들을 스프링부트로 구현하였습니다.



# 운영



# 신규 개발 조직의 추가

- **분석팀**이 추가되어 데이터 분석 및 통계 기능을 개발합니다.
- 기존 서비스와는 독립적으로 마이크로서비스를 추가하고, 이벤트 소싱을 활용하여 데이터 분석을 수행합니다.
- **Event Streaming**을 통해 기존 이벤트를 수신하여 분석에 활용합니다.
