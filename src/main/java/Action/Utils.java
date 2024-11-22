package Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Utils {
    static WebDriver driver;
    public Utils(WebDriver driver)
    {
        this.driver=driver;
    }
    public static void scrollToElement(By by)
    {
        WebElement element=driver.findElement(by);
        String js_code = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) driver).executeScript(js_code, element);

    }
}
