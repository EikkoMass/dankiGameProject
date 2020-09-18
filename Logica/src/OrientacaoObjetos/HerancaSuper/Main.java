package OrientacaoObjetos.HerancaSuper;

public class Main extends Player{
//Ao usar do extends, você está criando um vínculo de classes, no qual,
//Main está herdando todos os métodos de Player, logo, Main é a
//entidade filha e Player a Entidade Pai
	public static int min = 100, max = 200;
	public int meuMinimo= 50;
	
	public Main(int meuMinimo, int meuMaximo) {
		//como Player está sendo extendido para Main, e o método construtor de Player
		//está pedindo por atributos, é necessário usar no construtor de Main a função:
		
		//super(1, 100);
		super(meuMinimo, meuMaximo);
		
		//A função super(); tem como objetivo enviar os atributos desejados para Player (entidade pai)
		// <!> não pode tem mais de 1 super por vez
		
		//graças ao Super, também é possivel definir quando que o constructor de Player vai ocorrer (dentro
		// das programação do constructor de Main), em casos sem super, ele ocorreria a partir do momento 
		//que a classe identificasse a extensão.
		//(Sempre claro, aplicando as condições do constructor quando iniciar a classe Main ou um objeto
		// com Main)
		
		//<!>
		//--------------------------------------------------------------------------------------------------------
		//<!> ATENÇÃO! Nesse caso temos duas variáveis com mesmo nome, a variável global meuMinimo e a variável
		//de atributo meuMinimo, em casos como este a forma de diferenciar ambas é através do 'this', this é uma
		//referência com base na classe, logo, se eu usar this antes do nome da variável, ele usará a global, senão
		//usará a dos atributos!
		
		this.meuMinimo = meuMinimo;
		System.out.println(this.meuMinimo);
		//nesse caso não precisa ser uma variável estática por estarmos já referenciando com this, que condiz a própria
		//classe!
		
		this.meuMinimo = this.herdado; 		//<!>
		System.out.println(this.meuMinimo);
		//a variável 'herdado' está sendo herdada da classe Player, que a partir do momento que ocorreu a herança
		//de classes, começou a fazer parte também de Main, logo, 'this' funciona para chamá-la
		
	}
	
	public static void main(String[]args) {
		new Main(min, max);
		new Main(min, max).teste(min);
		//ao invocar a classe, além de executar o construtor da mesma, executa o construtor da classe extendida
		//no Main
	}
	
}
