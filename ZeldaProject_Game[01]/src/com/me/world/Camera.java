package com.me.world;

public class Camera {

	public static int x = 20, y = 20;
	
	public static int clamp(int Atual, int Min, int Max) {
		
		if(Atual < Min)
			Atual = Min;
		if(Atual > Max)
			Atual = Max;
		
		return Atual;
	}
	
	
}
