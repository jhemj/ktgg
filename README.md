
# KT.GG

**KT.GG**는 현장에서 영업활동을 하면서 고객들이 정부 지원사업에 대한 관심이 높다는 점을 반영하여 개발된 서비스입니다. 기존에는 지원사업 정보를 체계적으로 관리하지 않아 고객들이 지원 기회를 놓치는 경우가 있었고, 영업대표들이 신규 고객 발굴 및 미관리 고객을 방문할 때 적절한 대화 소재가 부족하여 어려움을 겪었습니다.

KT.GG는 이러한 문제를 해결하기 위해 지원사업 정보를 관리하고, 고객이 관심을 갖고 있는 사업에 대한 알림을 제공하며, 최적의 영업대표와 컨설턴트를 매칭하여 상담을 진행할 수 있도록 돕습니다. 이를 통해 고객 만족도를 높이고, 영업대표의 업무 효율성을 증대시키며, 새로운 고객 발굴을 위한 **Door Open Tool**로써 활용될 수 있습니다.

[서비스 설계 모델링은 이곳에서 확인할 수 있습니다.](https://www.msaez.io/#/180518912/storming/project)

---

# 목차

- [서비스 시나리오](#서비스-시나리오)
- [체크포인트](#체크포인트)
- [분석/설계](#분석설계)
- [구현](#구현)
- [운영](#운영)

---

## 서비스 시나리오

### 기능적 요구사항

1. **Staff**는 StaffPage에서 “CreateProject”, “StartProject”, “EndProject”, “EditProject” 기능을 수행할 수 있다.
2. “EndProject”에 의해 “ProjectEnded” 이벤트가 발생하면 동일 이름의 이벤트를 재생성한다. (매년 반복되는 사업 관리 목적)
3. **Customer**는 CustomerPage에서 “CreateInterest”와 “UpdateInterest” 명령을 실행할 수 있다.
4. **Customer**는 ConsultationPage에서 상담을 신청하거나 수정할 수 있다.
5. 각종 이벤트 발생 시 영업대표는 SalesmanPage에서 알림을 받고 대응할 수 있다.

### 비기능적 요구사항

1. **트랜잭션 처리**: 상담 신청과 영업대표 매칭은 비동기식 호출로 처리된다.
2. **장애격리**: 알림 기능이 수행되지 않더라도 상담 신청은 항상 가능하다.
3. **성능 요구**: 고객은 등록한 관심사업의 진행 상황을 실시간으로 조회할 수 있어야 한다.

---

## 마이크로서비스 구성

### 1. Project 서비스
- **주요 기능**: 
   - 프로젝트를 만들고, 시작하며, 종료하고 수정하는 기능을 제공합니다. 또한 각 프로젝트에 대해 진행 상황을 조회할 수 있습니다.
   - **확인(View) 기능**: 프로젝트별로 담당 영업대표, 고객, 프로젝트 진행 상태에 대해 GET 요청을 통해 데이터를 확인할 수 있습니다.

### 2. Interest 서비스
- **주요 기능**: 
   - 고객이 프로젝트에 대해 관심(interest)을 설정할 수 있습니다. 이때 프로젝트 이름과 고객의 전화번호를 받아 interest 객체를 생성하고 저장합니다.
   - 고객이 특정 프로젝트에 관심을 표시하면, 해당 정보를 기반으로 적합한 영업대표와 컨설턴트를 매칭합니다.

### 3. Consultation 서비스
- **주요 기능**: 
   - 고객이 프로젝트에 대한 상담 요청을 생성합니다. 상담 객체는 프로젝트 이름과 상담 희망 일자, 메모와 함께 생성되며, 초기 상태(step)는 "apply"로 설정됩니다.
   - 상담이 생성되면 SalesmanMatch가 호출되어 상담 담당자가 배정됩니다. 이후 영업대표는 상담 상태를 "승인", "취소", "성공", "실패"로 변경하여 관리할 수 있습니다.

### 4. SalesmanMatch 서비스
- **주요 기능**: 
   - MatchSalesman 정책에 따라 적합한 영업대표를 매칭합니다. 현재는 전화번호 뒷자리 기반의 랜덤 매칭 알고리즘을 사용하고 있으며, 향후 매칭 로직을 고도화할 예정입니다.
   -  null값으로 우선 생성 후 수정을 통해 추가할 수 있도록 구현하여, 매칭 시 발생할 수 있는 딜레이를 예방한다.

### 5. Salesman 서비스
- **주요 기능**: 
   - 영업대표에 대한 정보를 관리하며, 상담 성공 횟수와 실패 횟수를 저장합니다.
   - 상담의 성공 및 실패에 따른 이벤트가 발생할 때마다 자동으로 카운팅되며, 관리자 페이지에서 조회할 수 있습니다.

---

## 프론트엔드 관점

### 1. 관리자 페이지
- **주요 기능**: 
   - 관리자는 새로운 프로젝트를 추가하거나 수정할 수 있으며, 프로젝트 목록을 조회할 수 있습니다. 이를 통해 전체 프로젝트의 진행 상태를 한눈에 파악할 수 있습니다.

### 2. 고객 페이지
- **주요 기능**: 
   - 고객은 프로젝트 리스트를 확인하고, 관심 있는 프로젝트에 대해 관심(interest) 버튼을 클릭해 interest 객체를 생성할 수 있습니다.
   - 상담 요청 버튼을 눌러 프로젝트에 대한 상담을 신청할 수 있으며, 마이페이지에서 자신의 상담 신청 목록을 조회할 수 있습니다.

### 3. 영업대표 페이지
- **주요 기능**: 
   - 영업대표는 자신에게 할당된 상담 목록과 자신이 담당하고 있는 관심 고객 정보를 조회할 수 있습니다.
   - 상담의 진행 상황을 "승인", "취소", "성공", "실패"로 변경하여 실시간으로 관리할 수 있습니다.

---

## 체크포인트

- **분석 설계**: 
   - 이벤트스토밍을 통해 프로젝트 전반을 체계적으로 설계했으며, 각 도메인 이벤트를 명확히 정의했습니다.
   - 서브 도메인 및 바운디드 컨텍스트 분리를 통해 도메인 간의 독립성을 보장했습니다.
   - 서비스 간 통신 및 장애 대응을 위한 이벤트 드리븐 아키텍처를 설계하여 비기능 요구사항을 충족했습니다.

---

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

### 액터, 커맨드 부착

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

- **MatchSalesman**: InterestCreated, ConsultationCreated 이벤트 발생 시 실행하여 적절한 영업대표 매칭
- **EditSalesman**: SalesmanMatched 이벤트 발생 시 실행하여 담당 영업대표 저장

---

## 구현

분석 단계에서 도출된 이벤트스토밍을 바탕으로, 각 마이크로서비스는 독립적으로 스프링부트 기반으로 구현되었습니다. 이를 통해 서비스 간의 확장성과 유연성을 확보할 수 있었습니다.

---

## 운영

- **SLA 준수**: 
   - Liveness Probe 및 Readiness Probe를 통해 서비스 상태를 지속적으로 모니터링하고, 장애 발생 시 자동으로 복구되도록 설정했습니다.
   - 오토스케일러(HPA)를 통해 서비스의 부하에 맞춰 자동 확장이 가능하도록 설계되었습니다.

- **무정지 운영 CI/CD**: 
   - 무중단 배포를 위한 CI/CD 파이프라인을 구축하였으며, 신규 버전 배포 시에도 서비스 가용성을 유지하도록 Rolling Update 방식을 적용했습니다.

