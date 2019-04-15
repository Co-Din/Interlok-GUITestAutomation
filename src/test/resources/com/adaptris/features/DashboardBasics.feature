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
    And sees the adapters unique id:  Local Adapter - config-001-basic-components
    And sees the JMX URL address: service:jmx:jmxmp://localhost:5555
    And sees the adapter in 'Started' state
    And sees a check icon
    And sees a low Heap Memory
    And sees 4 started channels
    And sees the total number of 4 adapter channels expected
    And sees 0 failed messages
    And sees 0 in-flight messages
    And sees the 'Up Time' section
    And sees the 'Last Started' section
    And sees UI version Interlok UI  3.8.3-RELEASE
    And sees the table mode button
    And sees the add adapter button
    And sees the 'Refresh' button
    And sees the control-bar
    And sees the control-bar buttons
    And sees the error bar is blank
    And sees the Interlok Dashboard label

  @BasicAdapterOperations
  Scenario:  Control button functionality works
    And sees 4 started channels
    And sees the control-bar
    And sees the control-bar buttons
    Then clicks the stop button
    And sees a closed icon
    And sees the adapter in 'Closed' state
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
    Then clicks the start button
    And sees 4 started channels
    And sees the 'Up Time' section
    And sees the 'Last Started' section
    Then clicks the pause button
    And sees a stop icon
    And sees the adapter in 'Stopped' state
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
    Then clicks the start button
    And sees 4 started channels
    And sees the 'Up Time' section
    And sees the 'Last Started' section
    Then clicks the stop button
    Then clicks the pause button
    And sees the adapter in 'Closed' state
    And sees a closed icon
    And sees 0 started channels
    And sees the total number of 4 adapter channels expected
    And sees the Down Time section
    And sees the Last Stopped section
    Then clicks the start button
    Then clicks the control-bar drop down menu
    And sees the dropdown menu buttons and icons
    Then clicks the 'Force-Stop' button
    And sees the 'Interrupted Exception' alert box
    Then dismisses the 'Interrupted Exception' alert box
    And sees the adapter in 'Closed' state
    And sees a closed icon
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
    Then clicks the control-bar drop down menu
    Then clicks the 'Thread Dump' button
    And sees the 'Thread Dump' Modal
    Then clicks the dismiss button to shut the 'Thread Dump' modal
    And sees the 'Thread Dump' modal has been shut
    Then clicks the control-bar drop down menu
    Then clicks the 'Thread Dump' button
    Then clicks the close button to shut the 'Thread Dump' modal
    And sees the 'Thread Dump' modal has been shut
    Then clicks the control-bar 'Config' button
    Then expands the config xml
    And sees the 'Config' modal
    Then clicks the dismiss button to dismiss the control-bar'Config' modal
    And sees the control-bar 'Config' modal has been shut
    Then clicks the control-bar 'Config' button
    And sees the 'config unique-id' tag is expanded
    Then clicks the arrow to collapse the 'config unique-id' tag
    And sees the 'config unique-id' has been collapsed
    Then clicks the close button to shut the control-bar'Config' modal
    And sees the control-bar 'Config' modal has been shut
    Then clicks the control-bar drop down menu
    Then clicks the 'Run Garbage Collector' button
    And sees the 'Garbage Collector' alert box
    Then clicks the dismiss button to shut the 'Garbage Collector' alert box
    And sees the 'Garbage Collector' alert box has dismissed
    Then checks the 'Show Channels box'
    And sees all four channels
    Then unchecks the 'Show Channels box'
    And sees all four channels have been hidden
