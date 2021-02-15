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










------------------------------
SOLID 

### do you/why you want to write SOLID code?


SOLID - 딱딱하다?
아니다 
supple - 부드러운거
소프트웨어 라는 말에 있따 
하드웨어가 아니자나 "소프트"웨어자나

우리는 변경사항을 그 시점에 다 알 수 없기 때문에,
그래서 


SOLID가 아닌거
- 프레임워크가 아니다
- 라이브러리도 아니다
- 어떤 기술이 아니다 
=> 어떤 기술이나 어떤 언어나 어떤 상황에서도 모두 적용할 수 있다
java, C#, js, ...

측정할 수 있거나 잴 수 있는게 아니다
- pattern도 아니다
- 어떤 목표도 아니다 -> SOLIDness를 측정할 수 없기 때문에

SOLID code is different
계속 다가갈수만 있고 달성할 수는 없다





그래서 oop 언어를 쓴다고해서 oop 스타일로 코딩 할 수 있는건아니다
solid를 따르면 근데 할 수 있다


절차식으로 익숙해진 머리를 말랑말랑하게 해서
SOLID 코드에 익숙해져 보도록 하자
처음에는 낯설기 때문에 거부감을 느끼는게 당연하지만 
코드가 매우 많아지고 프로젝트가 커지면 OOP가 효과적이다


SOLID의 목적: be more productive
by making code more maintainable
through decomposition and decoupling 

**SOLID is a reaction to a set of design smells**
어떤 문제인지에 따라 SOLID가 최고의 해답이 아닐수도 있다 (성능 문제 등)

그러나 아래의 design smell들은 SOLID가 해결할 수 있다
- Rigidity - the design is difficult to change
- Fragility - the design is easy to break
- Immobility - the design is difficult to reuse
- Viscosity - It's difficult to do the right thing
- Needless Complexity - over design

개발자들은 약간 지금 작성하는 코드를 
"일반적인" 용도로 막~ 작성하고 쓰지도 않을거를 가지고 막 일반화 해서 코딩하려는 욕구가 있음

리팩토링 하고 나서도 complexity가 개선되지 않으면?
미안하지만 헛수고 한거다...


--------------------
SRP가 제일 중요해서 먼저 나온게 아니라 
그냥 아무런 순서 없이 아래처럼 조합하면 
SOLID라는 이름이 나와서 그런거

각각의 원칙은 매우 긴밀하게 조합해서 적용될 수 있다
"이번주에는 SRP를 적용해보자~" -> 이럴 수 없다. 오히려 더 나빠질 수 있다
"이 모듈에서 / 이 클래스에서 / 이 패키지에서" SOLID를 조금 적용해보자
이럴수는 있다


---------------

설계의 악취 
1. 경직성: 단순한 방법으로 소프트웨어를 변경하기 어려울 때: "코드가 생각했던 것 보다 훨씬 복잡하군!"
2. 취약성: 한 군데를 변경했을 때 프로그램의 많은 부분이 잘못 될 때: "코드를 고칠수록 점점 나빠지는 군!"
3. 부동성: 유용하게 쓸 수 있는 부분을 포함하지만, 그 부분을 분리하기 위험하거나 수고가 클 때: "코드를 가져다 쓰기보다 새로 만드는 게 낫겠군!"
4. 점착성: 변경사항을 마주했을 때, 설계를 유지하는 방법보다 엉터리 방법이 더 쉬울 때: "아무튼 돌아가긴 하니까 문제 없겠군!"
5. 불필요한 복잡성: 지금 유용하지 않은 요소가 설계에 있을 때: "이건 왜 필요한지 잘 모르겠군!"
6. 불필요한 반복: 조금씩 다른 중복 코드가 산재해 있을 때: "익숙한 코드가 많이 있군!"
7. 불투명성: 모듈을 이해하기 어려울 때: "단번에 이해하기 어렵군!"

