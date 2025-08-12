@echo off
allure generate allure-results -o test_outputs/reports/allure-report --clean
allure open "%CD%\test_outputs\reports\allure-report"

pause



::command run in terminal to generate report and open it
::   .\generate-and-open-report.bat



