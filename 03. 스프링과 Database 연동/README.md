# 03. 스프링과 Oracle Database 연동
## Oracle Database와 연동
실습을 위해 11g Express Edition Version을 설치한다.
```docker
docker pull wnameless/oracle-xe-11g-r2
```
### [Quick Start]
Orcale Database는 `1521` port를 사용한다. Host가 Database에 접근하는것을 허용하기 위해 1521 포트를 포트포워딩해준다.
```docker
docker run -d -p 1521:1521 wnameless/oracle-xe-11g-r2
```

### [외부 접근을 허용하는경우]
```docker
docker run -d -p 49161:1521 -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g-r2
```

### [Asynch IO를 비활성화하는경우]
성능문제로 Asynch IO를 비활성화 해야하는 경우의 명령어는 아래와 같다.
```docker
docker run -d -p 49161:1521 -e ORACLE_DISABLE_ASYNCH_IO=true wnameless/oracle-xe-11g-r2
```

Enable XDB user with default password: xdb, run this:
```docker
docker run -d -p 49161:1521 -e ORACLE_ENABLE_XDB=true wnameless/oracle-xe-11g-r2
```

### [기본설정]
- hostname: localhost
- port: 49161
- sid: xe
- username: system
- password: oracle
- Password for SYS & SYSTEM: oracle