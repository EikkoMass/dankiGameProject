package AnimacaoRotacionamento;

//<!> ATENÇÃO esse código foi alterado com base no arquivo em GraficosComJava (Java Project) > SpritesheetRender (package) > Game (class) 
//Se deseja ver os comentários desse código, vá para aquela class

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private boolean rota = false;
	
	private BufferedImage image; 
	private Spritesheet sheet;
	//private BufferedImage player; <!! Antes>
	private BufferedImage[] player;
	
	private int frames = 0;
	private int maxFrames = 15;
	private int curAnimation = 0;
	private int maxAnimation = 4;
	
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
		
		sheet = new Spritesheet("/spritesheet01.png");
		// player = sheet.getSprite(0, 0, 16, 16); <!!> Antes
		player = new BufferedImage[5];
		player[0] = sheet.getSprite(0, 0, 16, 16);
		player[1] = sheet.getSprite(16, 0, 16, 16);
		player[2] = sheet.getSprite(32, 0, 16, 16);
		player[3] = player[1];
		player[4] = sheet.getSprite(48, 0, 16, 16);
		
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
		frames++;
		if(rota) {
			x--;
			if(x==0) {
				rota=false;
			}
		}else {
			x++;
			if(x==50) {
				rota=true;
			}
		}
		
		if(frames > maxFrames) {
			frames=0;
			curAnimation++;
			if(curAnimation > maxAnimation) {
				curAnimation = 0;
			}
		}
		
		
		
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
		
		
		g.drawImage(player[curAnimation], 38, 38, null);
		
		
		
		g.setColor(Color.MAGENTA);
		g.drawLine(50, 50, x, 64);
			
		
		
		
		g.drawImage(player[curAnimation], x  - 2, 52, null);
		
		
		//Rotacionamento de Player:
		
		Graphics2D g2 = (Graphics2D) g; //Importar Java.awt.Graphics2D
		
		//Graphics g3 = (Graphics) g2;
		
		// A técnica usada acima se chama Cast, no qual:
		//g2 é uma variável objeto referente a Graphics2D
		//g2 tem seu valor igual a g (objeto Graphics)
		//porém g antes de se tornar valor de g2, é convertido para um valor compatível à Graphics2D
		
		//<!!>Nota: Lembrar de casos como do Inimigo // Tipo1 // Tipo2 em que Tipo1 e 2 eram entidades filhas de Inimigo
		//e por causa disso funcionavam na implementação de objetos (Graphics2D é pai ou filho de Graphics (provavelmente filho))
		
		
		g2.setColor(new Color(0,0,0, 180));
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		//aplicar sombramento nas camadas de baixo
		
		
		g2.rotate(Math.toRadians(x), 110 + 8, 25 + 8);
		//converter a rotação desejada para radianos com a classe nativa do java Math
		
		/*.rotate():	campo 1: Quantos graus você quer mover
		 				campo 2: em qual ponto x você quer que seja a referência da rotação?
		 				campo 3: em qual ponto y você quer que seja a referência da rotação?*/
		
		//<!!>No modo colocado, o ponto de referência é o centro do personagem.
		//Personagem está em 100x e 12y (adicionado + 8 em ambos por ser um desenho 16 bits)
		
		
		
		g.drawImage(player[curAnimation], 110, 25, null);

		g2.rotate(Math.toRadians(x), 90 + 8, 35 + 8);
		//provavelmente os dois rotate estão afetando esse elemento
		g.drawImage(player[curAnimation], 120, 25, null);
		//ao alcançar mais de 360, ele recomeça e contagem
		
		g.dispose(); 
			
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

