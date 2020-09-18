package OrientacaoObjetos;

public interface MinhaInterface {

	//Uma 'Interface' serve para indicar quais funções são obrigatórias para a classe implementada
	//Ou seja, se a class não possuir as funções requeridas pela interface, indicará erro
	
	public static void main(String[]args) {

	}
	// Em suma, interface serve para indicar que elementos serão obrigatórios dentro
	// da classe implementada!
	
	//<!> Podendo valer até para comentários
	//É sério, eu coloquei o comentário acima dentro do main e ele cobrou isso na classe!
	
	public abstract void iniciar();
	public abstract void eliminar();
	public abstract void sobrescrever();
	
	//<!> Se nomeia como abstrata por não conter nenhuma tarefa na Interface

	
}