=> 코드 리뷰 할 때 이런 7가지 항목들을 두고 하나씩 체크해보자

### SRP 
(끝나고 나서 전체 클래스 의존성을 그래프로 그리고
각 클래스별로 책임이 이동한 걸 보여주기)


============= 
하나 가지고는 좋아진 걸 느낄 수 없을거다.
SOLID는 모두 같이 적용되어야 좋음

GOTO conference; Greg Young 이라는 사람이 아래와 같은 말을 함
"Developers have a tendency to attempt to solve specific problems with general solutions"
=> this leads to coupling and complexity

"so, instead of being general, code should be specific"
SRP를 지키면, 각각의 class는 매우 문제에 집중하는 specific 해진다

근데 만약에 generality가 필요하면 어쩌나?
code duplication 이 발생하는데?
=> role interface 를 도입하라!
=> IStoreWriter, IStoreReader, IFileLocator
=> 이런 추상화는 "발견"하는거지, 내가 만드는게 아니다 

**Reused abstraction principle**
어떤 인터페이스나 추상 클래스의 구현체가 몇 개 안되면,
추상화가 구린거다.

추상화는 그래서 뭔데?
=> elimination of the irrelevant and the amplification of the essential
관련 없는 건 제거하고 본질적인건 과장하기.
예시) lock 인터페이스 - ttl을 내가 알 필요가 있나?


