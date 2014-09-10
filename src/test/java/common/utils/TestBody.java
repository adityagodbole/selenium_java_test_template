package common.utils;

import org.openqa.selenium.NoSuchElementException;

/**
 * Created by aditya on 06/09/14.
 */
@FunctionalInterface
public interface TestBody {
	Boolean doTest() throws NoSuchElementException;
}