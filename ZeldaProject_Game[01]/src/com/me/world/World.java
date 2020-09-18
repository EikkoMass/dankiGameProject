package com.me.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.me.entities.*;
import com.me.graficos.Spritesheet;
import com.me.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int HEIGHT, WIDTH;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path)); //pega o arquivo png do mapa
			int[] pixels = new int[map.getWidth() * map.getHeight()]; //pegando a quantidade de pixels do mapa e usa do mesmo para determinar o tamanho do array
			//pixels[20 * 20] no caso = pixels[400] (sendo 20 de largura e altura da imagem)
			
			tiles = new Tile[map.getWidth() * map.getHeight()]; //todos os pixels do mapa terão pisos ou paredes, logo, é o mesmo tamanho;
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth()); //filtra as cores desejadas da imagem e as alocam em um array
					// 1 - ponto x que eu quero que comece a análise de pixels
					// 2 - ponto y que eu quero que comece a análise de pixels
					// 3 - largura que eu quero que ele analise
					// 4 - altura que eu quero que ele analise
					// 5 - Array onde eu quero que seja armazenado as cores obtidas
					// 6 - offset (?)
					// 7 - scanalize (?)
			for(int xx = 0; xx < map.getWidth(); xx++) { //parte horizontal da imagem
				for(int yy = 0; yy < map.getHeight(); yy++) { //parte vertical da imagem

					int pixelAtual = pixels[xx + (yy * map.getWidth())]; //ele lê os pixels 'verticalmente' com base no ponto horizontal xx
					//linha 0: [xx + (yy * map.getWidth()] = 0 20 40 60 80 100 120 140 160 180 200 220 240 260 280 300 320 340 360 380
					//linha 1: [xx + (yy * map.getWidth()] = 1 21 41 61 81 101 121 141 161 181 201 221 241 261 281 301 321 341 361 381
					//linha 2: [xx + (yy * map.getWidth()] = 2 22 42 62 82 102 122 142 162 182 202 222 242 262 282 302 322 342 362 382
					//xx = 0 -> vai repetir yy 20 vezes, assim lendo a coluna 0 inteira
					//xx = 1 -> vai repetir yy 20 vezes, assim lendo a coluna 1 inteira
					// etc.
					
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					//sempre irá ser por padrão chão, o trabalho do if será substituir caso são seja
					
					if(pixelAtual == 0xFF000000) {
						//chão
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
						
					}else if(pixelAtual == 0xFF5b6ee1) {
		
					//identificando a cor do pixel em hexadecimal (sim, int aceita cores hexadecimal)
									//tem que colocar '0x' no inicio para ele entender que se trata de hexadecimal, e 'FF' em seguida do anterior para dizer que é sem transparência (opacidade)
						//player 
						Game.player.setX(xx*16); //definindo a localização de spawn do jogador com base na marcação no mapa
						Game.player.setY(yy*16);
						
						//(para o random map, o valor de ambos seria 0)
						//não ocorre erro de não ter chão por no começo do sistema de repetição sempre definirmos
						//tiles[...] como new FloorTile(...).
						
					}else if(pixelAtual == 0xFFFFFFFF) {
						//parede
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
					} else if(pixelAtual == 0xFFb76a71){
						//vida
						HealthPack pack = new HealthPack(xx*16, yy*16, 16, 16, Entity.LIFEPACK_EN);
						pack.setMask(1, 1, 14, 14);
						Game.entities.add(pack);
						
					}else if(pixelAtual == 0xFFdf7126){
						//arma
						Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.WEAPON_EN));
						
					}else if(pixelAtual == 0xFFfbf236){
						//munição
						Game.entities.add(new Bullet(xx*16, yy*16, 16, 16, Entity.BULLET_EN));
						
					}else if(pixelAtual == 0xFFac3232){
						//inimigo
						Enemy enemy = new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(enemy);
						Game.enemies.add(enemy);
					}else if(pixelAtual == 0xFF4CFF00){
						//flor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, Entity.FLOWER_EN);

					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
						
		/*WIDTH = 80;
		HEIGHT = 80;
		tiles = new Tile[WIDTH*HEIGHT];
		
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
				
				
			}	
		}
		
		
		int dir = 0,
			xx = 0,
			yy = 0;

		for(int i = 0; i < (50); i++) {
			
			if(dir == 0) {
				//direita
				
				if(xx < WIDTH) {
					xx++;
				}
			}else if(dir == 1) {
				//esquerda			
				
				if(xx > 0) {
					xx--;
				}
				
			}else if(dir == 2) {
				//baixo
				
				if(yy < HEIGHT) {
					yy++;
				}
				
			}else if(dir == 3) {
				//cima
				
				
				if(yy > 0) {
					yy--;
				}
			}
			
			if(Game.rand.nextInt(100) < 30) {
				dir = Game.rand.nextInt(4);
			}
			
			tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
		}*/
	}
	
	public static void resetGame(String level) {
		Game.entities.clear();
		Game.bullets.clear();
		Game.enemies.clear();
		Game.entities = new ArrayList<Entity>(); 
		Game.bullets = new ArrayList<BulletShoot>(); 
		Game.enemies = new ArrayList<Enemy>();
		Game.spritesheet = new Spritesheet("/Spritesheet01.png");
		Game.player = new Player(0, 0, 16,    16,     Game.spritesheet.getSprite(32, 0, 16, 16));
		Game.entities.add(Game.player);
		Game.npc = new Npc(32 , 16 , 16, 16, Entity.NPC_EN);
		Game.entities.add(Game.npc);
		Game.world = new World("/"+level);
		return;
	}
	
	public static boolean isFree(int xNext, int yNext, int zPlayer) {
		
		int 	x1 = xNext / TILE_SIZE, //próxima casa x
				y1 = yNext / TILE_SIZE, //próxima casa y
		
				x2 = (xNext + TILE_SIZE - 1) / TILE_SIZE,
				y2 = yNext / TILE_SIZE,
		
				x3 = xNext / TILE_SIZE,
				y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE,

				x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE,
				y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		
		if(!(tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile 
				|| tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile
				|| tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile 
				|| tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile)) {
			return true;
		}
		
		if(zPlayer > 0) {
			return true;
		}
		
		return false;
		//se for uma parede, vai retornar false (se não houvese o !() retornaria true, mas como queremos determinar se é um 'campo livre' ou não, fazemos)
		//assim para uma melhor leitura, exemplo: 'isFree'? : false (não é livre, logo, tem que ter colisão)
	}
	
	public static void generateParticles(int amount, int x, int y) {
		 
		
		for(int i = 0; i < amount; i++) {
			
			Game.entities.add(new Particle(x, y, 1, 1, null));
			
		}
		
		
	}
	
