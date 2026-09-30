Online Store

Spring Boot 기반의 온라인 스토어 개인 프로젝트입니다.

상품 CRUD부터 회원가입/로그인, 이미지 저장까지
웹 서비스의 기본적인 요청 흐름과 데이터 저장 구조를 직접 구현하며 학습한 프로젝트입니다.

Tech Stack

Category

Stack

Language

Java 17

Backend

Spring Boot, Spring MVC

Database

MySQL

ORM

Spring Data JPA

Security

Spring Security, BCrypt, CSRF

Session

Spring Session JDBC

View

Thymeleaf

Storage

AWS S3

Build

Gradle

주요 기능

상품 관리

상품 등록 / 조회 / 수정 / 삭제 기능 구현

Controller - Service - Repository 계층으로 역할 분리

Spring Data JPA를 이용한 데이터 관리

Slice 기반 상품 목록 페이징 구현

MySQL MATCH ... AGAINST를 활용한 상품 검색 쿼리 작성

상품 가격 및 제목에 대한 기본적인 유효성 검증

이미지 업로드

상품 이미지를 애플리케이션 서버에 직접 저장하지 않고 AWS S3 Object Storage에 저장하도록 구성했습니다.

이미지 업로드 시 서버가 파일 자체를 전달받는 방식 대신
Presigned URL을 발급하고 클라이언트가 S3에 직접 업로드하는 구조를 적용했습니다.

Client
   │
   ├─ Presigned URL 요청
   ▼
Spring Boot Server
   │
   ├─ S3 Presigned URL 발급
   ▼
Client ────────────── PUT ──────────────▶ AWS S3
   │
   └─ 업로드된 이미지 URL을 상품 정보와 함께 저장

이를 통해 서버가 이미지 파일을 직접 처리하면서 발생하는 저장 공간 및 파일 전송 부담을 줄였습니다.

회원 / 인증

회원가입 및 로그인 기능 구현

Spring Security 기반 인증 처리

BCrypt를 이용한 비밀번호 단방향 암호화

UserDetailsService를 이용한 사용자 인증 정보 조회

로그인 사용자 정보를 Session으로 관리

Spring Session JDBC를 이용한 세션 저장

CSRF Token을 적용해 상태 변경 요청 보호

프로젝트 구조

src/main/java/com/lis/shop
├── Item
│   ├── Item.java
│   ├── ItemController.java
│   ├── ItemService.java
│   ├── ItemRepository.java
│   └── S3Service.java
│
├── member
│   ├── Member.java
│   ├── MemberController.java
│   ├── MemberService.java
│   ├── MemberRepository.java
│   └── MyUserDetailsService.java
│
├── SecurityConfig.java
└── ShopApplication.java

Controller에서 모든 로직을 처리하지 않고
비즈니스 로직은 Service, 데이터 접근은 Repository로 분리해 관리했습니다.

구현하며 중점적으로 본 부분

1. 파일 저장 구조 개선

초기에는 이미지 파일을 애플리케이션 내부에서 관리하는 방식을 고려했지만,
파일 증가에 따라 서버 저장 공간과 요청 처리 부담이 커질 수 있다고 판단했습니다.

따라서 이미지 파일은 S3로 분리하고 서버는 Presigned URL 발급과 상품 메타데이터 관리에 집중하도록 변경했습니다.

2. 인증 정보 안전하게 관리

사용자의 비밀번호를 평문으로 저장하지 않고 BCrypt로 해싱하여 저장했습니다.

또한 Spring Security의 인증 흐름과 UserDetailsService를 연결해
DB의 회원 정보와 로그인 인증을 연동했습니다.

3. 역할 단위 코드 분리

상품과 회원 도메인을 분리하고 각각

Controller → Service → Repository → Database

흐름으로 구성하여 요청 처리, 비즈니스 로직, 데이터 접근의 책임을 나누었습니다.

Configuration

DB 및 AWS 인증정보는 코드에 직접 작성하지 않고 환경변수를 통해 주입하도록 구성했습니다.

spring.datasource.password=${...}

spring.cloud.aws.credentials.accessKey=${...}
spring.cloud.aws.credentials.secretKey=${...}

민감한 설정값은 별도 환경 설정으로 관리합니다.

Development Log

프로젝트를 진행하며 학습하고 개선한 내용은 별도로 기록했습니다.

초기 개발 기록: https://lis7.tistory.com/48

저장소 정리 과정에서 초기 일부 Git Commit History가 유실되어 관련 개발 기록을 위 링크에 남겨두었습니다.
