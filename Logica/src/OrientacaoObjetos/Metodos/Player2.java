package OrientacaoObjetos.Metodos;

public class Player2 extends Player{

	protected void metodoPegandoProtected() {
		protegidoMasUtil();
		// por ser um m�todo n�o est�tico e pegando um outro metodo protected e extendido, funciona
		this.protegidoMasUtil();
		// muito �til para rever quais m�todos voc� tem � disposi��o, que possam ter sido extendidos ou da propria
		// classe
		this.nascer();
	}

}
