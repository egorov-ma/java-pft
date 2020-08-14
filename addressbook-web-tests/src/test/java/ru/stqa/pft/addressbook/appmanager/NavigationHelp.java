package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelp extends BaseHelp {

    public NavigationHelp(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void contactPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
                && isElementPresent(By.name("submit"))) {
            return;
        }
        wd.findElement(By.linkText("add new")).click();
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        wd.findElement(By.linkText("home")).click();
    }

}
