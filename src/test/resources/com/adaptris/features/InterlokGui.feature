@FunctionalTest
Feature: InterlokGuiTest
  Verify if user is able to Login in to the site
  @FailedLogin
  Scenario: Attempt login with false credentials
    Given on a login page
    When the user enters an incorrect username or password
    Then the user reaches the failed login page

  @SuccessfulLogin
  Scenario: Login as an authenticated user
    Given a user is on login page
    When the user enters username and password
    Then the user reaches Dashboard