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
    Given on a login page
    When the user enters username and password
    Then the user reaches Dashboard

  @DashboardBasics @AutoDiscoveredAdapterPresent
  Scenario: Auto discovered adapter is present
    Given on a login page
    When the user enters username and password
    Then sees the auto discovered adapter
    And sees the adapters unique id
    And sees the JMX URL address
    And sees the adapter in 'Started' state
    And sees a check icon
    And sees 1 channels
