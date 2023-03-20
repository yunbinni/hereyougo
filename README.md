# 여기있소

## 소개
- 저희는 폐업으로 인한 자영업자들의 물품을 기부하는 플랫폼을 만들었습니다.
- 배포주소 : ec2-3-34-196-183.ap-northeast-2.compute.amazonaws.com
- 개발인원 및 기간 : [조윤빈](https://github.com/yunbinni), [김도희](https://github.com/KIM-DO-HEE)
- 저희끼리의 맡은 역할
    - 조윤빈 :
    - 김도희 :

## 기술스택
+ api + querydsl

### Back End Skills : (Querydsl 추가)
![SPRINGBOOT](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![JAVA](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge)
![MYSQL](https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

### Front End Skills : (Thymeleaf 추가)
![JAVASCRIPT](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
<!--![BOOTSTRAP](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)-->

### Tools and Infra :
![AWS](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

## 프로젝트 주요 기능(## 화면 구성과 API 주소[이미지](#))

1. 최근 인기글로 추천 수 10개 이상 받은 글을 3개 보여준다.
2. 내 주변에 있는 글들을 카카오맵에 마커로 표시해준다.

## 3. 회원가입
* 회원은 회원 정보를 입력하여 가입하면, 여기있소 서비스를 이용할 수 있다
* 이메일 인증을 통해 본인 인증을 할 수 있으며, 무작위로 생성되는 계정을 방지한다

  <img src="https://user-images.githubusercontent.com/47100801/226297770-1c8be501-96f4-4349-9f41-84fc511c2058.gif" width="700" height="300"/>

* 회원가입 시 입력한 주소는  메인 페이지에 내 주변 기부글 찾기의 위치로 반영된다

4. 글 작성

## 5. 채팅
- Web Socket, STOMP을 활용하여 채팅 기능 구현

### 5-1. 채팅하기

* 회원은 글 내부에 문의하기 버튼을 클릭하여, 해당 글과 관련하여 문의를 할 수 있다

  <img src="https://user-images.githubusercontent.com/47100801/226149880-8242f2ae-3c97-44da-98c1-48a63bdce095.gif" width="700" height="300"/>


### 5-2. 채팅방 나가기

*  회원은 거래가 종료되었거나, 해당 채팅방이 필요 없을 경우 채팅방을 삭제할 수 있다

  <img src="https://user-images.githubusercontent.com/47100801/226199793-a1340a9a-bf1a-44f8-ab68-cd4bc3f4296b.gif" width="700" height="300"/>


## 6. 예약

* 약속잡기를 통해 물품 예약 및 거래 일정을 정할 수 있다
* 예약 상태는 예약중, 예약취소, 거래 완료로 구분할 수 있다

### 6-1. 예약하기

* 약속잡기를 통해 물품 예약 및 거래 일정을 정할 수 있다
* 약속 잡기 버튼 클릭 후, 날짜/시간 수량을 입력하여 예약을 한다
* 글에 작성한 물품 수량, 예약 수량에 따라 예약 가능 여부를 결정한다

<img src="https://user-images.githubusercontent.com/47100801/226260977-c13dbfd7-807f-4189-925e-590a2e5bb85d.gif" width="700" height="300"/>

###  6-2. 예약 취소

* 사용자는 예약을 취소할 수 있다
* 예약을 취소하면 예약한 수량만큼 예약 수량이 차감된다
* 예약 상태는 "예약 취소"로 변경된다

<img src="https://user-images.githubusercontent.com/47100801/226261979-b5d4e920-2531-43cd-9044-643e3fb58db3.gif" width="700" height="300"/>


### 6-3. 거래 완료

* 거래자가 물품을 전달해당 물품을 전달받았으면, 전달 완료 버튼을 클릭하여
* 거래가 완료되면 글에 업로드된 물품 수량이 예약 수량만큼 차감된다
* 예약 상태는 "거래 완료"로 변경된다

<img src="https://user-images.githubusercontent.com/47100801/226210436-0297de93-67eb-4514-8aab-5a9d78764296.gif" width="700" height="300"/>

7. 글 검색

## 프로젝트 아키텍쳐
- ERD(workbench 캡쳐)
- User의 입장에서 Sequence Diagram
- 자기가 설명하기 원하는 기능을 자세히 보여주는 sequence diagram

## 액티비티 다이어그램

1. 예약하기
   <!-- ![예약하기](https://user-images.githubusercontent.com/47100801/226205514-da0b0731-4f64-4155-b434-630775d5bd1b.png) -->
   <img src="https://user-images.githubusercontent.com/47100801/226349822-7191a608-dd20-4b26-89c9-ba1fac0f143d.png" width="600" height="500"/>   

2. 문의하기(채팅)
   <!-- ![채팅하기](https://user-images.githubusercontent.com/47100801/226205447-9b5de027-994f-49e9-8d89-c266d5b3f51e.png) -->
   <img src="https://user-images.githubusercontent.com/47100801/226205447-9b5de027-994f-49e9-8d89-c266d5b3f51e.png" width="600" height="500"/>
