package GraficosDebug;
//<!> ATEN��O esse c�digo foi alterado com base no arquivo em GraficosComJava (Java Project) > RenderizandoIniciandoBuffers (package) > Game (class) 
//Se deseja ver os coment�rios desse c�digo, v� para aquela class

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
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
		
		/*<!> Caso voc� ache que os elementos est�o muito grandes, tipo, "Isso n�o � 80px nem na china, t� muito grande isso", lembre-se
		 * que estamos fazendo uso do sistema de escala (vari�vel scale), que multiplica todos os valores usado por ele, logo, se quiser
		 * os tamanhos reais do elemento, subtituia o valor de scale (para os reais, substitua para 1)*/
		
		g.setColor(new Color(100,12,118)); //todos os elementos desenhados a partir de agora ser�o dessa cor
		g.fillRect(0, 0, WIDTH, HEIGHT); 
		
		//^Como o render() � uma sequ�ncia de aplica��es gr�ficas, antes de qualquer altera��o n�s sempre devemos
		//limpar a tela, logo, nesse caso usamos de um ret�ngulo roxo para limpar (que no nosso caso, simula o background)
		
		g.setColor(Color.ORANGE); //todos os elementos desenhados a partir de agora ser�o em ciano
		g.fillRoundRect(20, 20, 60, 60, 20, 20);
		g.fillOval(100, 10, 50, 50);
		
		g.setColor(Color.RED); //todos os elementos desenhados a partir de agora ser�o em vermelho
		g.fillRoundRect(25, 25, 18, 18, 5, 5);
						//x, y, largura, altura, arredondamento largura, arredondamento altura
		
		g = bs.getDrawGraphics(); //g = buffer , pegue o que foi desenhado
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); 
		//coloca o "porta retrato", "papel em branco desenhado" na tela

		//Como a leitura do computador � de cima para baixo, os elementos mais abaixo ir�o estar sobrepostos aos de cima
		//(as camadas de cima ser�o as ultimas escritas no sistema)
		
		bs.show(); 
		
		
		
		/*DICA: PARA APLICAR ALTERA��ES EM TEMPO REAL NO SEU SOFTWARE USE A OP��O DEBUG GAME AO INV�S DE RUN (para
		 * rodar o sistema / jogo), COM ELA VOC� PODER� APLICAR AS ALTERA��ES DESEJADAS E AO MESMO TEMPO VER SE EST�O
		 * FAZENDO EFEITO, MUITO �TIL PARA IMPLEMENTA��ES EM PARTES DE JOGOS COMO MOVIMENTA��O, CEN�RIO, ETC*/
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
