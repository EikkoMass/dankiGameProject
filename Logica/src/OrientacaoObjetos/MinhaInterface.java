package OrientacaoObjetos;

public interface MinhaInterface {

	//Uma 'Interface' serve para indicar quais fun��es s�o obrigat�rias para a classe implementada
	//Ou seja, se a class n�o possuir as fun��es requeridas pela interface, indicar� erro
	
	public static void main(String[]args) {

	}
	// Em suma, interface serve para indicar que elementos ser�o obrigat�rios dentro
	// da classe implementada!
	
	//<!> Podendo valer at� para coment�rios
	//� s�rio, eu coloquei o coment�rio acima dentro do main e ele cobrou isso na classe!
	
	public abstract void iniciar();
	public abstract void eliminar();
	public abstract void sobrescrever();
	
	//<!> Se nomeia como abstrata por n�o conter nenhuma tarefa na Interface

	
}
