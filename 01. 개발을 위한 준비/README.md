# 01. 개발을 위한 준비
## 1.1. 개발 환경설정
<hr>

## 1.2. 스프링 프로젝트 생성
Eclipse STS를 이용하여 프로젝트를 생성하는 방식은 아래와 같다.
1. 스프링 프로젝트로 생성하는 방식
2. Maven이나 Gradle 프로젝트를 생성한 후 프레임워크를 추가하는 방식
3. 직접 프레임워크 라이브러리를 추가하는 방식

### 1.2.1. 예제 프로젝트 생성
**[준비사항]** 프로젝트를 생성하기 위해 `Perspective`에서 Spring를 선택한다.

![STS Setting](https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.02.png)

#### 스프링 프로젝트 생성
`Spring Legacy Project` 메뉴를 이용하면 아래와 같이 여러 종류의 스프링 기반 프로젝트를 Maven 기반으로 생성할 수 있다.
```
File > New > Spring Legacy Project
```
Spring MCV에 관련한 학습을 진행하므로 `Spring MCV Project`를 선택하고 원하는 프로젝트명을 설정한뒤 Next를 클릭한다.

프로젝트에 고유한 이름을 설정하기 위해 `package`를 설정한다.

#### 프로젝트 구조 살펴보기
`Spring Legacy Project`를 이용하여 생성된 Spring MVC는 아래 그림과 같은 구조를 가지며. 각 파일은 다음과 같은 역할을 수행한다.
![STS Setting](https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.06.png)

<hr>

