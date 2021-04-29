package package1;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import  org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;


public class SeleniumDemo {

    public WebDriver driver;

   static   Logger  logger = LogManager.getLogger();

        @Before
        public  void setup() {

            logger.info("info log");
// anasayfanın açılması.
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://gittigidiyor.com");
            System.out.println(driver.getTitle());
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void TestLogin(){
 // login butonuna tıklanması.
             WebElement signbtn= driver.findElement(By.cssSelector("#main-header > div:nth-child(3) > div > div > div > div.sc-1nx8ums-0.fQSWwJ > div > div:nth-child(1) > div > div.gekhq4-4.egoSnI"));
             signbtn.click();
             driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
             WebElement signbtn2= driver.findElement(By.cssSelector("a[data-cy='header-login-button']"));
             signbtn2.click();

 // kullanıcı adı girişi.
             WebElement mailbox= driver.findElement(By.id("L-UserNameField"));
             mailbox.click();
             mailbox.sendKeys("testhesabi9595@gmail.com");
  // parola girişi.
             WebElement password = driver.findElement(By.id("L-PasswordField"));
             password.click();
             password.sendKeys("edplxu95");
             driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
             driver.findElement(By.id("gg-login-enter")).click();
             logger.info("Login olundu.");
    }

         @Test
         public void TestSearch() throws InterruptedException {
 // bilgisayar kelimesinin aranması
             WebElement searchBox = driver.findElement(By.name("k"));
             searchBox.click();
             searchBox.sendKeys("Bilgisayar");
             driver.findElement(By.cssSelector("button[data-cy='search-find-button']")).click();
             logger.info("arama yapıldı");

//Arama sonuç sayfalarında 2. sayfanın açılması

          WebElement page = driver.findElement(By.xpath(".//*[@class='next-link']/a"));
             ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",page);
             //ElementClickInterceptedException hatası aldığım için üstteki kodu yazdım.
             page.click();
//2.sayfanın açıldığının kontrolü
             Assert.assertEquals("Bilgisayar - GittiGidiyor - 2/100",driver.getTitle());
             logger.info("2. sayfanın açıldığı kontrol edildi. ");

 //rastgele bir ürün seçilmesi

             driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div[3]/div[2]/ul/li[1]/a")).click();

             logger.info("ürün seçildi.");

//ürünün sepete eklenmesi

           WebElement basket = driver.findElement(By.id("add-to-basket"));
             ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",basket);
             //ElementClickInterceptedException hatası aldığım için üstteki kodu yazdım.
             basket.click();

             logger.info("ürün sepete eklendi");

             try {
                 Thread.sleep(2000);
             } catch(InterruptedException e) {
                 System.out.println("got interrupted!");
             }
//ürün fiyat bilgisi alınır
             String price=driver.findElement(By.cssSelector("div[id='sp-price-discountPrice']")).getText();

             //String price = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div[1]/div[2]/div[2]/div[1]/div/div[1]/div[2]/div/div/div[4]")).getText();
             logger.info("fiyat bilgisi alındı");

//sepete tıklanır
             WebElement basketBtn = driver.findElement(By.className("basket-title"));
             basketBtn.click();

//sepet fiyat bilgisi alınır

             String basketprice = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div/div[1]/div/div[5]/div[2]/div[3]/p")).getText();
             logger.info("sepet fiyat bilgisi alındı");
//ürün sayfasındaki fiyat ile sepet fiyatı karşılaştırılır.
             Assert.assertEquals(price,basketprice);
             logger.info("Ürün sayfasındaki fiyat ile sepetteki ürün fiyatının doğruluğu karşılaştırıldı.");

 //ürün sayısı arttırma
 //Aşağıdaki kodda UnexpectedTagNameException: Element should have been "select" but was "input"  hatası alıyorum.
//             WebElement plus = driver.findElement(By.className("amount"));
//             Select oSelect = new Select(plus);
//             try {
//                 Thread.sleep(2000);
//             } catch(InterruptedException e) {
//                 System.out.println("got interrupted!");
//             }
//             oSelect.selectByVisibleText("2");


//sepet boşatıldı
             driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/a/i")).click();
             logger.info("Sepet boşaltıldı ");

         }
}
