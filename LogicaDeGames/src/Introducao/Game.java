package Introducao;

public class Game {

	public static void main(String[] args) {

		//Normalmente um c�digo funciona com uma leitura de cima para baixo, em um sistema de 
		//a��o e rea��o, j� um Game funciona em um Game Loop, um game loop nada mais � do que um loop 'infinito'
		//do c�digo sendo executado (tick & render).
		
		//Threads: O sistema executar mais de 1 c�digo ao mesmo tempo.
		
		//Conceito Abstrato: =================================================
		
		while(true) {
			
		comandos();
		
		renderizar();
		
	
		//� necess�rio aplicar um limitador, pois sen�o o c�digo corre muito r�pido, e justamente por isso que
		// existe o 'FPS' (ex: 60fps, 120fps), sem o fps, nem o olho humano conseguiria captar direito;
			
		}
		
	}
	
	public static void renderizar() {
		//Aqui renderiza a parte gr�fica do jogo (que a movimenta��o segue a parte dos comandos / parte l�gica);
		
	}
	
	public static void comandos() {
		//aqui s�o inseridos os comandos do jogo, como andar, tirarVida, correr, abrirMenu, etc.
	}
	

}
