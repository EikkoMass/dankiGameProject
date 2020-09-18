package OrientacaoObjetos;

// public class Main extends AbstractClass {

// Se extendendo com uma classe abstrata, ou seja, Main est� herdando todas as fun��es da
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
		// voc� pode fazer quantas vari�veis quiser com o mesmo objeto, desde que a identifique
		
		
		//Caso queira apenas aplicar uma fun��o de uma classe, sem guarda-la em uma vair�vel
		//Pode fazer assim:
		
		new Player().IniciarJogador();
		//funciona da mesma forma, por�m sem uma vari�vel condizendo a mesma
		
		
		
		new Main().instanceMain2();
		//apenas � poss�vel usar da inner class se dedicar 1 fun��o para pegar o que deseja dela
		
		
		//AbstractClass abs = new AbstractClass();
		//   ^N�o funciona por se referir a uma classe abstrata (leia mais na AbstractClass.java)
		
		//como foi extendida para o main, agora � possivel fazer:
		
		
		//-----------------------------------------------------------------
		
		//FuncAbs(); 
		//^fun��o herdada do AbstractClass
		
		//logo, todas as fun��es passadas por extends podem ser usadas como heran�a no main
		
		//<!> Indica erro pois o extends do AbstractClass foi comentado
		
		//-----------------------------------------------------------------
		
		new Main().chamado();
		//precisa chamar a fun��o "chamado" assim por n�o ser 'static'
		
		
		//<!> TODO TIPO DE CLASS PODE SER USADA NO EXTENDS, O ABSTRACT FOI USADO APENAS
		//COMO EXEMPLIFICA��O (por ser o unico modo da abtract funcionar)
		
		
	}
	
	public void chamaThis(){
		System.out.println("This est� chamando com base na pr�pria classe");
	}
	
	public void chamado() {
		this.chamaThis();
		//this � um modo de se referir a pr�pria classe, muito �til para determinar
		// ou atualizar valores de vari�veis ou utilizar fun��es locais!
	}
	
	public void instanceMain2() {
		new Main2().print1();
		//fun��o pegando o que deseja do Main2
	}
	
	
	//classe privada, ou seja, apenas Main pode us�-la
	private class Main2{
		// chamado de Inner Class
		//Classe (Main2) dentro de outra classe (Main)!
		
		public void print1() {
			//fun��o da Main2
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
	//Override indica que a parte do c�digo foi automaticamente gerada!
	public void sobrescrever() {
		System.out.println("SobreEscrito");	
	}
	
	//As fun��es acima foram todas dissertadas como obrigat�rias para o c�digo, de acordo
	// com o MinhaInterface implementado!
	
	//logo, foram automaticamente geradas para evitar um erro!
	
}
