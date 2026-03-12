package test01;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initData();
        setInitLayout();
        addEventlistener();
    }

    private void addEventlistener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case  KeyEvent.VK_LEFT :
                        player.left();
                        break;
                    case  KeyEvent.VK_RIGHT :
                        player.right();
                        break;
                    case  KeyEvent.VK_UP :
                        player.up();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
    } // end of

    public static void main(String[] args) {
        new BubbleFrame();
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false); // 창 크기 고정
        setLocationRelativeTo(null); // 화면 정중앙 배치 (프레임)

        backgroundMap.add(player);

        setVisible(true);
    }

    private void initData() {
        setTitle("버블버블게임");
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png"));
        setContentPane(backgroundMap);

        player = new Player();

    }
}
