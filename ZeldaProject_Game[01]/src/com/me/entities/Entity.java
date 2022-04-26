package com.me.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import com.me.main.Game;
import com.me.world.Camera;
import com.me.world.Node;
import com.me.world.Vector2i;

public class Entity {

	protected double x, y;
	protected int width, height;
	protected int z;
	
	protected List<Node> path;
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(128, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(144, 0, 16, 16);
	public static BufferedImage WEAPON_EN_LEFT = Game.spritesheet.getSprite(144, 32, 16, 16);
	public static BufferedImage WEAPON_EN_RIGHT = Game.spritesheet.getSprite(144, 16, 16, 16);
	public static BufferedImage WEAPON_EN_FRONT = Game.spritesheet.getSprite(144, 48, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(128, 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(0, 64, 16, 16);
	public static BufferedImage ENEMY_EN_FEEDBACK = Game.spritesheet.getSprite(32, 96, 16, 16);
	public static BufferedImage FLOWER_EN = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage NPC_EN = Game.spritesheet.getSprite(160-16, 160-16
			
			, 16, 16);
	
	private int maskx, masky, mheight, mwidth;
	
	public int depth; //camada de renderiza��o da entidade
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x; 			//x da entidade
		this.y = y; 			//y da entidade
		this.width = width; 	//largura da entidade
		this.height = height; 	//altura da entidade
		this.sprite = sprite; 	//sprite da entidade (desenho)
	
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}
	
	public int getX() {
		return (int) this.x;
	}
	
	public int getY() {
		return (int) this.y;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() { //toda entidade vai ter seu pr�prio tick e render
		
		
		
	}
	
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); //retorna a distancia entre dois pontos
	}
	
	public void setMask(int maskx, int masky, int mwidth, int mheight) {
		
		this.maskx = maskx;
		this.masky = masky;
		this.mheight = mheight;
		this.mwidth = mwidth;
		
	}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
			
	
	public int compare(Entity e0, Entity e1) {
		if(e1.depth < e0.depth)
			return - 1;
		
		if(e1.depth > e0.depth)
			return + 1;
	
		return 0;
	}
	
	
	};
	
	public void render(Graphics g) {
		
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
				//Igual a:   x ou this.x |  y ou this.y
		
		//g.setColor(Color.red);
		//g.fillRect(this.getX() + this.maskx - Camera.x, this.getY() + this.masky - Camera.y, this.mwidth, this.mheight);
		
	
	}
	
	//ESSE M�TODO N�O FOI TESTADO!!!!!!!!! (M�todo de pixel perfect)
																														//i seria para enviar o index de cada entidade, para selecionar o sprite sendo usado
	public static boolean isColiddingPerfect(Entity e1, Entity e2, BufferedImage[] sprite1, BufferedImage[] sprite2, int i1, int i2) {
																											//sprite da entidade
		int[] spriteColor1 = new int[sprite1[i1].getWidth() * sprite1[i1].getHeight()];
		int[] spriteColor2 = new int[sprite2[i2].getWidth() * sprite2[i2].getHeight()];
		
		sprite1[i1].getRGB(0, 0, 16, 16, spriteColor1, 0, sprite1[i1].getWidth());
		sprite2[i2].getRGB(0, 0, 16, 16, spriteColor2, 0, sprite2[i2].getWidth());

		for(int xx1 = 0; xx1 < sprite1[i1].getWidth(); xx1++) {
			for(int yy1 = 0; yy1 < sprite1[i1].getHeight(); yy1++) {
				for(int xx2 = 0; xx2 < sprite2[i2].getWidth(); xx2++) {
					for(int yy2 = 0; yy2 < sprite2[i2].getHeight(); yy2++) {
						
						int pixelAtual1 = spriteColor1[xx1 + yy1 * sprite1[i1].getWidth()];
						int pixelAtual2 = spriteColor1[xx2 + yy2 * sprite2[i2].getWidth()];
						
						
						if(pixelAtual1 == 0x00FFFFFF || pixelAtual2 == 0x00FFFFFF) {
							continue;
						}
						
						if(xx1 + e1.x == xx2 + e2.x && yy1 + e1.y == yy2 + e2.y) {
							return true;
						}
					}
				}
			}
		}
		
		
//		Rectangle emask1 = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheight);
//		Rectangle emask2 = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheight);
//		
//		if(emask1.intersects(emask2) && e1.z == e2.z) {
//			return true;
//		}
		
		return false;
	}
	
	public static boolean isColidding(Entity e1, Entity e2) {
		
		Rectangle emask1 = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheight);
		Rectangle emask2 = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheight);
		
		if(emask1.intersects(emask2) && e1.z == e2.z) {
			return true;
		}
		
		return false;
	}
	
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
				//xPrev = x;
				//yPrev = y;
				if(x < target.x * 16 ) {
					x++;
					Enemy.dir = Enemy.rightDir;
				}else if(x > target.x * 16 ){
					x--;
					Enemy.dir = Enemy.leftDir;
					
				}
				
				if(y < target.y * 16 ) {
					y++;
					Enemy.dir = Enemy.downDir;
				}else if(y > target.y * 16 ){
					y--;
					Enemy.dir = Enemy.upDir;
					
				}
				
				if(x == target.x * 16 && y == target.y * 16 ) {
					path.remove(path.size() - 1);
				}
			}
		}
	}
	
	public boolean isColidding(int xnext, int ynext) {
		
		for(int i = 0; i < Game.enemies.size(); i++) {
			Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, mwidth, mheight);
			Enemy enemies = Game.enemies.get(i);
			
			if(enemies == this) { //se o respectivo inimigo na lista for eu mesmo,
				continue; //pule essa repeti��o
			}
			
			Rectangle targetEnemy = new Rectangle(enemies.getX() + maskx, enemies.getY() + masky, mwidth, mheight);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		
		
		return false;
		
	}
	
	
}
