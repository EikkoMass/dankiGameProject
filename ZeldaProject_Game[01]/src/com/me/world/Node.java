package com.me.world;

public class Node {

	public Vector2i tile;
	public Node parent;
	public double fCost, gCost, hCost;
	
	/* As classes AStar, Node e Vector2i foram criadas com o intuíto de aplicar o algorítmo A* no jogo*/

	
	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = hCost + gCost;
	}
	
}
