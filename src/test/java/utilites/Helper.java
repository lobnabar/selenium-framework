package utilites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	public static void captureScreenshot(WebDriver driver, String screenshotname) {
	    // 1. Added the dot for the extension
	    Path dest = Paths.get("./Screenshot", screenshotname + ".png"); 
	    
	    try {
	        // 2. Corrected dest.getParent()
	        Files.createDirectories(dest.getParent()); 
	        
	        // 3. Corrected FileOutputStream spelling and casing
	        FileOutputStream out = new FileOutputStream(dest.toString()); 
	        
	        // 4. Corrected TakesScreenshot casing
	        out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
	        out.close();
	    } catch (IOException e) {
	        System.out.println("Exception while taking screenshot: " + e.getMessage());
	    }
	
	}
}
