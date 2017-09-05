import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class PacMan extends Rectangle{
    private static final long serialVersionUID = 1L;
    
    public boolean right, left,up,down;
    private int speed = 4;
    
    public PacMan(int x, int y){
        setBounds(x,y,25,25);
    }
    
    public void tick(){
        if(right && canMove(x+speed,y))x+=speed;
        if(left && canMove(x-speed,y))x-=speed;
        if(up && canMove(x,y-speed))y-=speed;
        if(down && canMove(x,y+speed))y+=speed;
        
        Fase level = Game.fase;
        
        //remove as frutas
        for(int i = 0; i < level.frutas.size(); i++){
            if(this.intersects(level.frutas.get(i))){
                level.frutas.remove(i);
                break;
            }
        }
        //condição de vitoria do jogo se pegar todas as maças
        //if(level.apples.size()== 0)System.exit(0);
        if(level.frutas.size()== 0){
            Game.pacMan = new PacMan(32,32);
            Game.fase = new Fase("mapa02.png");
            System.out.println("Proxima Fase o/");
            return;
            
            
        }
        /*else{
            Game.fase = new Fase("mapa01.png");
            System.out.println("venceu o game o/");
            
        } */ 
        for(int i=0; i < Game.fase.inimigos.size(); i++){
            Inimigo fant = Game.fase.inimigos.get(i);
            if(fant.intersects(this)){
                //System.exit(0);
                
                //GAMEOVER
               Game.State = Game.State.GAMEOVER;
                //System.out.println("entrou");
            }
        }
    }
    
    //colisão
    private boolean canMove(int nextx, int nexty){
        Rectangle bounds = new Rectangle(nextx,nexty,width,height);
        Fase level = Game.fase;
        
        //testa colisao
        for(int xx=0; xx < level.paredes.length; xx++){
            for(int yy=0; yy < level.paredes[0].length; yy++){
                //verifica se tem uma parede
                if(level.paredes[xx][yy] != null)
                    if(bounds.intersects(level.paredes[xx][yy])){
                        return false;
                    }
            }
        }
                
        return true;
    }
    
    public void desenha(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x, y, width, height);
    }
    
}
