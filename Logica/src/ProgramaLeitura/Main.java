package ProgramaLeitura;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String valor;
		Scanner in = new Scanner(System.in);
		System.out.println("Insira um nome: ");
		valor = in.nextLine();
		System.out.println("O nome inserido foi: "+valor);

	}

}
