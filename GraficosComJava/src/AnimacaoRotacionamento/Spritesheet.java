package AnimacaoRotacionamento;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	//<!> ATEN��O esse c�digo foi alterado com base no arquivo em GraficosComJava (Java Project) > SpritesheetRender (package) > Spritesheet (class) 
	//Se deseja ver os coment�rios desse c�digo, v� para aquela class
	
	private BufferedImage spritesheet;
	
	
	public Spritesheet(String path) {
		
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
	
}
