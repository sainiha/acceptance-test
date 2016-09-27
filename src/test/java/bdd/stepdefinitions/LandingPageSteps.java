package bdd.stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.util.List;

import static bdd.hooks.SetUp.URL;
import static bdd.hooks.SetUp.driver;
import static bdd.stepdefinitions.CommonSteps.isElementVisible;
import static bdd.stepdefinitions.CommonSteps.waitForElementToAppear;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LandingPageSteps {

    public String xpathForNumberOfRows = "//span[contains(@data-test,'day')]";

    @Given("^I am on weather forecast landing page$")
    public void iAmOnWeatherForecastLandingPage() throws Throwable {
        driver.get(URL);
        waitForElementToAppear(xpathForNumberOfRows);
        assertTrue(isElementVisible(xpathForNumberOfRows));
    }

    @Given("^I am on weather forecast landing page for Glasgow$")
    public void iAmOnWeatherForecastLandingPageForGlasgow() throws Throwable {
        iAmOnWeatherForecastLandingPage();
        assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("Five Day Weather Forecast for"));
        assertEquals(driver.findElement(By.id("city")).getAttribute("value"), "Glasgow");
    }

    @Then("^I should be able to see (\\d) day weather forecast$")
    public void iShouldBeAbleToSeeDayWeatherForecast(int numDays) throws Throwable {
        assertEquals(driver.findElements(By.xpath(xpathForNumberOfRows)).size(), numDays);
    }

    @When("^I select \"([^\"]*)\"$")
    public void iSelect(String forecastDay) throws Throwable {
        driver.findElement(By.xpath("//*[@data-test='" + forecastDay + "']")).click();
        String expandedAreaXpath = "//*[@data-test='" + forecastDay + "']//ancestor::div[@class='summary']/following-sibling::div[@class='details' and contains(@style, 'max-height: 2000px')]";
        waitForElementToAppear(expandedAreaXpath);
        assertTrue(isElementVisible(expandedAreaXpath));
    }


    @Then("^I see the three hourly forecast for the following hours for day \"(\\d)\":$")
    public void iSeeTheThreeHourlyForecastForTheFollowingHoursForDay(int forecastDay, List<String> options) throws Throwable {
        String listXpath = "//span[contains(@data-test,'hour-" + forecastDay + "')]";
        assertTrue(CommonSteps.verifyDropdownValues(options, listXpath));
    }

    @And("^Selecting \"([^\"]*)\" again hides the three hourly forecast$")
    public void selectingAgainHidesTheThreeHourlyForecast(String forecastDay) throws Throwable {
        driver.findElement(By.xpath("//*[@data-test='" + forecastDay + "']")).click();
        //putting a sleep so that we can see that in test otherwise it is very fast
        Thread.sleep(2000);
        String collapsedAreaXpath = "//*[@data-test='" + forecastDay + "']//ancestor::div[@class='summary']/following-sibling::div[@class='details' and contains(@style, 'max-height: 0px')]";
        waitForElementToAppear(collapsedAreaXpath);
        assertTrue(isElementVisible(collapsedAreaXpath));

    }

    @Then("^the day for day \"(\\d)\" is \"([^\"]*)\"$")
    public void theDayForDayIs(int forecastDay, String dayOfWeek) throws Throwable {
        assertEquals(driver.findElement(By.xpath("//span[@data-test='day-" + forecastDay + "']")).getText(), dayOfWeek);
    }

    @And("^the date for day \"(\\d)\" is \"([^\"]*)\"$")
    public void theDateForDayIs(int forecastDay, String dateOfMonth) throws Throwable {
        assertEquals(driver.findElement(By.xpath("//span[@data-test='date-" + forecastDay + "']")).getText(), dateOfMonth);
    }

    @And("^the forecast for day \"(\\d)\" is \"([^\"]*)\"$")
    public void theForecastForDayIs(int forecastDay, String forecast) throws Throwable {
        assertEquals(driver.findElement(By.xpath("//*[@data-test='description-" + forecastDay + "']")).getAttribute("aria-label"), forecast);
    }

    @And("^the maximum temperature for day \"(\\d)\" is \"([^\"]*)\"$")
    public void theMaximumTemperatureForDayIs(int forecastDay, String maxTemp) throws Throwable {
        assertEquals(driver.findElement(By.xpath("//span[@data-test='maximum-" + forecastDay + "']")).getText(), maxTemp);
    }

    @And("^the minimum temperature for day \"(\\d)\" is \"([^\"]*)\"$")
    public void theMinimumTemperatureForDayIs(int forecastDay, String minTemp) throws Throwable {
        assertEquals(driver.findElement(By.xpath("//span[@data-test='minimum-" + forecastDay + "']")).getText(), minTemp);
    }


    @And("^for day \"(\\d)\" the wind speed is \"([^\"]*)\" and direction is \"([^\"]*)\"$")
    public void forDayTheWindSpeedIsAndDirectionIs(int forecastDay, String windSpeed, String windDirection) throws Throwable {
        assertEquals(driver.findElement(By.xpath("//span[@data-test='speed-" + forecastDay + "']")).getText(), windSpeed);
        assertTrue(driver.findElement(By.xpath("//span[@data-test='direction-" + forecastDay + "']/*")).getAttribute("style").contains(windDirection));
    }

    @And("^the aggregate rainfall for day \"(\\d)\" is \"([^\"]*)\"$")
    public void theAggregateRainfallForDayIs(int forecastDay, String aggRain) throws Throwable {
        assertEquals(driver.findElement(By.xpath("//span[@data-test='rainfall-" + forecastDay + "']")).getText(), aggRain);
    }
}