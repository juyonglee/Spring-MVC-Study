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
