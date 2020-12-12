package Game;

import Map.*;
import Entities.*;
import Items.*;

public class Game implements IGame{
	private Castle castle;

	@Override
	public void initialize() {
		Initialize init = new Initialize();
		try {
			this.castle = init.createCastle();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
}
