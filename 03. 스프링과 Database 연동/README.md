# 03. 스프링과 Oracle Database 연동
## 3.1. Oracle Database와 연동
실습을 위해 `Docker`를 이용하여 11g Express Edition Version을 설치한다.
```docker
docker pull wnameless/oracle-xe-11g-r2
```
#### [Quick Start]
Orcale Database는 `1521` port를 사용한다. Host에서 Database에 접근하는 것을 허용하기 위해 1521 포트를 포트포워딩해 준다.
```docker
docker run -d -p 1521:1521 wnameless/oracle-xe-11g-r2
```

#### [외부 접근을 허용하는 경우]
```docker
docker run -d -p 1521:1521 -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g-r2
```

#### [Asynch IO를 비활성화하는 경우]
성능문제로 Asynch IO를 비활성화 해야하는 경우의 명령어는 아래와 같다.
```docker
docker run -d -p 1521:1521 -e ORACLE_DISABLE_ASYNCH_IO=true wnameless/oracle-xe-11g-r2
```

#### [XDB를 사용하는 경우]
XDB를 사용하는 경우 기본 assword는 xdb이다.
```docker
docker run -d -p 1521:1521 -e ORACLE_ENABLE_XDB=true wnameless/oracle-xe-11g-r2
```

#### [기본설정]
- hostname: localhost
- port: 1521
- sid: xe
- username: system
- password: oracle
- Password for SYS & SYSTEM: oracle

<hr>

### 3.1.1. SQL Developer 설치 및 설정
Oracle Database를 쉽게 이용하기 위해 SQL Developer를 이용힌다.

**[참고]** [SQL Developer](https://www.oracle.com/kr/tools/downloads/sqldev-v192-downloads.html)

설치 후 Oracle Database 사용을 위해 위의 기본 설정대로 입력하고 연결을 수행한다.

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.1.01.png"  width="70%">

#### [Step01] 사용자 생성

실습을 수행하기 위해 실습용 계정을 생성한다. 이를 위해 SQL Developer의 질의 창에 아래와 같은 SQL문을 입력하고 실행한다.

```sql
-- CREATE USER {user_id} IDENTIFIED BY {user_passwd}
CREATE USER book_ex IDENTIFIED BY 1234
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;
```

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.1.02.png"  width="70%">

#### [Step02] 사용자 권한부여
사용자 계정으로 Dabase를 사용하기 위해서는 권한이나 Role이 필요하다. 이를 위해 `GRANT` 구문을 이용한다. 
```sql
-- 연결 (CONNECT)과 Database Administration (DBA) 권한을 부여
GRANT CONNECT, DBA TO book_ex;
```

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.1.03.png"  width="70%">

#### [Step03] 연결수립 확인
새로운 계정의 연결을 확인하기 위해 새롭게 연결을 시도한다.

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.1.04.png"  width="70%">

<hr>

### 3.1.2. JDBC와의 연결
**`[주의]`** SQL Developer들의 프로그램을 이용하여 Database 연결에 문제가 없는것을 확인하고 Spring 프로젝트와 연결을 진행햐야한다.

#### [Case01] JDBC Driver 추가하기
사용하는 Oracle Database 11g의 JDBC Driver는 maven을 통해 이용이 불가능 하기 때문에 직접 `jar` 파일을 프로젝트에 추가시켜야한다. 

```
[jdbc 설치 경로]
/Applications/SQLDeveloper.app/Contents/Resources/sqldeveloper/jdbc/lib
```
<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.2.01.png"  width="70%">

#### [Case02] JDBC Driver를 다운받는 경우
[Oracle JDBC Driver Download](https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html)

#### [JDBC 설정]
JDBC Driver를 추가하기 위해 Build Path를 이용한다.
```
Build Path > Configure Build Path... > Library > Add external JARs... >
```
<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.2.02.png"  width="70%">
<br>
<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.2.03.png"  width="70%">

war 파일로 만들어 질 때에도 jar 파일이 포함될 수 있도록 `Web Deployment Assembly`항목에도 jar 파일을 추가해야한다.
```
Web Deployment Assembly > Add > Java Build Path Entries
```
<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.2.05.png"  width="70%">
<br>
<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.2.06.png"  width="70%">
<br>
<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/3.1.2.07.png"  width="70%">

<hr>

### 3.1.3. JDBC 테스트
Connection 객체를 얻기위해 `DriverManager`를 이용한다.
- **Driver**: oracle.jdbc.driver.OracleDriver
- **Database Url**: jdbc:oracle:thin:@localhost:1521:XE

```java
package com.gmail.juyonglee0208;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class DatabaseTest {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void databaseTestConnection() {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "juyonglee", "1234");
			log.info(con);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}
```
