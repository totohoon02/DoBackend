# JWT

**Json Web Token**

- 사용자 인증 정보를 전달하기 위한 토큰 형식

```
인증
  ↓
ID/PW 검증
  ↓
서버가 JWT 발급
  ↓
클라이언트가 JWT 저장
  ↓
API 요청마다 JWT 전달
  ↓
서버가 JWT 검증
  ↓
사용자 인증
```

## 인증 방식 - Session

일반적인 세션 기반 인증 방식

- 사용자가 로그인하면 서버가 인증 상태를 저장

```
Client
   │
   │ ID / PW
   ▼
Server
   │
   │ 인증 성공
   ▼
Session 저장

sessionId = abc123

GET /api/users/me
Cookie: JSESSIONID=abc123

abc123
  ↓
Session 조회
  ↓
userId = 10
```

- 서버가 사용자의 인증 상태를 가지고 있음

### 세션 정보를 유지하는게 왜 이상한지?

- 서버가 한 대인 경우는 큰 문제가 없음
- 서버가 여러 대인 경우

```
Server A
abc123 → userId=100

Server B
세션 없음
```

#### 해결방법

- Sticky Session -> 같은 사용자의 요청을 계속 Server A로 보낸다.
- 세션을 각 서버에 복제
- Redis 같은 외부 저장소 사용

> ❗Redis 같은 추가 인프라가 생긴다.

- 관리 포인트의 증가
- 상태를 유지하면 분산 환경에서 어떻게 상태를 관리하는지에 대한 문제가 추가된다.

## 인증 방식 - JWT

```
로그인 성공

Server
   ↓
JWT 생성

eyJhbGciOiJIUzI1NiJ9...

GET /api/users/me

Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

JWT
 ↓
서명 검증
 ↓
사용자 정보 확인
 ↓
인증 완료
```

- 인증 정보를 보관하지 않고 토큰이 유효한지만 확인한다.

### JWT 구조

```
Header.Payload.Signature

```

#### Header

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

- 어떤 방식으로 서명했는지 메타데이터

#### Payload

```json
{
  "sub": "12345",
  "role": "USER",
  "iat": 1787396400,
  "exp": 1787400000
}
```

- 전달할 정보
- 각 값을 `Claim`이라고 부름
- 페이로드는 암호화된 값이 아님, `Base64`
- 누구나 디코드 가능, 중요한 정보 넣으면 안됨.

#### Signature

```
Header + Payload
        +
      Secret
        ↓
Signature

예시
HMAC(
    base64Url(Header) + "." + base64Url(Payload),
    secretKey
)

결과
Header.Payload.Signature
```

- 페이로드를 수정해서 보내면 기존 `Signature`와 다른 값이 나옴
- 시크릿 키 값을 모르면 올바른 시그니처를 만들 수 없는 구조
- 로그인 -> 토큰 발급 -> 요청 시 토큰을 포함해서 보냄
- 서버에서 토큰을 디코드, 시그니처를 다시 만들어서 요청의 시그니처와 비교

```
JWT 수신
  ↓
형식 확인
  ↓
Signature 검증
  ↓
exp 확인 (만료 여부)
  ↓
iss / aud 등 필요한 Claim 확인
  ↓
인증된 사용자로 처리
```

- 잔액: 1,000을 1,000,000으로 변조해서 보냄
- 시크릿 키를 모르니 정확한 시그니처를 생성할 수 없음
- 서버에서는 시그니처 비교 후 위조된 요청임을 확인

### JWT의 단점

- JWT 토큰은 만료 이전에 무효화가 불가능하다.
- `Access Token`과 `Refresh Token`으로 구분한다.

#### Access Token

- 요청 시 사용하는 토큰
- 수명을 짧게(10m) 한다.

#### Refresh Token

- `Access Token`을 갱신하는 토큰
- `Refresh Token`이 살아 있으면 `Access Token`을 재발급해준다.
- DB에 저장

> 로그아웃을 하더라도 Access Token이 만료되기 전까지 토큰 자체는 유효하다.

- 로그아웃 시에 DB에서 RF를 삭제

### 로그아웃을 했을 때는?

- `jti`라는 고유 토큰 ID를 사용
- 블랙리스트에 넣는다.

```
로그아웃 -> blacklist:550e8400-... Redis 저장

JWT 요청
  ↓
서명 검증
  ↓
만료시간 확인
  ↓
Redis Blacklist 확인
  ↓
있음 → 거부
없음 → 허용
```

### 이건 상태가 있는데?

서비스의 요구 사항에 따라 설정한다.

- 일반적인 서비스 -> JWT
- 금융 같은 서비스 -> JWT + Blacklist / Session

```
Stateless를 최대한 유지
→ Access Token을 짧게 사용
→ 만료될 때까지 기다림

즉시 무효화가 중요
→ Redis Blacklist 등 상태 관리
→ Stateless 장점 일부 포기

서버가 인증 상태를 완전히 통제해야 함
→ Session도 좋은 선택
```

## HS256 vs RS256

| 구분        | HS256                  | RS256                     |
| ----------- | ---------------------- | ------------------------- |
| 방식        | 대칭키                 | 비대칭키                  |
| 알고리즘    | HMAC + SHA-256         | RSA + SHA-256             |
| 서명 생성   | 비밀키                 | 개인키                    |
| 서명 검증   | 동일한 비밀키          | 공개키                    |
| 키 개수     | 1개                    | 개인키 + 공개키           |
| 처리 속도   | 상대적으로 빠름        | 상대적으로 느림           |
| 키 배포     | 비밀키 공유 필요       | 공개키만 배포             |
| 적합한 환경 | 단일 서버, 내부 서비스 | 여러 서버, 외부 연동, MSA |

### HS256

- 하나의 비밀키로 서명과 검증

````java
SecretKey key = Keys.hmacShaKeyFor(
    "충분히-길고-안전한-비밀키-최소-32바이트".getBytes(StandardCharsets.UTF_8)
);

// 토큰 생성
String token = Jwts.builder()
    .subject("user-1")
    .signWith(key)
    .compact();

// 토큰 검증
Claims claims = Jwts.parser()
    .verifyWith(key)
    .build()
    .parseSignedClaims(token)
    .getPayload();
    ```
````

### RS256

- 개인키로 서명, 공개키로 검증

```java
// 인증 서버
String token = Jwts.builder()
    .subject("user-1")
    .signWith(privateKey)
    .compact();

// API 서버
Claims claims = Jwts.parser()
    .verifyWith(publicKey)
    .build()
    .parseSignedClaims(token)
    .getPayload();
```

- 공개키가 개인키로 생성된게 맞는지 검증
