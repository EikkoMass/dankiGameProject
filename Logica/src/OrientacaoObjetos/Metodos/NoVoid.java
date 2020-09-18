package OrientacaoObjetos.Metodos;

public class NoVoid {
	
	//Void � um modo de metodo que ele n�o retorna nada, apenas executa uma s�rie de fun��es, logo,
	// a partir do momento que o m�todo n�o � void (String, int, double, float, char, boolean, etc),
	// obrigatoriamente ele tem que exibir um retorno final!

	public static String Texto() {
		return "Isso � um texto retornado de m�todo";
	}
	
	public static int Inteiro() {
		return 12;
	}
	
	public static double Real(double real) {
		double retornoReal = real * 5.54;
		return retornoReal;
	}
	
	public static boolean Booleano(String teste) {
		if(teste.equals("onomatopeia")) {
			return true;	
		}else {
			return false;
		}
	}
	
	//<!> tamb�m � poss�vel passar por par�metro Arrays
	
	public static int arrayRetornado(int[] teste) {
		
		
		return teste[0] * teste[2];
	}
	
	public static void main(String[]args) {
		int[] array = new int[5];
		//array
		
		System.out.println(Texto());
		System.out.println(Inteiro());
		System.out.println(Real(10));
		System.out.println(Booleano("onomatopeia"));
		
		for(int i=0; i<array.length;i++) {
			//inverte os valores, vetor[4] = 0 e vetor[0] = 4
			array[i] = array.length - i - 1;
			System.out.println(array[i]);
			}
		
		System.out.println(arrayRetornado(array));
	}
	
}
