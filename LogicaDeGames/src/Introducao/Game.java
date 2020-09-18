package Introducao;

public class Game {

	public static void main(String[] args) {

		//Normalmente um código funciona com uma leitura de cima para baixo, em um sistema de 
		//ação e reação, já um Game funciona em um Game Loop, um game loop nada mais é do que um loop 'infinito'
		//do código sendo executado (tick & render).
		
		//Threads: O sistema executar mais de 1 código ao mesmo tempo.
		
		//Conceito Abstrato: =================================================
		
		while(true) {
			
		comandos();
		
		renderizar();
		
	
		//É necessário aplicar um limitador, pois senão o código corre muito rápido, e justamente por isso que
		// existe o 'FPS' (ex: 60fps, 120fps), sem o fps, nem o olho humano conseguiria captar direito;
			
		}
		
	}
	
	public static void renderizar() {
		//Aqui renderiza a parte gráfica do jogo (que a movimentação segue a parte dos comandos / parte lógica);
		
	}
	
	public static void comandos() {
		//aqui são inseridos os comandos do jogo, como andar, tirarVida, correr, abrirMenu, etc.
	}
	

}
