package Action;

import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class CaptureScreenShot {
    static WebDriver driver;

    public CaptureScreenShot(WebDriver driver)
    {
        this.driver=driver;
    }

    public void capteElement(By Vue, String nomElement) {
        try {
            WebElement element = driver.findElement(Vue);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Obtenir la position et la taille de l'élément
        Point location = element.getLocation();
        Dimension size = element.getSize();

        // Recadrer l'image pour obtenir uniquement la zone de l'élément
        BufferedImage fullImage = null;

            fullImage = ImageIO.read(screenshot);

        BufferedImage elementScreenshot = fullImage.getSubimage(
                location.getX(), location.getY(),
                size.getWidth(), size.getHeight());
        ImageIO.write(elementScreenshot, "png", screenshot);

        // Sauvegarder la capture d'écran
        File screenshotLocation = new File("D:\\niji\\WebTables\\Capture\\"+nomElement+".png");
        FileUtils.copyFile(screenshot, screenshotLocation);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void captureScreenshot( String fileName) {
        try {
            Thread.sleep(100);

        // Convertir le WebDriver en TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Capturer l'écran et stocker le fichier temporairement
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Définir l'endroit où vous voulez enregistrer la capture d'écran
        File destFile = new File("D:\\niji\\WebTables\\Capture\\"+fileName+".png");


            // Copier le fichier vers le chemin de destination
            FileUtils.copyFile(srcFile, destFile);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
