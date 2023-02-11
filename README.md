# CoincidingTimesChallenge
## Solution Overview
* First the data is read into a list of maps (one for each worker) that have the days of the week as keys and lists of assistance time intervals as the values. 
* Afterwards, the solution is built taking advantage of the fact that the time intervals are ordered chronologically allowing one to reduce the required computations to find and count coinciding office times. By remembering previously found data (such as the index for the time interval that is earlier than the current), and recognizing when it isn't needed to iterate over reviewed/remaining list items, unnecessary computations are skipped.
* Finally, the solution is formatted to fit the required output format.
* The achieved time complexity is O(n)

## Architecture
Given that the challenge was presented in the same way as a programming marathons' problem, all that was needed was a main class (in this case scheduleTool.ScheduleConsole.java) with clear method responsibilities. The scheduleTool.ScheduleTestNG class was created to interact with TestNG without removing the option of running this challenge with the console (without needing to install maven and testng).

## Approach and Methodology
While there is what was mentioned in the Solution Overview, it could be added that given more time, the solution can be optimized by fully utilizing binary search to reduce the number of operations when comparing the time intervals to count the ones that overlap. Other possible ideas to consider could include building a graph or another data structure such as a variant of a tree to optimize the data processing.

## Instructions
### Testing with console 
To run the program you need to have Java 8 or higher installed and configured. Then use your preferred editor or IDE (console) to run the class scheduleTool.ScheduleConsole.java, proceed to copy a test case from Test Cases.txt into the console, rinse and repeat until all test cases are run. Important: Remember to add one or two extra lines (press enter) after inputting the data to make sure that all the data is read and processed.
### Testing with testng
To use testNG you must have maven 3.8.7 or higher installed and configured. Create a terminal/cmd in the project and input "mvn install" to get the required versions of TestNG and the maven SureFire plugin. Finally, input "mvn clean verify" to run the tests. 