package RenderizandoIniciandoBuffers;


//<!> ATENÇÃO esse código foi alterado com base no arquivo em GraficosComJava (Java Project) > GameLoopingProfissional (package) > Game (class) 
//Se deseja ver os comentários desse código, vá para aquela class

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
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //Variável objeto de imagem
		//dizendo qual largura e altura terá e que tipo de coloração
		/* BufferedImage():	campo 1: X
		 * 					campo 2: Y
		 * 					campo 3: Tipo de Imagem
		 * 									(BufferedImage.TYPE_INT_RGB é o padrão, que seria uma imagem colorida com
		 * 									angulação inteira, respeitando a grade de pixels)*/
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
		
		/*getBufferStratery() : Toda vez que o elemento entra no render ele tentará pegar a estratégia de buffer da 
		 * classe, caso não exista, ele cria a estratégia, para na criação da estratégia e segue normalmente o código
		 * (com a estratégia de buffer criada, ele consegue executar os comandos gráficos)*/
		
		
		
		//necessário importar o Java.awt.image.BufferStrategy;
		BufferStrategy bs = this.getBufferStrategy(); //this funciona por conta da classe estar estendida com canvas
		//BufferStrategy é uma sequência de buffers que colocamos na tela afim de otimizar a renderização de imagens
		
		//getBufferStrategy()  e createBufferStrategy() são usados com base na classe, para indicar que aquele
		//é o buffer desejado para AQUELA classe, aí o papel da variável BufferStrategy é pegar a estratégia de buffer
		//desejada.
		
		if(bs ==null) {
			this.createBufferStrategy(3);
			return; //terminar o render() aqui, primeiro iniciar o buffer e depois iniciar todas as renderizações
		}
		
		//Graphics g = bs.getDrawGraphics(); //agora elementos são renderizáveis na tela (sendo o que quiser)
		
		Graphics g = image.getGraphics(); //variável objeto gráfica (possuindo largura e altura desejada para o gráfico)
		
		//pegando um Objeto de Imagem como referência (assim podendo especificar as caracteríscticas da imagem
		//como altura, largura da área total de desenho e o estilo gráfico (div))
		
		//por ter pego os gráficos de image, g vai manipular as configurações de image
		
		
		g.setColor(new Color(100,12,118)); //importar Java.awt.Color //determinar cor para o elemento
		
		//semelhante a background-color: rgb(100, 12, 118, 1.0) no CSS
		//como se image fosse a div do elemento
		
		g.fillRect(0, 0, WIDTH, HEIGHT); //Fazer um quadrado (ou retângulo)
		/*FillRect(): campo 1: X (localização)
		 			  campo 2: Y (localização)
		 			  campo 3: largura do objeto
		 			  campo 4: altura do objeto */
		g = bs.getDrawGraphics(); //dizendo que você quer renderizar isso na tela
		
		//image é como se fosse uma folha de papel em branco, no qual dentro dele você vai desenhar o que deseja:
		
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); //desenhar a imagem colocada no campo 1
					// Quero desenhar nesse elemento (esboço) // tendo essa largura // essa altura // ? //
		
		
		/*drawImage():  campo 1: Elemento Base 
		 * 				campo 2: X (localização) 
		 * 				campo 3: Y (localização)
		  				campo 4: largura do objeto
		  				campo 5: altura do objeto
		  				campo 6: ? (padrão nulo) */
		
		bs.show(); //depois de organizar tudo, apresente esse comando para que ele saiba que pode mostrar o configurado
		
		
		//Esse foi todo o processo para renderizar 1 elemento, para fazer mais vezes, selecionaria outro campo de desenho com g,
		//desenharia o que deseja e bs.show(), e por aí segue.
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
