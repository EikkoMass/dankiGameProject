package com.me.world;

public class Vector2i {

	/* As classes AStar, Node e Vector2i foram criadas com o intuíto de aplicar o algorítmo A* no jogo*/

	public int x, y;


	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object object) {
		Vector2i vec = (Vector2i) object;


		if(vec.x == this.x && vec.y == this.y) {
			return true;
		}
		
			return false;
	}

	

}


