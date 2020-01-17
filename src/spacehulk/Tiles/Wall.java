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
 * @author 20AMoller
 */
public class Wall extends Tile {
    
    private Image image;
    
    public Wall(int x, int y)
    {
        super(x, y);
        
        initWall();
    }
    
    private void initWall()
    {
        ImageIcon iicon = new ImageIcon("SpaceHulkImages/Tiles/Wall.png");
        image = iicon.getImage();
        setImage(image);
        
        setMoveBlock(true);
        setLOSBlock(true);
    }
}
