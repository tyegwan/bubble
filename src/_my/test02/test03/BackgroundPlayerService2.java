package _my.test02.test03;

import test03.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundPlayerService2 implements Runnable{
    private BufferedImage image;
    private Player player;

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

    }


}
