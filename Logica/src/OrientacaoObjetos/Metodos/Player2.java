package OrientacaoObjetos.Metodos;

public class Player2 extends Player{

	protected void metodoPegandoProtected() {
		protegidoMasUtil();
		// por ser um método não estático e pegando um outro metodo protected e extendido, funciona
		this.protegidoMasUtil();
		// muito útil para rever quais métodos você tem à disposição, que possam ter sido extendidos ou da propria
		// classe
		this.nascer();
	}

}
