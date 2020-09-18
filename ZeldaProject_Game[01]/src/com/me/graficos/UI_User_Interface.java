package com.me.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.me.entities.Npc;
import com.me.main.Game;

public class UI_User_Interface {

	/*Nessa classe estamos aplicando a barra de vida do player*/
	
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRoundRect(1 , -3 ,(int) (Game.player.maxLife * 0.81) + 2, 12, 2, 4);
		g.setColor(new Color(200, 0, 0, 200));
		g.fillRoundRect(2 , -2 ,(int) (Game.player.maxLife * 0.81), 10, 2, 4); //os 0.81 usados é para determinar que o desenvolvedor deseja que apareça 81% do tamanho original da variável
		g.setColor(new Color(0, 200, 0, 200));
							//usado na aula (Player.life / Player.maxLife) * 50)
										//valor da vida / 100 (vida maxima) * 50 (a conta está pegando metade do valor da vida para a representação gráfica)
		g.fillRoundRect(2 , -2 ,(int) ((Game.player.life  * 0.81)), 10, 2, 4); //para manter em uma posição fixa na tela, apenas não coloque o offset da camera
													//life precisa ser declarado como double, para aplicar a divisão corretamente
		
		
		//(Player.life  * 0.81) == ((Player.life /100) * 81) == 81% de Player.life
		g.setColor(new Color(255, 255, 255, 150));
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString((int) Game.player.life + " / " + (int) Game.player.maxLife, 5, 8);
		
		if(Game.npc.isReading && (Math.abs(Game.player.getX() - Game.npc.getX()) < 50 && Math.abs(Game.player.getY() - Game.npc.getY()) < 50)) {
			
			
			

			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.black);
			g.fillRect(Game.npc.getX() - 20, Game.npc.getY() + 50, 150, 45);
			g.setColor(Color.white);
			
			
			
			Game.npc.maxIndex1 = Npc.frases[Game.npc.myTextCont].length();
			Game.npc.maxIndex2 = Npc.frases[Game.npc.myTextCont+1].length();
			Game.npc.maxIndex3 = Npc.frases[Game.npc.myTextCont+2].length();


			if(Game.npc.curIndex1 > Game.npc.maxIndex1)
				Game.npc.curIndex1 = Game.npc.maxIndex1;
			
			g.drawString(Npc.frases[Game.npc.myTextCont].substring(0, Game.npc.curIndex1), Game.npc.getX() - 10, Game.npc.getY() + 60);
			
			if(Game.npc.curIndex2 > Game.npc.maxIndex2)
				Game.npc.curIndex2 = Game.npc.maxIndex2;
			
			g.drawString(Npc.frases[Game.npc.myTextCont+1].substring(0, Game.npc.curIndex2), Game.npc.getX() - 10, Game.npc.getY() + 70);
			
			
			
			if(Game.npc.curIndex3 > Game.npc.maxIndex3)
				Game.npc.curIndex3 = Game.npc.maxIndex3;
			
			g.drawString(Npc.frases[Game.npc.myTextCont+2].substring(0, Game.npc.curIndex3), Game.npc.getX() - 10, Game.npc.getY() + 80);
			g.setColor(Color.magenta);
			g.setFont(new Font("Arial", Font.BOLD, 8));
			g.drawString("PRESS ENTER", Game.npc.getX() + 75, Game.npc.getY() + 95);
			
			

			if(Game.npc.nextMessage) {
				Game.npc.nextMessage = false;
				Game.npc.myTextCont+=3;
				Game.npc.curIndex1 = 0;
				Game.npc.curIndex2 = 0;
				Game.npc.curIndex3 = 0;
			}
		}else {
			if(Game.npc.showMessage) {
				g.setFont(new Font("Arial", Font.BOLD, 10));
				g.setColor(Color.black);
				g.fillRect(Game.npc.getX(), Game.npc.getY() + 50, 120, 17);
				g.setColor(Color.white);
				g.drawString("Aperte Q para interagir", Game.npc.getX() + 4, Game.npc.getY() + 60);
			}
		
			
		/*ALERTA: Ao utilizar da classe para manipular a variável, todos os objetos com essa classe irão obedecer o comando,
		 * logo, eu um contexto de multijogadores, isso é delicado, pois se 1 jogador perder vida com Player.life aplicado,
		 * todos os players perderão aquela quantidade de vida, por estar manipulando a classe e não o objeto*/
		
		}
	}
}
