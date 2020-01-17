/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacehulk;

import java.awt.Image;

/**
 *
 * @author 20AMoller
 */
public class Tile {
    private final int SIZE = 25;
    
    private int x;
    private int y;
    private Image image;
    
    private boolean moveBlock;
    private boolean LOSBlock;
    
    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public void setImage(Image img)
    {
        image = img;
    }
    
    public int x() 
    {
        return x;
    }
    
    public int y()
    {
        return y;
    }
    
    public void setX()
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public boolean isMoveBlock()
    {
        return moveBlock;
    }
    
    public void setMoveBlock(boolean moveBlock)
    {
        this.moveBlock = moveBlock;
    }
    
    public boolean isLOSBlock()
    {
        return LOSBlock;
    }
    
    public void setLOSBlock(boolean LOSBlock)
    {
        this.LOSBlock = LOSBlock;
    }
        
}
