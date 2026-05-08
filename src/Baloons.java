import java.awt.Color;
import java.util.Random;

public class Baloons {
    int x;
    int y;
    int dy;
    double dx;
    Color color;
    int size;
    Random rand=new Random();
    public Baloons(int x,int y,int dy,double dx,Color color,int size){
        this.x=x;
        this.y=y;
        this.dy=dy;
        this.dx=dx;
        this.color=color;
        this.size=size;
    }
    public void moveBaloon(int panelwidth,int panelheight){
        dx+=rand.nextDouble()-0.5*0.2;
        
    }

    
}
