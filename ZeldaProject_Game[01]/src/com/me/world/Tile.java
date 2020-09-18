package com.me.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.me.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16); //Chão do jogo
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16); //Parede do jogo
	//são estáticos para não iniciarem toda vez que chamar a class tile, ou seja, serão iniciados apenas 1 vez

	public boolean show = true; //para o fog of war funcionar precisa estar por padrão false
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		
	}
	
	public void render(Graphics g){
		if(show)
			g.drawImage(sprite, x - Camera.x, y - Camera.y, null); //posicionado levando em conta a camera
	}
}
