package test.example;

import test.example.values.CannedWalrusFood;
import test.example.values.WalrusFood;

public class OpensCan {

	public WalrusFood open(CannedWalrusFood can) {
		return can.extractContents();
	}

}
