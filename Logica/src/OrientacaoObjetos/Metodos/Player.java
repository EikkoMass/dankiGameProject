package OrientacaoObjetos.Metodos;

public class Player {

	public void nascer() {
		System.out.println("Nasceu!");
		
		new Player().criptografar();
		// funcionou por ser uma fun��o privada da pr�pria classe
		
		
		
		Main.comStatic();
		
		//como o m�todo do Main � est�tico e o main est� dentro do mesmo package de Player, pode-se usar assim
		//o relacionamento!
		
		//para usar o sem static, apenas definindo a class toda vez que usar ou em uma vari�vel objeto
		new Main().semStatic();
		
	}
	
	
	// modelo PRIVATE n�o funciona por extends e por objeto, se precisar que funcione pelo extends use o PROTECTED
	private void criptografar() {
		
		//<!> M�todos privados podem ser usados apenas pela pr�pria classe em que foram criados!
		// Podendo ser utilizados em m�todos p�blicos, assim saindo da propria classe
		// PRIVATE N�O FUNCIONA NEM SE VOC� CHAMAR O METODO DIRETAMENTE EM UMA DECLARA��O DE CLASSE EXTERNA
		
		System.out.println("Criptografado!");
	}
	
	protected void protegidoMasUtil() {
		// protected funciona com extends e com cria��o de objeto em outra classe
		// em suma, protected funciona apenas dentro do package em que est� alocado
		// proibindo contato direto externo do package
		System.out.println("Sou privado da minha classe e da classe na qual eu for extedido!");
	}
	
}
