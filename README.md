# Daraz UI Automation Testing Framework

> **Software Quality Engineering Project**

A Selenium-based UI test automation framework for the [Daraz](https://www.daraz.pk) e-commerce platform, built using Java, Cucumber BDD, TestNG, and Maven — with Allure reporting integration.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Features Tested](#features-tested)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Configuration](#configuration)

---

## Project Overview

This framework automates end-to-end UI testing of the Daraz e-commerce website. It follows the **Page Object Model (POM)** design pattern and uses **Cucumber BDD** (Gherkin syntax) to write human-readable test scenarios. Test data is driven from Excel files using Apache POI.

---

## Tech Stack

| Tool / Library         | Version   | Purpose                          |
|------------------------|-----------|----------------------------------|
| Java                   | 17        | Programming language             |
| Selenium WebDriver     | 4.25.0    | Browser automation               |
| Cucumber               | 7.20.1    | BDD framework (Gherkin)          |
| TestNG                 | 7.10.2    | Test execution & assertions      |
| JUnit                  | 4.13.2    | Cucumber runner support          |
| WebDriverManager       | 5.9.3     | Automatic ChromeDriver management|
| Apache POI             | 5.3.0     | Excel-based test data reading    |
| Allure                 | 2.29.0    | Test reporting                   |
| Log4j2                 | 2.23.1    | Logging                          |
| Maven                  | 3.x       | Build & dependency management    |

---

## Project Structure

```
uitest-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pageObjects/          # Page Object classes
│   │       │   ├── DarazLoginPage.java
│   │       │   ├── DarazSearchPage.java
│   │       │   ├── DarazProductPage.java
│   │       │   ├── DarazCartPage.java
│   │       │   ├── DarazCheckoutPage.java
│   │       │   ├── DarazFormPage.java
│   │       │   └── LanguagePage.java
│   │       ├── reporting/
│   │       │   └── AllureReport.java
│   │       └── utils/
│   │           ├── BaseClass.java       # WebDriver setup/teardown
│   │           ├── DriverManager.java   # Driver instance management
│   │           ├── ConfigReader.java    # Read config properties
│   │           ├── ExcelReader.java     # Read test data from Excel
│   │           └── ExcelUtil.java
│   └── test/
│       ├── java/
│       │   ├── runner/
│       │   │   └── TestRunner.java      # Cucumber test runner
│       │   ├── stepDef/                 # Cucumber step definitions
│       │   │   ├── DarazLoginStepDef.java
│       │   │   ├── SearchStepDefinitions.java
│       │   │   ├── ProductStepDef.java
│       │   │   ├── CheckoutStepDef.java
│       │   │   ├── FormStepDef.java
│       │   │   ├── LanguageStepDefinitions.java
│       │   │   ├── UiValidationStepDef.java
│       │   │   └── Hooks.java           # Before/After hooks
│       │   └── Databases/
│       │       └── DBConnection.java
│       └── resources/
│           ├── Features/                # Gherkin feature files
│           │   ├── loginDaraz.feature
│           │   ├── search.feature
│           │   ├── product.feature
│           │   ├── checkout.feature
│           │   ├── form.feature
│           │   ├── language.feature
│           │   └── ui_validation.feature
│           ├── drivers/
│           │   └── chromedriver.exe
│           └── testdata/
│               └── testData.xlsx        # Excel-driven test data
├── allure-results/                      # Allure raw results
├── target/                              # Maven build output & reports
├── pom.xml
└── README.md
```

---

## Features Tested

### 1. Login (`loginDaraz.feature`)
- Successful login with valid credentials
- Failed login with invalid credentials
- Failed login with empty fields
- Failed login with missing username
- Failed login with missing password

### 2. Search (`search.feature`)
- Search for a product using Excel-driven test data

### 3. Product (`product.feature`)
- Browse and interact with product listings

### 4. Checkout (`checkout.feature`)
- Proceed to checkout from cart
- Fill in shipping information
- Enter invalid payment data (error handling)
- Successful order placement with Cash on Delivery (simulation)

### 5. Form (`form.feature`)
- Form field validation and submission

### 6. Language (`language.feature`)
- Language selection and UI language switching

### 7. UI Validation (`ui_validation.feature`)
- Visual/UI element validation across pages

---

## Prerequisites

Before running the project, make sure you have the following installed:

- **Java JDK 17** — [Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **Maven 3.x** — [Download](https://maven.apache.org/download.cgi)
- **Google Chrome** (latest version)
- **Eclipse IDE** (or IntelliJ IDEA) with Maven plugin
- **Git**

---

## Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/AhmadAbdullah75/UI_Automation-Testing.git
cd UI_Automation-Testing
```

### 2. Import into Eclipse

1. Open Eclipse → **File → Import**
2. Select **General → Existing Projects into Workspace**
3. Browse to the cloned folder and click **Finish**
4. Eclipse will auto-resolve Maven dependencies (may take a few minutes)

### 3. Install dependencies via Maven

```bash
mvn clean install -DskipTests
```

---

## Running Tests

### Run all tests

```bash
mvn test
```

### Run with Maven and generate Allure results

```bash
mvn clean test
```

### Run a specific tag (e.g. Checkout tests only)

```bash
mvn test -Dcucumber.filter.tags="@Checkout"
```

### Run from Eclipse

Right-click `TestRunner.java` → **Run As → JUnit Test**

---

## Test Reports

### Allure Report (recommended)

After running tests, generate the Allure HTML report:

```bash
mvn allure:report
```

Then open:
```
target/site/allure-maven-plugin/index.html
```

Or serve it live:
```bash
mvn allure:serve
```

### Surefire Report

Available at:
```
target/surefire-reports/index.html
```

### Cucumber HTML Report

Available at:
```
target/HtmlReports/report.html
```

---

## Configuration

Test data is read from:
```
src/test/resources/testdata/testData.xlsx
```

The ChromeDriver is bundled at:
```
src/test/resources/drivers/chromedriver.exe
```

> **Note:** WebDriverManager is also configured as a dependency, so ChromeDriver can be managed automatically if the bundled one is outdated.

---

## Author

- **Ahmad Abdullah**

---

## Notes

- Tests are written against [daraz.pk](https://www.daraz.pk)
- Credentials used in feature files are for testing purposes only
- The `target/` and `allure-results/` directories are auto-generated and do not need to be committed (add to `.gitignore` if needed)
