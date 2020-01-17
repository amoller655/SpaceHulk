package spacehulk;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 20amoller
 */
public class DiceWindow extends JFrame{
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static int POS_X;
    private static int POS_Y;
//    private static final Color velvet = new Color(114, 7, 18);
    
//    rgb(114, 7, 18)
    public DiceWindow(int x, int y)
    {
        POS_X = x;
        POS_Y = y;
        initDiceBox(x, y);
    }

    private void initDiceBox(int x, int y) {
        
        DiceBox dicebox = new DiceBox();
        add(dicebox);
        
        JButton rollBtn = new JButton("Roll");
        rollBtn.setBackground(Color.red);
        
        rollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt){
                int roll = (int) (Math.random() * 6) + 1;
                String numStr = dicebox.num2Str(roll);
                int xRand = (int) (Math.random() * 50);
                int yRand = (int) (Math.random() * 50);
                dicebox.addDie(new Die(xRand, yRand, numStr));
                System.out.println(dicebox.num2Str(roll));
            }
        });
        dicebox.add(rollBtn);
        
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                dicebox.clearDice();
            }
        });
        dicebox.add(clearBtn);
//        add(btnPanel);
        
        
        setTitle("Dice Box");
        
        setSize(WIDTH, HEIGHT);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(x, y);
        
    }
    
    public class Die {
        private static final int WIDTH = 50;
        private static final int HEIGHT = 50;
        private Image image;
        
        private int x;
        private int y;
        private Die(int x, int y, String num) {
            String file = "SpaceHulkImages/Dice/Stearler/" + num + ".png";
            ImageIcon ii = new ImageIcon(file);
            System.out.println(file);
            image = ii.getImage();
        }
        
        public int getX() { return x; }
        
        public int getY() { return y; }
        
        public Image getImage() { return image; }
    }
    
    private class DiceBox extends JPanel implements ActionListener{
        private static final int WIDTH = 100;
        private static final int HEIGHT = 100;
        private final Color velvet = new Color(114, 7, 18);
        private final int DELAY  = 15;

        private Timer timer;
        
        private ArrayList<Die> dice = new ArrayList();
        private DiceBox()
        {
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            this.setBackground(velvet);
            timer = new Timer(DELAY, this);
            timer.start();  
        }
        
        private void addDie(Die die) {
            dice.add(die);
        }
        
        private void removeDie(Die die) {
            dice.remove(die);
        }
        
        private Die getDie(int index) {
            return dice.get(index);
        }
        
        private void clearDice() {
            dice = new ArrayList();
        }
        
        private String num2Str(int num)
        {
            switch(num)
            {
                case 1:
                    return "one";
                case 2:
                    return "two";
                case 3:
                    return "three";
                case 4:
                    return "four";
                case 5:
                    return "five";
                case 6:
                    return "six";
                default:
                    return null;
            }
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for(Die die : dice) {
                g.drawImage(die.getImage(), die.getX(), die.getY(), this);
                System.out.println("DRAWING DICE");
            }
            
            Toolkit.getDefaultToolkit().sync();
        }
        
        @Override
        public void actionPerformed(ActionEvent evt) {
            repaint();
        }
    }
    
}

