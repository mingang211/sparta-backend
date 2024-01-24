####
# 📚 스파르타코딩클럽 클론코딩
### 📌 [프로젝트 설명]
-제작기간 : 2024.01.19. ~ 2024.01.25. (7 일)

-목표: 스파르타코딩클럽 코딩클론 

-진행: code convention 에 따라 개별 기능을 구현한 후, 코드 리뷰를 통해 develop 하는 방식으로 진행

##
### 📌 [팀원 소개]
#### - 팀 프로젝트

이신지 : 회원가입 + 로그인 / AWS EC2 HTTPS 배포 (RDS, ACM, Route 53, ALB)

민경현 : 메인페이지, 내 강의실, 전체 강의(인기순,무료,국비지원, 최신순 정렬), 태그별 강의 불러오기, 검색 기능 

##
### 📌 [기능 정의]
### <회원가입 & 로그인>

#### 1. 회원가입

- Client 에서 name, email, password, major 입력

- e-mail(올바른 이메일 형식) 및 password(6~15자리, [a-z, A-Z, 0-9]) 유효성
  
#### 검사

- e-mail 중복 검사
  
- 패스워드 암호화 이후 회원 등록
  
#### 2. 로그인
   
- Client 에서 e-mail, password 입력
  
- Spring Security 의 Filter 를 거쳐 인증
  
- JWT 토큰 발급 후 발급한 토큰을 Cookie 에 담아 클라이언트에 전달
  
  
#### 공통: Spring Security 를 사용하여 토큰 검사 및 인증

        Spring Security Context Holder 에서 로그인 객체 전달받아 회원 정보 조회 및 확인 
        
        Http Status 코드와 함께 Dto 반환
        
        반환 시 null 값 반환하지 않도록 빈 객체 생성하여 반환
        
#### 메인페이지

- 메인 페이지에 무료 코스 불러오기

#### 전체 강의 불러오기
- 전체 코스 불러온 후 검색 기능, tag별 코스, 코스 분류(최신순,인기순,무료,국비지원)

#### 내 강의실 페이지

- 수강 신청한 코스 불러오기

#### 검색 기능

- title, description, tag의 검색 키워드가 있으면 불러오기

         
#### 
##
### 📌 [Code Convention 정립]
#### 코드 컨벤션
1. 생성자명과 클래스명은 파스칼 케이스에 따라 작성함
   
2. 변수명과 메서드명은 카멜 케이스에 따라 작성하며, 메서드명은 동사로 시작함

3. 생성 메서드명의 경우, create로 시작함(e.g. enrollBoard() -> createBoard())

4. 조회 메서드명의 경우, 반환값에 따라 find 또는 read로 시작함

5. Boolean의 경우, 동사 + flag 구성으로 통일함(e.g. isNum, hasNum)

6. DI(Dependency Injection)의 경우, 생성자 주입 방식으로 통일함

7. 주석의 경우 javadoc으로 통일함

8. magic number 사용을 지양함(e.g. str.equals(’보드’) -> str.equals(’보드 할당 변수명’))
##
#### 📌 [API 명세서 / Use Case Diagram / Entity Relationship Diagram]
[⭐️ 스파르타코딩클럽 클론코딩 API 명세서](https://www.notion.so/hiryuji/API-8ba3e3a897754633a242ea81d218c584)
// [Entity Relationship Diagram](https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Feb1978de-553e-4df9-894c-2ea28839f5eb%2F1aec801c-d0f1-4f8c-8d56-8bd9a060cbda%2F%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7_2024-01-24_113302.png?table=block&id=735ed206-bcca-4c44-877a-271f27c81153&spaceId=eb1978de-553e-4df9-894c-2ea28839f5eb&width=2000&userId=2a921a7e-1f77-46aa-b24d-eb58ef3c2eaa&cache=v2)
##
### 📌 [과제 회고]
#### **민경현** 👨🏻‍💻
 프로젝트를 진행하면서 클론코딩은 쉬울 줄 알았는데 이미 완성된 사이트를 똑같이 클론코딩을 하는게 더 어려울줄은 생각하지 못했습니다.
 그래도 이번 프로젝트를 통해서 ERD작성하는 방법과 코드를 작성할 때 ERD에 중요성을 깨달았고 이 부분에 대해 더 공부해야할 것 같습니다.
 빌드하는 과정에서 연관관계를 맺은 entity들이 서로를 계속 호출해 무한루프에 빠졌을 때 Dto를 하나 더 만들어 해결했을 때 너무행복했습니다.
 연관관계에 대해 더 공부하는 시간이 되어서 좋았던 것 같고 Https배포를 맡아서 너무 고생하신 신지님께 감사하고 고생하셨다고 전하고 싶습니다.
 저번 협업때는 안좋은일이 있었는데 이번에 아무 문제없이 끝낼수있어서 너무 좋습니다.
 
#### **이신지** 👩🏻‍💻 

