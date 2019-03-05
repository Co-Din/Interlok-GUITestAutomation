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
    Then the user is on the Dashboard

  @DashboardBasics @AutoDiscoveredAdapterPresent
  Scenario: Auto discovered adapter is present
    Given on a login page
    When the user enters username and password
    Then sees the auto discovered adapter
    And sees the adapters unique id
    And sees the JMX URL address
    And sees the adapter in 'Started' state
    And sees a check icon
    And sees 4 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Up Time section
    And sees the Last Started section
    And sees UI version Interlok UI  3.8.3-RELEASE
    And sees 0 failed messages
    And sees 0 in-flight messages
    And sees the table mode button
    And sees the add adapter button
    And sees the 'Refresh' button
    And sees the control bar
    And sees the control-bar buttons
    And sees the error bar is blank
    And sees the Interlok Dashboard label

  @DashboardBasics @BasicAdapterOperations
  Scenario:  Control button functionality works
    Given the user enters username and password
    Then the user is on the Dashboard
    And sees 4 started channels
    And sees the control bar
    And sees the control-bar buttons
    Then clicks the stop button
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
    Then clicks the start button
    And sees 4 started channels
    And sees the Up Time section
    And sees the Last Started section
    Then clicks the pause button
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
    Then clicks the start button
    And sees 4 started channels
    And sees the Up Time section
    And sees the Last Started section
    Then clicks the stop button
    Then clicks the pause button
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
    Then clicks the start button
    Then clicks the control bar drop down menu
    Then clicks the 'Force-Stop' button
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
