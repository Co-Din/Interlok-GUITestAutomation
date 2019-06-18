@FunctionalTests @WidgetBasics
Feature: Widget Basics
  Background: User is Logged In
    Given on a splash page
    Then clicks the 'Web UI' view button
    When the user enters username and password
    Then the user is on the Dashboard

  @AddWidgets
  Scenario: Adds all the widgets
    Given the user clicks the 'Widgets' button
    When the user lands on the 'Widgets' page
    Then the user sees the 'Widgets Modal'
    And sees the Interlok Widgets label
    Then selects the Local Adapter - config-001-basic-components adapter in the 'Widgets Modal'
    And sees the available widgets
    Then selects the 'Summary Details' tile
    Then bulk adds all the widgets bar 'Consumer Messages Remaining'
    And sees the 21 widgets added to the adapter
    Then clicks the 'Remove Widget' button
    And sees the 'Remove this Widget' modal
    Then clicks the dismiss button on the 'Remove Widget' modal
    Then clicks the 'Remove Widget' button
    And sees the 'Remove this Widget' modal
    Then clicks the Cancel button on the 'Remove Widget' modal
    Then clicks the 'Remove Widget' button
    And sees the 'Remove this Widget' modal
    Then clicks the OK button on the 'Remove Widget' modal
    And sees only 20 widgets
    Then then removes all 20 widgets remaining widgets
    Then selects the Local Adapter - config-001-basic-components adapter in the 'Widgets Modal'
    And sees the available widgets



  @FailingMessages
  Scenario: Changes to config with failing messages
    Then the user navigates to the config page
    When the user reaches the config page
    Then the user sees the Open config button
    Then clicks the 'Open Config' button
    And sees the 'Config Modal' and its options
    Then clicks the 'Saved Project' tile
    And sees the 'Interlok Container Saved Config Projects' modal
    Then selects the 'config-002-generates-dumb-data-with-fs' config
    And sees the 'Active Adapters' modal
    Then selects option Local Adapter - config-001-basic-components in 'Adapter Instance' in the 'Active Adapters' modal to apply the config to
    And selects the default 'Variable-Set' in the 'Active Adapters' modal to apply with the config
    And clicks Ok to apply the config
