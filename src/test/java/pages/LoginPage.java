package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.web.Page;

/**
 * Created by aditya on 06/09/14.
 */
public class LoginPage extends Page {

	public HomePage navigate(Class<HomePage> c) {
        if (!navigated(HomePage.class)) {
            submitLogin();
        }
		HomePage hp;
		try {
			hp = new HomePage(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        hp.waitForLoad();
        return hp;
    }


    public LoginPage(WebDriver driver, String url) throws IOException {
        super(driver, "login_page");
        driver.get(url);
    }

    public LoginPage(WebDriver driver) throws IOException {
        super(driver, "login_page");
    }

    @Override
	public Boolean waitForLoad() {
		return waitForID("load_check_div_id");
    }

    public void setUserName(String name) {
        WebElement uname = findElement("id", "email_div_id");
        uname.sendKeys(name);
    }

    public void setPass(String pass) {
        WebElement passwd = findElement("id", "pass_div_id");
        passwd.sendKeys(pass);
    }

    public void submitLogin() {
		WebElement submit = findElement("name", "submit_name");
        submit.click();
        setNavigation(HomePage.class);
    }

    public void doLogin(String user, String pass) {
        setUserName(user);
        setPass(pass);
        submitLogin();
    }


}
