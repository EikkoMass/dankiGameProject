package com.me.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.me.main.Game;
import com.me.main.Sound;
import com.me.world.Camera;
import com.me.world.World;

public class Player extends Entity{

	public boolean right, left, up, down; //vari�veis de movimento
	public double speed = 1.2; //velocidade de movimenta��o do personagem
	public int frames = 0, maxFrames = 5, index = 0, maxIndex = 3; //vari�veis de anima��o
	private boolean moved = false; //indicador de movimento
	public int leftDir = 0, rightDir = 1, upDir = 2, downDir = 3; //dire��es do personagem com respectivo valor para cada (vari�veis de anima��o)
	public int dir = downDir; //controla para onde o personagem est� direcionado
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	private BufferedImage playerDamaged = Game.spritesheet.getSprite(64, 32, 16, 16);
	private int damagedFrames = 0;
	public boolean isDamaged = false;
	public boolean hasGun = false;
	
	public int weaponBalanceX = 8;
	public int weaponBalanceY = 2;
	public int shootBalanceX = 0;
	public int shootBalanceY = 0;
	
	public boolean shoot = false;
	public boolean mouseShoot = false;
	
	public int mouseX = 0; //na aula sendo usado como mx
	public int mouseY = 0; //na aula sendo usado como my
	
	public int ammo = 0, maxAmmo = 50;
	
	public boolean jump = false, isJumping = false;
	public int z = 0;
	public int jumpFrames = 18, jumpCur = 0, jumpSpeed = 2;
	public boolean jumpUp = false, jumpDown = false;
	