public static boolean isFreeDynamic(int xNext, int yNext, int width, int height) { //colisão de bullets na parede
		
		int 	x1 = xNext / TILE_SIZE, //próxima casa x
				y1 = yNext / TILE_SIZE, //próxima casa y
		
				x2 = (xNext + width - 1) / TILE_SIZE,
				y2 = yNext / TILE_SIZE,
		
				x3 = xNext / TILE_SIZE,
				y3 = (yNext + height - 1) / TILE_SIZE,

				x4 = (xNext + width - 1) / TILE_SIZE,
				y4 = (yNext + height - 1) / TILE_SIZE;
		
		if(!(tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile 
				|| tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile
				|| tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile 
				|| tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile)) {
			return true;
		}
		
		return false;
		//se for uma parede, vai retornar false (se não houvese o !() retornaria true, mas como queremos determinar se é um 'campo livre' ou não, fazemos)
		//assim para uma melhor leitura, exemplo: 'isFree'? : false (não é livre, logo, tem que ter colisão)
	}
	
	public void render(Graphics g) {
		
		int		xstart = Camera.x >> 4, //valor inicial do x da câmera
				ystart = Camera.y >> 4; //valor inicial do x da câmera
				//igual a Camera.x / 16
		int		xfinal = xstart + (Game.WIDTH >> 4) + 1, //adicionado +1 em ambos para imprimir 1 tile a mais (para não ficar com borda preta no mapa // erro interno // não ocorreu durante o tutorial)
				yfinal = ystart + (Game.HEIGHT >> 4) + 1;
		
		//O objetivo dessas variáveis é determinar quantas tiles cabem dentro da câmera, levando em relação a localização
		//da câmera, a largura / altura do jogo
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			//começa no xInicial e acaba no xFinal
			for(int yy = ystart; yy <= yfinal; yy++) {
				//começa no yInicial e acaba no yFinal
				
				//com isso está determinando quais tiles dever estar visiveis na tela, assim otimizando o jogo
				//(renderizando apenas o que está na tela)
				
				//tile está pegando o objeto dentro do respectivo array passado
				//com esse objeto possuindo seu x, y e seu sprite
				
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue; //funciona como um break, porém ele apenas pula a repetição atual e segue para a próxima
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g); 
			}	
		}
		
	}
	
	public static void renderMinimap() {
		
		for(int i = 0; i < Game.minimapaPixels.length; i++) {
			Game.minimapaPixels[i] = 0;
		}
		
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				if(tiles[xx + (yy * WIDTH)] instanceof WallTile) {
					Game.minimapaPixels[xx + (yy * WIDTH)] = 0xffffff;
					
				}
			}
		}
		
		int xPlayer = (Game.player.getX() / 16); //dividir por 16 por conta dos tiles serem 16 por 16
		int yPlayer = (Game.player.getY() / 16);
		
		
		Game.minimapaPixels[xPlayer + (yPlayer * WIDTH)] = 0x0000ff;
		
		for(int i = 0; i < Game.entities.size(); i++) {
			if(Game.entities.get(i) instanceof Weapon) {
				int xGun = Game.entities.get(i).getX() / 16;
				int yGun = Game.entities.get(i).getY() / 16;
				Game.minimapaPixels[xGun + (yGun * WIDTH)] = 0xffff00;
			}
			
			if(Game.entities.get(i) instanceof Npc) {
				int xNpc = Game.entities.get(i).getX() / 16;
				int yNpc = Game.entities.get(i).getY() / 16;
				Game.minimapaPixels[xNpc + (yNpc * WIDTH)] = 0xff0f0f;
			}
		}
		
		
	}
	
}
