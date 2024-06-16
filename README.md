# LUBID USER SERVICE API 명세서 및 사양, 설명

## 사용 스펙
1. Spring boot 3.2.0
2. java 17

해당 서비스는 계속 변경될 수 있습니다.
변경될 때도 README를 수정할 예정이니 참고해주세요.



## API 명세서

### 회원가입
1. path : /lubid-user/auth/user/join
2. type : POST
2. 설명 : 간단한 회원가입을 할 수 있는 API 입니다.<br/>
         후에는 인증된 어플, 클라이언트 만 회원가입 할 수 있도록 
         header에 토큰을 넣을 예정입니다.
#### HEADER

```json
{
  "connection": "keep-alive",
}
```



#### BODY

```json
{
  "userName" : "test1",
  "email" : "test@test.com"
}


```

#### RESPONSE

```json
{
  "STATUS" : 200,
}


```

---

### 로그인
1. path : /lubid-user/auth/user/login
2. type : POST
2. 설명 : 회원가입된 계정을 통해 로그인 할 수 있습니다.<br/>
   로그인에 성공하면 JWT 토큰을 반환하며 해당 토큰으로  <br/>
   인증이 필요한 URL 들에 요청이 가능합니다.
#### HEADER

```json
{
  "Content-Type": "application/json",
  "Connection": "keep-alive",
  "withCredentials": true,
  "AUTHORIZATION": "Bearer [JWT TOKEN]"
}
```

#### BODY

```json
{
  "userName" : "test1",
  "password" : "1"
}


```

#### RESPONSE

```json
{
  "STATUS": 200,
  "data": "JWT TOKEN"
}


```