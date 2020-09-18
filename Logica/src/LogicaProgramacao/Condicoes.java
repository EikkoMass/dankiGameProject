package LogicaProgramacao;

import javax.swing.JOptionPane;

public class Condicoes {

	public static void main(String[] args) {
		
		//Formas de manipular o sistema
		
		// if -------------------------------------------------------------------------
		// usado para casos de compara��o ou de condi��o
		// (se isso for > ou = a isso)
		// (se isso for diferente disso)
		
		int num1 = 5;
		
		if(num1>6) { // se isso for assim [....]
			System.out.println(num1 + " � maior que 6");
		}else if (num1 == 6) { //sen�o, se for assim [...]
			System.out.println(num1 + " � igual a 6");
		}else { // se n�o for nenhuma das formas anteriores [...]
			System.out.println(num1 + " � menor que 6");
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
		// (condi��o de repeti��o constante)
		// (enquanto aquilo for maior ou igual a aquele outro, repita isso)
		
		int numRep = 1;
		
		while(numRep < 5) {
		numRep = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor do n�mero?"));	
		}
		
		// Do While -----------------------------------------------------------------
		// (condi��o de repeti��o abstrata (mostrando o que quer antes de aplicar comparativo))
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
		//sistema de repeti��o controlado
		// cria uma vari�vel de repeti��o definida, e seu valor vai definir quantas repeti��es acontecer�o
		
		//	      Igual a | adiciona 1 a cada repeti��o
		for(int i=0; i<9; i++) {
				   //  Menor que
			System.out.println("Isso se repetiu "+i+" vezes!");
		}
		
			}
}
