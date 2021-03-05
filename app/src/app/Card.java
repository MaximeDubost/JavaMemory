/**
 * @author Maxime DUBOST
 * Le 26/03/2020
 */
package app;

public class Card {
    private char symbol;
    private boolean isShown;
    private boolean isFound;

    public Card(char symbol) {
        this.symbol = symbol;
        this.isShown = false;
        this.isFound = false;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean getIsShown() {
        return this.isShown;
    }

    public void setIsShown(boolean isShown) {
        this.isShown = isShown;
    }

    public boolean getIsFound() {
        return this.isFound;
    }

    public void setIsFound(boolean isFound) {
        this.isFound = isFound;
    }
}