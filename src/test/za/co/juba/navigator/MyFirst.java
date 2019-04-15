package za.co.juba.navigator;

import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@EnableAutoWeld
class MyFirst {

	@Test
	void test() {
		System.out.println("I a running this shit!!!");
		Assertions.assertNotNull("What is this!!");
	}

}
