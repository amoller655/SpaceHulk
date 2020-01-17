/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacehulk.Tiles;

import java.awt.Image;
import javax.swing.ImageIcon;
import spacehulk.Tile;

/**
 *
 * @author 20amoller
 */
public class Door extends Tile{
    
    private Image openImg;
    private Image closedImg;
    
    
    private boolean open;
    
    public Door(int x, int y, String direction)
    {
        super(x, y);
        
        initDoor(direction);
        open = false;
        this.setMoveBlock(!open);
        this.setLOSBlock(!open);
    }

    private void initDoor(String direction) {
        if(direction.equals("v")){
            ImageIcon iicon = new ImageIcon("SpaceHulkImages/Tiles/Door/Closed/NorthSouth.png");
            closedImg = iicon.getImage();
            
            iicon = new ImageIcon("SpaceHulkImages/Tiles/Door/Open/NorthSouth.png");
            openImg = iicon.getImage();
            setImage(closedImg);
        } 
        else if(direction.equals("h"))
        {
            ImageIcon iicon = new ImageIcon("SpaceHulkImages/Tiles/Door/Closed/EastWest.png");
            closedImg = iicon.getImage();
            
            iicon = new ImageIcon("SpaceHulkImages/Tiles/Door/Open/EastWest.png");
            openImg = iicon.getImage();
            setImage(closedImg);
        }
        
    }
    
    public boolean isOpen()
    {
        return open;
    }
    
    public void setOpen(boolean open)
    {
        this.open = open;
        if(open) setImage(openImg);
        else setImage(closedImg);
        this.setMoveBlock(!open);
        this.setLOSBlock(!open);
    }
    
    public void toggle()
    {
        if(open){
            setImage(closedImg);
            open = false;
        }
        else {
            setImage(openImg);
            open = true;
        }
        this.setMoveBlock(!open);
        this.setLOSBlock(!open);
    }
    
}
