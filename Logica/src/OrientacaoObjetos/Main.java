package OrientacaoObjetos;

// public class Main extends AbstractClass {

// Se extendendo com uma classe abstrata, ou seja, Main está herdando todas as funções da
// AbstractClass!


public class Main implements MinhaInterface {
	
	
	public static void main(String[]args) {
		
		//================= Orientando Objetos ====================
		
		Player player = new Player();
		
		player.IniciarJogador();

		//=========================================================
		
		Inimigo inimigo = new Inimigo();
		
		inimigo.IniciarInimigo();
		
		//=========================================================
		
		Player player2 = new Player();
		
		player2.IniciarJogador();

		//=========================================================
		// você pode fazer quantas variáveis quiser com o mesmo objeto, desde que a identifique
		
		
		//Caso queira apenas aplicar uma função de uma classe, sem guarda-la em uma vairável
		//Pode fazer assim:
		
		new Player().IniciarJogador();
		//funciona da mesma forma, porém sem uma variável condizendo a mesma
		
		
		
		new Main().instanceMain2();
		//apenas é possível usar da inner class se dedicar 1 função para pegar o que deseja dela
		
		
		//AbstractClass abs = new AbstractClass();
		//   ^Não funciona por se referir a uma classe abstrata (leia mais na AbstractClass.java)
		
		//como foi extendida para o main, agora é possivel fazer:
		
		
		//-----------------------------------------------------------------
		
		//FuncAbs(); 
		//^função herdada do AbstractClass
		
		//logo, todas as funções passadas por extends podem ser usadas como herança no main
		
		//<!> Indica erro pois o extends do AbstractClass foi comentado
		
		//-----------------------------------------------------------------
		
		new Main().chamado();
		//precisa chamar a função "chamado" assim por não ser 'static'
		
		
		//<!> TODO TIPO DE CLASS PODE SER USADA NO EXTENDS, O ABSTRACT FOI USADO APENAS
		//COMO EXEMPLIFICAÇÃO (por ser o unico modo da abtract funcionar)
		
		
	}
	
	public void chamaThis(){
		System.out.println("This está chamando com base na própria classe");
	}
	
	public void chamado() {
		this.chamaThis();
		//this é um modo de se referir a própria classe, muito útil para determinar
		// ou atualizar valores de variáveis ou utilizar funções locais!
	}
	
	public void instanceMain2() {
		new Main2().print1();
		//função pegando o que deseja do Main2
	}
	
	
	//classe privada, ou seja, apenas Main pode usá-la
	private class Main2{
		// chamado de Inner Class
		//Classe (Main2) dentro de outra classe (Main)!
		
		public void print1() {
			//função da Main2
			System.out.println("Estamos dentro do Main2");
		}
		
	}
	
	//---------------- Aplicando da Interface ------------------------
	
	
	public void iniciar() {
		System.out.println("Iniciado");
	}

	@Override
	public void eliminar() {
		System.out.println("Eliminado");
		
	}

	@Override
	//Override indica que a parte do código foi automaticamente gerada!
	public void sobrescrever() {
		System.out.println("SobreEscrito");	
	}
	
	//As funções acima foram todas dissertadas como obrigatórias para o código, de acordo
	// com o MinhaInterface implementado!
	
	//logo, foram automaticamente geradas para evitar um erro!
	
}
