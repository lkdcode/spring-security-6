# spring security 의 기본 보안 정책

`spring-security` 는 선언만으로 최상위의 보안을 얻을 수 있다.<br/>
`spring-security` 의존성 추가 후 애플리케이션을 실행하게 되면,<br/>
아래와 같이 유저의 패스워드를 얻을 수 있다.<br/>

```text
***
Using generated security password: 2bfea7f1-9fe9-4a74-8279-35c9041a5f28

This generated password is for development use only. Your security configuration must be updated before running your application in production.
***
```

<br/>

해당 정책에 사용되는 클래스는 `UserDetailsServiceAutoConfiguration` <br/>
인증 객체를 생성한 후 `InMemory` 에 저장한다.<br/>
생성된 유저의 비밀번호는 패스워드에 출력한다.<br/>

```java
package org.springframework.boot.autoconfigure.security.servlet;

public class UserDetailsServiceAutoConfiguration {
    // ...생략
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(
            SecurityProperties properties, ObjectProvider<PasswordEncoder> passwordEncoder
    ) {
        // 인증 객체 생성 후 저장 ...생략
    }

    private String getOrDeducePassword(SecurityProperties.User user, PasswordEncoder encoder) {
        // 패스워드 출력 ...생략
    }
}
```

위의 클래스를 통해 인메모리에 인증 객체를 저장하고 비밀번호를 콘솔에 출력을 하게 된다.<br/>