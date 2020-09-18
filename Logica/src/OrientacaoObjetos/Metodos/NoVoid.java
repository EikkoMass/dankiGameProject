package OrientacaoObjetos.Metodos;

public class NoVoid {
	
	//Void é um modo de metodo que ele não retorna nada, apenas executa uma série de funções, logo,
	// a partir do momento que o método não é void (String, int, double, float, char, boolean, etc),
	// obrigatoriamente ele tem que exibir um retorno final!

	public static String Texto() {
		return "Isso é um texto retornado de método";
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
	
	//<!> também é possível passar por parâmetro Arrays
	
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
