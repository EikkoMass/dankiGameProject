package OrientacaoObjetos.Metodos;

public class ConstructorAtributos {

	//esses são atributos, assim como mótodos, as variáveis também podem ser definidas com public, private,
	//default e protected, funcionando da mesma forma que nos métodos
	private String textoSemStatic = "Testando";
	private static String textoComStatic = "Testando";
	protected int[] array; //não possui static, logo, precisa fazer um objeto da propria classe para usa-la
	
	
	public static final int INTEIRO = 5; //aqui estamos trabalhando com uma variável constante!
	//para definir uma constante, coloca-se o termo 'final' na hora de criá-la!
	//variáveis contantes vão ter valores fixos e não alteráveis (modelo recomendado para links ou coisas que
	// o objetivo é nunca alterar)
	//em variáveis constantes, é obrigatório determinar seu valor na hora da declaração e uma boa prática é 
	//nomear a variável em <!> UPPERCASE!
	
	
	public ConstructorAtributos() {
		
		
		
		
		// Esse é um método construtor, toda classe possui um método construtor,
		// que vai ser executado toda vez que criar um objeto ou invocar a classe em questão
		
		//em suma, você pode atribuir comandos para toda vez que criar o objeto ou invocar a classe
		
		System.out.println("Classe invocada [<!> Constructor Alert]");
	}
	
	public static void main(String[] args) {
		new ConstructorAtributos();
		//invocando classe
		ConstructorAtributos connect = new ConstructorAtributos();
		//criando objeto
		
		connect.textoSemStatic = "TESTA ISSO ENTAO";	
		textoComStatic = "VOU MESMO, ALGUM PROBLEMA?";
		
		System.out.println(connect.textoSemStatic);
		System.out.println(textoComStatic);
		
		connect.array = new int[textoComStatic.length()];
		System.out.println(connect.array.length);
		
		// length() <- para retornar o tamanho da string
		// length <- para descobrir o número de casas de um vetor
		
		System.out.println(INTEIRO);
		System.out.println(ConstructorAtributos.INTEIRO);
	}

}
