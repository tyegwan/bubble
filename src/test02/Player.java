package test02;

import javax.swing.*;

public class Player extends JLabel implements Moveable {

    // 플레이어의 현재 좌표 상태 값
    private int x;
    private int y;

    // 좌우 방향 이미지(방향키에 따라서 이미지 전환)
    private ImageIcon playerR;
    private ImageIcon playerL;

    // 속도 상수
    private final int SPEED = 4;           // 좌우 이동 속도 (픽셀)
    private final int JUMP_SPEED = 2;      // 점프/낙하 속도
    private final int JUMP_HEIGHT = 130;   // 점프 최대 높이

    // 이동 상태 플래스
    // true = 해당 방향으로 이동 중 (while 루프 조건)
    // false = 멈춤 (while 루프 탈출 -> Thread 종료)
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    // BubbleFrame 의 Key 이벤트에서 호출할 수 있도록 setter 설정
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }


    public Player() {
        initData();
        setInitLayout();
    }

    private void initData() {
        playerR = new ImageIcon("img/playerR.png");
        playerL = new ImageIcon("img/playerL.png");
    }

    private void setInitLayout() {
        // 캐릭터 초기 위치 설정
        x = 55;
        y = 535;
        setSize(50, 50);
        setIcon(playerR); // 초기 방향 설정
        setLocation(x, y);
    }


    @Override
    public void left() {
        if (left) {
            return;  // 이미 이동 중이면 중복 Thread 생성 방지
        }
        left = true;
        setIcon(playerL);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // left 가 true 인 동안 계속 이동
                // keyReleased 에서 setLeft(false) 가 호출이 되면 while 탈출
                while (left) {
                    x = x - SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void right() {
        if (right) {
            return;
        }
        right = true;
        setIcon(playerR);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (right) {
                    x = x + SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void up() {
        // 점프 기능을 어떻게 구현할까?
        if(up) {
            return;
        }
        up = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 130 / 2 --> 65 반복
                for (int i = 0; i < JUMP_HEIGHT / JUMP_SPEED; i++) {
                    y = y - JUMP_SPEED;
                    setLocation(x,y);

                    try {
                        Thread.sleep(5); //5ms 간격 (낙하 보다 느리게 설정 낙하 3ms)
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                up = false; // 최고점 도달 하고 -> 점프 상태 해제
                down(); // 자동 낙하 시작


            }
        }).start();

        System.out.println("player up() 호출");
    }

    @Override
    public void down() {
        System.out.println("player down() 호출");
    }
}