((OCP 부분 - SOLID concrete 에서 약 7분 경의 사각형 10개 그림 꼭 내용 넣기)
Reused abstraction principle을 위반하는 형태
=> 어떤 인터페이스가 특정한 구현에만 꼭 맞게 작성이 되어 있어서 
구현을 재사용 할 수 없을 때.

그래서 인터페이스를 먼저 선언하지 말고,
구현체를 먼저 만들어라. **SRP에 입각해서!!!!**
그리고 나서 잘 살펴봐라. 어디가 공통점이 있는지.
그래서 interface는 설계되는게 아니다.
시스템이 진화하면서 **발견**되는 거다. 

Start with concrete, 
then discover abstractions as commonality emerges.

Rule of three.
=> 3개가 모이기 전에는 리팩토링 뿐 아니라 추상화도 하지 말자ㅎㅎ 
=> 우리는 조그만 중복이 발생해도 helper나 static method를 만드는 경향이 있다. 
=> 근데 비슷한게 보여도 많은 sample이 모이지 않으면 뭐가 비슷한지/뭐가 다른지에 대한 데이터가 없어서  
=> 그러면 잘못된 helper나 잘못된 추상화로 이어질 가능성이 높다.
=> 그러니까 3개가 되기 전에는 그런 유혹을 참자.
=> 나중에 하면 더 좋은 코드가 나올 수 있다.



-----

내용 추가 (클린 소프트웨어) 
하나의 일만 해야한다는 SRP가 아니라 method이다. 
method가 진짜 하나의 일만 해야한다는거고, SRP는 하나의 책임이다.



SRP의 맥락에서 책임은 '변경을 위한 이유'로 정의한다
한 클래스를 변경하기 위한 한 가지 이상의 이유를 생각할 수 있다면, 그 클래스는 한 가지 이상의 책임을 갖는 것이다.

각각의 책임이 분리되어야 하나?
=> 이는 애플리케이션이 어떻게 바뀌느냐에 따라 달려있다.
서로 다른 시간에 두 가지 책임의 변경을 유발하는 방식으로 바뀌지 않는다면, 분리할 필요가 없다.
억지로 분리하면 -> **불필요한 복잡성** 이 나타난다

영속성?
business rule과 영속성
업무 규칙은 자주 바뀌고 영속성은 자주 바뀌지 않지만, 바뀌는 이유도 완전히 다르다

우리는 책임을 너무나 자연스럽게 결합한다.
가장 어렵다.

## 우연한 중복과 진짜 중복
소스코드가 서로 같다. 입력도 같고 출력도 같다. 메서드 이름도 같다.
어떻게 해야 하지? 
1. 중복 코드는 나쁜 것 이다. Util 클래스를 만들고 같은 부분을 추출해서 리팩토링 하자.
2. 냅둔다. 




리팩토링의 방향은 항상 복잡성이 줄어드는 방향이어야한다.
리팩토링을 한 뒤에 복잡성이 늘어났다면? 좋지 않다. 
그래서 항상 TEST 를 같이 해야한다...
-> TDD는 테스트 기법이 아니다. 설계 기법이다. interface를 중심으로 생각하게 만들어주거든.

끝

-----
Greg Young 
developers have a tendency to 




----- 


### OCP
Open for extensibility, closed for modification
확장에는 열려있고 수정에는 닫혀있다.

유일한 예외: bug fixing OK

왜? 
어떤 A 클래스가 작성되고, 다른 클래스나 메서드가 방금 작성된 A 클래스에 
의존하기 시작한다고 해보자.
그러면 A 클래스를 조금만 수정해도 A에 의존하는 모든 곳들에 영향을 받으니까.
이걸 일일히 조사하는거... 우리 이미 많이 해봤다.

Favour composition over inheritance
상속은 확장에 불리하다 - strategy pattern
composite, decorator pattern

((상속은 확장에 불리한 이유 설명하기))
OCP - 3번째 강의 (Refactoring the Example Code 3:00 쯔음)

Factory Method pattern 


#### append-only
변경은 진짜 코드를 한 글자라도 바꾸는거
코드를 CRUD 관점에서 생각할 수 있다.
코드를 생성하고 읽고 변경하고(update), 삭제하고...

OCP는 source code에 대해서 Create or Read만 해야한다고 말한다
이건 뭔가 이상한데, 우리가 코딩하던 방식이 아니기 때문이다.

장점은 무엇인가? 
이렇게 했을 때 장점이 있나?

너가 작성하는 소프트웨어 시스템에서 변경을 없앨 수만 있다면,

git 같은 시스템을 생각해보자
이걸 소스코드에도 적용하면 효과적일까?

그럼 이 말이 매번 완전한 코드를 완벽하게 만들라는 말일까?
완벽한 시스템으로 만들었다고 하더라도 그 시점에 우리의 요구사항을 완벽하게 알 수 없기 때문에
결국 변경을 해야 하고 게다가 우리는 사람이라서 항상 실수를 한다.

strangler pattern
우리가 모듈을 교체할 때 자연스럽게 하는거
1. 새로운 시스템을 만든다
2. client를 새로운 시스템을 사용하도록 
3. 점진적으로 변경한다
4. 모든 client가 새로운 시스템을 사용하면
5. old 시스템을 삭제한다
=> 이걸 코드 레벨에서도 할 수 있을까??

근데 같은 코드베이스, 내가 수정할 수 있는 범위에서는
IDE 들이 리팩토링 기능을 지원해주기 때문에 
이렇게까지 안해도된다

내가 근데 만약에 open source를 작성하고 있고
내 client가 누군지 모르면은 
이런 전략을 해볼 수 있다.

----------
내용추가

확장에 대해 열려 있다.
-> 모듈의 행위를 변경할 수 있다.

수정에 대해 닫혀 있다.
-> 소스 코드를 전혀 수정하지 않는다!

어떻게 할 수 있지?
=> 답은 추상화다.
*추상 클래스는 자신을 구현하는 클래스보다도 나를 사용하는 클라이언트에 더 밀접하다*
Client가 interface 를 소유한다. 

일반적으로, 모듈이 얼마나 닫혀있던지 간에 닫혀 있지 않은 것에 대한 변경은 항상 존재한다!
**모든 상황에서 자연스러운 모델은 없다!**
폐쇄는 완벽할 수 없으므로, 전략적이어야 한다.
닫혀 있는 변경의 종류를 선택해야 한다. -> 어떤 행위를 추상화할 건지 선택해야만 한다
결국 경험으로 얻는 통찰력이 필요.
사용자와 사업에 대한 관심과 시야가...만든다...

'올가미 놓기'
- 첫번째 총알은 그냥 맞는다.
- 그 이후 총알은 확실히 대비한다.

=> 변경 시뮬레이션 하기: 첫번째 총알을 맞기로 결정했으면, 자주, 그것도 빨리 맞는게 중요하다
- 먼저 사용해보기: 테스트를 먼저 작성한다
- 짧은 주기 개발: agile
- 가장 중요한 기능을 우선 개발

"순서에 대해 닫으려면" (클린 소프트웨어, 140p)

=================

"소프트웨어 아키텍처를 공부하는 가장 근본적인 이유가 바로 이 때문이다. 만약 요구사항을 살짝 확장하는데 소프트웨어를 엄청나게 수정해야 한다면, 그 소프트웨어 시스템을 설계한 아키텍트는 엄청난 실패에 맞닥뜨린 것이다."



어디를 닫을 것인가?


----------

### LSP 
You should favor composition over inheritance
LSP is about polymorphism.
이건 약간 논문으로...
이해가 잘 안됐음...

Robert martin 
=> Subtypes must be substitutable for their base types
(not very helpful, sounds very abstract)

예제를 많이 익히는게 좋지만...
다시 정의해보면

"If you imagine that you have a client,
that client should be able to consume any implementation 
without changing the correctness of the system"

'the correctness of the system'의 정확한 기준?
'정확하다'는 기준이 무엇인가? 

행동을 바꾸는 건 일단 아니다. 왜? 다형성은 행위를 바꾸는거니까
'the correctness of the system' 이건 application 마다 다를 수 밖에 없다

In the end, the correctness of the system is application specific,
but you can think about the correctness of the system as the superset of all the correct behavior that a system might exhibit.

////
아... 그러니까 
다운로드라고 하면은

1. storage download가 있고: correct behavior
2. thumbnail download가 있고: correct behavior
3. file cache download가 있는데: correct behavior

the superset of all the correct behavior: {1,2,3}
따라서 이 셋중에 하나기만하면은 
'the correctness of the system' 이라는 말이구나...ㅇㅋㅇㅋ
////

common the correctness of the system:
: the system should not crash

client가 A 구현을 사용할때는 crash 없다가
B 구현을 사용할때 crash가 일어나면, 
then you changed the correctness of the system 


#### When LSP violated?
throw UnsupportedOperationException 

코드 어딘가에서 저걸 던지고 있다는거는 
'the correctness of the system'이 무너진 상태라는 거. 
왜냐면 일부 operation이 저 예외로 동작하지 않을테니까

ex) Collection - UnmodifiableList
기존에 그냥 List를 잘 사용하고 있던 서버에
갑자기 UnmodifiableList를 사용하게 되면
일부 연산이 제대로 동작하지 않을 '위험'이 있기 때문에
'the correctness of the system'가 깨질 수 있다


