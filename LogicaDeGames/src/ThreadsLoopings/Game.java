package ThreadsLoopings;

//A o implementar Runnable, está criando a função public void run(), e o funcionamento de threads 
public class Game implements Runnable{
	//<!> Runnable é uma Interface (sempre obrigatório tem essa interface na criação de threads)

	private boolean isRunning; //Certifica se o jogo está rodando ou não
	private Thread thread; //Começamos a variável objeto thread, que obviamente, manipula as threads
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start(); //iniciamos a função start

	}
	
	public synchronized void start() { //cria um método de iniciação 'sincronizada'
		isRunning = true; //iniciamos o jogo
		thread = new Thread(this); //declaramos uma thread para a nossa propria classe
		thread.start(); //iniciamos a thread, logo, ele executará a função 'run()' aqui do código
	}
	
	
	public void tick(){ //no tick, vai ser implementado todos os comandos do jogo e atualizará o jogo
		System.out.println("Tick");
	}
	
	public void render(){ //no render, vai ser implementado toda a interface gráfica do jogo
		System.out.println("Render");
	}
	
	

	@Override //Criado por 'implements Runnable'
	public void run() { //ISSO JÁ É UMA THREAD

		while(isRunning) { //enquanto o jogo estiver rodando, faça:
			tick();
			render();
			
			/*thread.sleep(1000/60);
				no conceito acima estamos desenvolvendo um limitador por milisegundo, no qual,
				você dirá quanto tempo de delay você deseja para a próxima ação em milisegundos,
				o java esperará esse tempo de seguirá com a leitura.
			
				nesse caso foi usado 1000 milisegundos (1 segundo) dividido por 60, logo, equivalendo a,
				60 frames por segundo.
			
			após escrever o código acima o java pede para aplicar um 'concerto' no cógido, fazendo um tryCatch*/
			
			try {
				Thread.sleep(1000/60); //ele fará uma pausa de 16,666666... milisegundos (1000 / 60) que 
				//equivaleria a rodar 60 vezes por segundo
				
				//try é usado para tentar aplicar alguma ação desejada
			} catch (InterruptedException e) {
				// catch é usado para mostrar caso ocorra algum erro com o try
				e.printStackTrace();
				//nesse caso e seria um atributo que armazena o erro ocorrido printando-o para o desenvolvedor
			}
		
			
			/*O tryCatch e o Thread.sleep() usado acima não é o recomendado em casos de jogos grandes, para casos
			mais sérios existe variantes mais recomendadas, me se for para jogos pequenos não tem problema*/
			
		}
		
	}

}