	public double life = 100, maxLife = 100;	//na aula foi usado static para facilitar a manipula��o da vida, pois, ao manipular
	//a classe, o objeto da mesma se adapta
	
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite); //como estendeu de Entity, o constructor tem que ser igual ao de entity 
											//(isso valendo para o super tamb�m, que vai fazer exatamente o que o Entities requere que fa�a)
	
		leftPlayer = new BufferedImage[4];
		rightPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		for(int i = 0; i <leftPlayer.length; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(64 + (16 * i), 0, 16, 16);
			leftPlayer[i] = Game.spritesheet.getSprite(112 - (16 * i), 16, 16, 16);
			upPlayer[i] = Game.spritesheet.getSprite(48 , 0 + (16 * i), 16, 16);
			downPlayer[i] = Game.spritesheet.getSprite(32 , 0 + (16 * i), 16, 16);
		}
		
	}
	
	public void revealMap() { //aplicar um blur para a identifica��o do mapa
		//modelo fog of war
		
		int xx  = (int) (x / 16);
		int yy  = (int) (y / 16);
		
		World.tiles[xx+(yy*World.WIDTH)].show = true;
		World.tiles[xx+1+(yy*World.WIDTH)].show = true;
		World.tiles[xx-1+(yy*World.WIDTH)].show = true;
		
		
		World.tiles[xx+((yy+1)*World.WIDTH)].show = true;
		World.tiles[xx+((yy-1)*World.WIDTH)].show = true;
		
		
		World.tiles[xx-1+((yy+1)*World.WIDTH)].show = true;
		World.tiles[xx+1+((yy-1)*World.WIDTH)].show = true;
		World.tiles[xx-1+((yy-1)*World.WIDTH)].show = true;
		World.tiles[xx+1+((yy+1)*World.WIDTH)].show = true;
	}
	
	public void tick() { //por padr�o Entity j� possui um m�todo tick, logo, nesse caso estamos reescrevendo em cima do
						//padr�o, sendo agora esse que vai ser executado, t�cnica de orienta��o a objetos, chamada de polimorfismo
		depth = 1; //camada de renderiza��o da entidade
		
		
		//revealMap();
		
		if(jump) {
			if(isJumping == false) {
				jump = false;
				isJumping = true;
				jumpUp = true;
			}
			
		}
		
		if(isJumping == true) {
				 
				 if(jumpUp) {
					 if(jumpCur >= (jumpFrames * 0.75))
						 this.jumpCur+=jumpSpeed; 
					 else
						 this.jumpCur+=(jumpSpeed / 2); 
				 }else if(jumpDown) {
					 if(jumpCur <= (jumpFrames * 0.75))
						 this.jumpCur-=jumpSpeed; 
					 else
						 this.jumpCur-=(jumpSpeed / 2); 
					 if(jumpCur <= 0) {
						 jumpUp = false;
						 jumpDown = false;
						 isJumping = false;
						 jumpCur = 0;
					 }
				 }
				 
				z = jumpCur;
				
			if(jumpCur >= jumpFrames) { 
				jumpUp = false;
				jumpDown = true;
			}
		
		}
		
		moved = false;
		
		if(right && World.isFree((int) (x + speed), this.getY(), z)) { //se a vari�vel for true e o m�todo for true
			moved = true; //se movimentou
			dir = rightDir; //andou para a direita
			x+=speed; //apenas funciona por as vari�veis em Entity serem protected, sendo assim, as classes filhas podem acess�-las sem problemas
			
			
		} else if(left && World.isFree((int) (x - speed), this.getY(), z)) {
			moved = true; //se movimentou
			dir = leftDir; //andou para a esquerda
			x-=speed; //valor � incrementado com base no mesmo somado (ou diminuido) por speed
		
		}
		
		if(down && World.isFree(this.getX(),(int) (y + speed), z)) {
			moved = true; //se movimentou
			dir = downDir; //andou para a baixo
			y+=speed;
			
			
		} else if(up && World.isFree(this.getX(), (int) (y - speed), z)) {
			moved = true; //se movimentou
			dir = upDir; //andou para a cima
			y-=speed;
			
		}
		
		if(moved) { //se ele se movimentar
			frames++; //incrementa frames (controlador de velocidade da anima��o)
			if(frames == maxFrames) { //se frames for igual a seu limite de frames (no caso, 5)
				index++; //incrementa frames, assim trocando o sprite
				frames = 0; //zera frames
			}
			
			if(index > maxIndex) { //se index for maior que seu limite de sprites
				index = 0; //zera index
			}
		}
		if(!moved) {//se ele n�o se movimentar
			index = 0; //se ele n�o estiver se movimentando, vai ficar printado o primeiro sprite da ultima dire��o feita
		}
		
			this.checkCollisionHealthPack();
			this.checkCollisionAmmo();
			this.checkCollisionGun();
			
			if(isDamaged) {
				
				damagedFrames++;
				
				if(damagedFrames >= 8) {
					damagedFrames = 0;
					isDamaged = false;
				}
				
			}
			
			if(shoot) {
				shoot = false;
				if(hasGun)
				{
					if(ammo > 0) {
						//System.out.println("atirando");
						//caso o player atire
						ammo--;
						Sound.hitBullet.play();
						
						int dx = 0, dy = 0;
						
						if(dir == rightDir) {
							
							dx = 1;
							shootBalanceX = 17;
							shootBalanceY = 8;

							
						}else if(dir == leftDir) {
							
							dx = -1;
							shootBalanceX = -5;
							shootBalanceY = 8;

							
						}else if(dir == upDir) {
							
							dy = -1;
							shootBalanceX = 2;
							shootBalanceY = 2;

							
						}else {
							
							dy = 1;
							shootBalanceX = 3;
							shootBalanceY = 5;

						}
						
						BulletShoot bullet = new BulletShoot(this.getX() + shootBalanceX , this.getY() + shootBalanceY - z, 2, 2, null, dx, dy);
						Game.bullets.add(bullet);
						}
						else if (ammo <= 0)
						{
							Sound.emptyGun.play();
						}	
				}
			}
			
			if(mouseShoot) {
				mouseShoot = false;
				
							//this.getX() this.getY() posi��o do player
				double angle = 0; //calcula o �ngulo levando em conta o player e aonde foi clicado na tela
				//retorna o angulo    					   y					  x
				//System.out.println(angle);

				if(ammo > 0 && hasGun) {
				//System.out.println("atirando");
				//caso o player atire
				
				ammo--;
				Sound.hitBullet.play();
				
				if(dir == rightDir) {
					
					shootBalanceX = 17;
					shootBalanceY = 8;

					
				}else if(dir == leftDir) {
					
					shootBalanceX = -5;
					shootBalanceY = 8;

					
				}else if(dir == upDir) {
					
					shootBalanceX = 2;
					shootBalanceY = 2;

				}else {
					
					shootBalanceX = 3;
					shootBalanceY = 5;

				}
				
				angle = Math.atan2(mouseY - (this.getY() + shootBalanceY - Camera.y), mouseX - (this.getX() + shootBalanceX - Camera.x)); //calcula o �ngulo levando em conta o player(localiza��o de onde sai o tiro) e aonde foi clicado na tela
				double dx = Math.cos(angle), dy = Math.sin(angle);
				
				BulletShoot bullet = new BulletShoot(this.getX() + shootBalanceX, this.getY() + shootBalanceY - z, 2, 2, null, dx, dy);
				Game.bullets.add(bullet);
				}
			}
			
			if(life <= 0) {
				life = 0;
				Game.gameState = "GAME_OVER";
			}
			
			updateCamera();
	}
	
	public void updateCamera() {
		Camera.x = Camera.clamp((this.getX()) - (Game.WIDTH / 2), 0, (World.WIDTH*16) - Game.WIDTH); //Camera est� definindo seu x com base no x do player - metade da largura da tela do jogo
		Camera.y = Camera.clamp((this.getY()) - (Game.HEIGHT / 2), 0, (World.HEIGHT*16) - Game.HEIGHT); //Camera est� definindo seu x com base no y do player - metade da altura da tela do jogo
		//centralizando a c�mera no player									fazendo * o tamalho do Tile (para ficar no tamanho real do mapa)

		//Camera.clamp(Atual, Min, Max) est� pegando o xAtual, xM�nimo e xM�ximo que o Mapa pode alcan�ar (assim fazendo a colis�o de extremos, evitando aparecer aquelas partes pretas nas laterais)
		//											   yAtual, yM�nimo e yM�ximo
	}
	
	public void render(Graphics g) { //m�todo reescrito, logo, descartando o padr�o da classe pai
		if(!isDamaged) {
			
			switch(index) {
			
			case 1:
				weaponBalanceX = index + 6;
				break;
			
			case 2:
				weaponBalanceY = index - 1;
				
				break;
				
			case 3:
				weaponBalanceY = index - 1;
				weaponBalanceX = index + 5;
				break;
			
			}
			
			if(dir == rightDir) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y - z, null); //os sprites mudam de acordo com o valor de index, que
				//� alterado com o decorrer da movimenta��o do personagem, usando as vari�veis moved, index e frames
				
				if(hasGun) {
					//se tiver a arma e estiver andando para a direita, desenhar sprite direito da arma
					g.drawImage(Entity.WEAPON_EN_RIGHT, this.getX() - Camera.x + weaponBalanceX, this.getY() - Camera.y + weaponBalanceY - z, null);
				}
			}else if (dir == leftDir) {
				g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y - z, null);
				
				if(hasGun) {
					//se tiver a arma e estiver andando para a esquerda, desenhar sprite esquerda da arma
					g.drawImage(Entity.WEAPON_EN_LEFT, this.getX() - Camera.x - weaponBalanceX, this.getY() - Camera.y + weaponBalanceY - z, null);
				}
				
			}else if(dir==upDir) {
				g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y - z, null);
			}else if(dir == downDir) {
				g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y - z, null);
				
				if(hasGun) {
					//se tiver a arma e estiver andando para a direita, desenhar sprite direito da arma
					g.drawImage(Entity.WEAPON_EN_FRONT, this.getX() - Camera.x, this.getY() - Camera.y + weaponBalanceY - z, null);
					
				}
				
			}
		}else {
			g.drawImage(playerDamaged, this.getX() - Camera.x, this.getY() - Camera.y - z, null);
			if(hasGun) {
				//se tiver a arma e estiver andando para a direita, desenhar sprite direito da arma
				g.drawImage(Entity.WEAPON_EN_RIGHT, this.getX() - Camera.x + weaponBalanceX, this.getY() - Camera.y + weaponBalanceY - z, null);
			}
			
		}
		
		//Os x e y das entidades s�o double, ent�o usamos .getX() e .getY() para coloc�-las, pois no processo, os metodos
		//convertem para inteiro as vari�veis.
		
		//a posi��o � diminuida pelo x e y da c�mera para manter a tela centralizada no player (assim fazendo o player perder velocidade)
		
		
		if(isJumping) {
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0,0,0, 100));
			g2.fillOval(this.getX() - Camera.x , this.getY() - Camera.y + 14, 16, 4);
		}
	}
	
	public void checkCollisionAmmo() {
		
	for(int i = 0; i < Game.entities.size(); i++) {
				
				Entity atual = Game.entities.get(i);
				
				if(atual instanceof Bullet) {
					
					if(Entity.isColidding(this, atual)) { //this se refere ao Player
					if(ammo < maxAmmo) {
						ammo+=6;
						
						if(ammo > maxAmmo) {
							ammo = maxAmmo;
						}
						
						Game.player.shoot = false;
						Game.entities.remove(atual);
					}	
				}
			}
		}
		
	}
	
	public void checkCollisionGun() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
					
					Entity atual = Game.entities.get(i);
					
					if(atual instanceof Weapon) {
						
						if(Entity.isColidding(this, atual)) { //this se refere ao Player
							hasGun = true;
							Game.entities.remove(atual);
						}	
					}
				}
			}
			
		
	
	
	public void checkCollisionHealthPack() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			
			Entity atual = Game.entities.get(i);
			
			if(atual instanceof HealthPack) {
				
				if(Entity.isColidding(this, atual)) { //this se refere ao Player
					if(life < 100) {
						life+=10;
						
						if(life>100) {
							life = 100;
						}
						
						Game.entities.remove(atual);
					}	
				}
			}
		}
		
	}

}
