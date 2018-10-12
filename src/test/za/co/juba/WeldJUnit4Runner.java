package za.co.juba;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * 
 * @author Sabelo Simelane <sabside@gmail.com>
 *
 */
public class WeldJUnit4Runner extends BlockJUnit4ClassRunner {

	public WeldJUnit4Runner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
    protected Object createTest() {
		System.setProperty("is.test", "true");
		final Class<?> test = getTestClass().getJavaClass();
        return WeldContext.INSTANCE.getBean(test);
    }
}
