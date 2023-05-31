Feature: Add Places into Google map 

#Scenario: Verify add place API 
#	Given add place
#	When call "AaddplaceAPI" with "POST" request
#	Then status should 200
#	And reponse body "status" must have value "OK"
	
	@test1
Scenario Outline: Data drivern
Given add place with "<name>" "<website>" 
	When call "basecreatelocation" with "POST" request
	Then status should 200
	And reponse body "status" must have value "OK"
	And Verify the "<name>" from "basegetlocation" request
	
	Examples:
	| name | website |
	| jkjd | http://google.com |
#	| eerf | www.google.com |

@test1
Scenario: Verify delete functionality
Given Delete place
When call "baseddeletelocation" with "POST" request
Then status should 200