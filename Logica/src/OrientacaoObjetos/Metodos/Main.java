package OrientacaoObjetos.Metodos;

public class Main {

	// 'Static' seriam m�todos independentes, que funcionam por si mesmos:
	
	
	public void semStatic() {
		System.out.println("Estou chamando o metodo sem 'static'");	
	}
	
	public static void comStatic() {
		System.out.println("Estou chamando o metodo com 'static'");	
	}
	
	public static void main(String[] args) {
		
		Player player = new Player();
		
		// player.criptografar();
		// indica erro por  ser um m�todo privado da Classe Player
		
		player.nascer();
		
		//como a pr�pria classe usou do m�todo, ele foi aplicado sem problemas dentro do outro m�todo por o mesmo
		//ser p�blico!
		
		
		//funcionou por Main ser do mesmo package de Player2, se fosse de outro package, indicaria erro por
		//se um metodo protegido (protected)
		new Player2().metodoPegandoProtected();
		
		
		//______________________STATIC___________________________________________________
		
		//Para chamar um metodo sem static, � necess�rio indicar a classe que ele est� alocado
		//ISSO AT� SE O METODO FOR DA MESMA CLASSE
		
		Main main = new Main();
		main.semStatic();
		new Main().semStatic();
		//� necess�rio criar um objeto da pr�pria classe para pegar um metodo local
		//(que absurdo)
		
		//J� com o static n�o � necess�rio usar de todo esse arqu�tipo de chamar o mesmo objeto
		
		comStatic();
		Main.comStatic();
		
		//<!> Veja mais 1 exemplo na class Player.nascer();
	}

}
