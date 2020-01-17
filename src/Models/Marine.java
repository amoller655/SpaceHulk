/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Image;
import javax.swing.ImageIcon;
import spacehulk.Model;
import spacehulk.Tile;

/**
 *
 * @author 20amoller
 */
public class Marine extends Model {

    private Image up;
    private Image down;
    private Image right;
    private Image left;
    
    private Facing facing;
    
    
    public Marine(int x, int y, int TILE_SIZE, Facing facing) {
        super(x, y, TILE_SIZE, facing, 4, "marine");
        
        initMarine();
        this.facing = facing;
        this.setFacing(facing);
        this.setMoveBlock(true);
    }

    private void initMarine() {
        
        ImageIcon iicon = new ImageIcon("SpaceHulkImages/Marines/up.png");
        up = iicon.getImage();
        iicon = new ImageIcon("SpaceHulkImages/Marines/down.png");
        down = iicon.getImage();
        iicon = new ImageIcon("SpaceHulkImages/Marines/right.png");
        right = iicon.getImage();
        iicon = new ImageIcon("SpaceHulkImages/Marines/left.png");
        left = iicon.getImage();
        
        loadImages(up, down, left, right);
        
    }
    
    
    
}
