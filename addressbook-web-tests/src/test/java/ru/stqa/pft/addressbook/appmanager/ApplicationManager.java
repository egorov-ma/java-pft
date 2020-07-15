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
        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else  if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        groupHelper = new GroupHelp(wd);
        contactHelp = new ContactHelp(wd);
        navigationHelper = new NavigationHelp(wd);
        sessionHelper = new SessionHelp(wd);
        sessionHelper.login("admin","secret");
    }

    public void logout() {
      wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelp getGroupHelper() {
        return groupHelper;
    }

    public ContactHelp getContactHelp() {
        return contactHelp;
    }

    public NavigationHelp getNavigationHelper() {
        return navigationHelper;
    }
}
