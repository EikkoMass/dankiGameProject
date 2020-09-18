package ThreadsLoopings;

//A o implementar Runnable, est� criando a fun��o public void run(), e o funcionamento de threads 
public class Game implements Runnable{
	//<!> Runnable � uma Interface (sempre obrigat�rio tem essa interface na cria��o de threads)

	private boolean isRunning; //Certifica se o jogo est� rodando ou n�o
	private Thread thread; //Come�amos a vari�vel objeto thread, que obviamente, manipula as threads
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start(); //iniciamos a fun��o start

	}
	
	public synchronized void start() { //cria um m�todo de inicia��o 'sincronizada'
		isRunning = true; //iniciamos o jogo
		thread = new Thread(this); //declaramos uma thread para a nossa propria classe
		thread.start(); //iniciamos a thread, logo, ele executar� a fun��o 'run()' aqui do c�digo
	}
	
	
	public void tick(){ //no tick, vai ser implementado todos os comandos do jogo e atualizar� o jogo
		System.out.println("Tick");
	}
	
	public void render(){ //no render, vai ser implementado toda a interface gr�fica do jogo
		System.out.println("Render");
	}
	
	

	@Override //Criado por 'implements Runnable'
	public void run() { //ISSO J� � UMA THREAD

		while(isRunning) { //enquanto o jogo estiver rodando, fa�a:
			tick();
			render();
			
			/*thread.sleep(1000/60);
				no conceito acima estamos desenvolvendo um limitador por milisegundo, no qual,
				voc� dir� quanto tempo de delay voc� deseja para a pr�xima a��o em milisegundos,
				o java esperar� esse tempo de seguir� com a leitura.
			
				nesse caso foi usado 1000 milisegundos (1 segundo) dividido por 60, logo, equivalendo a,
				60 frames por segundo.
			
			ap�s escrever o c�digo acima o java pede para aplicar um 'concerto' no c�gido, fazendo um tryCatch*/
			
			try {
				Thread.sleep(1000/60); //ele far� uma pausa de 16,666666... milisegundos (1000 / 60) que 
				//equivaleria a rodar 60 vezes por segundo
				
				//try � usado para tentar aplicar alguma a��o desejada
			} catch (InterruptedException e) {
				// catch � usado para mostrar caso ocorra algum erro com o try
				e.printStackTrace();
				//nesse caso e seria um atributo que armazena o erro ocorrido printando-o para o desenvolvedor
			}
		
			
			/*O tryCatch e o Thread.sleep() usado acima n�o � o recomendado em casos de jogos grandes, para casos
			mais s�rios existe variantes mais recomendadas, me se for para jogos pequenos n�o tem problema*/
			
		}
		
	}

}
