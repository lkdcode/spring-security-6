# multi-filter

`API` 마다 다른 `필터`들을 통해 애플리케이션의 보안을 설정할 수 있다.<br/>

## sample API

`"/sample"` 의 앤드포인트에 4가지 메서드 `GET`, `POST`, `DELETE`, `PUT` 이 존재한다.<br/> 

```java

@RestController
@RequestMapping("/sample")
public class DefaultApi {

    @GetMapping
    public String get() {
        return "Method: GET";
    }

    @PostMapping
    public String post() {
        return "Method: POST";
    }

    @DeleteMapping
    public String delete() {
        return "Method: DELETE";
    }

    @PutMapping
    public String put() {
        return "Method: PUT";
    }
}
```

## sample security

```java

@EnableWebSecurity // ①
@Configuration // ②
public class DefaultSecurityConfig {

    private static final String TARGET = "/sample"; // ③

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception { // ④
        return http

                .csrf(AbstractHttpConfigurer::disable) // ⑤

                .authorizeHttpRequests(auth -> auth // ⑥
                        .requestMatchers(TARGET).permitAll() // ⑦
                        .anyRequest().authenticated() // ⑧
                )

                .build(); // ⑨
    }
}
```

① `@EnableWebSecurity` : 웹 애플리케이션에서 `spring-security`를 활성화한다.<br/>
② `@Configuration` : 해당 클래스는 구성 클래스임을 명시한다.<br/>
③ `String TARGET` : `spring-security`를 설정할 엔드 포인트<br/>
④ `@Bean SecurityFilterChain` : 스프링 컨테이너에 등록을 하고, `HttpSecurity`를 매개변수로 받는다.<br/>
⑤ `.csrf(AbstractHttpConfigurer::disable)` : `postman` 테스트를 위해 csrf 비활성화 <br/>
⑥ `.authorizeHttpRequests(auth -> auth)` : `HTTP` 요청에 대한 `인증&인가` 규칙을 정의한다.<br/>
⑦ `.requestMatchers(TARGET).permitAll()` : `TARGET`(엔드포인트)의 요청은 모두 허용한다.<br/>
⑧ `.anyRequest().authenticated()` : 그 외에 요청은 모두 인증이 필요하다.<br/>
⑨ `.build();` : 위의 보안 규칙을 가진 `SecurityFilterChain` 을 생성한다.<br/>

<br/>

> CSRF: 사용자가 의도하지 않은 요청을 웹 애플리케이션을 통해 전송하는 공격<br/>
> springSecurity는 기본적으로 모든 POST 요청에 대해 CSRF 토큰을 요청함.

위의 보안 규칙을 요약하자면, 해당 엔드포인트에 대한 접근은 모두 허용하며,<br/>
그 외에 요청은 인증이 필요하다는 규칙이다.<br/>