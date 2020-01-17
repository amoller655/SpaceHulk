/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Image;
import javax.swing.ImageIcon;
import spacehulk.Model;

/**
 *
 * @author 20AMoller
 */
public class Stealer extends Model {

    private Image up;
    private Image down;
    private Image right;
    private Image left;
    
    private Facing facing;
    

    
    public Stealer(int x, int y, int TILE_SIZE, Facing facing) {
        super(x, y, TILE_SIZE, facing, 6, "stealer");
        
        initMarine();
        this.facing = facing;
        this.setFacing(facing);
        this.setMoveBlock(true);
    }

    private void initMarine() {
        
        ImageIcon iicon = new ImageIcon("SpaceHulkImages/Stealers/Genestealer/up.png");
        up = iicon.getImage();
        iicon = new ImageIcon("SpaceHulkImages/Stealers/Genestealer/down.png");
        down = iicon.getImage();
        iicon = new ImageIcon("SpaceHulkImages/Stealers/Genestealer/right.png");
        right = iicon.getImage();
        iicon = new ImageIcon("SpaceHulkImages/Stealers/Genestealer/left.png");
        left = iicon.getImage();
        
        loadImages(up, down, left, right);
        
    }
    
    
    
}
