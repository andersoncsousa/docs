import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Fruta extends Rectangle{
    
    private static final long serialVersionUID = 1L;
    
    public Fruta(int x,int y){
        setBounds(x+10,y+10,8,8);
    }
    
    public void desenha(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x,y,width,height);
    }
}
