@FunctionalTests @LoginTests
Feature: Login Tests
  Verify if user is able to Login in to the site

  @FailedLogin
  Scenario: Attempt login with false credentials
    Given on a splash page
    Then clicks the 'Web UI' view button
    When the user enters an incorrect username or password
    Then the user reaches the failed login page

  @SuccessfulLogin
  Scenario: Login as an authenticated user
    Given on a splash page
    Then clicks the 'Web UI' view button
    When the user enters username and password
    Then the user is on the Dashboard
