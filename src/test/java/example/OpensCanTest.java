package test.java.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import test.model.CannedWalrusFood;
import test.model.OpensCan;
import test.model.WalrusFood;

public class OpensCanTest {

	OpensCan subject = new OpensCan();

	@Test
	public void test() {
		WalrusFood food = new WalrusFood();
		CannedWalrusFood can = new CannedWalrusFood(food);

		WalrusFood result = subject.open(can);

		assertThat(result, is(food));
	}
}