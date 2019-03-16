@FunctionalTests @DashboardBasics
Feature: Dashboard Basics
  Background: User is Logged In
    Given on a login page
    When the user enters username and password
    Then the user is on the Dashboard


  @AutoDiscoveredAdapterPresent
  Scenario: Auto discovered adapter is present
    Then the user sees the 'Welcome Modal'
    Then the user dismisses the 'Welcome Modal'
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

  @BasicAdapterOperations
  Scenario:  Control button functionality works
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
    And sees the dropdown menu buttons and icons
    Then clicks the 'Force-Stop' button
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
    Then clicks the start button
    Then clicks the information button
    And sees the 'Adapter Information' modal
    And sees the Adapter details
    Then clicks the close button to shut the 'Adapter Information' modal
    And sees the 'Adapter Information' modal has been shut
    Then clicks the information button
    Then clicks the dismiss button to shut the 'Adapter Information' modal
    And sees the 'Adapter Information' modal has been shut