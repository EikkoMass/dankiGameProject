package com.me.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.me.main.Game;
import com.me.main.Sound;
import com.me.world.AStar;
import com.me.world.Camera;
import com.me.world.Vector2i;


public class Enemy extends Entity{

	private double speed = 0.8;
	private int maskx = 1, masky = 1, mwidth = 12, mheight = 14; //manipulaï¿½ï¿½o de mï¿½scara de colisï¿½o do inimigo
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3; //variï¿½veis de animaï¿½ï¿½o
	
	/*Nota: se na variï¿½vel maxFrames vocï¿½ colocar = a 60, equivaleria e 1 segundo passado atï¿½ a proxima animaï¿½ï¿½o
	 * 
	 * 
	 * */
	public static int leftDir = 0, rightDir = 1, upDir = 2, downDir = 3; //direï¿½ï¿½es do personagem com respectivo valor para cada (variï¿½veis de animaï¿½ï¿½o)
	public static int dir = downDir; //controla para onde o personagem estï¿½ direcionado
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private BufferedImage[] upEnemy;
	private BufferedImage[] downEnemy;
	
	private boolean isDamaged = false;
	private int damageFrames = 15, damageCurrent = 0;
	private int life = 10;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		leftEnemy = new BufferedImage[4];
		rightEnemy = new BufferedImage[4];
		upEnemy = new BufferedImage[4];
		downEnemy = new BufferedImage[4];
		
		for(int i = 0; i < rightEnemy.length; i++) {
			
			rightEnemy[i] = Game.spritesheet.getSprite(32 + (16 * i) , 64, 16, 16);
			leftEnemy[i]  = Game.spritesheet.getSprite(32 + (16 * i) , 80, 16, 16);
			upEnemy[i] = Game.spritesheet.getSprite(16 , 64 + (16 * i), 16, 16);
			downEnemy[i] = Game.spritesheet.getSprite(0 , 64 + (16 * i), 16, 16);
		}
		
	}
	

	public void tick() {
		
		depth = 0;
		//if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 90) { //se o player estiver na area de visão do inimigo
			
		
		
		/*if(isColiddingWithPlayer() == false) {// se nï¿½o estiver colidindo com o player!!!!
			if(Game.rand.nextInt(100) > 30) { //faz uma anï¿½lise de probabilidade (o personagem tem 70% de chance de andar)
				/*if((int) x < Game.player.getX() && World.isFree((int) (x+speed), this.getY(), z) 
						&& !isColidding((int) (x+speed), this.getY())) {
					x+=speed;
					dir = rightDir;
				}else if((int) x > Game.player.getX() && World.isFree((int) (x-speed), this.getY(), z) 
						&& !isColidding((int) (x-speed), this.getY())) {
					x-=speed;
					dir = leftDir;
				}
			
				if((int) y < Game.player.getY() && World.isFree(this.getX(), (int) (y+speed), z) 
						&& !isColidding(this.getX(), (int) (y+speed))) {
					y+=speed;
					dir = downDir;
				}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int) (y-speed), z) 
						&& !isColidding(this.getX(), (int) (y-speed))) {
					y-=speed;
					dir = upDir;
				}
			
			
			}
		}else { //se estiver colidindo com o player
			if(Game.rand.nextInt(100) < 10) { //randomizando quando o dano serï¿½ recebido
				Game.player.life--;
				Game.player.isDamaged = true;
				//System.out.println("Vida: "+Game.player.life); //exibe a vida no console
			}
			
		}
		
		}else {
			index = 0;
		}*/
		
		if(!this.isColiddingWithPlayer()) {
			
		
		
		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			
			path = AStar.findPath(Game.world, start, end);
		}
		}else {
			
			if(Game.rand.nextInt(100) < 10) { //randomizando quando o dano serï¿½ recebido
				Game.player.life--;
				Game.player.isDamaged = true;
				//System.out.println("Vida: "+Game.player.life); //exibe a vida no console
			}
			
		}
		if(new Random().nextInt(100) < 60)
			followPath(path);
		
		if(new Random().nextInt(100) < 5) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			path = AStar.findPath(Game.world, start, end);
		}
		

		frames++; //incrementa frames (controlador de velocidade da animaï¿½ï¿½o)
		if(frames == maxFrames) { //se frames for igual a seu limite de frames (no caso, 5)
			index++; //incrementa frames, assim trocando o sprite
			frames = 0; //zera frames
		}
		
		if(index > maxIndex) { //se index for maior que seu limite de sprites
			index = 0; //zera index
		}
		
		collidingBullet();
		
		if(life <= 0) {
			Sound.deathEnemy.play();
			destroySelf();
		}
		
		if(isDamaged) {
			
			damageCurrent++;
			if(damageCurrent == damageFrames) {
				damageCurrent = 0;
				isDamaged = false;
			}
			
		}
		
	
		
		
	}
	
	public void destroySelf() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
	}
	
	public void collidingBullet() {
		
		for(int i = 0; i < Game.bullets.size(); i++) {
			
			Entity e = Game.bullets.get(i);
			
			if(Entity.isColidding(this, e)) {
				
				isDamaged = true;
				life-=2;
				Game.bullets.remove(i);
				
				return;
			}
			
		}
		
	}
	
	
	
	public boolean isColiddingWithPlayer() { //teste de colisï¿½o com o player
		
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx, this.getY() + masky, mwidth, mheight), //retï¿½ngulo de colisï¿½o do inimigo
				  player = new Rectangle(Game.player.getX(), Game.player.getY(), 13, 16); //retï¿½ngulo de colisï¿½o do player
		
		
		return enemyCurrent.intersects(player); //se colidir com player, retorna true;
		
	}
	
	public void render(Graphics g) {
		//super.render(g);
		if(!isDamaged) {
		if(dir == rightDir) {
			g.drawImage(rightEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null); //os sprites mudam de acordo com o valor de index, que
			//ï¿½ alterado com o decorrer da movimentaï¿½ï¿½o do personagem, usando as variï¿½veis moved, index e frames
		}else if (dir == leftDir) {
			g.drawImage(leftEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if(dir==upDir) {
			g.drawImage(upEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if(dir == downDir) {
			g.drawImage(downEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		}else {
			g.drawImage(Entity.ENEMY_EN_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		g.setColor(new Color(150, 50, 200));
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.drawString("+"+life, (this.getX() + 2) - Camera.x, (this.getY() - 6) - Camera.y);
		//g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, mheight); //teste para ver se a mï¿½scara estï¿½ funcionando
	}
}
