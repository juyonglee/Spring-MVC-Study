# 02. 스프링의 특징과 의존성 주입
## 2.1. 스프링 프레암워크의 간략한 역사
Software Framework는 복잡한 문제를 해결하거나 서술하는데 사용되는 기본 개념 구조이다. 즉, `뼈대나 근간을 이루는 코드들의 묶음`이라고 할 수 있다. 

#### **Framework와 Library의 차이점**
Framework와 Library의 차이는 제어 흐름에 대한 주도성이 누구에게 있는가에 달려있다.
  
Framework 장점은 개발에 필요한 구조를 이미 코드로 만들어 놓았기 때문에, 필요한 부분을 조립하는 형태의 개발이 가능하다.

- 복잡함에 반기를 들어서 만들어진 프레임워크

    Enterprise JavaBeans (EJB)에 비해 가볍기 때문에 빠르게 엔터프라이즈급이 시스템 작성이 가능하다.

-  프로젝트의 전체 구조를 설계할 때 유용한 프레임워크

#### Spring Version 변화
- Spring v2.5: `Annotation`을 활용하여 편리한 설정과 개발이 가능하도록 지원
- Spring v3.0: `Java Class`만으로 설정 파일을 대신할 수 있게 지원
- Spring v4.0: `REST 방식`의 컨트롤러 지원
- Spring v5.0: `Reactor`를 이용한 Reactive 스타일의 개발 환경 지원 

### 2.1.1. Spring의 주요 특징
1. POJO 기반의 구성
    
    스프링은 다른 프레임워크들과 다르게 객체 간의 관계를 구성할 때 별도의 API를 사용하지 않는 POJO (Plain Old Java Object)의 구성만으로 가능하도록 제작되어있다. 즉, 개발자가 특정 라이브러리나 컨테이너의 기술에 종속적이지 않습니다.
2. 의존성 주입 (DI)
    - 의존성 (Dependency): 하나의 객체가 다른 객체 없이 제대로 된 역할을 수행할수 없다는 것을 의미한다.
    - 주입 (Injection): 외부에서 `밀어 넣는 것`을 의미한다.
    - 주입을 받는 입장에서 어떤 객체인지 신경 쓸 필요가 없으며, 어떤 객체에 의존하든 자신의 역할은 변하지 않는다는 것을 의미한다.
    - `ApplicationContext`가 필요한 객체들을 생성하고, 주입하는 역할을 수행한다.
    - `ApplicationContext`가 관리하는 객체들을 `Bean`이라고 부른다.
        
3. AOP 지원
    - 보안, 로그, 트랜젝션과 같이 비즈니스 로직은 아니지만, 반드시 처리가 필요한 부분을 스프링에서는 `cross-concern`이라고 하며, 스프링은 이러한 cross-concern을 분리하여 제작하는 것이 가능하다.
    - AOP (Aspect Oriented Programming)은 이러한 cross-concern을 모듈로 분리하는 프로그래밍 패러다임이다.
    - 비즈니스 로직에만 집중해서 개발을 할 수 있으며, 각 프로젝트마다 코드의 수정 최소화 및 유지보수가 수월한 코드를 구성할 수 있다.
4. Transaction 지원

    트랜잭션의 관리를 `Annotation`이나 `XML`로 설정할 수 있기 때문에 개발자가 매번 상황에 맞는 코드를 작성할 필요가 없다.

## 2.2. 의존성 주입 테스트
스프링에서는 의존성 주입을 위한 크게 2가지의 방법이 있다.
1. 생성자를 이용한 주입
2. Setter를 이용한 주입

### 의존성 주입 테스트
1. Lombok 라이브러리 추가

    **[참조]** ~[Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
    ```xml
    <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
        <scope>provided</scope>
    </dependency>
    ```
2. log4j 라이브러리 추가
    ```xml
    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
     <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
        <scope>provided</scope>
    </dependency>
    ```
2. Chef Class 작성
    ```java
    package com.gmail.juyonglee0208;

    import org.springframework.stereotype.Component;

    import lombok.Data;

    @Data
    @Component
    public class Chef {

    }
    ```
    **`@Data`** Annotation
    생성자, toString(), setter를 생성하는 기능


3. Restraunt Class 생성
    ```java
    package com.gmail.juyonglee0208;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    import lombok.Data;
    import lombok.Setter;

    @Data
    @Component
    public class Restaurant {
        @Setter(onMethod_ = @Autowired)
        private Chef chef;
    }
    ```

4. 의존성 주입 설정

    - 프로젝트 src 폴더 내에 `root-context.xml`은 스프링 프레임워크에서 관리해야 하는 객체 (Bean)를 설정하는 설정 파일입니다.
    - root-context.xml의 아래쪽에 NameSpaces라는 탭에 `context`항목을 체크한다.
    - root-context.xml에 아래의 코드를 추가한다. 
        ```xml
        <context:component-scan base-package="com.gmail.juyonglee0208"></context:component-scan>
        ```
    - `Bean Graph`를 선택하여 Restaurant와 Chef 객체가 생성된 것을 확인한다.

    [동작 Overview]
    1. 스프링 프레임워크가 시작되면 스프링이 사용하는 메모리 영역인 `ApplicationContext`객체가 만들어진다.
    2. 스프링이 관리해야 하는 객체들을 설정하기 위해 `root-context.xml`을 이용한다.
    3. root-context.xml에 설정되어 있는 `<context:component-scan>`태그의 내용을 통해서 `com.gmail.juyonglee0208` 패키지를 스캔합니다.
    4. Restaurant 객체는 Chef 객체가 필요하다는 `@Autowired` Annotation이 있으므로, 스프링은 Chef 객체의 Reference를 Restaurant 객체에 주입한다. 

6. 테스트 코드를 통한 동작 확인

    `src/test/java` 폴더 내에 SampleTests Class를 추가한다.
    spring-test 모듈을 이용해서 스프링의 동작을 살펴본다.
    
    [참고] !(spring-test)[https://mvnrepository.com/artifact/org.springframework/spring-test/5.2.6.RELEASE]
    
    ```xml
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-test -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.2.6.RELEASE</version>
        <scope>test</scope>
    </dependency>
    ```
    **`[SampleTests Class]`**
    ```java
    package com.gmail.juyonglee0208;

    import static org.junit.Assert.assertNotNull;

    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.test.context.ContextConfiguration;
    import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

    import lombok.Setter;
    import lombok.extern.log4j.Log4j;

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
    @Log4j
    public class SampleTests {
        @Setter(onMethod_ = @Autowired)
        private Restaurant restaurant;
        
        @Test
        public void testExist() {
            assertNotNull(restaurant);
            log.info(restaurant);
            log.info("---------------------------------");
            log.info(restaurant.getChef());
        }
    }
    ```
    
- **@Runwith**: 현재 테스트 코드가 스프링을 실행하는 역할을 할 것이라는 의미이다.
- **@ContextConfiguration**: `classpath:`나 `file:`을 이용할 수 있다.
- **@Autowired**: 스프링으로부터 자동으로 주입해 달라는 표시이다.
- **@Test**: JUnit에서 테스트 대상을 표시하는 Annotation이다.

**[중요]** Spring은 관리가 필요한 객체 (Bean)를 Annotation 등을 이용해서 객체를 생성하고 관리하는 일종의 `컨테이너`나 `팩토리`기능을 가지고 있다.
