@FunctionalTests @WidgetBasics
Feature: Widget Basics

  @AddWidgets
  Scenario: Changes config and runs widget tests
    Given the user enters username and password
    And the user is on the Dashboard
    Then navigates to the config page
    And reaches the config page
    And sees the Open config button
    Then clicks the 'Open Config' button
    And sees the 'Config Modal' and its options
    Then clicks the 'Saved Project' tile
    And sees the 'Interlok Container Saved Config Projects' modal
    Then selects the 'config-002-generates-dumb-data-with-fs' config
    And sees the 'Active Adapters' modal
    Then selects option config-001-basic-components in 'Adapter Instance' in the 'Active Adapters' modal to apply the config to
    And selects the default 'Variable-Set' in the 'Active Adapters' modal to apply with the config
    And clicks Ok to apply the config