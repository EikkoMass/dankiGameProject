package GameLoopingProfissional;


// <!> ATEN��O esse c�digo foi alterado com base no arquivo em GraficosComJava (Java Project) > FrameCanvas (package) > Game (class) 
// Se deseja ver os coment�rios desse c�digo, v� para aquela class

import java.awt.Canvas; 
import java.awt.Dimension;

import javax.swing.JFrame; 



public class Game extends Canvas implements Runnable{
	
	public static JFrame frame; 
	private boolean isRunning;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	private Thread thread; //criando a thread
	
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		
	}
	
	
	public Game() {
		
		
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		//funciona sem this por j� definirmos qual class se refere (pelo fato do comando estar no constructor da
		//referente class);
		
		initFrame(); 
	}
	
	public void initFrame() {
		frame = new JFrame("My First Game"); 
		frame.add(this); //se o m�todo fosse est�tico, this n�o funcionaria
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
		
	}
	

	@Override 
	public void run() {
		
		
		/*<!!!> MILISEGUNDOS E NANOSEGUNDOS EM JAVA N�O CORREM COMO UM CRON�METRO EM SI
		 ELES SEGUEM UMA CONTAGEM DE 'SEGUNDOS' QUE OCORRE DESDE 1 DE JANEIRO DE 1970 UTC(coordinated universal time),
		LOGO, SE QUISER FAZER A CONTAGEM DE UM PERIODO DE TEMPO, FA�A:
		
		long comecoCodigo = System.currentTimeMillis();
		 	****todo o codigo aqui
		long fimCodigo = System.currentTimeMillis();
		
		long periodoTempo = (fimCodigo - comecoCodigo) //dar� n�meros como 1, 2 etc.
		
		
		================================================================================
		Se voc� chama uma vari�vel como System.nanoTime() ou System.currentTimeMillis(), ele n�o iniciar�
		uma contagem, ele ir� no contador de segundos que segue desde 1970 e pegar o n�mero que ele est� 
		definido naquele momento, exemplo:
		
		long milisegundosToday = System.currentTimeMillis() //retorna o valor 1589656970517 (16:22:55 // 16/05/2020)
		long milisegundosToday2 = System.currentTimeMillis() //retorna o valor 1589657001641 (16:23:33 // 16/05/2020)
																					//Em menos de 1 min aumentou 124 no valor 
		
		//<!!!> O VALOR � EST�TICO NA VARI�VEL, ELE N�O SE AUTO-INCREMENTA (se saiu 1589656970517, o valor vai se manter)
		 * (a n�o ser que voc� re-declare a vari�vel a cada segundo com currentTimeMillis() ou similares)
		Como pode ver, os valores vem de uma extensa contagem que ocorre a muito tempo, e que ainda segue, desde 1970,
		ent�o para manipular o tempo, � necess�rio usar desses n�merais que pegamos indiretamente.
	
		
		================================================================================================
		
		Teste para aplica��o:
		System.out.println(System.currentTimeMillis()); //valor: 1589656970517
		try {
			Thread.sleep(3000); //pause de 3s no c�digo
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()); //valor: 1589656970519
		
		//NESSE CASO O VALOR SE ALTERA VISUALMENTE	
		
		
		=================================================================================================
		
		Teste para aplica��o 2:
		long timer = System.currentTimeMillis(); //valor: 1589656970517
		System.out.println(timer); //valor: 1589656970517
		try {
			Thread.sleep(3000); //pause de 3s no c�digo
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(timer); //valor: 1589656970517
		
		//NESSE CASO O VALOR N�O SE ALTERA VISUALMENTE, POR PEDIR UM VALOR QUE FOI ARMAZENADO NA VARI�VEL,
		 * E N�O O VALOR EM TEMPO REAL
		*/
		
		
		long lastTime = System.nanoTime(); //Pega nosso tempo em nano segundos (1 segundo = 1e+9 nanosegundos = 1.000.000.000 nanoSegundos).
		double amountOfTicks = 60.0; //Quantas vezes (por segundo) eu quero que ele fa�a a repeti��o (16.666.666,66....).
		double ns = 1000000000 / amountOfTicks; // 1 segundo em nanosegundos dividido por 60 (60 vezes por segundo // 16.666.666,66....).
		int frames = 0; //contadora de repeti��es de tick e render;
		double timer = System.currentTimeMillis(); //Retorna o tempo passado em milisegundos

		
		double delta = 0; //quando delta alcan�ar 1s ou mais, entrar� em 1 if que executa o tick e o render
		
		while(isRunning) {
			
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns; // (16.666.666,66 (60 FPS em nanoSegundos) - tempo passado em nanoTime) /  
			//como um +=, ele vai incrementar at� chegar no n�mero 1 ou superior
			lastTime = now; //a partir do momento que correr o tempo, o tempo anterior vai ser anulado, e o atual vai se tornar o anteiror
			//e por a� segue
			
			if(delta>=1) {
				tick();
				render();
				frames++; //vai contabilizar quantas vezes por segundo ele passou na repeti��o (como uma contadora)
				delta--; //delta diminui 1, logo, saindo do rastro do if at� acumular 1s denovo
			}
			
			if(System.currentTimeMillis() - timer >= 1000) { //significa que passou 1 segundo
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000; //incrementa 1s em milisegundo (poderia usar System.currentTimeMillis())
			}
		}
		
	}

}
