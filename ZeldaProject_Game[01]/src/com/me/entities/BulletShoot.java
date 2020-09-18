package com.me.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.me.main.Game;
import com.me.world.Camera;
import com.me.world.World;

public class BulletShoot extends Entity{

	private double dx, dy;
	private double speed = 4;
	private int life = 35, curLife = 0; //tempo de vida da bala
	
	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
	}

	public void tick() {
		
		if(World.isFreeDynamic((int) (x + (dx * speed)),(int) (y + (dy * speed)), 2, 2)) {
		
			x+=dx * speed;
			y+=dy * speed;
			
		}else {
			
			Game.bullets.remove(this);
			World.generateParticles(100,(int) x,(int) y);
			return;
			
		}
		curLife++;
		if(curLife == life) {
			Game.bullets.remove(this);
			World.generateParticles(100,(int) x,(int) y);

			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, this.width, this.height);
	}
	
}
