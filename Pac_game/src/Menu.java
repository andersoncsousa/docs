
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Menu extends Rectangle{

    public Menu(int x, int y){//Construtor
        setBounds(x,y,90,40);  
    }
    
    public void desenha(Graphics g){
        //cria retangulo para selecao
        //g.setColor(Color.red);
        //g.drawRect(68, 220, width, height);
        //Titulo menu
        Font fnt0 = new Font("arial", Font.BOLD, 60);
        g.setFont(fnt0);
	g.setColor(Color.yellow);
	g.drawString("Pac Man", 70, 70);
        //opcao do menu
        Font fnt1 = new Font("arial", Font.BOLD, 30);
	g.setFont(fnt1);
        g.setColor(Color.white);
        g.drawString("Precione enter para começar a Jogar", 70, 250);
        g.drawString("Esc para Sair", 70, 300);
   
    }


    
}
