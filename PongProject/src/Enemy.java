import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	public double x, y;
	public int width, height;
	public int score;
	
	public Enemy(int x, int y, int score) {
		
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
		this.score = score;
	}
	

	public void tick() {
		
		x+= (Game.ball.x - x - 20) * 0.07; //pega o x da bola e diminui com o x atual depois somando com o x acumulado
		
		//x+= (Game.ball.x - x);
		//ex: x do inimigo está 10
		//x da bola está 50
		// x = 10 + (50 - 10);
		//(nesse caso ainda seria igual a um x = Game.ball.x)
		
		
		
		// * 0.4 para deixar possível que ele erre, deixando o comando dele mais lento
		
		if(x < 0) {
			x = 0;
		}else if(x + width >= Game.WIDTH) {
			x = Game.WIDTH - width;
		}
		
	}
	public void render(Graphics g) {
		g.setColor(Color.RED); //define a cor do inimigo
		g.fillRect((int) x, (int) y,  width,    height); //desenha o inimigo dizendo sua localização
				// x	y		   height width
	}
	
	
}
