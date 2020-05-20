# 30. Spring Web Security

### 동작방식
- Servlet의 여러 종류의 Filter
- Interceptor: Spring에서 Filter와 유사한 역할을 수행

### Filter와 Interceptor의 차이
- **Filter**: Only Servelet 자원
- **Interceptor**
    - Spring의 `Bean`으로 관리되면서 Spring Context에 속한다.
    - Spring의 내부에서 Controller를 호출할 때 관여한다.


## 2. Spring Security 설정
### [Step01] Spring Security 사용을 위한 Framwork 추가
```xml
<!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-core -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-core</artifactId>
	<version>5.3.2.RELEASE</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-web -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-web</artifactId>
	<version>5.3.2.RELEASE</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-config -->
<dependency>
    <groupId>org.springframework.security</groupId>
	<artifactId>spring-security-config</artifactId>
	<version>5.3.2.RELEASE</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-taglibs -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
	<version>5.3.2.RELEASE</version>
</dependency>
```

### [Ste02] security-context.xml 셍성
Spring Bean Configuration File 메뉴룰 통해 생성 후, namespace에서 security 항목을 체크한다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-5.3.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<security:http>
		
		<security:form-login/>
		
	</security:http>
	
	<security:authentication-manager>
	
	</security:authentication-manager>

</beans>
```

### [Ste03] web.xml 셍성
```xml
<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>/WEB-INF/spring/root-context.xml
		/WEB-INF/security-context.xml
	</param-value>
</context-param>
	
<filter>
	<filter-name>springSecurityFilterChain</filter-name>
	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
	
<filter-mapping>
	<filter-name>springSecurityFilterChain</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>

```

## 3. Security가 필요한 URI 설계
```java
package com.gmail.juyonglee0208.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = {"/sample/*"})
public class SampleController {
	
	@RequestMapping(value = {"/all"}, method = {RequestMethod.GET})
	public void fullyAccess() {
		log.info("[All] Fully Access!!");
	}
	
	@RequestMapping(value = {"/member"}, method = {RequestMethod.GET})
	public void memeberAccess() {
		log.info("[Member] Member Access!!");
	}
	
	@RequestMapping(value = {"/admin"}, method = {RequestMethod.GET})
	public void adminAccess() {
		log.info("[Admin] Admin Access!!");
	}
}
```

## 4. Authentication & Authorization
- **인증(Authentication)**: 자신을 증명하는 것
- **권한 (Authorization)**: 남에 의해 자격이 부여되는 것

### [Spring Security 구조]
- **`AuthenticationManager (Interface)`**: 다양한 방식의 인증을 처리할 수 있도록 설계되어 있다.

- **`ProviderManager (Class)`**: 인증에 대한 처리를 AuthenticationProvider라는 Type의 객체를 이용해서 위임한다.

- **`AuthenticationProvider (Interface)`**: 실제 인증 작업을 진행하며 사용자의 정보나 권한에 대한 정보를 `UserDetailsService(Interface)`에 전달한다.

- **`UserDetailsService Interface 구현체`**: 실제 사용자 정보와 사용자가 가진 권한의 정보를 처리해서 반환한다.
