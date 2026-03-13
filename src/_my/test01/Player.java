package _my.test01;

import javax.swing.*;

public class Player extends JLabel implements Moveable {

    // 플레이어의 현재 좌표 상태 값
    private int x;
    private int y;

    // 좌우 방향 이미지(방향키에 따라서 이미지 전환)
    private ImageIcon playerR;
    private ImageIcon playerL;

    // 속도 상수
    private final int SPEED = 4;         // 좌우 이동 속도 (픽셀)
    private final int JUMP_SPEED = 2;    // 점프/낙하 속도
    private final int JUMP_HEIGHT = 130; // 점프 최대 높이

    // 이동 상태 플래스
    // true = 해당 방향으로 이동 중 (while 루프 조건)
    //false = 멈춤 (while 루프 탈출 -> Thread 종료)
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;



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
        setSize(50,50);
        setIcon(playerR); // 초기 방향 설정
        setLocation(x, y);
    }

    @Override
    public void left() {
        setIcon(playerL);
        System.out.println("player left() 호출");
    }

    @Override
    public void right() {
        setIcon(playerR);
        System.out.println("player right() 호출");

    }

    @Override
    public void up() {
        System.out.println("player up() 호출");

    }

    @Override
    public void down() {
        System.out.println("player down() 호출");


    }
}
