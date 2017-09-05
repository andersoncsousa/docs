import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
    BufferedImage backBuffer;
    
    private boolean jogoAtivo = false;

    public static final int WIDTH = 640, HEIGHT = 480;
    public static final String TITLE = "Pac-Man";

    private Thread thread;
    
    public static PacMan pacMan;
    public static Fase fase;
    public static Inimigo inimigos;
    public static Menu menu = new Menu(100,100);
    public static GameOver gameOver = new GameOver(200,200);
    public static Vitoria vitoria = new Vitoria(300,300);

    //Estado do jogo(menu ou jogo ativo)
    public static enum STATE{
        MENU,
        GAME,
        GAMEOVER,
        VITORIA
    };
    public static STATE State = STATE.MENU;
    public static STATE fim = STATE.GAMEOVER;
    public static STATE venceu = STATE.VITORIA;

    
    //Construtor
    public Game(){
        Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        
        addKeyListener(this);
        pacMan = new PacMan(Game.WIDTH/2,Game.HEIGHT/2);
        fase = new Fase("primeiro-level.png");
        //inimigos = new Inimigo (Game.WIDTH/2 + 90, Game.HEIGHT/2);

    }
    //metodo para inicializar o jogo
    public synchronized void start(){
        if(jogoAtivo) return;
        jogoAtivo = true;
        thread = new Thread(this);
        thread.start();
    }
    //metodo para parada do jogo
    public synchronized void stop(){
        if(!jogoAtivo) return;
        jogoAtivo = false;
        try {
                thread.join();
        } catch (InterruptedException e ){
                e.printStackTrace();
        }
    }
    //ações de cada classe no game
    private void tick(){
        //System.out.println("funciona!!!!");
        if(State == STATE.GAME){
            pacMan.tick();
            fase.tick();
        }
    }
    
    //Desenha na tela
    private void desenha(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        if(State == STATE.GAME){
            pacMan.desenha(g);
            fase.desenha(g);
            
        }else if(State == STATE.MENU){
            menu.desenha(g);
        } //else if(venceu == STATE.VITORIA){
            //vitoria.desenha(g);}
        else if(fim == STATE.GAMEOVER){
            gameOver.desenha(g);
        }
        
        //inimigos.desenha(g);
        g.dispose();
        bs.show();
    }
    
    //Fps
    @Override
    public void run(){
        requestFocus();
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta = 0;
        double ns = 1000000000/targetTick;

        while(jogoAtivo){
            long now = System.nanoTime();
            delta+=(now - lastTime)/ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                desenha();
                fps++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                //printa o Fps no console
                //System.out.println(fps);
                fps = 0;
                timer+=1000;
            }

        }

        stop();
    }
    
    //MAIN
    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setTitle(game.TITLE);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        game.start();

    }
    
    //Controles de eventos do teclado
    @Override
    public void keyPressed(KeyEvent e) {
        if(State == STATE.MENU){
            if(e.getKeyCode() == KeyEvent.VK_ENTER)State = STATE.GAME;
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0); 
        }
        if(State == STATE.GAMEOVER){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0); 
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) pacMan.right = true;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) pacMan.left = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacMan.up = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacMan.down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) pacMan.right = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) pacMan.left = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacMan.up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacMan.down = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
