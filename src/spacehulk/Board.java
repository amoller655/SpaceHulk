/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacehulk;

import Models.Direction;
import Models.Facing;
import Models.Marine;
import Models.Stealer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import spacehulk.Tiles.Door;
import spacehulk.Tiles.Floor;
import spacehulk.Tiles.Wall;

/**
 *
 * @author 20AMoller
 */
public class Board extends JPanel implements ActionListener{
    
    private Timer timer;
    private final int DELAY  = 15;
    
    private final int B_WIDTH_PIXELS = 300;
    private final int B_HEIGHT_PIXELS = 300;
    private final int TILE_SIZE = 25;
    
    private final int B_WIDTH_TILES = 12;
    private final int B_HEIGHT_TILES = 13;

    private String mapStr;
    
    
    private ArrayList<Door> doors;
    private ArrayList<Wall> walls;
    private ArrayList<Floor> floors;
    private Tile[][] map = new Tile[B_HEIGHT_TILES][B_WIDTH_TILES];
    
    private Model[][] modelsLayer = new Model[B_HEIGHT_TILES][B_WIDTH_TILES];
    
    private int highlighted;
    
    private ArrayList<Model> models;
    private ArrayList<Marine> marines;
    private ArrayList<Stealer> stealers;
    
    private Image highlight;
    
    public Board()
    {
        initBoard();
    }
    
    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setBackground(Color.BLUE);
        setFocusable(true);
        
        setPreferredSize(new Dimension(B_WIDTH_PIXELS, B_HEIGHT_PIXELS));
        
        mapStr =  "############\n"
                + "###      ###\n"
                + "### ####-###\n"
                + "### ###   ##\n"
                + "# |   |   ##\n"
                + "# # ###   ##\n"
                + "# #-#### ###\n"
                + "# # ###   ##\n"
                + "# #  |    ##\n"
                + "#-#####   ##\n"
                + "# #####-####\n"
                + "#     |    #\n"
                + "############\n";
        
        walls = new ArrayList();
        doors = new ArrayList();
        floors = new ArrayList();
        
        buildMap(mapStr);
        
        
        
        marines = new ArrayList();
        stealers = new ArrayList();
        
        models = new ArrayList();
        
        marines.add(new Marine(10, 11, TILE_SIZE, Facing.LEFT));
        marines.add(new Marine(9, 11, TILE_SIZE, Facing.LEFT));
//        marines.add(new Marine(7, 7, TILE_SIZE, Facing.LEFT));
        stealers.add(new Stealer(8, 4, TILE_SIZE, Facing.RIGHT));
        
        models.add(marines.get(0));
        models.add(marines.get(1));
        models.add(stealers.get(0));
        
        modelsLayer[11][10] = marines.get(0);
        modelsLayer[11][9] = marines.get(1);
        modelsLayer[4][8] = stealers.get(0);
        
        highlighted = -1;
        
        ImageIcon iicon = new ImageIcon("SpaceHulkImages/UI/Highlight.png");
        highlight = iicon.getImage();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void buildMap(String mapString)
    {
        Wall wall;
        Floor floor;
        Door door;
        
        String[] mapStrings = mapString.split("\n");
        for(int yT = 0; yT < mapStrings.length; yT++)
        {
            int y = yT * TILE_SIZE;
            for(int xT = 0; xT < mapStrings[yT].length(); xT++)
            {
                int x = xT * TILE_SIZE;
                
                char item = mapStrings[yT].charAt(xT);
                
                switch(item)
                {
                    case '#':
                        wall = new Wall(x, y);
                        map[yT][xT] = wall;
                        walls.add(wall);
                        break;
                    
                    case ' ':
                        floor = new Floor(x, y);
                        map[yT][xT] = floor;
                        floors.add(floor);
                        break;
                        
                    case '-':
                        door = new Door(x, y, "v");
                        map[yT][xT] = door;
                        doors.add(door);
                        break;
                        
                    case '|':
                        door = new Door(x, y, "h");
                        map[yT][xT] = door;
                        doors.add(door);
                        break;
                }
            }
        }
    }
    
