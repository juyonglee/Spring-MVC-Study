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

### 3.1.1. SQL Developer 설치 및 설정
Oracle Database를 쉽게 이용하기 위해 SQL Developer를 이용힌다.

**[참고]** [SQL Developer](https://www.oracle.com/kr/tools/downloads/sqldev-v192-downloads.html)

설치 후 Oracle Database 사용을 위해 위의 기본 설정대로 입력하고 연결을 수행한다.

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/2.1.1.01.png"  width="70%">

실습을 수행하기 위해 실습용 계정을 생성한다. 이를 위해 SQL Developer의 질의 창에 아래와 같은 SQL문을 입력하고 실행한다.

```sql
// CREATE USER {user_id} IDENTIFIED BY {user_passwd}
CREATE USER 'book_ex' IDENTIFIED BY 1234
```
