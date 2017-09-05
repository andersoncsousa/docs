
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class GameOver extends Rectangle{

    public GameOver(int x, int y){//Construtor
        setBounds(x,y,90,40);  
    }
    
    public void desenha(Graphics g){
        Font fnt0 = new Font("arial", Font.BOLD, 60);
        g.setFont(fnt0);
	g.setColor(Color.yellow);
	g.drawString("GAME OVER :(",120, 250);
    }


    
}
