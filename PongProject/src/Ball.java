import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {	//O mais recomendado era criar uma classe entities, e extender em Enemy, Player e Ball, já que os 3
					//possuem as mesma características

	public double x, y;
	public int width, height;
	
	public double dx, dy; //direção x e y da bola (para movimentos aleatórios)
	public double speed = 1.2; //velocidade que a bola irá se mover na tela
	
	public Ball(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.width = 6;
		this.height = 6;
		
		//dx = new Random().nextGaussian(); //Random determina um número aleatório desejado
									//já o nextGaussian limita a randomização para 0 e 1's em double
		//dy = new Random().nextGaussian();
		
		int angle = new Random().nextInt(120 - 45)+ 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));


	}
	
	public void tick() {
		
		//antes de incrementar para o próximo valor de x, ele prevê se tal valor não excede a caixa, se não exceder,
		//continua incrementando, caso contrário ele aplica dx*=-1
		
		if(x+(dx*speed) + width >= Game.WIDTH) { //se minha posição + meu tamanho exceder a tela do jogo
			dx*=-1; //irá manter a movimentação mas dentro da tela, dando um efeito de colisão
		}else if(x+(dx*speed) < 0) { //se meu x exceder o limite de 0
			dx*=-1;
		}
		
		if(y>=Game.HEIGHT) { //ultrapassou o limite da altura, logo, ponto do inimigo
			
			Game.enemy.score++;
			System.out.println("Ponto do Inimigo");
			
			Game.player = new Player(100, Game.HEIGHT - 5, Game.player.score); //crio um novo player na variável antigo mas com o score atualizado
			Game.enemy = new Enemy(100, 0, Game.enemy.score); //crio um novo inimigo mas com o score atualizado
			Game.ball = new Ball((Game.WIDTH / 2) - 2, (Game.HEIGHT / 2) - 2);
			return;
			
		}else if(y < 0) { //ponto do jogador
			
			Game.player.score++;
			System.out.println("Ponto do Jogador");
			Game.player = new Player(100, Game.HEIGHT - 5, Game.player.score); //crio um novo player na variável antigo mas com o score atualizado
			Game.enemy = new Enemy(100, 0, Game.enemy.score); //crio um novo inimigo mas com o score atualizado
			Game.ball = new Ball((Game.WIDTH / 2) - 2, (Game.HEIGHT / 2) - 2);
			return;
		}
		
		Rectangle bounds = new Rectangle((int) (x+(dx*speed)), (int) (y+(dy*speed)), width, height);
		
		//Criando o quadrado de colisão da bola, ele é invisível no jogo, e seu trabalho será detectar se
		//algo conflitou com ele
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width, Game.enemy.height);
												//x e y do enemy são double, então precisa fazer a conversão para int
		
		if(bounds.intersects(boundsPlayer)) { //se colidir com player
			int angle = new Random().nextInt(120 - 45)+ 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0) {
				dy*=-1;}
		}else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45)+ 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0) {
				dy*=-1;}
			
		}
		
		x+=dx*speed; //determina o x da bola com base na velocidade * dx + x
		y+=dy*speed; //determina o y da bola com base na velocidade * dy + y
		System.out.println("x: "+x);
		//System.out.println("y: "+y);
		
		//pelo fato de dx e dy serem feitos com base em seno e cosseno, os valores serão parecidos com 0.42907,
		//-1.344343, 0.0434343, etc, que serão multiplicados por speed que é 1.3 e somados com x que seria a posiçao
		//atual da bola, em suma, não muda muito de um sistema do tipo x+=2, apenas sendo usado esse metodo de seno e
		//cosseno para gerar números aleatórios.
				
	}
	public void render(Graphics g) {

		g.setColor(Color.white); //define a cor da bola
		g.fillRect((int) x, (int) y,  width,    height); //desenha a bola dizendo sua localização
				// x	y		   height width

	}
	
	
}
