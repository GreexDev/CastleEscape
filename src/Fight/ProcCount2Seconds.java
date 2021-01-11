package Fight;

import java.time.Duration;
import java.time.Instant;

public class ProcCount2Seconds extends Thread{
	private Instant t1,t2;
	
	public ProcCount2Seconds() {
		// do nothing
	}

	public void run() {
		// Set duration
		t1 = t2 = Instant.now();
		// Let the time for player to attack the monster
		while(Duration.between(t1, t2).getSeconds() < 2) {
			t2 = Instant.now();
		}
	}
}
