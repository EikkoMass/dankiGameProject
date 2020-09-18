package FrameCanvas;

import java.awt.Canvas; //Importando (por n�o estar no package local) Canvas do extends
import java.awt.Dimension;

import javax.swing.JFrame; //Class que simula a Interface de software nativa do Java


//<!> NESSE CASO O RUNNABLE N�O FOI USADO, APESAR DE ESTAR IMPLEMENTADO
public class Game extends Canvas implements Runnable{

	//Runnable: funcionamento de Threads do jogo!
	//Canvas: Class de funcionamento gr�fico nativo do Java, para fazer a parte visual
	
	public static JFrame frame; //objeto de inicializa��o de janela
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	//private static Thread thread;
	
	public Game() {
		
		
		//Dimension: Classe que define uma dimens�o para o computador / classe requerida
		//setPreferredSize() = M�todo do Canvas para definir o tamanho de prefer�ncia da classe
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); //fazendo dimens�o * escala para maior flexibilidade de tamanho da janela
		//Escala de dimens�o que a classe tem prefer�ncia em obter!
		// Console: 'java.awt.Dimension[width=640,height=480]' 
		
		//thread = new Thread(this); //n�o funciona o this no main (recomendado declarar em contrutoras e m�todos pr�prios para o caso)
		
		System.out.println(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame = new JFrame("My First Game"); //Ao iniciar a classe JFrame no objeto, � poss�vel adicionar o nome da janela
		
		frame.add(this); //Adicionamos a classe (com Canvas imbutido) dentro da Janela do software
		frame.setResizable(false); // A janela em quest�o n�o � redimension�vel (pelo usu�rio)
		frame.pack(); //Detectar as dimens�es desejadas pela classe imbutida e aplicar (adicionar as dimens�es de prefer�ncia)
		frame.setLocationRelativeTo(null); //Centraliza a Janela no monitor (se n�o definir uma localiza��o ele centraliza)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando a pessoa apertar no X, � para fechar a janela e todas as execu��es em segundo plano da mesma
		frame.setVisible(true); //A janela ficar vis�vel
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
