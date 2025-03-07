package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.WordPage;

public class BaseTest {
    public WebDriver driver;
    private Object WebDriverManager;


    @Before
    public void driverSetup(){
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.diki.pl");
        //driver.navigate().to("https://www.diki.pl/slownik-angielskiego?q=scraping");
        driver.manage().window().maximize();
    }

    @Test
    public void checkAWordCorrectPath(){
        WordPage wordPage = new WordPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        final WebElement linkToCookiesWindow = driver.findElement(By.className("abstractButtonAppearance"));
        if(linkToCookiesWindow.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(linkToCookiesWindow));
            linkToCookiesWindow.findElement(By.cssSelector(".buttonText")).click();
        }
        driver.findElement(wordPage.linkToInputWord).sendKeys("scraping");
        driver.findElement(wordPage.linkToSearchingLoop).click();
        driver.findElement(wordPage.linkToPronounciation).click();
        driver.findElement(wordPage.linkToGetSentences).click();
        driver.findElement(wordPage.linkToFirstWord).click();
    }

    @Test
    public void checkAWordIfMissingWordInSearch()
    {   WordPage wordPage = new WordPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        final WebElement linkToCookiesWindow = driver.findElement(By.className("abstractButtonAppearance"));
        if(linkToCookiesWindow.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(linkToCookiesWindow));
            linkToCookiesWindow.findElement(By.cssSelector(".buttonText")).click();
        }
        driver.findElement(wordPage.linkToSearchingLoop).click();
    }

    @Test
    public void checkAWordIfNonexistingWordInSearch()
    {   WordPage wordPage = new WordPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        final WebElement linkToCookiesWindow = driver.findElement(By.className("abstractButtonAppearance"));
        if(linkToCookiesWindow.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(linkToCookiesWindow));
            linkToCookiesWindow.findElement(By.cssSelector(".buttonText")).click();
        }
        driver.findElement(wordPage.linkToInputWord).sendKeys("dfdgffg");
        driver.findElement(wordPage.linkToSearchingLoop).click();
    }

    @Test
    public void cancelSearchingAWord()
    {   WordPage wordPage = new WordPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        final WebElement linkToCookiesWindow = driver.findElement(By.className("abstractButtonAppearance"));
        if(linkToCookiesWindow.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(linkToCookiesWindow));
            linkToCookiesWindow.findElement(By.cssSelector(".buttonText")).click();
        }
        driver.findElement(wordPage.linkToInputWord).sendKeys("scraping");
        driver.findElement(wordPage.linkToCancelWord).click();
    }

    @Test
    public void goToRegistrationPage()
    {   WordPage wordPage = new WordPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        final WebElement linkToCookiesWindow = driver.findElement(By.className("abstractButtonAppearance"));
        if(linkToCookiesWindow.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(linkToCookiesWindow));
            linkToCookiesWindow.findElement(By.cssSelector(".buttonText")).click();
        }

        driver.findElement(wordPage.drop).click();
        final WebElement dropDownMenu = driver.findElement(By.cssSelector("div.menuItem:nth-child(1)"));
        if(dropDownMenu.isDisplayed())
        {
            WebDriverWait waitForDropDownMenu = new WebDriverWait(driver,10);
            driver.findElement(wordPage.linkToRegistrationPage).click();
        }

        /*For drop downs use Select class
        /WebElement dropDownMenu = driver.findElement(By.className
        Select select = new Select(dropDownMenu);
        select.selectByIndex(1);
        select.selectByVisibleText("Załóż konto");
         */

    }

    @After
    public void driverQuit () {
        if (driver != null) {
            driver.quit();
        }
    }
}
