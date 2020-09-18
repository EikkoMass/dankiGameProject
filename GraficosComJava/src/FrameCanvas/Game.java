package FrameCanvas;

import java.awt.Canvas; //Importando (por não estar no package local) Canvas do extends
import java.awt.Dimension;

import javax.swing.JFrame; //Class que simula a Interface de software nativa do Java


//<!> NESSE CASO O RUNNABLE NÃO FOI USADO, APESAR DE ESTAR IMPLEMENTADO
public class Game extends Canvas implements Runnable{

	//Runnable: funcionamento de Threads do jogo!
	//Canvas: Class de funcionamento gráfico nativo do Java, para fazer a parte visual
	
	public static JFrame frame; //objeto de inicialização de janela
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	//private static Thread thread;
	
	public Game() {
		
		
		//Dimension: Classe que define uma dimensão para o computador / classe requerida
		//setPreferredSize() = Método do Canvas para definir o tamanho de preferência da classe
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); //fazendo dimensão * escala para maior flexibilidade de tamanho da janela
		//Escala de dimensão que a classe tem preferência em obter!
		// Console: 'java.awt.Dimension[width=640,height=480]' 
		
		//thread = new Thread(this); //não funciona o this no main (recomendado declarar em contrutoras e métodos próprios para o caso)
		
		System.out.println(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame = new JFrame("My First Game"); //Ao iniciar a classe JFrame no objeto, é possível adicionar o nome da janela
		
		frame.add(this); //Adicionamos a classe (com Canvas imbutido) dentro da Janela do software
		frame.setResizable(false); // A janela em questão não é redimensionável (pelo usuário)
		frame.pack(); //Detectar as dimensões desejadas pela classe imbutida e aplicar (adicionar as dimensões de preferência)
		frame.setLocationRelativeTo(null); //Centraliza a Janela no monitor (se não definir uma localização ele centraliza)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando a pessoa apertar no X, é para fechar a janela e todas as execuções em segundo plano da mesma
		frame.setVisible(true); //A janela ficar visível
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		
		//thread.start();
	}

	@Override //Gerado automaticamente pelo Runnable
	public void run() {
		//System.out.println("Rodou o Thread");
		
	}

}
