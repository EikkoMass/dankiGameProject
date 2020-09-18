package OrientacaoObjetos.ExemploExercicio;
//package: conjunto de classes


public class Game {
	
	private Player player; //Jogador privado do jogo (personagem com características de 'Player')
	private Inimigo inimigo; //Inimigo privado do jogo (personagem com características de 'Inimigo')
	
	
	public Game() { //Construtor iniciando variáveis objeto
		
		if(player == null) { //Verificar se Objeto ainda não foi criado
			System.out.println("player não foi instanciado ainda!!!");
		}
		
		player = new Tipo1(); //funciona usar Tipo1 pois Tipo1 está extendido com Player, logo,
		//Tipo1 é um Player
		
		if(player instanceof Tipo1) {
			
			/*	INSTANCEOF é uma entidade relacional para determinar qual tipo de Class / Objeto foi
			 * instanciada em determinada variável, assim facilitando para determinar qual tipo de 
			 * objeto está sendo usado!
			 */
			
			System.out.println("player foi instanciado a partir de Tipo1"); //Instanciado como Tipo 1 extends Player
		}else if(player instanceof Player) {
			System.out.println("player foi instanciado a partir de Player"); //Instanciado como Player
		}
		
		/*Nesse caso consideremos que existam vários tipo de player [Roadhog, Reinhardth, Tracer, Hanzo],
		/todos eles tem conceitos básicos de player, como movimentação, vida, arsenal, etc.
		Porém, cada player tem características diferentes, logo, temos vários tipos de players
		
		E aqui que entra o conceito, Tipo1 é uma class criada em extensão com Player, ou seja, ela é
		denominada um player (assim podendo ser usada em uma criação de objeto Player player), mas além
		disso, um tipo de player, assim podendo fazer classes diferenciadas para cada tipo de player sem
		perder os conceitos básicos de cada player.	*/
		
		inimigo = new Inimigo();
		
	}
			//Obrigatoriamente tem que retornar um objeto 'Inimigo'
	public Inimigo getInimigo() {
		return inimigo; //pegando a variável objeto privada (inimigo do jogo)
	}
	
			//Obrigatoriamente tem que retornar um objeto 'Player'
	public Player getPlayer() {
		return player; //pegando a variável objeto privada (jogador do jogo)
	}


	public static void main(String[]args) {
		Game game = new Game(); //inicia a variável objeto e a classe (iniciando o constuctor)
		Player player = game.getPlayer(); //dizendo que a variável player local condiz a variável player privada da classe
		player.atacarInimigo(game.getInimigo()); //Player player -> atacarInimigo -> game.getInimigo() = inimigo privado da classe
		//essa variável player é diferente da privada, mas no caso está sendo usada para manipular a privada
		System.out.println(game.getInimigo().life); //game.getInimigo().life => mostrar vida do objeto privado 'inimigo'
		
		
		//<!> PLAYER FUNCIONA NORMALMENTE POR TIPO1 TER EXTENDS COM PLAYER, LOGO, TEM TODOS OS MÉTODOS DE PLAYER
	}
	
}
