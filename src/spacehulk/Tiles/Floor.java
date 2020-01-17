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
public class Floor extends Tile{
    
    private Image image;
    
    public Floor(int x, int y) {
        super(x, y);
        
        initFloor();
    }

    private void initFloor() {
        ImageIcon iicon = new ImageIcon("SpaceHulkImages/Tiles/Empty.png");
        image = iicon.getImage();
        setImage(image);
        
        setMoveBlock(false);
        setLOSBlock(false); 
    }
    
    
    
}