    private void drawMap(Graphics g)
    {
//        System.out.println("Drawing!");
        for(Tile[] row : map)
        {
            for(Tile tile : row)
            {
                g.drawImage(tile.getImage(), tile.x(), tile.y(), this);
            }
        }
        for(Model[] row : modelsLayer)
        {
            for(Model model : row)
            {
                if(model != null)
                {
                    g.drawImage(model.getImage(), model.xAbs(), model.yAbs(), this);
                    if(model.getType().equals("marine"))
                        g.setColor(Color.blue);
                    else
                        g.setColor(Color.red);
                    g.fillOval(model.xAbs()-2, model.yAbs()-10, 10, 10);
                    g.setColor(Color.white);
                    g.drawString(String.valueOf(model.getAp()), model.xAbs(), model.yAbs());
                    
//                    System.out.println("Drawing Marine!");
                }
                
            }
        }
        if(highlighted != -1)
        {
            int x = models.get(highlighted).xAbs();
            int y = models.get(highlighted).yAbs();
            g.drawImage(highlight, x, y, this);
//            System.out.println("Drawing highlight");
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        drawMap(g);
        
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Action Event!");
        repaint();
    }
    
//    private void loadTileset()
    
    private class TAdapter extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
            
            if(key == KeyEvent.VK_SPACE)
            {
                highlighted++;
//                System.out.println(highlighted);
//                System.out.println(e.isShiftDown());
                if(highlighted >= models.size())
                    highlighted = -1;
//                System.out.println(highlighted);
            }
            if(highlighted != -1)
            {
                Model model = models.get(highlighted);
                switch(key){
                    case KeyEvent.VK_Z:
                        if(((model.getFacing() == Facing.DOWN) || (model.getFacing() == Facing.LEFT)) && model.getAp() > 0){
                            modelsLayer = model.move(modelsLayer, map, Direction.SW);
                            model.spendAp(1);
                        } else if(e.isShiftDown() && model.getAp() > 1){
                            modelsLayer = model.move(modelsLayer, map, Direction.SW);
                            model.spendAp(2);
                        }
                        break;
                    case KeyEvent.VK_C:
                        if(((model.getFacing() == Facing.DOWN) || (model.getFacing() == Facing.RIGHT)) && model.getAp() > 0){
                            modelsLayer = model.move(modelsLayer, map, Direction.SE);
                            model.spendAp(1);
                        } else if(e.isShiftDown() && model.getAp() > 1)
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.SE);
                            model.spendAp(2);
                        }
                        break;
                    case KeyEvent.VK_Q:
                        if(((model.getFacing() == Facing.UP) || (model.getFacing() == Facing.LEFT)) && model.getAp() > 0){
                            modelsLayer = model.move(modelsLayer, map, Direction.NW);
                            model.spendAp(1);
                        } else if(e.isShiftDown() && model.getAp() > 1)
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.NW);
                            model.spendAp(2);
                        }
                        break;
                    case KeyEvent.VK_A:
                        if(model.getFacing() == Facing.LEFT && model.getAp() > 0)
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.W);
                            model.spendAp(1);
                        } else if(e.isShiftDown())
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.W);
                        }
                        else{
                            model.setFacing(Facing.LEFT);
                        }
//                        System.out.println("LEFT " + model.xTile() + " " + model.yTile());
                        break;
                    case KeyEvent.VK_D:
                        if(model.getFacing() == Facing.RIGHT && model.getAp() > 0)
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.E);
                            model.spendAp(1);
                        } else if(e.isShiftDown())
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.E);
                        }
                        else{
                            model.setFacing(Facing.RIGHT);
                        }
//                        System.out.println("RIGHT " + model.xTile() + " " + model.yTile());
                        break;
                    case KeyEvent.VK_W:
                        if(model.getFacing() == Facing.UP && model.getAp() > 0)
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.N);
                            model.spendAp(1);
                        } else if(e.isShiftDown())
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.N);
                        }
                        else{
                            model.setFacing(Facing.UP);
                        }
