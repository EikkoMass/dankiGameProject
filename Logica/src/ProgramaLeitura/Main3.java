package ProgramaLeitura;

import java.util.Random;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		String nome, comando;
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		System.out.println("Ol�, Por favor insira seu nome: ");
		nome = in.nextLine();
		
		System.out.println("Boas Vindas "+nome+", Deseja ir para qual dire��o? [W / A / S / D]");
		comando = in.nextLine();
		
		if(comando.equals("W")) {
			System.out.println("Voc� saiu voando e nunca mais voltou! D:");
		}else if(comando.equals("S")) {
			
			System.out.println("Voc� entrou no subsolo da terra e morreu pela magma!");
			
		}else if(comando.equals("A")) {
			System.out.println("Todo mundo sabe que se come�a o jogo andando para a direita, Duhh");	
		}else {
			System.out.println("Voc� encontrou 1 inimigo, deseja: [A / Atacar | C / Correr]");
			comando = in.nextLine();
			if(comando.equals("A")) {
				if(rand.nextInt(100) < 75) {
					System.out.println("Voc� o matou, parab�ns!");
				}else {
					System.out.println("Voc� perdeu");				
				}
				
			}else {
				System.out.println("Voc� correu e perdeu!");
			}
		}

	}

}
