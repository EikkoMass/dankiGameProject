package SpritesheetRender;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private BufferedImage spritesheet;
	
	
	public Spritesheet(String path) {
		
		//try / catch gerado automaticamente
		try {
			
			/*ImageIO é uma classe nativa do java para manipulação, renderização, armazenamento e edição de
			 * imagens, também sendo o modo mais prático para capturar imagens dentro de variáveis
			 * (também podendo funcionar para arquivos no geral)*/
			
			spritesheet = ImageIO.read(getClass().getResource(path));
			//getClass() = Pega a classe em questão
			//getResource() = pega na pasta res os arquivos com o nome indicado no path
			
			/*Nesse caso estamos armazenando a imagem selecionada na variável path dentro da variável spritesheet*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
		//retorna a imagem que já estava no spritesheet, mas recortada no parâmetros desejados
		//pegar imagem a partir desse x, com esse y, tendo isso de largura e isso de altura
	}
	
}
