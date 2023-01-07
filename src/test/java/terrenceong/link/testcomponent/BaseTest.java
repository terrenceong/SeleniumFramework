package terrenceong.link.testcomponent;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import terrenceong.link.pageobjects.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LoginPage loginPage;
    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main" +
                "\\resources\\test.properties");
//        FileInputStream fis = new FileInputStream("src/main" +
//                "/resources/test.properties");
        prop.load(fis);
        String browserName =
                System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        browserName = browserName.toLowerCase();
        switch(browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;
            case "chromeheadless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                this.driver = new ChromeDriver(options);
                // following line allows headless mode to run in full screen to prevent flaky test case
                this.driver.manage().window().setSize(new Dimension(1440,900));
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
               this.driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                this.driver =  new FirefoxDriver();
                break;
        }
        this.driver.manage().window().maximize();
        return this.driver;

    }
    public static String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ssDate = dateFormat.format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+ "\\reports\\" + testCaseName + "_"+ ssDate+ ".png");
        FileUtils.copyFile(source,file);
        FileInputStream fileInputStream =new FileInputStream(file);
            byte[] bytes =new byte[(int)file.length()];
            fileInputStream.read(bytes);
            String encodedBase64 = new String(Base64.encodeBase64(bytes));
        return  "data:image/png;base64,"+encodedBase64;
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        this.initializeDriver();
        this.loginPage = new LoginPage(this.driver);
        this.loginPage.goTo();
    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        this.driver.quit();
    }
}
