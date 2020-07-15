package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelp extends BaseHelp {

    public NavigationHelp(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void gotoHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

}
