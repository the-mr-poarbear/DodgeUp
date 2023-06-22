import processing.core.PApplet;

public class Ball implements IDrawable{

    static PApplet a = Main.proccessing;
    int diameter;
    public Ball(int diameter) {
        this.diameter = diameter;
    }

    public void Show(int x , int y , int R , int G , int B){

        a.fill(R , G , B);
        a.circle(x , y , diameter);
    }
}
