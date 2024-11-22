package POM;

import Action.ActionBySelenium;
import Action.CaptureScreenShot;
import Action.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WebTables {

    private static String urlWebTables="https://demoqa.com/webtables";
    private static WebDriver driver;
    static ActionBySelenium actionBySelenium;
    static Formulaire formulaire;
    static CaptureScreenShot captureScreenShot;
    static Utils utils;
    static boolean employeeTrouve=false;
    static boolean employeeSupprimer=true;
    static boolean inputValidOuPas=false;
    static String firstName  ;
    static String lastName   ;
    static String email      ;
    static String age        ;
    static String salary     ;
    static String department ;
    static int i=0;
    By boutonAdd=By.xpath("//*[@id=\"addNewRecordButton\"]");
    By tableRow=By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]");

            @BeforeAll
            @Given("L'utilisateur est sur la page de gestion des employés")
            public static void setUp() {
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

                if(driver==null) {
                    driver = new ChromeDriver();
                    driver.get(urlWebTables);
                    System.out.println("driver ok");


                }
                driver.manage().window().maximize();
                if(actionBySelenium==null){
                    actionBySelenium=new ActionBySelenium(driver);
                    System.out.println("action By Selenium ok");
                }
                if(captureScreenShot==null){
                    captureScreenShot=new CaptureScreenShot(driver);
                    System.out.println("capture Screen Shot ok");
                }
                if(formulaire==null) {
                    formulaire = new Formulaire(driver, actionBySelenium, captureScreenShot, firstName, lastName, email, age, salary, department);
                    System.out.println("formulaire ok");
                }
                if(utils==null)
                {
                    utils=new Utils(driver);
                    System.out.println("Utils ok");
                }
            }
