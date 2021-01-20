Q1: Do you build application on top of reusable components?

spring이나 lock library 이런거 쓰나? netty 같은거

Q2: Do you regularly look at the source code of those components?

근데 그 코드 들여다 보나?
"그것도 자주?"

안 볼거다. 왜? 완전히 구현 상세를 다~ 이해하지 않아도 사용할 수 있기 때문에 우리는 안 본다.
그냥 재사용하고 말지.
이게 뭐다? encapsulation의 기본이다!

netty epoll 이냐아니냐 이런거 신경안씀 ㅎㅎ

-----------------



write for stupid programmers


-----------------

- 단순히 코드가 별로라는 "느낌"만으로는 부족하다
- Long term *productivity*
- 뭐가 구체적으로 별로고 어디를 구체적으로 신경써야 하는지 명확하게 정해야 한다
- 다 알겠지만, 코드 읽는게 작성하는 것보다 한 10배는 더 많다. 
  => 그러니까 읽는 시간을 줄이면? 생산성이 쭉쭉 올라가지 않을까?
  => 어떻게?
  => 구 체 적 인 기준이 필요하다
  => 6개월 후의 나, 아니면 전혀 이 이슈에 대해 모르는 사람이, 별도로 뭔가 내용을 찾아보지 않아도 코드를 바로 이해할 수 있다? => 그게 바로 encapsulation이다 
  
  
-----------------

information hiding 이라는 말은 별로다
implementation hiding 이 더 맞다


- invariants? = pre-condition + post-condition (assertion, design by contract)
- invalid state vs valid state 


-----------------
- 이런 원칙들을 달성하려면 구체적으로 어떻게 해야할까?
- command query separation, postel's law

CQS vs CQRS(architecture pattern, not this!)

command는 *관찰 가능한 side-effect*를 발생시키는 모든거
query는 db query말하는게 아니다 데이터를 반환하는 모든거

## command: mutate state
void save(Person person);
void send(T message);
void associate(Auth auth, Request request)

::: return void

이게 항상 가능한 경우인가? 
예시: JPA에서는 불가능할 수 있다. T save(T t)가 대표적. void save()로 사용하면 auto increment id 를 사용하기 어렵다


## queries: do not mutate observable state
Address[] getAddress(long personId)
T create();
Foo map(Bar bar)

::: return something!
::: idempotent: asking a question should not change the answer
::: safe to invoke(no side-effect)


구분하기 쉽다. 

-------------------------

샘플 코드에서
질문1: 뭐가 query고 뭐가 command냐?
질문2: side-effect free query인가?
   => 그렇지 않다.
질문3: event 발행은 side-effect 인가? (애매한가?)
   => 그렇다.
   => 파악하기 쉬운 방법: event handler를 생각해봐라. 어떤 method signature를 갖겠는가? - return void - 따라서 command이다.
   => query를 호출하면 -> event 가 발행되고 -> 발행한 event는 event handler를 호출하는데 -> event handler는 command인데 -> command는 side-effect를 발생시키므로 -> query는 결국 side-effect를 가지고 있게 된다



--------------------------

CQS makes it easier to REASON about the code

CQS를 철저하게 모든 코드에서 지킨다면, 
전체 구현을 상세히 들여다 보지 않아도 
코드를 "믿게" 되기 시작할 것이다. : high-level understanding

우리가 Netty, Spring을 대하는 것처럼.
잊지말자 우리는 코드를 읽는 시간이 훨씬 많다는 거


---------------------------

## Postel's Law (robustness principle: network protocol?)
Q1. How can you TRUST that a command accepts your input?
Q2? How can you TRUST a query to return a useful response?


=> Be conservative in what you send,
Be liberal in what you accept

- guarantees are good.
- strong guarantees are better.



























































