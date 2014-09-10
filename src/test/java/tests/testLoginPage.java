package tests;

import java.io.IOException;
import java.util.HashMap;

import junit.framework.Assert;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

import common.utils.TestBase;

/**
 * Created by aditya on 08/09/14.
 */
@Test
public class testLoginPage extends TestBase {
    private static LoginPage login_page;

    public testLoginPage() {
        setDataSource("login_page");
    }


	@Test
	public void testLoginLoad() throws IOException {
		LoginPage login = new LoginPage(driver,
				"http://app-staging.cloudinsynergy.com");
		Assert.assertTrue(login.waitForLoad());
	}

	@Test(dataProvider = "csvdata", dependsOnMethods = { "testLoginLoad" })
    public void testLogin(HashMap<String,String> data) throws IOException, NoSuchElementException {
		expect(data, () -> {
			HomePage hp;
    		try {
				LoginPage login = new LoginPage(driver,
						"http://app-staging.cloudinsynergy.com");
    			login_page = login;
    			login.doLogin(data.get("username"), data.get("password"));
    		} catch (Exception e) {
				e.printStackTrace();
    			throw new RuntimeException("Cannot find element");
			}
			hp = login_page.navigate(HomePage.class);
			if (hp == null)
				return false;
			return hp.waitForLoad();
    	});
	}
}
