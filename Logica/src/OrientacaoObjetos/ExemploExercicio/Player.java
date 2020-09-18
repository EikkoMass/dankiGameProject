package OrientacaoObjetos.ExemploExercicio;

public class Player {

	private int life = 100; // vida player
								//obrigatoriamente algum objeto do tipo Inimigo vai entrar aqui
	public void atacarInimigo(Inimigo inimigo) {
		inimigo.life--; // diminuí a vida do Inimigo inserido por atributo
	}
	
}
