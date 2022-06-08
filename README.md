# Blackjack(21)

## Technical Overview
- Java 17
- Maven 3.8.4

## Useful commands
For application build and test execution
```
mvn clean install
```
## Testing tips

- For testing sample incomplete card sets, do use `BlackjackTest.java`
- For testing with file:

    - File has to be in a specific format and should contain the complete deck with unique 52 cards, refer `complete_shuffled_deck.txt` for reference
    - For testing complete file, recommended to use `BlackjackIntegrationTest.java`
    - Use the psvm in `Play.java`to test for files placed at any location on your machine from IDE
    - You can use the command `mvn compile exec:java -Dexec.mainClass="com.achint.blackjack.Play` to run the game as well