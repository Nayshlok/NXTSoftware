package controller;

import lejos.nxt.comm.RConsole;
import model.Rover;

public class Main {

	public static void main(String[] args){
		Rover rover = new Rover();
		RConsole.open();
		rover.start();
	}
}
