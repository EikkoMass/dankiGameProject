package com.me.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.me.world.World;

public class Menu {

	public String[] options = {"New Game", "Load Game", "Options", "Quit Game"};
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	public boolean up, down, left, right, enter;
	public static boolean pause, saveExists, sameGame;
	
	
	public void tick() {
		
		File file = new File("save.txt");
		
		if(file.exists()) {
			saveExists = true;
		}else {
			saveExists = false;
		}
		
		if(pause) {
			
			options[0] = "Resume Game";
		}else {
			options[0] = "New Game";
		}
		
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}
		
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}
		
		if(enter) {
			enter = false;
			switch(currentOption) {
			
			case 0: //New Game
				file = new File("save.txt");
				file.delete();
				Game.newWorld = "Map1.png";
				World.resetGame(Game.newWorld);
				Game.gameState = "NORMAL";
				break;
				
			case 1: //Load Game
				file = new File("save.txt");
				if(file.exists()) {
					String saver = loadGame(10);
					applySave(saver);
				}
				
				break;
			
			case 2: //options
				break;
				
			case 3: //Exit Game
				System.exit(0);
				break;
			
			}
			
			pause = false;
		}
		
	}
	public void render(Graphics g) {
		
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 180));
		g2.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		//g2.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

		g.setColor(Color.white);
		g.setFont(Game.newFont70);
		g.drawString("Tiny Mistakes", ((Game.WIDTH*Game.SCALE) / 2) - 170, ((Game.HEIGHT*Game.SCALE) / 2) - 100);
		
		//Menu Options
		
		g.setColor(Color.yellow);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		for(int i = 0; i < maxOption + 1; i++) {
			
			if(currentOption != i) {
				g.setColor(Color.yellow);
				g.drawString(options[i], (((Game.WIDTH*Game.SCALE) / 2) - 120) + (i*50) ,(int)((((Game.HEIGHT*Game.SCALE) / 2)) + (60 * i)));
			}else {
				g.setColor(Color.MAGENTA);
				g.drawString(">", (((Game.WIDTH*Game.SCALE) / 2) - 140) + (i*50) ,(int)((((Game.HEIGHT*Game.SCALE) / 2)) + (60 * i)));
				g.drawString(options[i], (((Game.WIDTH*Game.SCALE) / 2) - 120) + (i*50) ,(int)((((Game.HEIGHT*Game.SCALE) / 2)) + (60 * i)));
			}
		}
	}
	
	public static void saveGame(String[] val1, int[] val2, int encode) { //salvar os dados do jogo e criptografando-os
	
		BufferedWriter write = null; //clase manipuladora de arquivos (escreve dentro deles)
		try {
			write = new BufferedWriter(new FileWriter("save.txt")); //criamos um sistema de escrita de elementos, aplicando todos em um arquivo criado e chamado 'save.txt'
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < val1.length; i++) {
			String current = val1[i];
			current+=":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			//pegamos o valor de val2[i] em string e depois adicionamos a string inteira em um unico array de char (o array inteiro � uma palavra em String)
		
			for(int j = 0; j < value.length; j++) {
				value[j]+=encode; //criptografando o save do jogo
				current+=value[j];
			}
			try{
				write.write(current);
				
				if(i < val1.length - 1) //se for a ultima casa o array val1
					write.newLine(); //pule uma (fa�a uma nova) linha no documento
				
				
			}catch(IOException e) {
			}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e){
			
		}
		
	}
	
	public static String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt"); //objeto que possui a capacidade de armazenar arquivos externos para manipula��o
	
		if(file.exists()) { // se o arquivo existir dentro do objeto
			
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				
				try {
					while((singleLine = reader.readLine()) != null) { //se ambos forem iguais e ao mesmo tempo diferentes de null
					
						String[] trans = singleLine.split(":");
						//split separa partes de vari�veis a partir de algum caractere desejado
						
						/*Por exemplo, o valor seria String string = "level:3";
						 * 
						 * String[] trans = string.split(":");
						 * 
						 * A linha acima ir� resultar no array:
						 * 
						 * trans[0] = level;
						 * trans[1] = t4e4s4t4e4;
						 * */
						
						char[] val = trans[1].toCharArray(); //charArray do dado criptografado
						trans[1] = "";
						
						for(int i = 0; i < val.length; i++) {
							val[i]-=encode; //tira a criptografia da letra no charArray
							
							//Pense que voc� est� manipulando um Array de char:
							
							/*Os valores criptografados estar�o da seguinte forma:
							 * 
							 *  't' 'e' 's' 't' 'e' (valor que queremos, sem criptografia)
							 * 
							 * val[0] = 't';
							 * val[1] = '4'; //criptografia
							 * val[2] = 'e';
							 * val[3] = '4'; //criptografia
							 * val[4] = 's';
							 * val[5] = '4'; //criptografia
							 * val[6] = 't';
							 * val[7] = '4'; //criptografia
							 * val[8] = 'e';
							 * val[9] = '4'; //criptografia
							 * 
							 * n�s aplicamos no for a senguinte fun��o
							 * 
							 * enconde = 4, pois esse � nosso valor de criptografia
							 * 
							 * val[i] -= encode; 
							 * 
							 * encode -> 4
							 * val[i] --> se for = encode, ele apagar� o valor armazenado deixando a casa vazia.
							 * caso contr�rio, manter� o valor
							 * */
							
							
							trans[1] += val[i]; //incrementa a letra descriptografada
							//caso tenha sido uma casa de valor criptogrado, vai receber variavel+="";
							//caso contrario, incrementara o resultado: variavel+='t', variavel+='e' ...
						
							char teste = '1';
							teste-=1;
						}
						
						line +=trans[0];
						line +=":";
						line +=trans[1]; //remodelando a linha, agora sem o dado descriptografado
						line+="/";
						
						//line = "level:teste/"; , por exemplo
						
					}
					
				}catch(IOException e){ 
					
				}
				
			}catch(FileNotFoundException e) { //caso n�o encontre o arquivo
				
			}
			
		}
		return line;
		
	}
	
	public static void applySave(String str) {
		
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			
			switch(spl2[0]) { //nunca passar� de 0 e 1 no for(){}
			
			case "level":
				
				World.resetGame("Map"+spl2[1]+".png");
				Game.gameState = "NORMAL";
				pause = false;	 
				break;
				
			case "vida":
				Game.player.life = Double.parseDouble(spl2[1]);			
				break;
				
			case "playerX":
				Game.player.setX(Integer.parseInt(spl2[1])) ;			
				break;
			
			case "playerY":
				Game.player.setY(Integer.parseInt(spl2[1])) ;			
				break;
			
			}
		}
	}
	
}
