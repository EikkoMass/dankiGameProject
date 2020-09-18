package OrientacaoObjetos.Metodos;

public class Player {

	public void nascer() {
		System.out.println("Nasceu!");
		
		new Player().criptografar();
		// funcionou por ser uma função privada da própria classe
		
		
		
		Main.comStatic();
		
		//como o método do Main é estático e o main está dentro do mesmo package de Player, pode-se usar assim
		//o relacionamento!
		
		//para usar o sem static, apenas definindo a class toda vez que usar ou em uma variável objeto
		new Main().semStatic();
		
	}
	
	
	// modelo PRIVATE não funciona por extends e por objeto, se precisar que funcione pelo extends use o PROTECTED
	private void criptografar() {
		
		//<!> Métodos privados podem ser usados apenas pela própria classe em que foram criados!
		// Podendo ser utilizados em métodos públicos, assim saindo da propria classe
		// PRIVATE NÃO FUNCIONA NEM SE VOCÊ CHAMAR O METODO DIRETAMENTE EM UMA DECLARAÇÃO DE CLASSE EXTERNA
		
		System.out.println("Criptografado!");
	}
	
	protected void protegidoMasUtil() {
		// protected funciona com extends e com criação de objeto em outra classe
		// em suma, protected funciona apenas dentro do package em que está alocado
		// proibindo contato direto externo do package
		System.out.println("Sou privado da minha classe e da classe na qual eu for extedido!");
	}
	
}