//                        System.out.println("UP " + model.xTile() + " " + model.yTile());
                        break;
                    case KeyEvent.VK_E:
                        System.out.println(e.isShiftDown());
                        if(((model.getFacing() == Facing.UP) || (model.getFacing() == Facing.RIGHT)) && model.getAp() > 0){
                            modelsLayer = model.move(modelsLayer, map, Direction.NE);
                            model.spendAp(1);
                        } else if(e.isShiftDown() && model.getAp() > 1)
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.NE);
                            model.spendAp(2);
                        }
                        break; 
                    case KeyEvent.VK_X:
                        if(model.getFacing() == Facing.DOWN && model.getAp() > 0)
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.S);
                            model.spendAp(1);
                        } else if(e.isShiftDown())
                        {
                            modelsLayer = model.move(modelsLayer, map, Direction.S);
                        }
                        else{
                            model.setFacing(Facing.DOWN);
                        }
//                        System.out.println("DOWN " + model.xTile() + " " + model.yTile());
                        break;
                    case KeyEvent.VK_F:
                        int x;
                        int y;
                        if(model.getAp() > 0)
                        {
                            model.spendAp(1);
                            System.out.println(model.getAp());
                            switch(model.getFacing())
                            {
                                case DOWN:
                                    x = model.xTile();
                                    y = model.yTile() + 1;
                                    if(doors.contains(map[y][x]))
                                    {
                                        int index = doors.indexOf(map[y][x]);
                                        doors.get(index).toggle();
                                    }
                                    break;
                                case UP:
                                    x = model.xTile();
                                    y = model.yTile() - 1;
                                    if(doors.contains(map[y][x]))
                                    {
                                        int index = doors.indexOf(map[y][x]);
                                        doors.get(index).toggle();
                                    }
                                    break;
                                case RIGHT:
                                    x = model.xTile() + 1;
                                    y = model.yTile();
                                    if(doors.contains(map[y][x]))
                                    {
                                        int index = doors.indexOf(map[y][x]);
                                        doors.get(index).toggle();
                                    }
                                    break;
                                case LEFT:
                                    x = model.xTile() - 1;
                                    y = model.yTile();
                                    if(doors.contains(map[y][x]))
                                    {
                                        int index = doors.indexOf(map[y][x]);
                                        doors.get(index).toggle();
                                    }
                                    break;
                            }
                            
                        } else System.out.println("I am out of AP");
                        
                        break;
                    case KeyEvent.VK_R:
                        System.out.println(model.roll(2, 1));
                        break;
                    case KeyEvent.VK_M:
                        
                        Model attacker = model;
                        Model defender = null;
                        switch(model.getFacing()){
                            case UP:
                                x = model.xTile();
                                y = model.yTile() - 1;
                                if(modelsLayer[y][x] != null) {
                                    defender = modelsLayer[y][x];
                                }
                                break;
                            case DOWN:
                                x = model.xTile();
                                y = model.yTile() + 1;
                                if(modelsLayer[y][x] != null) {
                                    defender = modelsLayer[y][x];
                                }
                                break;
                            case LEFT:
                                x = model.xTile() - 1;
                                y = model.yTile();
                                if(modelsLayer[y][x] != null) {
                                    defender = modelsLayer[y][x];
                                }
                                break;
                            case RIGHT:
                                x = model.xTile() + 1;
                                y = model.yTile();
                                if(modelsLayer[y][x] != null) {
                                    defender = modelsLayer[y][x];
                                }
                                break;
                        }
                        if(defender != null) {
                            String typeA = attacker.getType();
                            String typeD = defender.getType();
                            System.out.println(typeA + " vs " + typeD);
                            int rollA = 0;
                            if("stealer".equals(typeA))
                            {
                                rollA = attacker.roll(3, 0);
                            } else {
                                rollA = attacker.roll(1, 0);
                            }
                            int rollD = 0;
                            if("stealer".equals(typeD))
                            {
                                rollD = defender.roll(3, 0);
                            } else {
                                rollD = defender.roll(1, 0);
                            }
                            if(rollA > rollD) {
                                System.out.println(typeA + " wins!");
                                modelsLayer[defender.yTile()][defender.xTile()] = null;
                                models.remove(defender);
                                highlighted = -1;
                                if(typeD.equals("marine")) 
                                    marines.remove(defender);
                                else
                                    stealers.remove(defender);
                            }
                            else if (rollD > rollA) {
                                System.out.println(typeD + " wins!");
                                if(defender.getFacing() != attacker.getFacing()) {
                                    switch(attacker.getFacing()) {
                                        case UP:
                                            defender.setFacing(Facing.DOWN);
                                            break;
                                        case DOWN:
                                            defender.setFacing(Facing.UP);
                                            break;
                                        case LEFT:
                                            defender.setFacing(Facing.RIGHT);
                                            break;
                                        case RIGHT:
                                            defender.setFacing(Facing.LEFT);
                                            break;
                                    }
                                } else {
                                    modelsLayer[attacker.yTile()][attacker.xTile()] = null;
                                    models.remove(attacker);
                                    highlighted = -1;
                                    if(typeA.equals("marine")) 
                                        marines.remove(attacker);
                                    else
                                        stealers.remove(attacker);
                                }
                            }
                            else System.out.println("Draw!");
                            
                        }
                        
                        break;
                    case KeyEvent.VK_ENTER:
                        for(Model model1: models)
                        {
                            model1.resetAp();
                        }
                }
            }
