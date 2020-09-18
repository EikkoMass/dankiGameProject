package OrientacaoObjetos.Metodos;

public class Main {

	// 'Static' seriam métodos independentes, que funcionam por si mesmos:
	
	
	public void semStatic() {
		System.out.println("Estou chamando o metodo sem 'static'");	
	}
	
	public static void comStatic() {
		System.out.println("Estou chamando o metodo com 'static'");	
	}
	
	public static void main(String[] args) {
		
		Player player = new Player();
		
		// player.criptografar();
		// indica erro por  ser um método privado da Classe Player
		
		player.nascer();
		
		//como a própria classe usou do método, ele foi aplicado sem problemas dentro do outro método por o mesmo
		//ser público!
		
		
		//funcionou por Main ser do mesmo package de Player2, se fosse de outro package, indicaria erro por
		//se um metodo protegido (protected)
		new Player2().metodoPegandoProtected();
		
		
		//______________________STATIC___________________________________________________
		
		//Para chamar um metodo sem static, é necessário indicar a classe que ele está alocado
		//ISSO ATÉ SE O METODO FOR DA MESMA CLASSE
		
		Main main = new Main();
		main.semStatic();
		new Main().semStatic();
		//é necessário criar um objeto da própria classe para pegar um metodo local
		//(que absurdo)
		
		//Já com o static não é necessário usar de todo esse arquétipo de chamar o mesmo objeto
		
		comStatic();
		Main.comStatic();
		
		//<!> Veja mais 1 exemplo na class Player.nascer();
	}

}
