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
<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.02.png"  width="70%">

#### 스프링 프로젝트 생성
`Spring Legacy Project` 메뉴를 이용하면 아래와 같이 여러 종류의 스프링 기반 프로젝트를 Maven 기반으로 생성할 수 있다.
```
File > New > Spring Legacy Project
```
Spring MVC에 관련한 학습을 진행하므로 `Spring MVC Project`를 선택하고 원하는 프로젝트명을 설정한뒤 Next를 클릭한다.

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.04.png"  width="70%">

프로젝트에 고유한 이름을 설정하기 위해 `package`를 설정한다.

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.05.png"  width="70%">

#### 프로젝트 구조 살펴보기
`Spring Legacy Project`를 이용하여 생성된 Spring MVC는 아래 그림과 같은 구조를 가지며. 각 파일은 다음과 같은 역할을 수행한다.

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.06.png"  width="48%">

#### Spring 및 Java Version 변경
최신의 Spring 및 Java를 이용하기 위해 Maven이 사용하는 `pom.xml`파일을 이용한다.

**[참조]** [Maven Repository](https://mvnrepository.com/artifact/org.springframework/spring-core)

[Spring Version 변경]
```xml
<properties>
	<java-version>1.6</java-version>
	<org.springframework-version>5.2.6.RELEASE</org.springframework-version>
	<org.aspectj-version>1.6.10</org.aspectj-version>
	<org.slf4j-version>1.6.6</org.slf4j-version>
</properties>
```

[Java Version 변경]

```xml
 <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>2.5.1</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <compilerArgument>-Xlint:all</compilerArgument>
        <showWarnings>true</showWarnings>
        <showDeprecation>true</showDeprecation>
    </configuration>
</plugin>
```
<hr>

## 1.3. Lombok Libray 설치
Lombok을 사용하면 Java 개발시 constructor, getter/setter, toStinrg()등을 자동으로 생성해주므로 편리하게 개발이 가능하다. Lombok은 다른 jar 파일들과 달리 특정 프로젝트에서만 사용되는 것이 아니기 때문에 별도로 설치가 필요하다.

**[참조]** [Lombok](https://projectlombok.org)

1. Lombok 사이트에서 `lombok.jar`을 다운로드 받는다. 
2. lombok.jar을 클릭하여 설치를 진행한다.

    <img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.08.png"  width="48%">

3. Eclipse가 설치된 경로에 lombok이 존재하는지 확인한다.

    <img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.09.png"  width="48%">

## 1.4. Java Configuration을 이용하는 경우
Spring Verion 3 이후부터는 `Java Class File`을 이용하는 설정을 지원한다. 이를 위해 다음과 같은 작업을 수행해야한다.

**[참조]** [Example01_JAVA](https://github.com/juyonglee/Spring-MVC-Study/tree/master/01.%20개발을%20위한%20준비/Example01_JAVA)

1. web.xml 파일 삭제 및 Spring 관련 파일 삭제

    <img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/1.2.1.06.png"  width="48%">
    
2. pom.xml 수정 및 Spring Version 변경
    [pom.xml 변경]
    `web.xml`을 사용하지 않기 때문에 아래와 같은 설정을 pom.xml에 추가해준다. web.xml이 없어도 동작하도록 하기위해 `failOnMissingWebXml` 설정이 필요하다.
    ```xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.2.3</version>
        <configuration>
            <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
    </plugin>
    ```

    [Java Version 변경]
    ```xml
    <properties>
        <java-version>1.8</java-version>
        <org.springframework-version>5.2.6.RELEASE</org.springframework-version>
        <org.aspectj-version>1.6.10</org.aspectj-version>
        <org.slf4j-version>1.6.6</org.slf4j-version>
    </properties>
                    .
                    .
                    .
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>2.5.1</version>
        <configuration>
            <source>1.8</source>
            <target>1.8</target>
            <compilerArgument>-Xlint:all</compilerArgument>
            <showWarnings>true</showWarnings>
            <showDeprecation>true</showDeprecation>
        </configuration>
    </plugin>
    ```
3. Java 설정 관련 package 생성

    xml 설정 파일을 삭제하였기 때문에 Java를 이용하여 설정을 해줘야한다. Spring에서는 **`@Configuration`** 이라는 Annotation을 이영해서 해당 Class의 Instance로 설정 파일을 대신한다.
    
    [**RootConfig.java**: root-context.xml을 대신하는 Class]
    ```java
    package com.gmail.juyonglee0208;

    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class RootConfig {
	
    }
    ```
    [**WebConfig.java**: web.xml을 대신하는 Class]
    추상 클래스 `AbstractAnnotationConfigDispatcherServletInitializer`를 상속받아 구현
    ```java
    package com.gmail.juyonglee0208;
    import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

    public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

        @Override
        protected Class<?>[] getRootConfigClasses() {
            // TODO Auto-generated method stub
            return new Class[] {RootConfig.class};
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        protected String[] getServletMappings() {
            // TODO Auto-generated method stub
            return null;
        }

    }
    ```
