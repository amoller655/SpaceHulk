/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacehulk;

import Models.Direction;
import Models.Facing;
import java.awt.Image;

/**
 *
 * @author 20amoller
 */
public class Model {
    
    
    private int xAbs;
    private int yAbs;
    private int xTile;
    private int yTile;
    
    private final int TILE_SIZE;
    
    private Facing facing;
    
    
    private Image image;
    private Image up;
    private Image down;
    private Image right;
    private Image left;
    
    private boolean moveBlock;
    private boolean LOSBlock;
    
    private final int MAX_AP;
    private int ap;
    
    
    
    private final String type;
    
    
    public Model(int x, int y, int TILE_SIZE, Facing facing, int maxAp, String type)
    {
        this.xTile = x;
        this.yTile = y;
        this.TILE_SIZE = TILE_SIZE;
        this.facing = facing;
        this.MAX_AP = maxAp;
        this.type = type;
        ap = maxAp;
        
        xAbs = xTile * TILE_SIZE;
        yAbs = yTile * TILE_SIZE;
        
        
    }
    
    public String getType()
    {
        return type;
    }
    
    public int roll(int numDice, int mod) {
        int max = -1;
        for(int i = 1; i <= numDice; i++)
        {
            int roll = (int) (Math.random() * 6) + 1 + mod;
            System.out.print(roll + ", ");
            if(roll > max) max = roll;
        }
        System.out.print("\n");
        return max;
    }
    
    public int getAp()
    {
        return ap;
    }
    
    public void spendAp(int spent)
    {
        ap -= spent;
    }
    
    public void resetAp()
    {
        ap = MAX_AP;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public void setImage(Image img)
    {
        image = img;
    }
    
    public void loadImages(Image up, Image down, Image left, Image right)
    {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
    
    public int xAbs()
    {
        return xAbs;
    }
    
    public int yAbs()
    {
        return yAbs;
    }
    
    public void setXAbs(int x)
    {
        this.xAbs = x;
        this.xTile = xAbs / TILE_SIZE;
    }
    
    public void setYAbs(int y)
    {
        this.yAbs = y;
        this.yTile = yAbs / TILE_SIZE;
    }
    
    public int xTile()
    {
        return xTile;
    }
    
    public int yTile()
    {
        return yTile;
    }
    
    public void setXTile(int x)
    {
        this.xTile = x;
        this.xAbs = xTile * TILE_SIZE;
    }
    
    public void setYTile(int y)
    {
        this.yTile = y;
        this.yAbs = yTile * TILE_SIZE;
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
    
    public void setFacing(Facing facing)
    {
        if(facing == Facing.LEFT) setImage(left);
        if(facing == Facing.RIGHT) setImage(right);
        if(facing == Facing.UP) setImage(up);
        if(facing == Facing.DOWN) setImage(down);
        this.facing = facing;
    }
    
    public Facing getFacing()
    {
        return facing;
    }
    
    
    
    public Model[][] move(Model[][] models, Tile[][] map, Direction direction)
    {
        int dx;
        int dy;
        Model[][] copy = models.clone();
        switch(direction){
            case N:
                dx = xTile();
                dy = yTile() - 1;
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    copy[yTile()][xTile()] = null;
                    copy[dy][dx] = this;
                    setXTile(dx);
                    setYTile(dy);
                }
                break;
            case S:
                dx = xTile();
                dy = yTile() + 1;
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    copy[yTile()][xTile()] = null;
                    copy[dy][dx] = this;
                    setXTile(dx);
                    setYTile(dy);
                }
                break;
            case E:
                dx = xTile() + 1;
                dy = yTile();
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    copy[yTile()][xTile()] = null;
                    copy[dy][dx] = this;
                    setXTile(dx);
                    setYTile(dy);
                }
                break;
            case W:
                dx = xTile() - 1;
                dy = yTile();
                
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    copy[yTile()][xTile()] = null;
                    copy[dy][dx] = this;
                    setXTile(dx);
                    setYTile(dy);
                }
                break;
            case NE:
                dx = xTile() + 1;
                dy = yTile() - 1;
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    if((!map[dy][xTile()].isMoveBlock() && models[dy][xTile] == null) || (!map[yTile][dx].isMoveBlock() && models[yTile][dx] == null)){
                        copy[yTile()][xTile()] = null;
                        copy[dy][dx] = this;
                        setXTile(dx);
                        setYTile(dy);
                    }
                }
                break;
            case SE:
                dx = xTile() + 1;
                dy = yTile() + 1;
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    if((!map[dy][xTile()].isMoveBlock() && models[dy][xTile] == null) || (!map[yTile][dx].isMoveBlock() && models[yTile][dx] == null)){
                        copy[yTile()][xTile()] = null;
                        copy[dy][dx] = this;
                        setXTile(dx);
                        setYTile(dy);
                    }
                }
                break;
            case SW:
                dx = xTile() - 1;
                dy = yTile() + 1;
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    if((!map[dy][xTile()].isMoveBlock() && models[dy][xTile] == null) || (!map[yTile][dx].isMoveBlock() && models[yTile][dx] == null)){
                        copy[yTile()][xTile()] = null;
                        copy[dy][dx] = this;
                        setXTile(dx);
                        setYTile(dy);
                    }
                }
                break;
            case NW:
                dx = xTile() - 1;
                dy = yTile() - 1;
                if(!map[dy][dx].isMoveBlock() && models[dy][dx] == null)
                {
                    if((!map[dy][xTile()].isMoveBlock() && models[dy][xTile] == null) || (!map[yTile][dx].isMoveBlock() && models[yTile][dx] == null)){
                        copy[yTile()][xTile()] = null;
                        copy[dy][dx] = this;
                        setXTile(dx);
                        setYTile(dy);
                    }
                }
                break;
        }
        return copy;
    }
}
