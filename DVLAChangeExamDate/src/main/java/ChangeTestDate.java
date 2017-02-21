import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pkumar on 21/02/2017.
 */
public class ChangeTestDate {

    @Test
    public void changeDate() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdrivers/chromedriver/Chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.gov.uk/change-driving-test");
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.linkText("Start now")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("driving-licence-number")).sendKeys("ADDA858DFDS99YM22"); // //AADDA858032S99YM22
        Thread.sleep(2000);
        driver.findElement(By.id("application-reference-number")).sendKeys("56134490"); // //47134490
        Thread.sleep(2000);
        driver.findElement(By.id("booking-login")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@id='date-time-change']")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[contains(text(), 'Show earliest available date')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='driving-licence-submit']")).click();
        Thread.sleep(5000);

        // To handle situation where it displays message
        // You’ve reached the maximum number of searches per hour. Please try again later.

        if (driver.findElements(By.xpath("//h1[contains(text(), 'Search limit reached')]")).size() > 0){
            driver.findElement(By.xpath("//a[text()='Ok']")).click();
        } else if (driver.findElements(By.xpath("//p[contains(text(),'Please try again later')]")).size() > 0){
            System.out.println("text displayed: " + driver.findElement(By.xpath("//p[contains(text(),'Please try again later')]")).getText());
            driver.findElement(By.xpath("//a[text()='Exit']")).click();
            driver.quit();
        } else {

            String displayedMonth = driver.findElement(By.xpath("//span[@class='BookingCalendar-currentMonth']")).getText();

            if (displayedMonth.equalsIgnoreCase("March")) {
                driver.findElement(By.xpath("//a[@id='return-original-booking-link']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[contains(text(), 'Sign out')]")).click();
                driver.quit();

                Assert.assertTrue(false, "AVAILABLE..AVAILABLE..AVAILABLE.." + displayedMonth);

            } else {
                driver.findElement(By.xpath("//a[@id='return-original-booking-link']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[contains(text(), 'Sign out')]")).click();
                driver.quit();

                System.out.println("displayedMonth = " + displayedMonth);
            }
        }
    }
}
