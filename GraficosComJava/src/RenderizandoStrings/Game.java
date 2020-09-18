package RenderizandoStrings;

//<!> ATENÇÃO esse código foi alterado com base no arquivo em GraficosComJava (Java Project) > GraficosDebug (package) > Game (class) 
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
	
	private BufferedImage image; 
	
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
		
		g.setColor(Color.ORANGE);
		g.fillRoundRect(20, 20, 60, 60, 20, 20);
		g.fillOval(100, 10, 50, 50);
		
		//----------------------------------------------------------------------------------------------------------	
		
		g.setColor(Color.BLACK);
		//importar java.awt.Font
		g.setFont(new Font("Arial", Font.BOLD, 10));
							//Qual fonte , Alguma modificação , Tamanho
		
		/*setFont() pré-implementará as características desejadas para os textos escritos sequencialmente, assim como
		  o setColor().*/
		g.drawString("Fala meu consagrado!", 30, 95);
						//Conteúdo que deseja exibir, Coordenada x, Coordenada y
		
		/*LEMBRANDO NOVAMENTE QUE A FONTE APARENTA ESTAR COM EFEITO PIXELADO POR CONTA DE ESTARMOS 
		  USANDO A VARIÁVEL SCALE*/
	
		//----------------------------------------------------------------------------------------------------------	
		
		g.setColor(Color.RED); 
		g.fillRoundRect(25, 25, 18, 18, 5, 5);
		
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

