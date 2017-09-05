
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Vitoria extends Rectangle{

    public Vitoria(int x, int y){//Construtor
        setBounds(x,y,90,40);  
    }
    
    public void desenha(Graphics g){
        Font fnt0 = new Font("arial", Font.BOLD, 60);
        g.setFont(fnt0);
	g.setColor(Color.yellow);
	g.drawString("o/ VITORIA  o/",150, 250);
    }


    
}
