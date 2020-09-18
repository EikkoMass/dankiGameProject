package LogicaProgramacao;

public class Variaveis {

	public static void main(String[] args) {

		//Variáveis Especiais ----------------------------------------------------------
		
		final long cpf = 1287653090; //"final" é a definição para uma variável (ou não variável) constante
		//ou seja, seu valor (ou função) inicial será fixo(a) e não poderá ser alterado
		
		
		//vetores podem ser de QUALQUER formato de variável
		int[] vetor = new int[4]; // vetor seria uma variável que armazena diversos valores
		String[][] nomes = new String[2][2]; // vetor bidimensional, também chamado de matriz
		//melhor referência para o mesmo seriam listas e colunas de uma planilha
		
		//<!> O primeiro campo da matriz é o que determina seu .length
		//exemplo: nomes[2][2] => nomes.length = 2 | nomes[4][2] => nomes.length = 4
		
		//<!> para pegar todos os valores de uma parte de uma matriz faria nomes[i].length
		//exemplo: nomes[0][2] => nomes[0].length = 2 | nomes[0][7] => nomes[0].length = 7
		
		String[][][][] nomesMultiDimensionais = new String[2][2][1][7]; //vetores multidimensionais possuem
		//quantas casas você desejar!
		
		
		vetor[0] = 1;
		vetor[1] = 2;
		vetor[2] = 3;
		vetor[3] = 4;
		nomes[0][0] = "Rodrigo";
		nomes[0][1] = "Paulo";
		nomes[1][0] = "Guilherme";
		nomes[1][1] = "Marcos";
		
		//Variáveis Convencionais ------------------------------------------------------
		
		String texto; //Apenas recebe textos
		double numeroQuebradoGrande; //apenas recebes números quebrados (ou inteiros) grandes
		float  numeroQuebradoPequeno; //apenas recebe numeros quebrados (ou inteiros) pequenos
		int	   numeroInteiro; //apenas recebe números inteiros
		long   numeroInteiroLongo; //apenas recebe numeros inteiros BEM longos
		boolean verdadeiroOuFalso; //apenas recebe true ou false
		char 	letra; // apenas recebe um caractere (ex: 'a', 'l', '3', etc.)

		texto = "Meu Texto";
		numeroQuebradoGrande = 7924837294728324232434242343.28;
		numeroQuebradoPequeno = (float) 3.123234;
		numeroInteiro = 23423;
		numeroInteiroLongo = 783242342;
		verdadeiroOuFalso = true;
		letra = 'a';
		
		System.out.println("String: "+texto+" \n"
				+"double: "+numeroQuebradoGrande+" \n"
				+"float: "+numeroQuebradoPequeno+" \n"
				+"int: "+numeroInteiro+" \n"
				+"long: "+numeroInteiroLongo+" \n"
				+"boolean: "+verdadeiroOuFalso+" \n"
				+"char: "+letra+" \n\n"
				+"Valores de variáveis variam!"+ "\n");
		
		texto = "Meu Texto2";
		numeroQuebradoGrande = 12313143522343.28;
		numeroQuebradoPequeno = (float) 7.1232434;
		numeroInteiro = 777423;
		numeroInteiroLongo = 999999999;
		verdadeiroOuFalso = false;
		letra = '4';
		
		System.out.println("String: "+texto+" \n"
				+"double: "+numeroQuebradoGrande+" \n"
				+"float: "+numeroQuebradoPequeno+" \n"
				+"int: "+numeroInteiro+" \n"
				+"long: "+numeroInteiroLongo+" \n"
				+"boolean: "+verdadeiroOuFalso+" \n"
				+"char: "+letra+" \n\n");
		
		System.out.println("valor constante: " + cpf+"\n\n");
		
		for(int i=0; i<vetor.length;i++) {
			System.out.println("Vetor ["+i+"]: " + vetor[i]);
		}
		
		System.out.println("\n");
		
		for(int i=0; i<nomes.length;i++) {
			for(int j=0; j<nomes[i].length;j++) {
			System.out.println("Nome["+i+"]["+j+"]: "+nomes[i][j]);
			}			
		}
		
	}

}
