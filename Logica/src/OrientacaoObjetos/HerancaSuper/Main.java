package OrientacaoObjetos.HerancaSuper;

public class Main extends Player{
//Ao usar do extends, voc� est� criando um v�nculo de classes, no qual,
//Main est� herdando todos os m�todos de Player, logo, Main � a
//entidade filha e Player a Entidade Pai
	public static int min = 100, max = 200;
	public int meuMinimo= 50;
	
	public Main(int meuMinimo, int meuMaximo) {
		//como Player est� sendo extendido para Main, e o m�todo construtor de Player
		//est� pedindo por atributos, � necess�rio usar no construtor de Main a fun��o:
		
		//super(1, 100);
		super(meuMinimo, meuMaximo);
		
		//A fun��o super(); tem como objetivo enviar os atributos desejados para Player (entidade pai)
		// <!> n�o pode tem mais de 1 super por vez
		
		//gra�as ao Super, tamb�m � possivel definir quando que o constructor de Player vai ocorrer (dentro
		// das programa��o do constructor de Main), em casos sem super, ele ocorreria a partir do momento 
		//que a classe identificasse a extens�o.
		//(Sempre claro, aplicando as condi��es do constructor quando iniciar a classe Main ou um objeto
		// com Main)
		
		//<!>
		//--------------------------------------------------------------------------------------------------------
		//<!> ATEN��O! Nesse caso temos duas vari�veis com mesmo nome, a vari�vel global meuMinimo e a vari�vel
		//de atributo meuMinimo, em casos como este a forma de diferenciar ambas � atrav�s do 'this', this � uma
		//refer�ncia com base na classe, logo, se eu usar this antes do nome da vari�vel, ele usar� a global, sen�o
		//usar� a dos atributos!
		
		this.meuMinimo = meuMinimo;
		System.out.println(this.meuMinimo);
		//nesse caso n�o precisa ser uma vari�vel est�tica por estarmos j� referenciando com this, que condiz a pr�pria
		//classe!
		
		this.meuMinimo = this.herdado; 		//<!>
		System.out.println(this.meuMinimo);
		//a vari�vel 'herdado' est� sendo herdada da classe Player, que a partir do momento que ocorreu a heran�a
		//de classes, come�ou a fazer parte tamb�m de Main, logo, 'this' funciona para cham�-la
		
	}
	
	public static void main(String[]args) {
		new Main(min, max);
		new Main(min, max).teste(min);
		//ao invocar a classe, al�m de executar o construtor da mesma, executa o construtor da classe extendida
		//no Main
	}
	
}
