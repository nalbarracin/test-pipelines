package test.example;

import test.example.values.CannedWalrusFood;
import test.example.values.Walrus;

public class FeedsWalrus {

	OpensCan opensCan = new OpensCan();

	public void feed(Walrus gary, CannedWalrusFood can) {
		gary.addToStomach(opensCan.open(can));
	}

}
