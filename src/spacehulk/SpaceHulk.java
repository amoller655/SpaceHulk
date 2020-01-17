/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacehulk;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author 20AMoller
 */
public class SpaceHulk extends JFrame {
    
    public SpaceHulk() {
        initUI();
    }

    private void initUI() {
        
        Board board = new Board();
//        DiceWindow diceBox = new DiceWindow(1, 1);
//        diceBox.setVisible(true);
        add(board);
//        add(diceBox);

        setTitle("Space Hulk");
        
        setSize(400, 400);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SpaceHulk game = new SpaceHulk();
            game.setVisible(true);
        });
    }
    
}
