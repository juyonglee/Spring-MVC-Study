# 04. MyBatis와 스프링 연동
## 4.1. MyBatis
**[참고]**: [MyBatis](https://mybatis.org/mybatis-3/ko/index.html)

MyBatis는 custom SQL, stored procedures 그리고 mappings을 지원하는 persistence framework이다. 마이바티스는 JDBC로 처리하는 상당부분의 코드와 파라미터 설정 및 결과 mapping을 대신해준다. 


| 일반적인 JDBC | MyBatis |
| :-------------: |:-------------:|
| 직접 Connection을 열어야한다.| 자동으로 연결을 열어준다. |
| 사용 완료 후 직접 Close 해야한다.| 자동으로 연결을 close 해준다.      |
| PreparedStatement를 직접 생성하고 처리해야한다.| MyBatis 내부적으로 PreparedStatement 처리를 해준다.
| 직접 ResultSet을 처리해야한다.| Return type을 지정하는 경우 자동으로 객체 생성 및 ResultSet을 처리해준다.

### 4.1.1. MyBatis 관련 라이브러리 추가
MyBatis를 사용하기 위해 추가적인 라이브러리들의 설치가 필요하다.
1. **spring-jdbc**: 스프링에서 데이터베이스 처리
    
    ```xml
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.2.6.RELEASE</version>
    </dependency>
    ```
    **[참고]** [Spring JDBC](https://mvnrepository.com/artifact/org.springframework/spring-jdbc)
2. **spring-tx**: 스프링에서 트랜잭션 처리
    
    ```xml
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
        <version>5.2.6.RELEASE</version>
    </dependency>
    ```
    **[참고]** [Spring Transaction](https://mvnrepository.com/artifact/org.springframework/spring-tx)
3. **mybatis**
    ```xml
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.4</version>
    </dependency>
    ```
    **[참고]** [MyBatis](https://mvnrepository.com/artifact/org.mybatis/mybatis)
4. **mybatis-spring**: 스프링의 프레임워크의 특징은 다른 프레임워크들의 연동을 쉽게하는 `추가적인 라이브러리`가 많다는 것이다. MyBatis 또한 스프링에서 연결가능하도록 만들어주는 라이브러리가 존재한다.
    ```xml
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.4</version>
    </dependency>
    ```
    **[참고]** [MyBatis Spring](https://mvnrepository.com/artifact/org.mybatis/mybatis-spring)
    