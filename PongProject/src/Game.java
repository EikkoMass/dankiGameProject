import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //gerado automaticamente
	public static int WIDTH = 240, HEIGHT = 128, SCALE = 3;
	public static Player player; //os 3 est�o est�ticos para ter acesso �s mesmas em Ball e Enemy
	public static Enemy enemy;
	public static Ball ball;

	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Game() {

		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); //definindo um tamanho como profer�ncia para a classe
		this.addKeyListener(this); //adicionando eventos de clique na class Game
		player = new Player(100, HEIGHT - 5, 0); //inicia player indicando x e y
		enemy = new Enemy(100, 0, 0);
		ball = new Ball((WIDTH / 2) - 2, (HEIGHT / 2) - 2);
	}
	
	public static void main(String[] args) {

		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game); //adiciona a classe no jFrame
		frame.pack();
		frame.setLocationRelativeTo(null);//deixar como um dos ultimos (caso contr�rio acaba n�o executando por 
		//pack manipular certas partes desse c�digo)
		frame.setVisible(true); //procurar deixar sempre esse por ultimo
		
		new Thread(game).start(); //Indica que quer usar e iniciar a Thread que est� na class Game
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics(); //layer est� pegando todos os gr�ficos que forem colocados em g;
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(new Color(255, 255, 255, 25));
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(player.score + " | " + enemy.score, (WIDTH / 2) - 22, (HEIGHT / 2)); //n�o funciona por o jogo resetar
		player.render(g); //toda a renderiza��o do player
		enemy.render(g); //toda a renderiza��o do enemy
		ball.render(g);
		
		g = bs.getDrawGraphics(); // para o BufferStrategy (Thread) renderizar o que o g quiser
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null); //coloca para renderizar tudo que foi posto em g e
		//armazenado em layer.
		
		bs.show();
	}

	@Override
	public void run() {
		while(true) {
			requestFocus();
			try {
				Thread.sleep(1000/60);			 //60 fps
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			tick();
			render();
			
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //pressionar o bot�o

		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
		player.right = true;	
		player.left = false;
		
		player.colors[0]+=2;
		player.colors[1]++;
		player.colors[2]+=3;
		
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
		player.left = true;	
		player.right = false;
		}
		
		//Se n�o determinar o oposto como false, ocorre um bug, no qual, se voc� manter pressionado o bot�o
		//esquerdo e apertar o direito, ele vai come�ar a andar para a direita, por ser a primeira condi��o do
		//if, por conta disso ele simplesmente ignora a outra tela
		
	}

	@Override
	public void keyReleased(KeyEvent e) { //soltar o bot�o
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) { 
			//se o bot�o soltado for a seta direita do teclado ou o bot�o D:
				
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			//se o bot�o soltado for a seta esquerda do teclado ou o bot�o A:
			
			player.left = false;
		}
		
	}

}
