import processing.core.PApplet;

import java.util.ArrayList;
import java.sql.*;

public class Main extends PApplet {

    String about = " heyy !! ok so lets go right into it \n in my douge up project " +
            "i used data base \n for storing top 3 scores \n( i put the .sql file in the zip i uploded) \n" +
            "i also made a proper menu for my game \n" +
            "and the speed of the blocks coming down \n goes faster with every 25 score you get \n" +
            "i also made the blocks disapear \nafter they pass the line " + " \n and also if you get golden blocks " +
            "\n your score goes up \n" +
            "and you can see your score and life while playing \n" +
            "and after you lost you can see your score\n and best score and if you are among three best \nit will tell you and " +
            "save your score in database "+
            "\n thats all ! enjoy playing and give me \nsome extra scores plzz i really need it man  ";
    int xDif ;
    int yDif;
    int score = 0 ;
    int life = 100 ;
    int speed = 4;

    boolean accepted = false ;
    boolean wannaPlay = false ;
    boolean wannaQuit = false ;
    boolean wannaAbout = false ;
    int bistpanjTabBaad = 25;
    public static PApplet proccessing ;


    public static ArrayList<Block> blocks = new ArrayList();
    public static int speedX = 5 ;
    public static boolean check = true;
    public static void main(String[] args) {
        PApplet.main("Main" , args);
    }

    Ball ball;


    @Override
    public void setup() {
        proccessing = this;
        Block.makeBlocks();
        ball = new Ball(40);


    }

    @Override
    public void settings() {
        size(400 , 700);
    }

    @Override
    public void draw() {

        if(wannaPlay){
            if(life > 0){
                background(0);
                ball.Show(mouseX , 580 , 255 , 255 , 255);
                stroke(255);
                line(0 , 600 , 400 , 600);
                strokeWeight(5);
                for (Block c : blocks) {
                    textSize(20);
                    fill(0, 408, 612);
                    text("Life : " + life, 10, 40);
                    text("Score : " + score, 200, 40);
                    xDif = abs(mouseX - (c.getBlockX() + 15) );
                    yDif = abs(580 - c.getBlockY() - 30);
                    c.Show(c.getBlockX() , c.getBlockY() , c.getBlockColorR() , c.getBlockColorG() , c.getBlockColorB());
                    if( xDif<=35 && yDif <=35 && !c.isGoodBLock() ){
                        life--;
                    } else
                    if(xDif<=35 && yDif <=35 && c.isGoodBLock()){
                       score++;
                    }else
                    if((c.getBlockY() >= 580) && (c.getBlockY() <= 700 )){
                        c.setBlockY(1000);
                        score++;
                    }

                    if(score == bistpanjTabBaad){
                        speed++;
                        bistpanjTabBaad += 25;
                    }

                }
                MoveBlocks();
            }

            if (life <= 0){
                noLoop();
                background(0);
                textSize(50);
                fill(0, 408, 612, 204);
                text("You Lost", 110, 350);
                textSize(30);
                text("Your Score : " + score, 110 , 460);

                TopThreeFromSQL.TopThree();
                if(TopThreeFromSQL.topThreeScore[0] < score ){
                    text("NEW BEST SCORE" ,70 , 610);
                    TopThreeFromSQL.topThreeScore[2] =   TopThreeFromSQL.topThreeScore[1];
                    TopThreeFromSQL.topThreeScore[1] =   TopThreeFromSQL.topThreeScore[0];
                    TopThreeFromSQL.topThreeScore[0] = score;

                    TopThreeFromSQL.Update();

                }else if(TopThreeFromSQL.topThreeScore[1] < score  && score < TopThreeFromSQL.topThreeScore[0]){
                    text("NEW SECOND BEST SCORE" ,60 , 610);
                    TopThreeFromSQL.topThreeScore[2] =   TopThreeFromSQL.topThreeScore[1];
                    TopThreeFromSQL.topThreeScore[1] = score;
                    TopThreeFromSQL.Update();

                }else if(TopThreeFromSQL.topThreeScore[2] < score && score < TopThreeFromSQL.topThreeScore[1]){
                    text("NEW THIRD BEST SCORE" ,60 , 610);
                    TopThreeFromSQL.topThreeScore[2] = score;
                    TopThreeFromSQL.Update();
                }

                textSize(20);
                text("BEST SCORE : " + TopThreeFromSQL.topThreeScore[0] , 90 , 680 );

            }

        } else


        if(!accepted){
            background(0);
            textSize(50);
            fill(0, 408, 612);
            text("DOUGE UP !!" , 50 , 200 );
            fill(207, 3, 252);
            text("START " , 50 , 350 );
            fill(252, 3, 86);
            text("ABOUT " , 50 , 450 );
            fill(252, 144, 3);
            text("QUIT" , 50 , 550 );

            if(mouseX >= 50 && mouseX <= 200 && mouseY >= 300 && mouseY <= 350  ){
                fill(3, 252, 32);
                text("START " , 50 , 350 );
                if(mousePressed){
                    accepted = true;
                    wannaPlay = true ;
                }
            }
            if(mouseX >= 50 && mouseX <= 200 && mouseY >= 400 && mouseY <= 450  ){
                fill(7, 214, 250);
                text("ABOUT " , 50 , 450 );
                if(mousePressed){
                    accepted = true;
                    wannaAbout = true ;
                }
            }
            if(mouseX >= 50 && mouseX <= 200 && mouseY >= 500 && mouseY <= 550  ){
                fill(102, 26, 217);
                text("QUIT" , 50 , 550 );
                if(mousePressed){
                    accepted = true;
                    wannaQuit = true ;
                }
            }
        } else

        if(wannaAbout){
            background(0);
            fill(0, 408, 612);
            textSize(20);
            text(about , 10 , 50 );
            fill(250, 2, 143);
            textSize(50);
            text("GO BACK " , 70 , 650 );

            if(mouseX >= 70 && mouseX <= 250 && mouseY >= 600 && mouseY <= 650  ){
                fill(14, 17, 230);
                text("GO BACK " , 70 , 650 );
                if(mousePressed){
                    accepted = false ;
                    wannaAbout = false ;
                }
            }

        } else

        if(wannaQuit){
            exit();
        }

    }

    public void CheckCrash(){
        if(speedX >= 380){
          check = false ;
        }else if ( speedX <= 20){
            check = true;
        }
    }

    public void MoveBlocks(){
        for (Block c : blocks) {
            c.setBlockY(c.getBlockY()+speed);
        }
    }


}
