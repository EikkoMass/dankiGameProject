package ArrayList;

import java.util.ArrayList;

public class Game {

	/*ArrayLists s�o array no qual n�o temos um n�mero limite de casas no vetor para definir, logo,
	� criado um arrayList, em que voc� vai adicionando quandos dados voc� desejar.
	
	ArrayList podendo funcionar em qualquer elemento primitivo da linguagem (int, double, String, etc)
	e at� com objetos (como no caso apresentado abaixo)*/
	
	private ArrayList<Entidade> entidades = new ArrayList<>();
						//^tipo de classe que vai guardar nosso ArrayList
	
	public Game() {
		
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade()); //4 objetos adicionados
		
		for(int i = 0; i < entidades.size(); i++) { //Size() analisa o tamanho de um vetor ArrayList em java
			System.out.println(entidades.size());
			entidades.remove(i);	
			
			//<!> ArrayList possuem m�todos exclusivos dele pr�prio, para adicionar, remover, alterar, etc !
		}
		
		Entidade e = entidades.get(1); //Pega o valor (objeto) desejado da casa requerida no ArrayList
		System.out.println(e.vida);
		e.vida = 50;
		System.out.println(e);
		System.out.println(e.vida);
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();

	}

}
