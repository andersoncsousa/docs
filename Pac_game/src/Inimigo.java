import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Inimigo extends Rectangle{
    
    private static final long serialVersionUID = 1L;
    private int random = 0, smart = 1, find_path = 2;
    private int estado = smart;
    private int right = 0, left = 1, up = 2, down = 3;
    private int dir = -1;
    public Random randomGen;
    private int time = 0;
    private int targetTime = 60*4;
    private int vel = 4;
    private int lastDir = -1;
    
    public Inimigo(int x, int y){
        //iniciaza metodo random
        randomGen = new Random();
        setBounds(x,y,25,100);
        dir = randomGen.nextInt(4);
    }
    
    public void tick(){
        
        if(estado == random){
        if(dir == right){
            if(canMove(x+vel,y)){
                if(randomGen.nextInt(100) < 50) x+=vel;
            }else{
                dir = randomGen.nextInt(4);
            }
        }else if(dir == left){
            if(canMove(x-vel,y)){
                if(randomGen.nextInt(100) < 50) x-=vel;
            }else{
                dir = randomGen.nextInt(4);
            }
        }else if(dir == up){
            if(canMove(x,y-vel)){
                if(randomGen.nextInt(100) < 50) y-=vel;
            }else{
                dir = randomGen.nextInt(4);
            }
        }else if(dir == down){
            if(canMove(x,y+vel)){
                if(randomGen.nextInt(100) < 50) y+=vel;
            }else{
                dir = randomGen.nextInt(4);
            }
        }
        time++;
        if(time == targetTime){ 
            estado = smart;
            time  = 0;
        }
        
        }else if (estado == smart){
            //segue o jogador
            boolean move = false;
            
            if(x < Game.pacMan.x){
                if(canMove(x+vel,y)){
                    if(randomGen.nextInt(100) < 50) x+=vel;
                    move = true;
                    lastDir = right;
                }
            }
            
            if(x > Game.pacMan.x){
                if(canMove(x-vel,y)){
                    if(randomGen.nextInt(100) < 50) x-=vel;
                    move = true;
                    lastDir = left;
                }
            }
            
            if(y < Game.pacMan.y){
                if(canMove(x,y+vel)){
                    if(randomGen.nextInt(100) < 50) y+=vel;
                    move = true;
                    lastDir = down;
                }
            }
            
            if(y > Game.pacMan.y){
                if(canMove(x,y-vel)){
                    if(randomGen.nextInt(100) < 50) y-=vel;
                    move = true;
                    lastDir = up;
                }
            }
            
            if(x == Game.pacMan.x && y == Game.pacMan.y) move = true;
            if(!move){
                estado = find_path;
            }
            time++;
            if(time == targetTime){ 
               estado = random;
               time = 0;
            }
        }else if(estado == find_path){
            if(lastDir == right){
                    if(y < Game.pacMan.y){
                        if(canMove(x,y+vel)){
                            if(randomGen.nextInt(100) < 50) y+=vel;
                            estado = smart;
                        }
                    }else{
                        if(canMove(x,y-vel)){
                            if(randomGen.nextInt(100) < 50) y-=vel;
                            estado = smart;
                        }
                    }
                    if(canMove(x+vel,y)){
                            x+=vel;
                    }
                }else if(lastDir == left){
                    if(y < Game.pacMan.y){
                        if(canMove(x,y+vel)){
                            if(randomGen.nextInt(100) < 50)y+=vel;
                        }
                    }else{
                        if(canMove(x,y-vel)){
                            if(randomGen.nextInt(100) < 50)y-=vel;
                        }
                    }
                    if(canMove(x-vel,y)){
                            x-=vel;
                    }
                }else if(lastDir == up){
                    if(y < Game.pacMan.x){
                        if(canMove(x+vel,y)){
                            if(randomGen.nextInt(100) < 50)x+=vel;
                        }
                    }else{
                        if(canMove(x-vel,y)){
                           if(randomGen.nextInt(100) < 50)x-=vel;
                        }
                    }
                    if(canMove(x,y-vel)){
                            y-=vel;
                    }
                }else if(lastDir == down){
                    if(y < Game.pacMan.x){
                        if(canMove(x+vel,y)){
                            if(randomGen.nextInt(100) < 50)x+=vel;
                        }
                    }else{
                        if(canMove(x-vel,y)){
                            if(randomGen.nextInt(100) < 50)x-=vel;
                        }
                    }
                    if(canMove(x,y+vel)){
                            y+=vel;
                    }
                }
                time++;
                if(time == targetTime){ 
                    estado = random;
                    time = 0;
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
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 32);
    }
    
    
}
