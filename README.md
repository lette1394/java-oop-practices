oop practices in JAVA

- number baseball: 숫자 야구 (성공)
- ladder: 사다리 타기 (실패)
- calculator: 문자열 계산기 (실패)
- calculator2: 문자열 계산기 (진행 중)

-------

## calculator2 (wip)
### TODO: support timeout
- 몇 분 이상 지나면 계산 실패 처리
- 몇 초 이상 지나면 계산 실패 처리
- 몇 밀리초 이상 지나면 계산 실패 처리


### TODO: configurable calculator
- overflow/underflow 가 나면 실패한다
- overflow/underflow 가 나면 array based operator를 사용한다
- 계산 중간에 실수형으로 변환되면 실패한다 
- 계산 중간에 실수형으로 변환되면 강제로 정수형으로 바꾼다
- 계산 중간에 실수형으로 변환되면 실수형으로 계산한다
- 처음부터 실수형으로 계산한다
- 실수형 정확도 설정


### TODO: print detail expression process
- 계산 과정을 추적할 수 있다
- 어디서 중간에 어떻게 계산되었는지 확인 가능
- 결과 및 과정은 다양한 포맷으로 제공 가능 (html, string, text, json, ...)


### TODO: support advanced operator
- 사칙연산 외에 다른 연산자도 추가한다
- 제곱근, n제곱, 팩토리얼, 괄호(), 로그, 미분, 적분


### TODO: support various environment
- console (jar) 지원
- web-server 지원

