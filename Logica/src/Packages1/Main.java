package Packages1; //O package é como se fosse uma pasta com todos os arquivos desejados, afim de organizá-los
//package: conjunto de classes

import Packages2.*; //importado o Packages2 para usufruir da classe Player nela alocada
//ao importar, tudo que está dentro do importado fica disponível para uso nessa class

public class Main {

	public static void main(String[] args) {
		new Player(); //indica erro (sem o import) pois não existe nesse package uma classe com esse nome!

	}

}
