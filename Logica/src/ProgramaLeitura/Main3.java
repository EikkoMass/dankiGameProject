package ProgramaLeitura;

import java.util.Random;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		String nome, comando;
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		System.out.println("Olá, Por favor insira seu nome: ");
		nome = in.nextLine();
		
		System.out.println("Boas Vindas "+nome+", Deseja ir para qual direção? [W / A / S / D]");
		comando = in.nextLine();
		
		if(comando.equals("W")) {
			System.out.println("Você saiu voando e nunca mais voltou! D:");
		}else if(comando.equals("S")) {
			
			System.out.println("Você entrou no subsolo da terra e morreu pela magma!");
			
		}else if(comando.equals("A")) {
			System.out.println("Todo mundo sabe que se começa o jogo andando para a direita, Duhh");	
		}else {
			System.out.println("Você encontrou 1 inimigo, deseja: [A / Atacar | C / Correr]");
			comando = in.nextLine();
			if(comando.equals("A")) {
				if(rand.nextInt(100) < 75) {
					System.out.println("Você o matou, parabéns!");
				}else {
					System.out.println("Você perdeu");				
				}
				
			}else {
				System.out.println("Você correu e perdeu!");
			}
		}

	}

}
