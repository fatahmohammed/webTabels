package POM;

import Action.ActionBySelenium;
import Action.CaptureScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Formulaire {

    WebDriver driver;
    ActionBySelenium actionBySelenium;
    CaptureScreenShot captureScreenShot;
    String firstName  ;
    String lastName   ;
    String email      ;
    String age        ;
    String salary     ;
    String department ;

    public Formulaire(WebDriver driver,ActionBySelenium actionBySelenium,CaptureScreenShot captureScreenShot,String firstName, String lastName, String email, String age, String salary, String department) {

        this.driver=driver;
        this.actionBySelenium=actionBySelenium;
        this.captureScreenShot= captureScreenShot;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.age=age;
        this.salary=salary;
        this.department=department;

    }

    By inputFirstName=By.xpath("//div[contains(@class, 'col-md-6') and contains(@class, 'col-sm-6')]/input[@id='firstName']");
    By inputLastName=By.xpath("//*[@id=\"lastName\"]");
    By inputEmail=By.xpath("//*[@id=\"userEmail\"]");
    By inputAge=By.xpath("//*[@id=\"age\"]");
    By inputSalary=By.xpath("//*[@id=\"salary\"]");
    By inputDepartment=By.xpath("//*[@id=\"department\"]");
    By submitBouton =By.xpath("//*[@id=\"submit\"]");
    By formulaireVue=By.xpath("/html/body/div[4]/div/div");
    By fermerFormulaire=By.xpath("/html/body/div[4]/div/div/div[1]/button/span[1]");
    By inputInvalid=By.cssSelector(".form-control:invalid");





    boolean inputInvalidVisible=false;
    public void remplireFormulaire()
    {

        actionBySelenium.inputSendKey(inputFirstName,firstName,"f");
        actionBySelenium.inputSendKey(inputLastName,lastName,"l");
        actionBySelenium.inputSendKey(inputEmail,email,"e");
        actionBySelenium.inputSendKey(inputAge,age,"a");
        actionBySelenium.inputSendKey(inputSalary,salary,"s");
        actionBySelenium.inputSendKey(inputDepartment,department,"d");

    }

    public void soumetFormulaire()
    {
    actionBySelenium.boutonClick(submitBouton);
    }

    public boolean inputValidOuPas()
    {
        inputInvalidVisible=actionBySelenium.inputInvalidVisible(inputInvalid);
        return inputInvalidVisible;
    }
    public void fermerLeFormulaire()
    {
        actionBySelenium.boutonClick(fermerFormulaire);

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
