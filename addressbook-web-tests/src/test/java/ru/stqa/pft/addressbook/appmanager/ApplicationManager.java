package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    protected WebDriver wd;

    private SessionHelp sessionHelper;
    private NavigationHelp navigationHelper;
    private GroupHelp groupHelper;
    private ContactHelp contactHelp;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        switch (browser) {
            case BrowserType.FIREFOX:
                wd = new FirefoxDriver();
                break;
            case BrowserType.CHROME:
                wd = new ChromeDriver();
                break;
            case BrowserType.IE:
                wd = new InternetExplorerDriver();
                break;
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        groupHelper = new GroupHelp(wd);
        contactHelp = new ContactHelp(wd);
        navigationHelper = new NavigationHelp(wd);
        sessionHelper = new SessionHelp(wd);
        sessionHelper.login("admin","secret");
    }

    public GroupHelp group() {
        return groupHelper;
    }

    public ContactHelp contact() {
        return contactHelp;
    }

    public NavigationHelp goTo() {
        return navigationHelper;
    }

    public void stop() {
        wd.quit();
    }

    public void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }
}
