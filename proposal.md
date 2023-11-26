# CSCI E-10B Term Project Proposal: TypeMaster

|Project information | |
|:--|:--|
|Name   | Jonathan Buchner  |
|Course | CSCI E-10b |
|Semester   | Fall 2023 |
|Email  | job3767@g.harvard.edu|
|Credit | Graduate student|

## What is the project supposed to do?
Upon running the application, the user is presented with a list of typing challenges that cover common Java syntax, such as creating a 'Hello World' app or writing a for loop. After providing their first name and last name, the user can choose (and repeat) any of the challenges and attempt to beat their fastest time, as well as the fastest time among all users. A user's competitive nature and desire to improve their coding skills will motivate them to keep trying the challenges in order to outrank their friends on the leaderboard!

The real value of the project, however, is in the data collected.  

I have worked as a Software Engineer instructor at Microsoft and Epicodus. While teaching, I have made a controversial observation: participants who are slow at typing computer programs tend to be less successful in the classroom and perform worse in interviews. This observation suggests a positive correlation between keyboard typing speed and performance metrics such as grades and job offers. It should be noted that increasing typing speed has diminishing returns (logarithmic decay) up to some point where further improvements in accuracy and speed have a negligible impact on performance. The purpose of this project (theoretically—I do not actually plan to collect test data) is to ask CSCI E-10b students to use the application in the Harvard computer lab and then provide the data to faculty and staff to look for a correlation between the test data and class performance.

## Why does the project have value?
The data collected by the application could be used to guide future computer science curricula. If a correlation exists, I hypothesize students new to learning computer science through writing java programs have the internalization of new concepts interrupted by focusing instead on remembering basic syntax and locating the correct keys on the keyboard. A curriculum approach that encourages dedicated time for the rote memorization of syntax patterns and where a key exists on a keyboard may later enable students to learn computer science concepts faster.

The program also has value as it encourages the practice of typing and fosters healthy competition among friends!

## What will be the form of the project? What will be the flow of the project?
Upon launching the application, the user will be greeted by the leaderboard window, accompanied by a prompt asking them to enter their first and last name. After typing in their name, the user will either be greeted as a new user or welcomed back. Displayed before the user will be a leaderboard featuring a selection of basic Java syntax typing challenges. Next to each challenger's name will be the user's highest score and the top score among all users (score only appear if they exist).

The user chooses a challenge, at which point a new window will pop up with the following details:

- The name of the challenge.
- A description of the challenge.
- A block of text to replicate.
- A timer.
- A textbox for input.
- A button to submit their attempt and a button to exit.

Upon exiting, the user will see updates to the leaderboard and may select another typing challenge.

Many typing tests track speed and accuracy by requiring each letter to be correctly typed before being able to type the next letter. This test is different. The user can type the code snippet in any order, and once the user's input matches the challenge snippet exactly (probably ignoring white space), the test is marked as complete, and the timer stops. The timing starts as soon as the user types the first letter.

## What are the major parts of the project? Algorithms, data structures and classes?
Below are the divisions of major responsibilities for the project. I anticipate that the following functionality may be broken down into multiple classes.

### Main
The program will need a main class and a set of helper classes to manage the looping and running the program. There will be an app runner class that is responsible for initializing the application and looping through the behavior indicated by the user. I hope to use a state machine type approach for managing program state.  These classes will work closely with the display class.

### Display
These classes will be responsible for displaying the main window and the typing test window. Windows will receive the information to display before being opened. These classes will also handle collecting user input from both the main and typing test windows. When the typing test window closes, it will return the results of the challenge.

A critical method within the display functionality will be parsing the user's input to determine if they have completed the challenge. This method will compare the user's input to the challenge text and return a boolean value after each keystroke (or possibly after a set time period, like a second).

If time allows, I would also like to implement anti cheat measures like looking for more than a single key stroke being entered at one time (no copying and pasting!).

### Reader and Writer
These functions will handle reading from and writing to files.

#### data.csv
This file will store test results, including a single line with these values separated by commas: datetime test was taken, first name, last name, challenge id, and time in seconds. This data will be easily interpretable as a CSV file. Note that if a user does not submit at least one score after providing their name, he or she will not be remembered by the program.

#### Markdown files
Each challenge will be contained in a markdown file to be parsed.

- The first line will be the challenge name and will begin with a single hash.
- The second line will contain the challenge description.
- The third line will present the challenge text, enclosed in code block syntax (three backticks).

I plan to not use line numbers but rather markdown syntax clues to parse the files.  Writing the files in mark down makes them easy to create and edit.

### Data parser and markdown parser
I will need a class for parsing the markdown files and the data.txt file. The parser will read these files and return the data in a structured object.

### Extra: data visualization
This is a stretch goal. It would be interesting to display a user's data over time in a meaningful way, such as through a graph or chart. This functionality would parse the data.txt file for a specific challenge and display the scores over time.

If I have extra time, I plan to make the layout look professional.


