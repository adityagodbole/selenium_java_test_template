package common.web;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aditya on 06/09/14.
 */
public abstract class Page {
    protected WebDriver driver;
    protected Locator locator;
    protected navStatus navstatus = new navStatus();

    public Page(WebDriver driver, String locatorName) throws IOException {
        this.driver = driver;
        this.locator = new Locator(driver, locatorName);
    }

	protected Boolean navigated(Class<?> pageClass) {
        return navstatus.navigated(pageClass);
    }

    protected Boolean navigated() {
        return navstatus.navigated();
    }

	protected void setNavigation(Class<?> pageClass) {
        navstatus.navigate(pageClass);
    }

    protected void unsetNavigation() {
        setNavigation(null);
    }

    protected WebElement findElement(String how, String what) {
		return locator.findElement(how, what);
    }

	abstract public Boolean waitForLoad();

	protected Boolean waitForID(String locator) {
		return waitForID(locator, 2);
    }

	protected Boolean waitForID(String locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String wait_id = this.locator.get(locator);
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.id(wait_id)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        if (element == null) {
			return false;
        }
		return true;
    }
}
