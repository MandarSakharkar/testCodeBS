Feature: BrowserStack Demo

  @price
  Scenario: print sorted mobile list as per Price and expert score
    Given I am on the webPage 'https://www.mysmartprice.com'
    When I navigate to compare mobiles page
    When I entered mobile name as "Samsung Galaxy S21" in search field 0
    When I select mobile as "Samsung Galaxy S21"
    When I entered mobile name as "vivo X60 Pro" in search field 1
    When I select mobile as "vivo X60 Pro"
    When I entered mobile name as "OnePlus 9" in search field 2
    When I select mobile as "OnePlus 9"
    When I entered mobile name as "Apple iPhone 12" in search field 3
    When I select mobile as "Apple iPhone 12"
    When I print the search results as per price in ascending order
    When I print the search results as per expert score in descending order