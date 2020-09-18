package GameLoopingProfissional;


// <!> ATENÇÃO esse código foi alterado com base no arquivo em GraficosComJava (Java Project) > FrameCanvas (package) > Game (class) 
// Se deseja ver os comentários desse código, vá para aquela class

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
		//funciona sem this por já definirmos qual class se refere (pelo fato do comando estar no constructor da
		//referente class);
		
		initFrame(); 
	}
	
	public void initFrame() {
		frame = new JFrame("My First Game"); 
		frame.add(this); //se o método fosse estático, this não funcionaria
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
		
		
		/*<!!!> MILISEGUNDOS E NANOSEGUNDOS EM JAVA NÃO CORREM COMO UM CRONÔMETRO EM SI
		 ELES SEGUEM UMA CONTAGEM DE 'SEGUNDOS' QUE OCORRE DESDE 1 DE JANEIRO DE 1970 UTC(coordinated universal time),
		LOGO, SE QUISER FAZER A CONTAGEM DE UM PERIODO DE TEMPO, FAÇA:
		
		long comecoCodigo = System.currentTimeMillis();
		 	****todo o codigo aqui
		long fimCodigo = System.currentTimeMillis();
		
		long periodoTempo = (fimCodigo - comecoCodigo) //dará números como 1, 2 etc.
		
		
		================================================================================
		Se você chama uma variável como System.nanoTime() ou System.currentTimeMillis(), ele não iniciará
		uma contagem, ele irá no contador de segundos que segue desde 1970 e pegar o número que ele está 
		definido naquele momento, exemplo:
		
		long milisegundosToday = System.currentTimeMillis() //retorna o valor 1589656970517 (16:22:55 // 16/05/2020)
		long milisegundosToday2 = System.currentTimeMillis() //retorna o valor 1589657001641 (16:23:33 // 16/05/2020)
																					//Em menos de 1 min aumentou 124 no valor 
		
		//<!!!> O VALOR É ESTÁTICO NA VARIÁVEL, ELE NÃO SE AUTO-INCREMENTA (se saiu 1589656970517, o valor vai se manter)
		 * (a não ser que você re-declare a variável a cada segundo com currentTimeMillis() ou similares)
		Como pode ver, os valores vem de uma extensa contagem que ocorre a muito tempo, e que ainda segue, desde 1970,
		então para manipular o tempo, é necessário usar desses númerais que pegamos indiretamente.
	
		
		================================================================================================
		
		Teste para aplicação:
		System.out.println(System.currentTimeMillis()); //valor: 1589656970517
		try {
			Thread.sleep(3000); //pause de 3s no código
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()); //valor: 1589656970519
		
		//NESSE CASO O VALOR SE ALTERA VISUALMENTE	
		
		
		=================================================================================================
		
		Teste para aplicação 2:
		long timer = System.currentTimeMillis(); //valor: 1589656970517
		System.out.println(timer); //valor: 1589656970517
		try {
			Thread.sleep(3000); //pause de 3s no código
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(timer); //valor: 1589656970517
		
		//NESSE CASO O VALOR NÃO SE ALTERA VISUALMENTE, POR PEDIR UM VALOR QUE FOI ARMAZENADO NA VARIÁVEL,
		 * E NÃO O VALOR EM TEMPO REAL
		*/
		
		
		long lastTime = System.nanoTime(); //Pega nosso tempo em nano segundos (1 segundo = 1e+9 nanosegundos = 1.000.000.000 nanoSegundos).
		double amountOfTicks = 60.0; //Quantas vezes (por segundo) eu quero que ele faça a repetição (16.666.666,66....).
		double ns = 1000000000 / amountOfTicks; // 1 segundo em nanosegundos dividido por 60 (60 vezes por segundo // 16.666.666,66....).
		int frames = 0; //contadora de repetições de tick e render;
		double timer = System.currentTimeMillis(); //Retorna o tempo passado em milisegundos

		
		double delta = 0; //quando delta alcançar 1s ou mais, entrará em 1 if que executa o tick e o render
		
		while(isRunning) {
			
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns; // (16.666.666,66 (60 FPS em nanoSegundos) - tempo passado em nanoTime) /  
			//como um +=, ele vai incrementar até chegar no número 1 ou superior
			lastTime = now; //a partir do momento que correr o tempo, o tempo anterior vai ser anulado, e o atual vai se tornar o anteiror
			//e por aí segue
			
			if(delta>=1) {
				tick();
				render();
				frames++; //vai contabilizar quantas vezes por segundo ele passou na repetição (como uma contadora)
				delta--; //delta diminui 1, logo, saindo do rastro do if até acumular 1s denovo
			}
			
			if(System.currentTimeMillis() - timer >= 1000) { //significa que passou 1 segundo
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000; //incrementa 1s em milisegundo (poderia usar System.currentTimeMillis())
			}
		}
		
	}

}
