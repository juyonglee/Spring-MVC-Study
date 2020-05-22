# 31. 로그인과 로그아웃 처리

## 1. 접근 제한 설정
security-context.xml에 접근 제한을 설정한다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-5.3.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<security:http>
		
		<security:intercept-url pattern="/sample/all" method="GET" access="permitAll"/>
		
		<security:form-login/>
		
	</security:http>
	
	<security:authentication-manager>
	
	</security:authentication-manager>
</beans>

```