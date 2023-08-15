package com.freecrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public static Properties prop;
    public static WebDriver driver;
    public Base(){
        PageFactory.initElements(driver,this);
    }

    public void loadConfig(){
        try{
            prop = new Properties();
            FileInputStream ip =  new FileInputStream(System.getProperty(("user.dir")+"/Config/config.properties"));
            prop.load(ip);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void driverInit(String mode){
        String browser = prop.getProperty("browser");
        switch(browser.toLowerCase()){
            case "Chrome":
                if(mode=="headless"){
                    ChromeOptions co = new ChromeOptions();
                    co.addArguments("--headless");
                    co.addArguments("--disable-gpu");
                    driver = new ChromeDriver(co);
                    break;
                }else{
                    ChromeOptions co = new ChromeOptions();
                    co.addArguments("--disable-gpu");
                    co.addArguments("--window-size=1920x1080");
                    driver = new ChromeDriver(co);
                    break;
                }
            case "Edge":
                if(mode == "headless"){
                    EdgeOptions op = new EdgeOptions();
                    op.addArguments("--headless");
                    op.addArguments("--disable-gpu");
                    driver = new EdgeDriver(op);
                    break;
                }else{
                    EdgeOptions op = new EdgeOptions();
                    op.addArguments("--disable-gpu");
                    op.addArguments("--window-size=1920x1080");
                    driver = new EdgeDriver(op);
                    break;
                }

            case "Firefox":
                if(mode=="headless"){
                    FirefoxOptions fo = new FirefoxOptions();
                    fo.addArguments("--headless");
                    fo.addArguments("--disable-gpu");
                    driver = new FirefoxDriver(fo);
                    break;
                }else{
                    FirefoxOptions fo = new FirefoxOptions();
                    fo.addArguments("--disable-gpu");
                    fo.addArguments("--window-size=1920x1080");
                    driver = new FirefoxDriver(fo);
                    break;
                }
        }
        driver.manage().deleteAllCookies();;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get(System.getProperty("url"));

    }
    @BeforeTest
    public void setup(String mode){
        driverInit(mode);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