//            for(Marine marine : marines)
//            {
//                switch(key){
//                    case KeyEvent.VK_LEFT:
//                        if(marine.getFacing() == Facing.LEFT)
//                        {
//                            modelsLayer = marine.move(modelsLayer, map, Facing.LEFT);
//                        }
//                        else{
//                            marine.setFacing(Facing.LEFT);
//                        }
//                        System.out.println("LEFT " + marine.xTile() + " " + marine.yTile());
//                        break;
//                    case KeyEvent.VK_RIGHT:
//                        if(marine.getFacing() == Facing.RIGHT)
//                        {
//                            modelsLayer = marine.move(modelsLayer, map, Facing.RIGHT);
//                        }
//                        else{
//                            marine.setFacing(Facing.RIGHT);
//                        }
//                        System.out.println("RIGHT " + marine.xTile() + " " + marine.yTile());
//                        break;
//                    case KeyEvent.VK_UP:
//                        if(marine.getFacing() == Facing.UP)
//                        {
//                            modelsLayer = marine.move(modelsLayer, map, Facing.UP);
//                        }
//                        else{
//                            marine.setFacing(Facing.UP);
//                        }
//                        System.out.println("UP " + marine.xTile() + " " + marine.yTile());
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        if(marine.getFacing() == Facing.DOWN)
//                        {
//                            modelsLayer = marine.move(modelsLayer, map, Facing.DOWN);
//                        }
//                        else{
//                            marine.setFacing(Facing.DOWN);
//                        }
//                        System.out.println("DOWN " + marine.xTile() + " " + marine.yTile());
//                        break;
//                }
//            }
//            for(Stealer stealer : stealers)
//            {
//                switch(key){
//                    case KeyEvent.VK_A:
//                        if(stealer.getFacing() == Facing.LEFT)
//                        {
//                            modelsLayer = stealer.move(modelsLayer, map, Facing.LEFT);
//                        }
//                        else{
//                            stealer.setFacing(Facing.LEFT);
//                        }
//                        System.out.println("LEFT " + stealer.xTile() + " " + stealer.yTile());
//                        break;
//                    case KeyEvent.VK_D:
//                        if(stealer.getFacing() == Facing.RIGHT)
//                        {
//                            modelsLayer = stealer.move(modelsLayer, map, Facing.RIGHT);
//                        }
//                        else{
//                            stealer.setFacing(Facing.RIGHT);
//                        }
//                        System.out.println("RIGHT " + stealer.xTile() + " " + stealer.yTile());
//                        break;
//                    case KeyEvent.VK_W:
//                        if(stealer.getFacing() == Facing.UP)
//                        {
//                            modelsLayer = stealer.move(modelsLayer, map, Facing.UP);
//                        }
//                        else{
//                            stealer.setFacing(Facing.UP);
//                        }
//                        System.out.println("UP " + stealer.xTile() + " " + stealer.yTile());
//                        break;
//                    case KeyEvent.VK_S:
//                        if(stealer.getFacing() == Facing.DOWN)
//                        {
//                            modelsLayer = stealer.move(modelsLayer, map, Facing.DOWN);
//                        }
//                        else{
//                            stealer.setFacing(Facing.DOWN);
//                        }
//                        System.out.println("DOWN " + stealer.xTile() + " " + stealer.yTile());
//                        break;
//                }
//            }
        }
    }
    
}
