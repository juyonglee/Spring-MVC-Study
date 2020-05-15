# 05. 스프링 MVC의 기본 구조
Spring MVC는 Spring의 **`Sub-Project`** 이다. 스프링은 하나의 기능을 위해서 만들어진 Framework가 아니다. `Core`라고 할 수 있는 Framwork에 여러 Sub-Project를 결합해서 다양한 상황에 대처할 수 있도록 개발되었다. 즉, 별도의 설정이 존재할 수 있다로 이해하면 좋다.

**[Example: Spring Legacy Project로 생성한 경우]**
- **web-context.xml**: Tomcat 설정에 관련된 설정
- **root-context.xml**: Spring Core에 관련된 설정
- **servlet-context.xml**: Spring MCV애 관련된 설정

<img src="https://github.com/juyonglee/Spring-MVC-Study/blob/master/Images/5.1.0.01.png"  width="70%">

<hr>
