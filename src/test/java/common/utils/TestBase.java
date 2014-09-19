package common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import common.web.Driver;

public class TestBase {
    protected WebDriver driver;
    protected String dataSource = null;
    protected void setDataSource(String name) { dataSource = name;}

	protected HashMap<String, String> props;

    @BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) throws Exception {
		driver = Driver.make(browser);
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @DataProvider(name = "csvdata")
    protected Object[][] testDataFromCSV() throws IOException {
        List<HashMap<String, String>> data = dataFromCSV();
        Object[][] objects = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            objects[i][0] = data.get(i);
        }
        return objects;
    }

	protected List<HashMap<String, String>> dataFromCSV(String resource_name) throws IOException {
		String dataResource = "/data/" + resource_name;
        List<HashMap<String, String>> data = common.utils.Resource.csvToHashMaps(dataResource);
		return data;
	}
	
	protected List<HashMap<String, String>> dataFromCSV() throws IOException {
		return dataFromCSV(dataSource);
	}

	protected HashMap<String, String> dataFromProps() throws IOException {
		String dataResource = "/data/" + dataSource;
		return common.utils.Resource.propToHashMap(dataResource);
	}
    
	protected HashMap<String, String> fetchProps() throws IOException {
		props = dataFromProps();
		return props;
	}
	protected void expect(HashMap<String,String> data, TestBody block) {
		String expect = data.get("result");
		Boolean result = block.doTest();
		if (expect.equals("pass") || expect.equals("Pass")) {
			if (!result)
				Assert.fail();
		} else {
			if (result)
				Assert.fail();
		}
	}
}
