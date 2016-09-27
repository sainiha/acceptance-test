package bdd.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static bdd.hooks.SetUp.driver;

public class CommonSteps {

    public static boolean isElementVisible(String xpath) {

        return (driver.findElements(By.xpath(xpath)).size() > 0);

    }

    public static void waitForElementToAppear(String xpath) throws InterruptedException {
        int count = 0;
        while (count <= 10 && !isElementVisible(xpath)) {
            count++;
            Thread.sleep(500);
        }
    }

    public static boolean verifyDropdownValues(List<String> options, String xpath) {
        int count = 0;
        boolean isMatched = false;

        List<WebElement> webOptions = driver.findElements(By.xpath(xpath));
        for (WebElement we : webOptions) {
            for (String textExpected : options) {
                if (we.getText().equals(textExpected)) {
                    count++;
                }
            }
        }

        if ((count == options.size()) && (count == webOptions.size())) {
            isMatched = true;
        }

        return isMatched;
    }
}
