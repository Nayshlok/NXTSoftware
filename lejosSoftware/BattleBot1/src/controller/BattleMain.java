package controller;

import lejos.nxt.comm.RConsole;

public class BattleMain {

	public static void main(String[] args){
		BattleBot rover = new BattleBot();
		rover.start();
	}
}