# CoincidingTimesChallenge
## Solution Overview
* First the data is read into a list of maps (one for each worker) that have the days of the week as keys and lists of assistance time intervals as the values. 
* Afterwards, the solution is built taking advantage of the fact that the time intervals are ordered chronologically allowing one to reduce the required computations to find and count coinciding office times. By remembering previously found data (such as the index for the time interval that is earlier than the current), and recognizing when it isn't needed to iterate over reviewed/remaining list items, unnecessary computations are skipped.
* Finally, the solution is formatted to fit the required output format.
* The achieved time complexity is O(n)

## Architechture
Given that the challenge was presented in the same way as a programming marathons' problem, all that was needed was a main class (in this case Schedule.java) with clear method responsibilities. Moving forward, the project could include unit tests in the test directory as well as testing tools such as TestNG and Allure.

## Approach and Methodology
While there is what was mentioned in the Solution Overview, it could be added that given more time, the solution can be optimized by fully utilizing binary search to reduce the number of operations when comparing the time intervals to count the ones that overlap. Other possible ideas to consider could include building a graph or another data tructure such as a variant of a tree to optimize the data processing.

## Instructions
To run the program you need to have Java 8 or higher installed and configured. Then use your preferred editor or IDE (console) to run the program, remember to add an extra line (press enter) when inputting the data to make sure that all the data is read and processed.
