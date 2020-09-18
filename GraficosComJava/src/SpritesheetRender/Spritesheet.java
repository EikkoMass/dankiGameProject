package SpritesheetRender;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private BufferedImage spritesheet;
	
	
	public Spritesheet(String path) {
		
		//try / catch gerado automaticamente
		try {
			
			/*ImageIO � uma classe nativa do java para manipula��o, renderiza��o, armazenamento e edi��o de
			 * imagens, tamb�m sendo o modo mais pr�tico para capturar imagens dentro de vari�veis
			 * (tamb�m podendo funcionar para arquivos no geral)*/
			
			spritesheet = ImageIO.read(getClass().getResource(path));
			//getClass() = Pega a classe em quest�o
			//getResource() = pega na pasta res os arquivos com o nome indicado no path
			
			/*Nesse caso estamos armazenando a imagem selecionada na vari�vel path dentro da vari�vel spritesheet*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
		//retorna a imagem que j� estava no spritesheet, mas recortada no par�metros desejados
		//pegar imagem a partir desse x, com esse y, tendo isso de largura e isso de altura
	}
	
}