//            @AfterAll
//            public static void endUp()
//            {
//                if(driver!=null) {
//                    driver.quit();
//                }
//            }



            @When("L'utilisateur clique sur {string}")
            public void clickAdd(String action)
            {
                actionBySelenium.boutonClick(boutonAdd);
                captureScreenShot.captureScreenshot(action+" -- "+i);
                i++;
            }


            @And("L'utilisateur remplit le formulaire avec des informations :")
            public void remplireFormulaire(DataTable dataTable)
            {

                List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

                firstName  = data.get(0).get(    "First Name"    );
                lastName   = data.get(0).get(    "Last Name"     );
                email      = data.get(0).get(    "Email"         );
                age        = data.get(0).get(    "Age"           );
                salary     = data.get(0).get(    "Salary"        );
                department = data.get(0).get(    "Department"    );

                formulaire.setAge(age);
                formulaire.setDepartment(department);
                formulaire.setEmail(email);
                formulaire.setSalary(salary);
                formulaire.setFirstName(firstName);
                formulaire.setLastName(lastName);

                formulaire.remplireFormulaire();
                captureScreenShot.captureScreenshot("Formulaire");
            }

            @And("L'utilisateur {string} le formulaire")
            public void soumissionFormulaire(String action)
            {


                formulaire.soumetFormulaire();
                captureScreenShot.captureScreenshot(action+" -- "+i);
                i++;

            }

            @Then("Le nouvel {string}  doit apparaître dans le tableau")
            @And("Les {string} dans le tableau doivent correspondre à celles saisies dans le formulaire")
            public void ilEstDansLaTable(String action)
            {

                employeeTrouve=actionBySelenium.vrifierTableauSiAEmployer(tableRow,firstName, lastName,age, email,salary,department,"R");
                assertTrue("Le nouvel employé doit être présent dans le tableau", employeeTrouve);
                captureScreenShot.captureScreenshot(action+" -- "+i);
                i++;

            }

            @And("Le tableau doit se mettre à jour en temps réel sans rechargement de la page")
            public void tableauAjour(){

                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(employeeTrouve);
                Assert.assertEquals(currentUrl,urlWebTables );

            }

            @Then ("Des {string} doivent s'afficher pour chaque champ manquant ou invalide")
            public void erreurMessage(String action )
            {

                inputValidOuPas= formulaire.inputValidOuPas();

                if(inputValidOuPas)
                {
                    formulaire.fermerLeFormulaire();
                    assertTrue("Des messages d'erreur doivent s'afficher pour chaque champ manquant ou invalide",inputValidOuPas);
                }else
                {
                    assertFalse("Pas des messages d'erreur qui doivent s'afficher pour chaque champ manquant ou invalide",inputValidOuPas);
                }
                captureScreenShot.captureScreenshot(action+" -- "+i);
                i++;

            }

            @And ("Aucun {string} ne doit être ajouté au tableau")
            public void ilExisitPasDansLaTable(String action)
            {
                captureScreenShot.captureScreenshot(action+" -- "+i);
                i++;
                employeeTrouve=actionBySelenium.vrifierTableauSiAEmployer(tableRow,firstName, lastName,age, email,salary,department,"R");
                assertFalse("Erreur : L'employé avec First Name : "+firstName+" a été ajouté au tableau malgré des données invalides", employeeTrouve);
            }

            @And("Le tableau doit rester inchangé")
            public void tableauNeChangePas(){

                String currentUrl = driver.getCurrentUrl();
                Assert.assertFalse(employeeTrouve);
                Assert.assertEquals(currentUrl,urlWebTables );

            }

            @When ("L'utilisateur clique sur l'icône d'édition pour l'employé suivant")
            public void clickInconeEdit(DataTable dataTable){

                List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

                firstName  = data.get(0).get(    "First Name"    );
                lastName   = data.get(0).get(    "Last Name"     );
                email      = data.get(0).get(    "Email"         );
                age        = data.get(0).get(    "Age"           );
                salary     = data.get(0).get(    "Salary"        );
                department = data.get(0).get(    "Department"    );

                actionBySelenium.vrifierTableauSiAEmployer(tableRow,firstName, lastName,age, email,salary,department,"M");
            }

            @When("L'utilisateur clique sur l'icône supprimer pour l'employé suivant")
            public void clickInconeSup(DataTable dataTable){

                List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

                firstName  = data.get(0).get(    "First Name"    );
                lastName   = data.get(0).get(    "Last Name"     );
                email      = data.get(0).get(    "Email"         );
                age        = data.get(0).get(    "Age"           );
                salary     = data.get(0).get(    "Salary"        );
                department = data.get(0).get(    "Department"    );

                employeeSupprimer=actionBySelenium.vrifierTableauSiAEmployer(tableRow,firstName, lastName,age, email,salary,department,"S");
                assertTrue("Erreur : Employee existe pas",employeeSupprimer);
            }


            @Then("Les mise a jour sur l'employé doit apparaître dans le tableau")
            public void maitreAJourEmployer()
            {

                employeeTrouve=actionBySelenium.vrifierTableauSiAEmployer(tableRow,firstName, lastName,age, email,salary,department,"R");
                assertTrue("Le nouvel employé doit être présent dans le tableau", employeeTrouve);
            }

            @And("Les informations de l'employé ne doivent pas être mises à jour dans le tableau")
            public void pasMiseAJour()
            {

                employeeTrouve=actionBySelenium.vrifierTableauSiAEmployer(tableRow,firstName, lastName,age, email,salary,department,"R");
                assertFalse("Aucun employé ne doit être ajouté au tableau", employeeTrouve);
            }

            @Then("L'employé en question ne doit plus être présent dans le tableau")
            public void employeeEstSupprimer()
            {
                employeeSupprimer=actionBySelenium.vrifierTableauSiAEmployer(tableRow,firstName, lastName,age, email,salary,department,"R");
                assertFalse("Le nouvel employé doit être présent dans le tableau", employeeSupprimer);
            }

    @And("Le tableau doit se mettre à jour en temps réel après suppression  sans rechargement de la page")
    public void tableauAjourSupprimer(){

        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(employeeSupprimer);
        Assert.assertEquals(currentUrl,urlWebTables );

    }


}
