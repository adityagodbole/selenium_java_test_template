package common.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.utils.Resource;


/**
 * Created by aditya on 05/09/14.
 */

public class Locator {
    private WebDriver driver;
    private HashMap<String, String> elements;

    public Locator(WebDriver driver, String locatorName) throws IOException {
        this.driver = driver;
        this.elements = loadElements(locatorName);
    }

    private HashMap<String, String> loadElements(String locatorName) throws IOException {
        String loc_name = "/pagelocators/" + locatorName;
        List<String> lines;
        lines = Resource.toLines(loc_name);
        return Resource.toHashMap(lines);
    }

    public String get(String name) {
        return elements.get(name);
    }


	public WebElement findElement(String how, String what)
			throws NoSuchElementException {
        java.lang.reflect.Method howMethod = null;
        Class<?> cls = null;
        try {
            cls = Class.forName("org.openqa.selenium.By");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new NoSuchElementException("Invalid locator type - " + how);
        }
        try {
            howMethod = cls.getMethod(how, String.class);
        } catch (NoSuchMethodException e) {
            throw new NoSuchElementException("Invalid locator type - " + how);
        }
        String elem_id = get(what);
        try {
            return this.driver.findElement((By) howMethod.invoke(null, elem_id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSuchElementException("Invalid locator");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new NoSuchElementException("Invalid locator");
        }
    }
}
