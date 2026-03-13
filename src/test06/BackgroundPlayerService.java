package test06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [클래스 역할] - 플레이어 충돌 감지 서비스(백그라운드에서 계속 돌아감) - 메인 쓰레드는 너무 바쁨
 */
public class BackgroundPlayerService implements Runnable {

    // ImageIcon / Image
    // 화면에 그려서 보여주기 위한 이미지
    // --> 픽셀 데이터에 직접 접근이 불가능 함
    // BufferedImage
    // --> 메모리에 픽셀 배열로 저장된 이미지
    // --> getRGB(x, y)로 특정 좌표의 색상값을 직접 읽을 수 있음
    private BufferedImage image;
    private Player player;

    // 의존성 주입 DI(Dependency Injection)
    // player 를 생성자를 통해서 외부에서 주입 받음
    // 즉, 이 서비스가 직접 플레이어를 생성하지 않고 외부에서 주입 받아 사용 됨.
    public BackgroundPlayerService(Player player) {
        this.player = player;
        try {
            image = ImageIO.read(new File("img/backgroundMapService.png"));
        } catch (IOException e) {
            throw new RuntimeException(" 충돌 감지 이미지를 찾을 수 없습니다 " + e);
        }
    }


    @Override
    public void run() {

        // 게임 끝날 때 까지 계속 실행 되어야 함.
        while (true) {

            Color leftColor = new Color(image.getRGB(player.getX(), player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY() + 25));

            // 바닥/층 감지 좌표 (플레이어 발 아래 두점)
            int bottomLeft = image.getRGB(player.getX() + 20, player.getY() + 55);
            int bottomRight = image.getRGB(player.getX() + 50, player.getY() + 55);
            System.out.println("bottomLeft : " + bottomLeft);
            System.out.println("bottomRight : " + bottomRight);

            if (bottomLeft + bottomRight == -2) {
                // 계속 낙하 시킬 예정
                // 발 아래가 허공 -> 아직 점프/낙하 중이 아닐 때만 낙하 시작해야 함
                // player.isUp() == false -> 점프 중이 아님
                // player.isDown() == false -> 낙하 중이 아님
                if (player.isUp() == false && player.isDown() == false) {
                    player.down();
                }
            } else {
                // 발 아래가 바닥/ 층 -> 낙하 즉시 중단
                player.setDown(false); // while(down) --> false -> while 종료 , Thread 종료
            }

            //  정수 값 - 1 면 흰색
            //  정수 값 -65536 면 빨간색

            // 왼쪽 벽 충돌 감지 판단
            if (isRed(leftColor)) {
                // 충돌 상태 변수 ON
                player.setLeftWallCrash(true);
                player.setLeft(false); // while(false) 종료 --> 이동 멈춤 (Thread 종료)
            } else {
                // 벽에서 벗어나면 즉시 해제 --> 다시 이동 가능하게 설정
                player.setLeftWallCrash(false);
            }

            // 오른쪽 벽 충돌 감지 판단
            if (isRed(rightColor)) {
                // 충돌 상태 변수 ON
                player.setRightWallCrash(true); // 충돌 상태 ON
                player.setRight(false);         // while(right) 종료 --> 이동 멈춤 Thread 종료
            } else {
                player.setRightWallCrash(false); // 벽에서 벗어나면 즉시 해제
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 255, 0 , 0 -> 빨간색
    private boolean isRed(Color color) {
        return color.getRed() == 255
                && color.getGreen() == 0
                && color.getBlue() == 0;
    }

}
