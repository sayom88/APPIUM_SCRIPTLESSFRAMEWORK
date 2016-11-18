Feature: To Find A Tesco Store using Postcode
  Description: This feature will perform the Tesco Store Locator functionality to search a Tesco Store via Postcode

  Scenario: Successful Tesco Store Search via Postcode
    Given User launches Tesco Mobile App on a Mobile Device
    When User taps Action-Bar-Navigator in the app
    And User select Store locator menu to go to the Store locator page
    And User enters Postcode in the Find stores by postcode or town textbox
    And User taps Find stores
    Then All Tesco Stores will be displayed successfully