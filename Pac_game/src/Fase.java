import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Fase {
    
    public int width;
    public int height;
    public int pontos = 10;
    
    public Parede[][] paredes;
    
    public List<Fruta> frutas;
    public List<Inimigo> inimigos;
    
    public Fase(String path){
        frutas = new ArrayList<>();
        inimigos = new ArrayList<>();
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            this.width = map.getWidth();
            this.height = map.getHeight();
            int[] pixels =new int[width*height];
            paredes = new Parede[width][height];
            map.getRGB(0, 0, width, height, pixels, 0, width);
            
            for (int xx = 0; xx < width; xx++) {
                for (int yy = 0; yy < height; yy++) {
                    int val = pixels[xx + (yy*width)];
                    
                    if(val == 0xFF000000){
                        //Tile
                        paredes[xx][yy] = new Parede(xx*32,yy*32);
                    }else if(val == 0xFF0000FF){
                        //Player
                        Game.pacMan.x = xx*32;
                        Game.pacMan.y = yy*32;
                        
                    }else if(val == 0xFFFF0000){
                        //Enemy
                        inimigos.add(new Inimigo(xx*32,yy*32));
                    }else{
                        frutas.add(new Fruta(xx*32,yy*32));
                        
                        
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        
        
    }
    
    public void tick(){
        for (int i = 0; i < inimigos.size(); i++) {
            inimigos.get(i).tick();
        }
    }
    
    public void desenha(Graphics g){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(paredes[x][y] != null)paredes[x][y].desenha(g);
            }
        }
        for (int i = 0; i < frutas.size(); i++) {
            frutas.get(i).desenha(g);
        }
        
        for (int i = 0; i < inimigos.size(); i++) {
            inimigos.get(i).desenha(g);
        }
    }
}
