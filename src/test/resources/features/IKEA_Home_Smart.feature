@IKEA_Home_Smart
Feature: IKEA Home Smart Application

  @TC01_IKEASmartApp
  Scenario Outline: Connect to IKEA Home Smart Hub
    Given User Launch the IKEA Home Smart application
    When User getting started by reading the product glance
    Then User choose a Country "<Country>"
    Then User read and accept privacy statement
    Then User accept the consent for analytics
    Then User Connects to the Hub
    Examples:
      | Country |
      | Sweden  |

  @TC02_IKEASmartApp
  Scenario Outline: User view the privacy document
    Given User Launch the IKEA Home Smart application
    When User getting started by reading the product glance
    Then User choose a Country "<Country>"
    Then User click on privacy statement link
    And User should view the privacy statement document
    Examples:
      | Country |
      | Sweden  |