ex2) downcast 
=> 하위 타입이 뭔지 확인하고 뭔가를 처리한다는거는 
인터페이스에서 말하는 약속을, 하위 구현체가 제대로 지원하지 않을 가능성이 높기 때문
- 그러나 하위타입을 체크한다고 해도 실제로 LSP를 위반하지 않을 수는 있다
- 여기서 우리가 해야할 일은 하위 구현 타입을 체크하면서, 내가 the correctness of the system을 깨뜨리지 않을 수 있는 fallback rule이 항상 있는 지가 중요
- fallback rule이 없는 상태에서, 기대하는 하위 구현 타입이 아니라면, 시스템은 잘못 동작하기 쉬워진다. 
  
ex3) (most common, subtle) Extracted interfaces 
- concrete class 에서 interface를 만드는 행위
- 가장 흔하게 LSP가 위반되는 경우 
- intellij에서 class를 interface로 만들기! 라고 하면은 자동으로 interface를 만들어주는데, 이건 거의 모든 경우에 문제가 된다
- very very likely to violate LSP




**LSP is often violated by attempts to REMOVE features**
- UnmodifiableList는 List에서 `add,remove,clear, ...`를 없애려고 하는거다
- 근데 interface에서 메서드를 삭제할 수는 없으니... throw UOE

