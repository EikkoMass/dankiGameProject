package com.me.entities;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.me.main.Game;

public class Npc extends Entity{

	public int curIndex1 = 0, curIndex2 = 0, curIndex3 = 0, maxIndex1, maxIndex2, maxIndex3;
	public int currentTime = 0, maxTime = 3;
	
	
	public static String[] frases = {
			"Essas criaturas saíram",
			"das profundezas da terra",
			"e estão atrás de você",
			"Pegue sua arma e munição",
			"para derrotá-los, clicando com",
			"seu 'mouse' ou 'Z' do teclado",
			"Quer minha ajuda? Acho que ",
			"não percebeu, mas eu nem",
			"sequer tenho pernas"
			};
	
	public int myTextCont = 0;
	
	public boolean showMessage = false, isReading = false, nextMessage = false;
	
	public Npc(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		this.depth = 2;
	}
	
	public void tick(){
		int xPlayer = Game.player.getX();
		int yPlayer = Game.player.getY();
		
		
		int xNpc = this.getX();
		int yNpc = this.getY();
		
		if(Math.abs(xPlayer - xNpc) < 50 && Math.abs(yPlayer - yNpc) < 50) {
			showMessage = true;
		}else {
			showMessage = false;
		}
		
		if(myTextCont >= frases.length) {
			isReading = false;
			myTextCont = 0;
		}
		
		if(isReading) {
			currentTime++;
			if(currentTime > maxTime) {
				currentTime = 0;
				
				if(curIndex1 < maxIndex1)
					curIndex1++;
				else if(curIndex2 < maxIndex2 && curIndex1 >= maxIndex1)
					curIndex2++;
				else if(curIndex3 < maxIndex3 && curIndex2 >= maxIndex2)
					curIndex3++;
			}
		}
}	
	
	public void render(Graphics g) {
		super.render(g);
		
	}

	
	
}
