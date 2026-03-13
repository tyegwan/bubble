package _my.test02.test03;

import test03.BackgroundPlayerService;

import javax.swing.*;

public class BubbleFrame2 extends JFrame {
    public BubbleFrame() {
        initData();
        setInitLayout();
        addEventListener();
        // 충돌감시 백그라운드 서비스 시작

    }

    private void initData(){
        setTitle("버블버블게임");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
