package SpritesheetRender;

//<!> ATENÇÃO esse código foi alterado com base no arquivo em GraficosComJava (Java Project) > RenderizandoStrings (package) > Game (class) 
//Se deseja ver os comentários desse código, vá para aquela class

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame; 



public class Game extends Canvas implements Runnable{
	
	public static JFrame frame; 
	private boolean isRunning;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	private Thread thread; 
	private int x = 0;
	
	private BufferedImage image; 
	private Spritesheet sheet;
	private BufferedImage player;
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Game() {
		
		sheet = new Spritesheet("/spritesheet01.png"); //criamos uma variável objeto que armazena o spritesheet selecionado
		player = sheet.getSprite(0, 0, 16, 16); //temos uma variável objeto que armazena a imagem do player (recortada do spritesheet sheet)
		
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame(); 
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	}
	
	public void initFrame() {
		frame = new JFrame("My First Game"); 
		frame.add(this); 
		frame.setResizable(false); 
		frame.pack(); 
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {	//<!!!> Main
		Game game = new Game();
		game.start();
		
	}
	
	public void tick() {
		x++; //incrementa o movimento x de alguns elementos
		//logo, quando a tela atualizar, a posição será diferente, causando o efeito de movimento (animação)
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
	
		if(bs ==null) {
			this.createBufferStrategy(3);
			return; 
		}
		
		Graphics g = image.getGraphics();
		
		g.setColor(new Color(100,12,118)); 
		g.fillRect(0, 0, WIDTH, HEIGHT); 
		
		g.setColor(Color.CYAN);
		g.fillRoundRect(20, 20, 60, 60, 20, 20);
		g.fillOval(100, 10, 50, 50);
				
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		
		g.drawString("Fala meu consagrado!", 30, 95);
		
		g.setColor(Color.GREEN); 
		g.fillRoundRect(25, 25, 18, 18, 5, 5);
		/************/
		
		
		/*<!> DESENHANDO SPRITE DO PLAYER*/
		
		g.drawImage(player, 38, 38, null);
		/* drawImage: 1 - Qual imagem você quer colocar
					  2 - Posição x na tela
					  3 - Posição y na tela
					  4 - ?
		*/
		
		
		/*EXTRAS: DESENHANDO LINHAS*/
		g.setColor(Color.MAGENTA);
		g.drawLine(50, 50, x, 64);
		/************/
		

		
		
		
		g.drawImage(player, x  - 2, 52, null);
		//podendo ser colocado quantas vezes quiser
		
		//O personagem aparenta estar com o efeito de segurar a corda por conta do -2 colocado, sem ele o efeito
		//não ocorre corretamente por o sprite do player ter 2px de camada alfa entre o começo da box e a mão
		
		g.dispose(); //comando que visa a performance gráfica do jogo
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); 
		

		bs.show(); 
	}
	

	@Override 
	public void run() {
		
		long lastTime = System.nanoTime(); 
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		int frames = 0;
		double timer = System.currentTimeMillis();

		
		double delta = 0;
		
		while(isRunning) {
			
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now; 
			
			
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) { 
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
		
	}

}