=> 그래서 인터페이스의 모든 부분을 다 구현하든지, 아예 구현하면 안된다.

interface에 더 많은 멤버, 메서드, 구현이 있을 수록
하위 구현에서 뭔가 지우고 싶은 기능이 많아질 가능성이 높기 때문에
interface의 메서드가 많을 수록, LSP를 위반할 가능성도 같이 높아진다

=> Reused abstractions principle compliance
INDICATES
LSP compliance

어떤 interface에 대해, 다수의 구현체가 있다면
그건 이미 그 interface를 통해 다양한 동작을(with correctness) 할 수 있다는 증명이 되는 셈이므로
LSP도 덩달아 만족할 가능성이 높다.
항상 그렇다는 건 아니고 그런 경향이 있다!



#### SOLID Isn't : (6. The ISP: SOLID Isn't)
Duplo example
-> duplo 4개로 dragon을 만들어달라

Granularity: 입자의 단위?  
Granularity 가 smaller 할 수록 more recognizable 하다.


lots of classes


hundreds of classes which contain dozens lines of code (smaller Granularity)
vs
few class which contains hundreds/thousand lines of code (bigger Granularity)

duplo 드래곤이 더 알아보기 쉬웠나?
lego 드래곤이 더 알아보기 쉬웠나?

재사용성은 덤이다. 



### ISP
client should NOT be forced to depend on method they do not use

**Who owns the interfaces?**
who defineds the interfaces?

==> 일단 interface가 필요한 이유는 loose coupling이고,
concrete class가 그 인터페이스를 "구현"하려고 필요한게 아니다

오히려 그 interface를 사용하는 "CLIENT"가 
CLIENT가 interface를 정의한다.
왜? client가 없으면 그걸 사용하는 곳이 없으니까


Favor Role interfaces over header interfaces
header class: cpp header file 같은 interface. extracted interface와 같은 맥락. big interface, lots of members on the interface 
role interface: very few members. client가 interface를 정의하므로, 정확히 딱 필요한 것만 interface에 정의가 됨 
extreme role interface: an interface with only a single member.

FileStore 예제에서 보듯, 3개의 메서드만 있는 interface도 충분히 문제가 될 수 있다.(LSP 위반)


ISP help LSP issues
**문제는 기능을 추가하는게 아니라 빼는데에 있다**





#### Functional
closure vs object vs function?

object are data with behaviour
closure are data with behaviour


that if you follow the SOLID principles to some of its logical conclusions, 
you end up with some object-oriented code that's not too far from being functional, 
or it could easily become functional code, so that's interesting. 

==> 성능 향상?
==> functional programming 은 상태가 없으므로 근본적으로 멀티스레딩 이슈에 대응하는...
==> 결국 고성능의 아키텍처를 구축하기 용이해진다 



### DIP 

favor composition over inheritance

composition을 어떻게 하는지 알려주겠다. 크게 두 가지 방법.
1. composite pattern
2. decorator pattern


























## 말하지 못한 내용들 
- 추상화 레벨: 입력과 출력으로부터 얼마나 떨어져 있는가?
- client가 interface를 소유한다 (클린 소프트웨어 410p)
- 경계: 선 긋기 / 경계 횡단하기  
- 패키지 안정성 원칙: 내가 추상화된 만큼 안정적이어야 한다 (탁자랑 동전 넘어뜨리기 뭐가 더 안정적인가? 비유)
- 무엇이 세부사항인가? 웹은 세부사항이다 DB는 세부사항이다 










