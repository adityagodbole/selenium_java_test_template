package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import common.web.Page;

/**
 * Created by aditya on 06/09/14.
 */
public class HomePage extends Page {
    public HomePage(WebDriver driver, String url) throws IOException {
        super(driver, "home_page");
    }

    @Override
	public Boolean waitForLoad() {
		return waitForID("load_wait_id", 30);
    }

    public HomePage(WebDriver driver) throws IOException {
        super(driver, "home_page");
    }
}
