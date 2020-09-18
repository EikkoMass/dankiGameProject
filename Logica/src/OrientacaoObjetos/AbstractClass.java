package OrientacaoObjetos;

abstract class AbstractClass {

//Uma classe abstrata não pode ser instanciada em variáveis, ela funciona apenas a partir
//do 'extends' com outra classe, fazendo assim, a outra classe herdar todas as funções da
//classe abstract
	
public static void FuncAbs() {
	
	System.out.println("Função abstrata no main, porém herdada no extends com AbstractClass");
	
}
	
	
}
