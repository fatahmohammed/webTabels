package Action;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

// La class Action de Selenium sur les element de la page
public class ActionBySelenium {

    static WebDriver driver;

    public ActionBySelenium( WebDriver driver)
    {
        this.driver=driver;
    }
    // une methode pour clicker sur les bouton
    public static void boutonClick(By by)
    {
        Utils.scrollToElement(by);
        driver.findElement(by).click();
    }
    // une methode pour envoyer le texte sur les inputs
    public static void inputSendKey(By by, String key,String m)
    {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(by));



        if(key!=null) {
            Utils.scrollToElement(by);
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.BACK_SPACE);
            input.sendKeys(key);
        }
        if(key==null&& m.equals("f")){
            Utils.scrollToElement(by);
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.BACK_SPACE);
            }

    }
    public static boolean vrifierTableauSiAEmployer(By by,String firstName,String lastName,String age, String email,String salary, String department,String Type)
    {
        if(firstName == null)
        {firstName="";}
        if(lastName == null)
        {lastName="";}
        if(email == null)
        {email="";}
        if(age == null)
        {age="";}
        if(salary == null)
        {salary="";}
        if(department == null)
        {department="";}
        WebElement tableauElement=driver.findElement(by);
        List<WebElement> ligne=tableauElement.findElements(By.cssSelector(".rt-tr"));

        boolean employeeTrouve=false;
        int i=0;
        for(WebElement lign: ligne)
        {

            i++;

            List<WebElement> cells=lign.findElements(By.cssSelector(".rt-td"));

                if( cells.get(0).getText().equals(firstName)&& cells.get(1).getText().equals(lastName)&& cells.get(2).getText().equals(age)&&cells.get(3).getText().equals(email)&& cells.get(4).getText().equals(salary)&& cells.get(5).getText().equals(department)) {

                    employeeTrouve = true;
                    if (Type.equals("M")) {
                        try {
                            WebElement element = driver.findElement(By.xpath("//div[@class='action-buttons']//span[@id='edit-record-" + i + "']"));
                            Utils.scrollToElement(By.xpath("//div[@class='action-buttons']//span[@id='edit-record-" + i + "']"));
                            element.click();

                        } catch (NoSuchElementException e) {
                            e.printStackTrace();
                        }
                    }

                    if (Type.equals("S")) {
                        try {
                            WebElement element = driver.findElement(By.xpath("//div[@class='action-buttons']//span[@id='delete-record-" + i + "']"));
                            Utils.scrollToElement(By.xpath("//div[@class='action-buttons']//span[@id='delete-record-" + i + "']"));
                            element.click();

                        } catch (NoSuchElementException e) {
                            e.printStackTrace();
                        }
                    }

                    return employeeTrouve;
                }

        }

        return employeeTrouve;
    }

    public boolean inputInvalidVisible(By by)
    {
        List<WebElement> invalidInput=driver.findElements(by);
        boolean inputInvalidVisible=false;
        if(invalidInput.size()>=1)
        {
            inputInvalidVisible=true;
        }

        return inputInvalidVisible;

    }


}
