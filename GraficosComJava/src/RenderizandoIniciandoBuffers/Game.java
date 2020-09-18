package RenderizandoIniciandoBuffers;


//<!> ATEN��O esse c�digo foi alterado com base no arquivo em GraficosComJava (Java Project) > GameLoopingProfissional (package) > Game (class) 
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
	
	private BufferedImage image; //importar bufferedImage
	
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		
	}
	
	
	public Game() {
		
		
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame(); 
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //Vari�vel objeto de imagem
		//dizendo qual largura e altura ter� e que tipo de colora��o
		/* BufferedImage():	campo 1: X
		 * 					campo 2: Y
		 * 					campo 3: Tipo de Imagem
		 * 									(BufferedImage.TYPE_INT_RGB � o padr�o, que seria uma imagem colorida com
		 * 									angula��o inteira, respeitando a grade de pixels)*/
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
		
		/*getBufferStratery() : Toda vez que o elemento entra no render ele tentar� pegar a estrat�gia de buffer da 
		 * classe, caso n�o exista, ele cria a estrat�gia, para na cria��o da estrat�gia e segue normalmente o c�digo
		 * (com a estrat�gia de buffer criada, ele consegue executar os comandos gr�ficos)*/
		
		
		
		//necess�rio importar o Java.awt.image.BufferStrategy;
		BufferStrategy bs = this.getBufferStrategy(); //this funciona por conta da classe estar estendida com canvas
		//BufferStrategy � uma sequ�ncia de buffers que colocamos na tela afim de otimizar a renderiza��o de imagens
		
		//getBufferStrategy()  e createBufferStrategy() s�o usados com base na classe, para indicar que aquele
		//� o buffer desejado para AQUELA classe, a� o papel da vari�vel BufferStrategy � pegar a estrat�gia de buffer
		//desejada.
		
		if(bs ==null) {
			this.createBufferStrategy(3);
			return; //terminar o render() aqui, primeiro iniciar o buffer e depois iniciar todas as renderiza��es
		}
		
		//Graphics g = bs.getDrawGraphics(); //agora elementos s�o renderiz�veis na tela (sendo o que quiser)
		
		Graphics g = image.getGraphics(); //vari�vel objeto gr�fica (possuindo largura e altura desejada para o gr�fico)
		
		//pegando um Objeto de Imagem como refer�ncia (assim podendo especificar as caracter�scticas da imagem
		//como altura, largura da �rea total de desenho e o estilo gr�fico (div))
		
		//por ter pego os gr�ficos de image, g vai manipular as configura��es de image
		
		
		g.setColor(new Color(100,12,118)); //importar Java.awt.Color //determinar cor para o elemento
		
		//semelhante a background-color: rgb(100, 12, 118, 1.0) no CSS
		//como se image fosse a div do elemento
		
		g.fillRect(0, 0, WIDTH, HEIGHT); //Fazer um quadrado (ou ret�ngulo)
		/*FillRect(): campo 1: X (localiza��o)
		 			  campo 2: Y (localiza��o)
		 			  campo 3: largura do objeto
		 			  campo 4: altura do objeto */
		g = bs.getDrawGraphics(); //dizendo que voc� quer renderizar isso na tela
		
		//image � como se fosse uma folha de papel em branco, no qual dentro dele voc� vai desenhar o que deseja:
		
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); //desenhar a imagem colocada no campo 1
					// Quero desenhar nesse elemento (esbo�o) // tendo essa largura // essa altura // ? //
		
		
		/*drawImage():  campo 1: Elemento Base 
		 * 				campo 2: X (localiza��o) 
		 * 				campo 3: Y (localiza��o)
		  				campo 4: largura do objeto
		  				campo 5: altura do objeto
		  				campo 6: ? (padr�o nulo) */
		
		bs.show(); //depois de organizar tudo, apresente esse comando para que ele saiba que pode mostrar o configurado
		
		
		//Esse foi todo o processo para renderizar 1 elemento, para fazer mais vezes, selecionaria outro campo de desenho com g,
		//desenharia o que deseja e bs.show(), e por a� segue.
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
		
	}

}
