package ProgramaLeitura;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		String nome, cidade, estado, idade;
		Scanner in = new Scanner(System.in);
		System.out.println("Insira seu nome: ");
		nome = in.nextLine();
		
		System.out.println("Insira sua idade: ");
		idade = in.nextLine();
		
		System.out.println("Insira seu estado: ");
		estado = in.nextLine();
		
		System.out.println("Insira sua cidade: ");
		cidade = in.nextLine();
		
		if(cidade.equals("Joinville")) 
			System.out.println("Niice");
		
		System.out.println("_____________________");
		
		System.out.println("Dados: \n"+
		"Nome: "+nome+"\n"+
		"Idade: "+idade+"\n"+
		"Estado: "+estado+"\n"+
		"Cidade: "+cidade+"\n");
	}

}
