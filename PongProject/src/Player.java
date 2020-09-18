import java.awt.Color;
import java.awt.Graphics;


public class Player {

	public int x, y;
	public boolean right, left;
	public int height, width;
	public int[] colors = new int[3];
	public int score;

	public Player(int x, int y, int score) {
		this.x = x;	//x do player
		this.y = y; //y do player
		this.height = 5; //altura do player
		this.width = 40; //largura do player
		this.score = score;
		colors[0] = 50;
		colors[1] = 0;
		colors[2] = 70;
	}
	
	public void tick() {
		
		for(int i = 0; i<3; i++) {
			if(colors[i]>255) {
				colors[i]=50;
			}
		}
		
		if(right) { //se right for igual a true, x do player é incrementado
			x++;
		}else if(left) { //se left for igual a true, x do player é decrementado
			x--;
		}
		
		if(x+width > Game.WIDTH) { //se x for maior que a largura da tela do game, x é igual a largura do
									//jogo menos a largura do player
			x = Game.WIDTH - this.width;
		}else if(x < 0){ //Se x do player for menor que 0, significa que não está na tela, logo, seu 0 vira 0
			x = 0;
		}
	}
	
	public void render(Graphics g) {
		
		
		
		g.setColor(new Color(colors[0], colors[1], colors[2])); //define a cor do player
		g.fillRect(x, y,  width,    height); //desenha o player dizendo sua localização
				// x	y		   height width
		
		
		
	}

}
