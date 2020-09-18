package OrientacaoObjetos.ExemploExercicio;
//package: conjunto de classes


public class Game {
	
	private Player player; //Jogador privado do jogo (personagem com caracter�sticas de 'Player')
	private Inimigo inimigo; //Inimigo privado do jogo (personagem com caracter�sticas de 'Inimigo')
	
	
	public Game() { //Construtor iniciando vari�veis objeto
		
		if(player == null) { //Verificar se Objeto ainda n�o foi criado
			System.out.println("player n�o foi instanciado ainda!!!");
		}
		
		player = new Tipo1(); //funciona usar Tipo1 pois Tipo1 est� extendido com Player, logo,
		//Tipo1 � um Player
		
		if(player instanceof Tipo1) {
			
			/*	INSTANCEOF � uma entidade relacional para determinar qual tipo de Class / Objeto foi
			 * instanciada em determinada vari�vel, assim facilitando para determinar qual tipo de 
			 * objeto est� sendo usado!
			 */
			
			System.out.println("player foi instanciado a partir de Tipo1"); //Instanciado como Tipo 1 extends Player
		}else if(player instanceof Player) {
			System.out.println("player foi instanciado a partir de Player"); //Instanciado como Player
		}
		
		/*Nesse caso consideremos que existam v�rios tipo de player [Roadhog, Reinhardth, Tracer, Hanzo],
		/todos eles tem conceitos b�sicos de player, como movimenta��o, vida, arsenal, etc.
		Por�m, cada player tem caracter�sticas diferentes, logo, temos v�rios tipos de players
		
		E aqui que entra o conceito, Tipo1 � uma class criada em extens�o com Player, ou seja, ela �
		denominada um player (assim podendo ser usada em uma cria��o de objeto Player player), mas al�m
		disso, um tipo de player, assim podendo fazer classes diferenciadas para cada tipo de player sem
		perder os conceitos b�sicos de cada player.	*/
		
		inimigo = new Inimigo();
		
	}
			//Obrigatoriamente tem que retornar um objeto 'Inimigo'
	public Inimigo getInimigo() {
		return inimigo; //pegando a vari�vel objeto privada (inimigo do jogo)
	}
	
			//Obrigatoriamente tem que retornar um objeto 'Player'
	public Player getPlayer() {
		return player; //pegando a vari�vel objeto privada (jogador do jogo)
	}


	public static void main(String[]args) {
		Game game = new Game(); //inicia a vari�vel objeto e a classe (iniciando o constuctor)
		Player player = game.getPlayer(); //dizendo que a vari�vel player local condiz a vari�vel player privada da classe
		player.atacarInimigo(game.getInimigo()); //Player player -> atacarInimigo -> game.getInimigo() = inimigo privado da classe
		//essa vari�vel player � diferente da privada, mas no caso est� sendo usada para manipular a privada
		System.out.println(game.getInimigo().life); //game.getInimigo().life => mostrar vida do objeto privado 'inimigo'
		
		
		//<!> PLAYER FUNCIONA NORMALMENTE POR TIPO1 TER EXTENDS COM PLAYER, LOGO, TEM TODOS OS M�TODOS DE PLAYER
	}
	
}
