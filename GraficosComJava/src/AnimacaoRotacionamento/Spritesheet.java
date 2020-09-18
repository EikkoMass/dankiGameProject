package AnimacaoRotacionamento;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	//<!> ATENÇÃO esse código foi alterado com base no arquivo em GraficosComJava (Java Project) > SpritesheetRender (package) > Spritesheet (class) 
	//Se deseja ver os comentários desse código, vá para aquela class
	
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
