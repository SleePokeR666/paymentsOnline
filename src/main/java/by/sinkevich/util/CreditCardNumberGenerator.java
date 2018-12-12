package by.sinkevich.util;

import java.util.Date;
import java.util.Random;

public class CreditCardNumberGenerator {

	private static final Random RANDOM = new Random(new Date().getTime());

	public static int generateCreditCardNumber() {
		return RANDOM.nextInt(Integer.MAX_VALUE);
	}
}
