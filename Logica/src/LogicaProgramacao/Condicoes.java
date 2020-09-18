package LogicaProgramacao;

import javax.swing.JOptionPane;

public class Condicoes {

	public static void main(String[] args) {
		
		//Formas de manipular o sistema
		
		// if -------------------------------------------------------------------------
		// usado para casos de comparação ou de condição
		// (se isso for > ou = a isso)
		// (se isso for diferente disso)
		
		int num1 = 5;
		
		if(num1>6) { // se isso for assim [....]
			System.out.println(num1 + " é maior que 6");
		}else if (num1 == 6) { //senão, se for assim [...]
			System.out.println(num1 + " é igual a 6");
		}else { // se não for nenhuma das formas anteriores [...]
			System.out.println(num1 + " é menor que 6");
		}
	
		// Switch case-----------------------------------------------------------------
		// (usado para comparativos exatos)
		// (caso o valor seja esse)
		
		char caractere = '3';
		
		switch(caractere) {
		
		case '1':
			System.out.println(caractere + " seria 1");
			break;
		case '2':
			System.out.println(caractere + " seria 2");
			break;
		case '3':
		case '4':
			System.out.println(caractere + " seria 3 ou 4");
			break;

		default:
			System.out.println(caractere + " seria nenhum dos registrados");
			break;
		}
		
		// While -----------------------------------------------------------------
		// (condição de repetição constante)
		// (enquanto aquilo for maior ou igual a aquele outro, repita isso)
		
		int numRep = 1;
		
		while(numRep < 5) {
		numRep = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor do número?"));	
		}
		
		// Do While -----------------------------------------------------------------
		// (condição de repetição abstrata (mostrando o que quer antes de aplicar comparativo))
		// repita isso enquanto aquilo for igual a aquele
		
		int numRepDo = 5;
		
		do {
		System.out.println(numRepDo);
		numRepDo = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor repetido?"));
		//repita "ate"
		// repita enquanto isso for diferente de 5
		} while(numRepDo != 5);
		
		
		// for ---------------------------------------------------------------------------
		//Vulgo melhor amigo dos vetores
		//sistema de repetição controlado
		// cria uma variável de repetição definida, e seu valor vai definir quantas repetições acontecerão
		
		//	      Igual a | adiciona 1 a cada repetição
		for(int i=0; i<9; i++) {
				   //  Menor que
			System.out.println("Isso se repetiu "+i+" vezes!");
		}
		
			}
}
