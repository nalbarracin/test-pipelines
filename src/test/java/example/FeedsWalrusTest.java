package test.java.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import test.model.CannedWalrusFood;
import test.model.FeedsWalrus;
import test.model.OpensCan;
import test.model.Walrus;
import test.model.WalrusFood;

@RunWith(MockitoJUnitRunner.class)
public class FeedsWalrusTest {

	@InjectMocks
	FeedsWalrus subject;

	@Mock
	OpensCan opensCan;

	@Test
	public void test() {
		Walrus gary = new Walrus();
		CannedWalrusFood can = new CannedWalrusFood();
		WalrusFood food = new WalrusFood();
		when(opensCan.open(can)).thenReturn(food);

		subject.feed(gary, can);

		assertThat(gary.hasEaten(food), is(true));
	}
}