Feature: All the acceptance test required can be found here

  @online_test
  Scenario: Page should load, just to test page is loaded
    Given I am on weather forecast landing page

  Scenario Outline: Enter city name and get 5 day forecast (not able to find the ability in hosted app)
    Given I am on weather forecast landing page
    When I type the "<cityName>" in the search box
    Then I get the 5 day forecast for that city

    Examples:
      | cityName  |
      | aberdeen  |
      | dundee    |
      | edinburgh |
      | glasgow   |

  Scenario: Edge case, incorrect city name
    Given I am on weather forecast landing page
    When I type the "CityNotExist" in the search box
    Then I will get a message saying that "city does not exist"

  Scenario: Edge case, weather service is down
    Given I am on weather forecast landing page
    When The open weather map service is down
    And I type the "Edinburgh" in the search box
    Then I will get a message saying that "service is down"

  @online_test
  Scenario: User should be able to see 5 day weather forecast for Glasgow
    Given I am on weather forecast landing page for Glasgow
    Then I should be able to see 5 day weather forecast

  @online_test
  Scenario: Select day, get 3 hourly forecast, selecting day again hides it. Selecting next day will show complete day's data
    Given I am on weather forecast landing page for Glasgow
    When I select "day-2"
    Then I see the three hourly forecast for the following hours for day "2":
      | 0100 |
      | 0400 |
      | 0700 |
      | 1000 |
      | 1300 |
      | 1600 |
      | 1900 |
      | 2200 |
    And Selecting "day-2" again hides the three hourly forecast

  @online_test
  Scenario: Select current day will show partial data
    Given I am on weather forecast landing page for Glasgow
    When I select "day-1"
    Then I see the three hourly forecast for the following hours for day "1":
      | 1300 |
      | 1600 |
      | 1900 |
      | 2200 |

  @online_test
  Scenario: I should be able to see the summary of current day's forecast which is the current condition
    Given I am on weather forecast landing page for Glasgow
    Then the day for day "1" is "Tue"
    And the date for day "1" is "20"
    And the forecast for day "1" is "Rain"
    And the maximum temperature for day "1" is "16°"
    And the minimum temperature for day "1" is "7°"
    And for day "1" the wind speed is "3kph" and direction is "207deg"
    And the aggregate rainfall for day "1" is "1mm"

  @online_test
  Scenario: I should be able to see the summary of upcoming day's forecast which is the most dominant condition
    Given I am on weather forecast landing page for Glasgow
    Then the day for day "4" is "Fri"
    And the date for day "4" is "23"
    And the forecast for day "4" is "Clouds"
    And the maximum temperature for day "4" is "13°"
    And the minimum temperature for day "4" is "9°"
    And for day "4" the wind speed is "6kph" and direction is "237deg"
    And the aggregate rainfall for day "4" is "4mm"