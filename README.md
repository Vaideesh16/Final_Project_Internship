# Booking.com Test Automation Project

This project is a Java-based test automation framework for Booking.com workflows. It uses Selenium WebDriver for browser automation, Cucumber for BDD feature files, TestNG as the test runner, Maven for dependency management, Log4j for logging, Apache POI for Excel test data support, and ExtentReports for HTML reporting.

## Tech Stack

- Java 21
- Maven
- Selenium WebDriver
- Cucumber
- TestNG
- ExtentReports
- Log4j2
- Apache POI

## Project Structure

```text
Final_Project/
+-- features/
|   +-- cruise.feature
|   +-- hotelSearch.feature
+-- logs/
|   +-- app.log
+-- reports/
|   +-- ExtentReport.html
+-- screenshots/
+-- src/test/java/
|   +-- factory/
|   |   +-- DriverManager.java
|   +-- pagedObjects/
|   |   +-- CruisePage.java
|   |   +-- HotelSearchPage.java
|   +-- stepDefinitions/
|   |   +-- CruiseSteps.java
|   |   +-- Hooks.java
|   |   +-- HotelSearchSteps.java
|   +-- testRunner/
|   |   +-- TestRunner.java
|   +-- utilities/
|       +-- ConfigReader.java
|       +-- ExcelUtils.java
|       +-- ExtentReportManager.java
|       +-- RetryAnalyzer.java
|       +-- Screenshot.java
+-- src/test/resources/
|   +-- config.properties
|   +-- log4j2.xml
+-- target/
+-- testdata/
|   +-- HotelDetails.xlsx
+-- pom.xml
+-- testng.xml
```

## Test Coverage

The framework currently includes BDD scenarios for:

- Hotel search on Booking.com
- Cruise search on Booking.com

Feature files are available in the `features/` directory.

## Prerequisites

Make sure the following are installed and available on your machine:

- Java 21
- Maven
- Chrome, Edge, or Firefox browser

Selenium Manager is included with Selenium 4, so browser drivers are handled automatically in most environments.

## Configuration

Update test settings in:

```text
src/test/resources/config.properties
```

Current configuration:

```properties
browser=chrome
app.url=https://www.booking.com
```

Supported browser values:

- `chrome`
- `edge`
- `firefox`

## How to Run Tests

From the project root, run:

```bash
mvn test
```

This executes the TestNG suite defined in:

```text
testng.xml
```

The TestNG suite runs the Cucumber test runner:

```text
src/test/java/testRunner/TestRunner.java
```

## Reports and Output

After execution, the framework generates test output in these locations:

- Cucumber HTML report: `target/cucumber-html-report.html`
- Cucumber JSON report: `target/cucumber-report.json`
- Extent report: `reports/ExtentReport.html`
- Additional Extent report: `target/extentReport/report.html`
- Screenshots: `screenshots/`
- Application logs: `logs/app.log`

## Test Data

Excel-based test data is stored in:

```text
testdata/HotelDetails.xlsx
```

Excel utilities are available in:

```text
src/test/java/utilities/ExcelUtils.java
```

## Framework Design

- `features/` contains Cucumber feature files written in Gherkin.
- `stepDefinitions/` maps Gherkin steps to Java test logic.
- `pagedObjects/` contains page object classes for Booking.com pages.
- `factory/DriverManager.java` manages WebDriver initialization and cleanup.
- `utilities/` contains reusable helpers for config, screenshots, retries, reports, and Excel data.
- `Hooks.java` handles setup, teardown, screenshots, and ExtentReports integration for each scenario.

## Notes

- Tests open the configured browser, navigate to Booking.com, execute the scenario steps, capture screenshots, and close the browser after each scenario.
- If Booking.com shows popups, location prompts, CAPTCHA, or dynamic layout changes, test execution may require updates to the relevant page objects or step definitions.
- Report and screenshot folders may be regenerated during test runs.
