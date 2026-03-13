package bubble;

/**
 * [enum] 플레이어의 방향 상태
 *
 * enum 을 사용하는 이유:
 * - boolean 두 개(isLeft, isRight)로 방향을 관리하면
 * - 둘다 true가 되는 잘못된 상태가 생길 수 있음
 * - enum 은 정해진 값 중 하나만 가질 수 있어서 안전함
 *
 * 사용 방법 :
 * playerWay = Playerway.LEFT // 값 설정
 * if(playerWay == PlayerWay.LEFT) {} 값 비교
 *
 *  // 왜 사용할까?
 *  나의 프로젝트나 논리 안에서 데이터의 범위를 지정하고 싶을 때 사용한다.
 */
public enum PlayerWay {
    LEFT, RIGHT

}
