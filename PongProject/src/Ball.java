import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {	//O mais recomendado era criar uma classe entities, e extender em Enemy, Player e Ball, j� que os 3
					//possuem as mesma caracter�sticas

	public double x, y;
	public int width, height;
	
	public double dx, dy; //dire��o x e y da bola (para movimentos aleat�rios)
	public double speed = 1.2; //velocidade que a bola ir� se mover na tela
	
	public Ball(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.width = 6;
		this.height = 6;
		
		//dx = new Random().nextGaussian(); //Random determina um n�mero aleat�rio desejado
									//j� o nextGaussian limita a randomiza��o para 0 e 1's em double
		//dy = new Random().nextGaussian();
		
		int angle = new Random().nextInt(120 - 45)+ 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));


	}
	
	public void tick() {
		
		//antes de incrementar para o pr�ximo valor de x, ele prev� se tal valor n�o excede a caixa, se n�o exceder,
		//continua incrementando, caso contr�rio ele aplica dx*=-1
		
		if(x+(dx*speed) + width >= Game.WIDTH) { //se minha posi��o + meu tamanho exceder a tela do jogo
			dx*=-1; //ir� manter a movimenta��o mas dentro da tela, dando um efeito de colis�o
		}else if(x+(dx*speed) < 0) { //se meu x exceder o limite de 0
			dx*=-1;
		}
		
		if(y>=Game.HEIGHT) { //ultrapassou o limite da altura, logo, ponto do inimigo
			
			Game.enemy.score++;
			System.out.println("Ponto do Inimigo");
			
			Game.player = new Player(100, Game.HEIGHT - 5, Game.player.score); //crio um novo player na vari�vel antigo mas com o score atualizado
			Game.enemy = new Enemy(100, 0, Game.enemy.score); //crio um novo inimigo mas com o score atualizado
			Game.ball = new Ball((Game.WIDTH / 2) - 2, (Game.HEIGHT / 2) - 2);
			return;
			
		}else if(y < 0) { //ponto do jogador
			
			Game.player.score++;
			System.out.println("Ponto do Jogador");
			Game.player = new Player(100, Game.HEIGHT - 5, Game.player.score); //crio um novo player na vari�vel antigo mas com o score atualizado
			Game.enemy = new Enemy(100, 0, Game.enemy.score); //crio um novo inimigo mas com o score atualizado
			Game.ball = new Ball((Game.WIDTH / 2) - 2, (Game.HEIGHT / 2) - 2);
			return;
		}
		
		Rectangle bounds = new Rectangle((int) (x+(dx*speed)), (int) (y+(dy*speed)), width, height);
		
		//Criando o quadrado de colis�o da bola, ele � invis�vel no jogo, e seu trabalho ser� detectar se
		//algo conflitou com ele
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width, Game.enemy.height);
												//x e y do enemy s�o double, ent�o precisa fazer a convers�o para int
		
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
		
		//pelo fato de dx e dy serem feitos com base em seno e cosseno, os valores ser�o parecidos com 0.42907,
		//-1.344343, 0.0434343, etc, que ser�o multiplicados por speed que � 1.3 e somados com x que seria a posi�ao
		//atual da bola, em suma, n�o muda muito de um sistema do tipo x+=2, apenas sendo usado esse metodo de seno e
		//cosseno para gerar n�meros aleat�rios.
				
	}
	public void render(Graphics g) {

		g.setColor(Color.white); //define a cor da bola
		g.fillRect((int) x, (int) y,  width,    height); //desenha a bola dizendo sua localiza��o
				// x	y		   height width

	}
	
	
}
