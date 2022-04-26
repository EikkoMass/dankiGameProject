package com.me.main;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.InputStream;

import com.me.entities.*;
import com.me.graficos.Spritesheet;
import com.me.graficos.UI_User_Interface;
import com.me.world.Camera;
import com.me.world.World;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


	//<!> ATEN��O esse c�digo reaproveitado de GraficosComJava (Java Project) > AnimacaoRotacionamento (package) > Game (class) 
	//Se deseja ver os coment�rios desse c�digo, v� para aquela class


	//criada pasta res para armazenamento de sprites

	public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; //gerado automaticamente
		public static JFrame frame; 
		private boolean isRunning;
		public static final int WIDTH = 170;
		public static final int HEIGHT = 150;
		public static final int SCALE = 4;
		private Thread thread; 
		public static Player player;
		public static Random rand;
		public UI_User_Interface ui;
		
		public InputStream stream  = ClassLoader.getSystemClassLoader().getResourceAsStream("BebasNeue-Regular.otf");
		public InputStream stream2  = ClassLoader.getSystemClassLoader().getResourceAsStream("BebasNeue-Regular.otf"); 
		public InputStream stream3  = ClassLoader.getSystemClassLoader().getResourceAsStream("BebasNeue-Regular.otf"); 
		public InputStream stream4  = ClassLoader.getSystemClassLoader().getResourceAsStream("BebasNeue-Regular.otf"); 

		//n�o � poss�vel reaproveitar o mesmo InputStream
		//(inputStream est� sendo usado para cria��o das fontes abaixo)
		public static Font newFont35, newFont170, newFont14;
		public static Font newFont70;
		
		public static BufferedImage minimapa;
		
		public static Npc npc;
		
		private BufferedImage image;
		public static Spritesheet spritesheet; // importar com.me.graficos
		//est�tico para Player conseguir usar
		
		public static World world;
		public Menu menu;
		
		public int[] pixels;
		public BufferedImage lightMap;
		public int[] lightMapPixels;
		public static int[] minimapaPixels;
		
		public static int entrada = 1, comecar = 2, jogando = 3, estadoCena = entrada; //vari�veis de Cutscene
		public int timeCena = 0, maxTime = 60*3;
		public static List<Entity> entities; //importar java.util.List e package com.me.entities
		//agora temos uma lista com slots infinitos para entidades
		
		public static List<Enemy> enemies;
		
		public static List<BulletShoot> bullets;
		private boolean showMessageGameOver;
		public boolean restartGame;
		private int framesGameOver = 0;
		private int MAX_LEVEL = 3, CUR_LEVEL = 1;
		public static String newWorld, gameState = "MENU";
		public boolean saveGame = false;
		
		public int mx, my;
		
		//public int xx, yy; //para a manipula��o de pixels em drawRectangleExample(xx, yy);
		
		
		//lista est�tica para uso na cria��o e localiza��o de inimigos (Class: World / Enemy)
		
		public synchronized void start() {
			thread = new Thread(this);
			isRunning = true;
			thread.start();
		}
		
		public synchronized void stop() {
			isRunning = false;
			try {
				thread.join(); //finaliza a Thread (Runnable)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		public Game() {
				
			try {
																		//35 float (equivalente a 35 pixels)
				newFont35 = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(35f); //adicionando uma nova fonte ao jogo
							//adicionando uma nova fonte ao projeto			definindo tamanho da fonte
				newFont70 = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(70f);
				newFont170 = Font.createFont(Font.TRUETYPE_FONT, stream3).deriveFont(170f);
				newFont14 = Font.createFont(Font.TRUETYPE_FONT, stream4).deriveFont(14f);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			Sound.musicBackgroundAlchemy.loop();
			newWorld = "Map"+CUR_LEVEL+".png";
			rand = new Random();
			setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); //modelo padr�o
			//setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize())); //pega o tamanho da tela
			
			this.addKeyListener(this); //implementa eventos de clique do teclado na classe Game
			addMouseListener(this);
			addMouseMotionListener(this);
			initFrame(); 
			
			ui = new UI_User_Interface();
			
			
			try {
				lightMap = ImageIO.read(getClass().getResource("/lightMap2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			lightMapPixels = new int[lightMap.getWidth() * lightMap.getHeight()];
			lightMap.getRGB(0, 0, lightMap.getWidth(), lightMap.getHeight(), lightMapPixels, 0, lightMap.getWidth());

			
			
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //vai pegar pixel por pixel de 'image'
			//ele filtra quantos pixels tem a 'image' ao todo e faz disso o tamanho do Array.
			
			//Iniciando ArrayList
			
			entities = new ArrayList<Entity>(); //importar java.util.ArrayList
			//armazenar� todas as entidades do jogo, como players, inimigos, npc's, etc.
			
			enemies = new ArrayList<Enemy>();
			bullets = new ArrayList<BulletShoot>();
			spritesheet = new Spritesheet("/Spritesheet01.png"); //pegando o Spritesheet01.png na pasta res
			
									 //x, y, width, height, sprite				xSprite, ySprite, widthSprite, heightSprite (para o recorte)
			player = new Player(0, 0, 16,    16,     spritesheet.getSprite(32, 0, 16, 16)); //importar Player (ou package entities)
			
			
			entities.add(player); //adicionamos uma entidade no ArrayList, no caso, player
			world = new World("/"+newWorld); //como definimos a localiza��o de player a partir da vari�vel est�tica em Game,
			//primeiro precisamos declarar a veri�vel player, depois o mapa
			
			minimapa = new BufferedImage(world.WIDTH, world.HEIGHT, BufferedImage.TYPE_INT_RGB);
			minimapaPixels = ((DataBufferInt)minimapa.getRaster().getDataBuffer()).getData();
			
			npc = new Npc(32 , 16 , 16, 16, Entity.NPC_EN);
			
			entities.add(npc);
			
			menu = new Menu();
			
		}
		
		public void initFrame() {
			frame = new JFrame("My Link Game"); 
			frame.add(this); 
			
			//frame.setUndecorated(true);
			frame.setUndecorated(false);
			frame.setResizable(false);
			
			frame.pack(); 
			
			// icone da janela do jogo
			
			Image imagem = null;
			
			try {
				imagem = ImageIO.read(getClass().getResource("/icon.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			
			Image image = toolkit.getImage(getClass().getResource("/cursor.png"));
			
			Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
			
			frame.setCursor(c); //personalizar o cursor do frame
			frame.setIconImage(imagem); //personalizar o icone do frame
			frame.setAlwaysOnTop(true);
			frame.setLocationRelativeTo(null); 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		public static void main(String[] args) {	//<!!!> Main
			Game game = new Game();
			
			game.start();
			
		}
		
		public void tick() { //o jogo � separado com um mesmo tick / render com um switch case determinando o que vai rodar em cada
							//gameState (sistema semelhante a um processo de rotas de um website)
			//tick das entidades
			switch(gameState) {
			case "NORMAL":
				if(this.saveGame) {
					this.saveGame = false;
					
					String[] opt1 = {"level", "vida", "playerX", "playerY"};
					int[] opt2 = {this.CUR_LEVEL,(int) player.life, player.getX(), player.getY()};
					
					Menu.saveGame(opt1, opt2, 10);
					System.out.println("Jogo Salvo");
				}
			
			if(Game.estadoCena == Game.jogando) {		
				for(int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i); //pega cada uma das entidades do ArrayList
					
					if(e instanceof Player && npc.isReading == true) {
						//esta Entidade � um Player
						continue;
					}
					
					if(e instanceof Enemy && npc.isReading == true) {
						continue;
					}
						e.tick(); // e executa o tick
				}
				
				for(int i = 0; i < bullets.size(); i++) {
					
					bullets.get(i).tick();
					
				}
			}else {
				if(Game.estadoCena == Game.entrada) {
					if(player.getY() < (World.HEIGHT * 16) - 100) {
						
						player.setY(player.getY() + 1);
						
						player.frames++;
						
						if(player.frames == player.maxFrames) {
							player.index++;
							player.frames = 0;
						}
						
						if(player.index > player.maxIndex) { //se index for maior que seu limite de sprites
							player.index = 0; //zera index
						}
						
						player.updateCamera();
					}else {
					 System.out.println("Caminhada da cutscene conclu�da!");
					 player.index = 0;
					 
					 Game.estadoCena = Game.comecar;
					}
				}
				
				if(Game.estadoCena == Game.comecar) {
					
					
					if(timeCena == 0) {
						player.dir = player.leftDir;
					}
					
					if(timeCena == (int) 60*1.5) {
						player.dir = player.rightDir;
					}
					
					if(timeCena == (int) 60*2.5) {
						player.dir = player.downDir;
					}
					
					if(timeCena < this.maxTime) {
						timeCena++;
					}
					
					if(timeCena >= this.maxTime) {
						Game.estadoCena = Game.jogando;
					}
				}
			}
			
			if((enemies.size() == 0) && gameState.equals("NORMAL")) { //para o random map, esse if causa conflito
				//pr�xima fase
				CUR_LEVEL++;
				
				if(CUR_LEVEL > MAX_LEVEL) {
					CUR_LEVEL = 1;
				}
				
				newWorld = "Map"+CUR_LEVEL+".png"; 
				World.resetGame(newWorld);
			}
			break;
			
			case "GAME_OVER":
				this.framesGameOver++;
				
				if(this.framesGameOver >= 30) {
					this.framesGameOver = 0;
					if(showMessageGameOver) 
						showMessageGameOver = false;
					else
						showMessageGameOver = true;
					
					
				}
				//fim de jogo
				
				if(restartGame) {
					this.gameState = "NORMAL";
					restartGame = false;
					CUR_LEVEL = 1;
					newWorld = "Map"+CUR_LEVEL+".png"; 
					World.resetGame(newWorld);
				}
				break;
				
			case "MENU":
				//menu do jogo
				player.updateCamera();
				menu.tick();
				//xx++;
				//yy++;
				break;
			
			}
		}
	
		/*public void drawRectangleExample(int xoff, int yoff) {// quadrado desenhado totalmente com manipula��o de pixels
			
			for(int xx = 0; xx < 32; xx++) {
				
				for(int yy = 0; yy < 32; yy++) {
					
					int xOff = xx + xoff;
					int yOff = yy + yoff;
					
					if(xOff < 0 || yOff < 0 || xOff > WIDTH || yOff > HEIGHT)
						continue;
					pixels[xOff + (yOff * WIDTH)] = 0xff0000;
					
				}
				
			}
			
		}*/ 
		
		/*public void applyLight() { //modelo antigo
			
			for(int xx = 0; xx < Game.WIDTH; xx++) {
				for(int yy = 0; yy < Game.HEIGHT; yy++) {
					if(lightMapPixels[xx + (yy * Game.WIDTH)] == 0xffffffff) { //se o pixel for branco (hexadecimal)
						pixels[xx + (yy * Game.WIDTH)] = 0; //quero que pinte-o de preto (rgb)
					}else {
						continue;
					}
				}				
			}
			
		}*/
		
		public void applyLight() { //usando da classe pixel
			
			for(int xx = 0; xx < Game.WIDTH; xx++) {
				for(int yy = 0; yy < Game.HEIGHT; yy++) {
					if(lightMapPixels[xx + (yy * Game.WIDTH)] == 0xFFFFFFFF) { //se o pixel for branco (hexadecimal)
						int pixel = Pixel.getLightBlend(pixels[xx+(yy*WIDTH)], 0x808080, 0);
						pixels[xx + (yy * Game.WIDTH)] = pixel; //quero que pinte-o de preto (rgb)
					}
				}				
			}
			
		}
		
		public void render() {
			
			BufferStrategy bs = this.getBufferStrategy();
		
			if(bs == null) {
				this.createBufferStrategy(3);
				return; 
			}
			
			Graphics g = image.getGraphics(); //image � usado para desenhar o desejado, mas n�o para mostrar
			
			//agora g � image.getGraphics()
			
			
			g.setColor(new Color(0,0,0)); 
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			world.render(g); // renderiza o mapa
			
			
			Collections.sort(entities, Entity.nodeSorter);
			
			//render das entidades
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i); //pega cada uma das entidades do ArrayList (pega uma por uma por causa da estrutura de repeti��o for)
				e.render(g); //e renderiza
			}
			
			for(int i = 0; i < bullets.size(); i++) {
				
				bullets.get(i).render(g);
				
			}
			applyLight();
			ui.render(g);/*Nessa classe estamos aplicando a barra de vida do player*/
			
			g.dispose(); 
			
			
			g = bs.getDrawGraphics(); //bs � usado para "pendurar" o que for desenhado
			//agora g � bs.getDrawGraphics()
			
			//drawRectangleExample(xx, yy);
			
			g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); //imprime todo o jogo aplicando scale
			//g.drawImage(image, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, null);
			
			g.setColor(new Color(255, 255, 255, 170));
			g.setFont(newFont35);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //aplicar anti-analiasing no jogo (diminuir serrilhado ds imagens)
			g.drawString("" + player.ammo, 18 + (Entity.BULLET_EN.getWidth() * SCALE), 22 + (Entity.BULLET_EN.getHeight() * SCALE)); //esses 3 fazem o contador de vida, por isso que ele n�o � pixelado, por n�o usar escala
			
			//manipula��o de opacidade de elementos � uma funcionalidade padr�o da classe Graphics2D, ent�o para
			//us�-lo, � neces�rio converter g para o desejado
															//pegar instancia					//o quanto de opacidade desejada (em float)
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.7)); //aplica opacidade nos pr�ximos elementos
			 				//setar composi��o	composi��o Alfa (tranparencia)	//?
			 
			g.drawImage(Entity.BULLET_EN, 10, 30, Entity.BULLET_EN.getWidth() * SCALE , Entity.BULLET_EN.getHeight() * SCALE, null); //ilustra��o de bullet para identificar quantas muni��es est�o dispon�veis
			
			
			World.renderMinimap();
			g.drawImage(minimapa, (WIDTH * SCALE) - (minimapa.getWidth() * SCALE) - 70, 30, world.WIDTH * (SCALE + 1), world.HEIGHT * (SCALE + 1),  null);
			
			//g.drawImage(minimapa, (Toolkit.getDefaultToolkit().getScreenSize().width) - (minimapa.getWidth() * SCALE) - 70, 30, world.WIDTH * (SCALE + 1), world.HEIGHT * (SCALE + 1),  null);

			if(Game.estadoCena == Game.comecar) {
				g.setFont(this.newFont170);
				((Graphics2D) g).drawString("READY?", ((WIDTH) / 2) + 70, ((HEIGHT * SCALE) / 2));
			}
			
			if(gameState == "GAME_OVER") {

				g = image.getGraphics();
				//drawRectangleExample(xx, yy);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(0,0,0, 180));
				g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
				//g2.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

				g2.setColor(new Color(255,255,255));
				
				g2.setFont(new Font("Arial", Font.BOLD, 30));
				g2.drawString("Game", (WIDTH / 2) - 45, (HEIGHT / 2) - 5);
				g2.setFont(new Font("Arial", Font.BOLD, 38));
				g2.drawString("Over", (WIDTH / 2) - 47, (HEIGHT / 2) + 25);
				g2.setColor(new Color(255,255,0));
				g2.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
				
				if(showMessageGameOver) 
					g2.drawString(">Press Enter to Continue<", (WIDTH / 2) - 62, (HEIGHT / 2) + 45);
					
					g2 = (Graphics2D) bs.getDrawGraphics();
				g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
				
			}else if(gameState == "MENU") {
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
				
				menu.render(g);
				
			}
			
			/* rotacionamento de objetos a partir na movimenta��o do mouse
			 * 
			 * 
			Graphics2D g2 = (Graphics2D) g;
			double angle = Math.atan2(my - 200 + 25, mx - 200 + 25);
			g2.rotate(angle, 200 + 25, 200 + 25);
					// radianos,  x de ponto de refer�ncia, y de ponto de refer�ncia
			g.setColor(Color.red);
			g.fillRect(200, 200, 50, 50);
			*/
	
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
				requestFocus(); //deixa a janela do game loop (Runnable) em foco
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

		@Override
		public void keyTyped(KeyEvent e) {
					
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			
			if((e.getKeyCode() == e.VK_ENTER)) {
				if(gameState.equals("GAME_OVER") && restartGame == false)
					restartGame = true;
				else if(gameState.equals("MENU")) 
					menu.enter = true;
				
				if(gameState.equals("NORMAL") && npc.nextMessage == false && npc.isReading == true)
					npc.nextMessage = true;
				
			}
			
			if(e.getKeyCode() == e.VK_Q) {
				if(npc.showMessage && Game.gameState.equals("NORMAL")) {
					npc.isReading = true;
				}
					
			}
			if(e.getKeyCode() == e.VK_SPACE || e.getKeyCode() == e.VK_X) {
				if(gameState.equals("NORMAL")) 
					player.jump = true;
			}
			
			
			if(e.getKeyCode() == e.VK_S || e.getKeyCode() == e.VK_DOWN) {
				//System.out.println("Baixo");
				player.down = true;
				
				if(gameState.equals("MENU")) {
					menu.down = true;
				}
			}else if(e.getKeyCode() == e.VK_W || e.getKeyCode() == e.VK_UP) {
				//System.out.println("Alto");	
				player.up = true;
				
				if(gameState.equals("MENU")) {
					menu.up = true;
				}
			}
			
			if(e.getKeyCode() == e.VK_A || e.getKeyCode() == e.VK_LEFT) {
				//System.out.println("Esquerda");	
				player.left = true;
				
				if(gameState.equals("MENU")) {
					menu.left = true;
				}
			}else if(e.getKeyCode() == e.VK_D || e.getKeyCode() == e.VK_RIGHT) {
				//System.out.println("Direita");
				player.right = true;
				
				if(gameState.equals("MENU")) {
					menu.right = true;
				}
			}
			
			if(e.getKeyCode() == e.VK_Z || e.getKeyCode() == e.VK_E) {
				player.shoot = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				menu.pause = true;
				gameState = "MENU";
			}
			
			if(e.getKeyCode() == e.VK_G) { //Salvar jogo com a tecla G
				if(gameState.equals("NORMAL"))
					saveGame = true;
				
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == e.VK_S || e.getKeyCode() == e.VK_DOWN) {
				player.down = false;
				
				
				}else if(e.getKeyCode() == e.VK_W || e.getKeyCode() == e.VK_UP) {
					player.up = false;
					
				}
				
				if(e.getKeyCode() == e.VK_A || e.getKeyCode() == e.VK_LEFT) {
					player.left = false;
				}else if(e.getKeyCode() == e.VK_D || e.getKeyCode() == e.VK_RIGHT) {
					player.right = false;
				}
			
				
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {

			player.mouseShoot = true;
			
			
			//pegamos a localiza��o do mouse no jogo (localiza��o na janela, n�o no mapa), dividindo a mesma pela escala por causa do jogo estar esticado
			player.mouseX = e.getX() / SCALE; //  / por 4 pois a escala esticada do jogo � x4
			//player.mouseX = (e.getX() / SCALE) + Camera.x seria o modo de capturar o x e y do mapa e n�o da janela (JFrame), como estamos fazendo;
			player.mouseY = e.getY() / SCALE;
			
			//System.out.println(player.mouseX);
			//System.out.println(player.mouseY);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			this.mx = e.getX();
			this.my = e.getY();
			
		}

	}

