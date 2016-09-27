I have used Java Selenium Webdriver with cucumber. Build tool is Gradle. There is no need to install gradle I have used gradle wrapper. Desired OS to run the tests is Linux and desired browser is chrome. But I have added support for firefox & chrome. I am not sure your version is supported by the version of selenium webdriver I have used. Please check the compatibility of firefox versions with selenium 2.53.0

To run test on the application hosted on heroku, please use:

./gradlew clean cucumber

To Run tests on a locally running version ./gradlew clean cucumber -Durl=localhost:3000

To run test on firefox

./gradlew clean cucumber -Dbrowser=firefox

Acceptance test feature file can be found at: /src/test/resources/features

The filename is acceptancetest.feature

Only the test with @online_test are automated. Rest of the scenarios are written to show the capability for writing BDDs.

I have also added a defects.txt file which contains the list of defects found while testing the hosted app.
