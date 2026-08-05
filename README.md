# 백엔드 개발 통합 로드맵

## CS + Spring + Database + Infra + 성장 단계

``` text
백엔드 개발자
│
├── 1. Computer Science (기반)
│   ├── Java
│   │   ├── 객체지향
│   │   ├── 컬렉션
│   │   ├── 예외
│   │   ├── Stream/Lambda
│   │   └── 멀티스레드
│   │
│   ├── 자료구조
│   │   ├── List
│   │   ├── Stack/Queue
│   │   ├── HashMap
│   │   ├── Tree
│   │   └── Heap
│   │
│   ├── 알고리즘
│   ├── 운영체제
│   │   ├── 프로세스/스레드
│   │   ├── 메모리
│   │   ├── 동기화
│   │   └── 스케줄링
│   │
│   └── 네트워크
│       ├── TCP/IP
│       ├── HTTP
│       ├── HTTPS
│       ├── DNS
│       └── Cookie / Session / JWT
│
├── 2. Database
│   ├── SQL
│   ├── ERD
│   ├── 정규화
│   ├── PK/FK
│   ├── Index(B+Tree)
│   ├── 실행계획(EXPLAIN)
│   ├── Transaction
│   ├── Lock / MVCC
│   ├── Isolation Level
│   └── 성능 튜닝
│
├── 3. Spring
│   ├── Spring Core
│   ├── Spring MVC
│   ├── Validation
│   ├── Exception
│   ├── JPA
│   ├── QueryDSL
│   ├── Security
│   ├── Redis
│   ├── Batch
│   └── 테스트
│
├── 4. Infrastructure
│   ├── Linux
│   ├── Docker
│   ├── Nginx
│   ├── AWS
│   ├── CI/CD
│   ├── Monitoring
│   └── Logging
│
└── 5. Architecture
    ├── Design Pattern
    ├── DDD
    ├── Clean Architecture
    ├── Event Driven
    ├── Kafka
    ├── MSA
    └── 성능 최적화
```

------------------------------------------------------------------------

# 성장 로드맵

## Junior (0\~2년)

### 목표

-   기능 구현
-   CRUD
-   API 개발
-   버그 수정
-   배포 가능

### 반드시 알아야 할 것

-   Java
-   Spring Boot
-   JPA
-   SQL
-   Git
-   Docker 기초
-   Linux 기초
-   HTTP

### 프로젝트

-   게시판
-   쇼핑몰
-   예약 시스템

------------------------------------------------------------------------

## Middle (2\~5년)

### 목표

-   성능 개선
-   장애 분석
-   시스템 설계

### 학습

-   QueryDSL
-   Redis
-   Kafka
-   JVM
-   GC
-   실행계획
-   인덱스 튜닝
-   동시성
-   분산락
-   캐시 전략

### 프로젝트

-   대용량 트래픽
-   캐시 적용
-   비동기 처리
-   CI/CD 구축

------------------------------------------------------------------------

## Senior (5년 이상)

### 목표

-   시스템 아키텍처 설계
-   기술 의사결정
-   장애 대응
-   팀 리딩

### 학습

-   DDD
-   MSA
-   Event Driven
-   Kubernetes
-   Observability
-   비용 최적화
-   데이터 모델링
-   보안
-   코드 리뷰 문화

### 역할

-   기술 선택
-   시스템 설계
-   성능 최적화
-   장애 대응 체계 구축
-   주니어 멘토링

------------------------------------------------------------------------

# 추천 학습 순서

1.  Java
2.  자료구조 + 알고리즘
3.  SQL
4.  Database 내부 동작
5.  HTTP + Network
6.  Spring Core
7.  Spring MVC
8.  JPA
9.  QueryDSL
10. Security
11. Redis
12. Linux
13. Docker
14. AWS
15. CI/CD
16. Monitoring
17. DDD
18. Kafka
19. MSA

------------------------------------------------------------------------

# 핵심 원칙

새로운 기술을 배울 때마다 아래 흐름을 반복한다.

    개념 이해
        ↓
    직접 구현
        ↓
    프로젝트 적용
        ↓
    성능 측정
        ↓
    왜 그렇게 동작하는지 분석
        ↓
    실무 사례 조사

기술을 '사용하는 수준'에서 끝내지 말고, 내부 동작과 장단점을 설명할 수
있는 수준을 목표로 한다.
