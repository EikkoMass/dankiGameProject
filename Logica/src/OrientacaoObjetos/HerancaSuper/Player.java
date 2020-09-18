package OrientacaoObjetos.HerancaSuper;

public class Player {
	
	public int herdado = 500;
	
	public Player(int minimo, int maximo) {
		//método construtor da classe
		System.out.println(minimo+" e "+maximo);		
		
	}
	
	public void teste(int valor) {
		System.out.println("Estou extendendo de player com valor "+valor);		
	}

}
