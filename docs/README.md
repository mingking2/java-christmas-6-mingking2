# 미션 - 크리스마스 프로모션

## 🚀 요구사항

### 🖥️ 프로그래밍 요구사항
#### JDK 17 버전 사용

- 프로그램은 JDK 17 버전에서 정상적으로 실행되어야 합니다.
- JDK 17에서 정상적으로 동작하지 않을 경우 0점 처리됩니다.

#### 프로그램 실행 시작점

- 프로그램 실행의 시작점은 Application의 main() 메서드여야 합니다.

#### 라이브러리 사용 제한

- `build.gradle` 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않습니다.

#### Java 코드 컨벤션 준수

- Java 코드 컨벤션 가이드를 준수하여 프로그래밍합니다.

#### `System.exit()` 미사용

- 프로그램 종료 시 `System.exit()`를 호출하지 않아야 합니다.

#### 테스트 코드 작성

- JUnit 5와 AssertJ를 이용하여 기능 목록이 정상 동작함을 테스트 코드로 확인합니다.

#### Exception 처리

- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 해당 부분부터 입력을 다시 받습니다.
- `Exception`이 아닌 명확한 유형의 예외(`IllegalArgumentException`, `IllegalStateException` 등)를 처리합니다.


### ✏️ 기능 요구사항
#### 예상 방문 날짜 입력
   - 사용자는 12월 중 방문할 식당의 날짜를 숫자로 입력합니다. 
   - 입력값은 1에서 31까지의 숫자여야 하며, 그렇지 않을 경우 에러 메시지가 표시됩니다.

#### 주문 메뉴 입력
   - 사용자는 주문할 메뉴와 해당 메뉴의 수량을 입력합니다.
   - 메뉴는 메뉴판에 있는 항목이어야 하며, 그렇지 않을 경우 에러 메시지가 표시됩니다.
   - 메뉴의 수량은 1 이상의 숫자여야 하며, 그렇지 않을 경우 에러 메시지가 표시됩니다.
   - 중복된 메뉴를 입력하거나 메뉴 형식이 잘못된 경우 에러 메시지가 표시됩니다.

#### 이벤트 혜택 및 정보 확인
   - 입력된 정보를 기반으로 주문 메뉴, 할인 전 총 주문 금액, 증정 메뉴, 혜택 내역, 총 혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 배지 내용을 출력합니다.
   - 혜택이 적용되지 않거나 배지가 부여되지 않은 경우에는 해당 부분에 "없음"이라는 내용이 출력됩니다.


### ➕ 추가된 요구사항

#### 입출력 클래스 구현

- `InputView` 및 `OutputView` 클래스를 구현합니다.
  - 라이브러리 `camp.nextstep.edu.missionutils.Console`을 사용하여 입출력을 처리합니다.

#### 입력값 유효성 검사

- 입력값이 유효하지 않을 경우 에러 메시지를 출력하고 해당 부분부터 다시 입력을 받습니다.

#### 주의 사항

- 총 주문 금액이 10,000원 이상부터 이벤트가 적용됩니다.
- 음료만 주문하는 경우 주문이 불가능합니다.
- 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.

---

## 👨🏻‍💻 프로젝트 구조

```agsl
├─main
│  └─java
│      └─christmas
│          │  Application.java
│          │
│          ├─constant
│          │      DateConstant.java
│          │      DiscountConstant.java
│          │      ErrorConstant.java
│          │      MenuConstant.java
│          │      UtilConstant.java
│          │
│          ├─controller
│          │      OrderController.java
│          │
│          ├─dto
│          │      DateDTO.java
│          │      OrderMenuRequest.java
│          │      OrderResponse.java
│          │
│          ├─model
│          │  │  Date.java
│          │  │  Menu.java
│          │  │  MenuType.java
│          │  │  Order.java
│          │  │  OrderMenu.java
│          │  │
│          │  └─discount
│          │          ChristmasDiscount.java
│          │          Discount.java
│          │          GiftPriceDiscount.java
│          │          SpecialDiscount.java
│          │          WeekdayDiscount.java
│          │          WeekendDiscount.java
│          │
│          ├─repository
│          │      OrderRepository.java
│          │
│          ├─service
│          │      DiscountService.java
│          │      OrderService.java
│          │
│          ├─util
│          │      Format.java
│          │
│          ├─validator
│          │      DateValidator.java
│          │      MenuValidator.java
│          │
│          └─view
│                  ErrorView.java
│                  InputView.java
│                  OutputView.java
│
└─test
    └─java
        └─christmas
                ApplicationTest.java
                DiscountServiceTest.java
                OrderServiceTest.java

```


- **Controller**: 주문 시스템을 제어하고 사용자 입력을 처리하는 클래스
- **Model**: 주문, 메뉴, 날짜 및 할인과 관련된 클래스
- **View**: 사용자에게 결과를 제시하는 클래스
- **Service**: 주문 생성, 할인 및 이벤트 혜택을 계산하는 클래스
- **Repository**: 주문 정보를 저장하고 검색하는 클래스
- **DTO**: 데이터 전송 객체로 데이터 전송에 사용
- **Constant**: 상수 값을 포함하는 클래스
- **Validator**: 사용자 입력을 검증하기 위한 클래스
- **Util**: 데이터 형식 변환 및 기타 목적을 위한 유틸리티 클래스


### 🔎 자세한 클래스 및 모듈 정보
#### Controller
- OrderController: 주문 흐름을 제어하고 결과를 출력하는 클래스입니다.

#### Model
- Date: 주문 날짜를 처리합니다.
- Menu: 사용 가능한 메뉴 및 메뉴 유형을 정의하는 열거형 클래스입니다.
- MenuType: 메뉴 유형을 정의하는 열거형 클래스입니다.
- Order: 주문 정보와 총 금액을 포함하는 클래스입니다.
- OrderMenu: 주문된 메뉴에 대한 정보를 포함하는 클래스입니다.

##### Model.discount
- Discount: 할인과 관련된 인터페이스입니다.

##### Model.discount.impl
- ChristmasDiscount: 크리스마스 이벤트 할인을 계산하는 클래스입니다.
- GiftPriceDiscount: 선물 이벤트 혜택을 처리하는 클래스입니다.
- SpecialDiscount: 특별 이벤트 할인을 계산하는 클래스입니다.
- WeekdayDiscount: 평일 이벤트 할인을 계산하는 클래스입니다.
- WeekendDiscount: 주말 이벤트 할인을 계산하는 클래스입니다.

#### DTO
- DateDTO: 날짜 데이터를 전송하기 클래스입니다.
- OrderMenuRequest: 메뉴 및 수량 데이터를 전송하기 클래스입니다.
- OrderResponse: Id 및 총금액 데이터를 전송하기 클래스이다.


#### Repository
- OrderRepository: 주문 정보를 저장하고 검색하는 클래스입니다.

#### Service
- OrderService: 주문 생성 및 저장하는 서비스 클래스이다.
- DiscountService: 할인 및 이벤트 혜택을 계산하는 서비스 클래스입니다.

#### Util
- Format: 데이터 형식 변환을 위한 유틸리티 클래스입니다.

#### Validator
- DateValidator: 날짜 입력을 검증하는 클래스입니다.
- MenuValidator: 메뉴 주문 입력을 검증하는 클래스입니다.

#### View
- ErrorView: 에러 메시지를 출력하는 클래스입니다.
- InputView: 사용자 입력을 받는 클래스입니다.
- OutputView: 결과를 출력하는 클래스입니다.