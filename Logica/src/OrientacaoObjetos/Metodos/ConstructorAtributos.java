package OrientacaoObjetos.Metodos;

public class ConstructorAtributos {

	//esses s�o atributos, assim como m�todos, as vari�veis tamb�m podem ser definidas com public, private,
	//default e protected, funcionando da mesma forma que nos m�todos
	private String textoSemStatic = "Testando";
	private static String textoComStatic = "Testando";
	protected int[] array; //n�o possui static, logo, precisa fazer um objeto da propria classe para usa-la
	
	
	public static final int INTEIRO = 5; //aqui estamos trabalhando com uma vari�vel constante!
	//para definir uma constante, coloca-se o termo 'final' na hora de cri�-la!
	//vari�veis contantes v�o ter valores fixos e n�o alter�veis (modelo recomendado para links ou coisas que
	// o objetivo � nunca alterar)
	//em vari�veis constantes, � obrigat�rio determinar seu valor na hora da declara��o e uma boa pr�tica � 
	//nomear a vari�vel em <!> UPPERCASE!
	
	
	public ConstructorAtributos() {
		
		
		
		
		// Esse � um m�todo construtor, toda classe possui um m�todo construtor,
		// que vai ser executado toda vez que criar um objeto ou invocar a classe em quest�o
		
		//em suma, voc� pode atribuir comandos para toda vez que criar o objeto ou invocar a classe
		
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
		// length <- para descobrir o n�mero de casas de um vetor
		
		System.out.println(INTEIRO);
		System.out.println(ConstructorAtributos.INTEIRO);
	}

}
