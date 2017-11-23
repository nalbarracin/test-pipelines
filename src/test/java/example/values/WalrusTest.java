package test.java.example.values;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import test.model.Walrus;
import test.model.WalrusFood;

public class WalrusTest {

	Walrus subject = new Walrus();

	@Test
	public void wrongFood() {
		WalrusFood someFood = new WalrusFood();
		WalrusFood moreFood = new WalrusFood();

		subject.addToStomach(someFood);

		assertThat(subject.hasEaten(moreFood), is(false));
	}

	@Test
	public void rightFood() {
		WalrusFood food = new WalrusFood();

		subject.addToStomach(food);

		assertThat(subject.hasEaten(food), is(true));
	}
}