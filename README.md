# 🧪 Automation Exercise Website Automation Project

## 📌 Overview
This project automates the testing of the **Automation Exercise** website using **Java**, **Selenium WebDriver**, and **TestNG**.  
It covers functional, UI, and regression test cases, and integrates with **Jenkins** for CI/CD and **Allure Reports** for test reporting.  
The main goal is to ensure the reliability and quality of the website through automated test execution.

---

## 🛠️ Tech Stack

- **Programming Language:** Java
- **Automation Framework:** Selenium WebDriver
- **Test Framework:** TestNG
- **Build Tool:** Maven
- **Reporting:** Allure Reports, HTML Reports
- **CI/CD:** Jenkins

---

## 📂 Project Structure

Automation-Exercise-Website-Automation-Project/
│
├── .gitignore                        # Git ignore file to exclude unnecessary files
├── generate-and-open-report.bat      # Script to generate and open Allure report
├── jenkins.war                       # Jenkins server WAR file
├── pom.xml                           # Maven configuration file
│
├── .allure                           # Allure report temporary files
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── pages                 # Page Object Model classes
│   │   │   │   ├── P00_HomePage.java
│   │   │   │   ├── P01_LoginSignupPage.java
│   │   │   │   ├── P02_EnterAccountInformationPage.java
│   │   │   │   ├── P03_LoggedHomePage.java
│   │   │   │   ├── P04_AccountCreated.java
│   │   │   │   ├── P05_AccountDeleted.java
│   │   │   │   ├── P06_ContactUsPage.java
│   │   │   │   ├── P07_TestCasePage.java
│   │   │   │   ├── P08_ProductsPage.java
│   │   │   │   ├── P09_ProductDetailsPage.java
│   │   │   │   ├── P10_CartPage.java
│   │   │   │   └── P12_PaymentPage.java
│   │   │   └── utilities             # Utility and helper classes
│   │   │       ├── BrowserManager.java
│   │   │       ├── DataUtils.java
│   │   │       ├── LogUtility.java
│   │   │       ├── Utility.java
│   │   │       └── VerifyDownload.java
│   │   └── resources
│   │       ├── allure.properties
│   │       └── log4j2.properties
│   │
│   └── test
│       ├── java
│       │   ├── Listeners             # TestNG listeners
│       │   │   ├── IInvokedMethod.java
│       │   │   └── ITest.java
│       │   ├── TC01_RegisterUser.java
│       │   ├── TC02_ValidLogin.java
│       │   ├── TC03_InvalidLogin.java
│       │   ├── TC04_LogoutUser.java
│       │   ├── TC05_RegisterUserWithExistingEmail.java
│       │   ├── TC06_ContactUsForm.java
│       │   ├── TC07_VerifyTestCasesPage.java
│       │   ├── TC08_VerifyAllProductsAndProductDetailPage.java
│       │   ├── TC09_SearchProduct.java
│       │   ├── TC10_AddProductsInCart.java
│       │   ├── TC11_VerifyProductQuantityInCart.java
│       │   ├── TC12_RegisterWhileCheckout.java
│       │   ├── TC13_RegisterBeforeCheckout.java
│       │   ├── TC14_LoginBeforeCheckout.java
│       │   ├── TC15_RemoveProductsFromCart.java
│       │   ├── TC16_ViewCategoryProducts.java
│       │   ├── TC17_ViewCartBrandProducts.java
│       │   ├── TC18_AddToCartFromRecommendedItems.java
│       │   ├── TC19_DownloadInvoiceAfterPurchaseOrder.java
│       │   ├── TC20_VerifyScrollingUsingArrowButton.java
│       │   └── TestBasic.java
│       │
│       └── resources
│           └── testData              # Test data files
│               ├── AccountDetails.json
│               ├── environment.properties
│               ├── existingUser.json
│               ├── MadameBrandProducts.json
│               ├── paymentDetails.json
│               ├── poloBrandItem.json
│               ├── searchKeyWord.json
│               └── Uploads
│                   └── test_image.png
│
├── target                            # Maven build output (auto-generated)
│
├── test_outputs
│   ├── logs                          # Log files generated during execution
│   ├── reports
│   │   └── allure-report             # Allure HTML reports
│   └── screenshots                   # Screenshots captured during tests
│
└── Test_Runner
    ├── E2E.xml                        # End-to-End TestNG suite
    └── testing.xml                    # Main TestNG suite
--------------------------------
## ✅ Features

- **Page Object Model (POM)** design pattern for maintainable and scalable tests.
- **20 Automated Test Cases** covering:
  - Registration
  - Login
  - Add to Cart
  - Checkout
  - Category Browsing
  - Contact Us form
  - Product Search
  - Others (Functional, UI, Regression)
- **Cross-browser support**.
- **Parameterized test execution** via Maven.
- **Detailed reporting** using Allure and HTML reports.

---
