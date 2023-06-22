import processing.core.PApplet;

import java.util.Random;

public class Block implements IDrawable{
    static PApplet a = Main.proccessing;
    public static int blockWidth = 30 ;
    public static int blockHeight = 60 ;
    private int blockX ;
    private int blockY;
    private int blockColorR ;
    private int blockColorG;
    private int blockColorB;
    public static int speedY = -80;

    private boolean goodBLock ;
    public Block(int blockX, int blockY, int blockColorR, int blockColorG, int blockColorB , boolean goodBLock) {
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockColorR = blockColorR;
        this.blockColorG = blockColorG;
        this.blockColorB = blockColorB;
        this.goodBLock = goodBLock;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockColorR() {
        return blockColorR;
    }

    public int getBlockColorG() {
        return blockColorG;
    }

    public int getBlockColorB() {
        return blockColorB;
    }

    public boolean isGoodBLock() {
        return goodBLock;
    }

    public static void makeBlocks() {
        Random random = new Random();
        for (int i = 0; i < 300; i++) {

            int x = random.nextInt(1 , 400);
            Main.blocks.add(new Block(x ,speedY , 227, 11, 105  , false));
            speedY -= 80;
            x = random.nextInt(1 , 400);
            Main.blocks.add(new Block(x ,speedY , 130, 2, 250 , false ));
            x = random.nextInt(1 , 400);
            speedY -= 80;
            Main.blocks.add(new Block(x ,speedY , 186, 14, 230  , false));
            x = random.nextInt(1 , 400);
            speedY -= 80;
            Main.blocks.add(new Block(x ,speedY , 18, 165, 179 , false ));
            x = random.nextInt(1 , 400);
            speedY -= 80;

            Main.blocks.add(new Block(x ,speedY , 255, 215, 0  , true));
            x = random.nextInt(1 , 400);
            speedY -= 80;
        }


    }

    public void Show(int x , int y , int R , int G , int B){

        a.fill(R,G,B);
        a.noStroke();
        a.rect(x,y,Block.blockWidth ,Block.blockHeight);

    }
}
