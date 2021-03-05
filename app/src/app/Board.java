/**
 * @author Maxime DUBOST
 * Le 26/03/2020
 */
package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Board {
    private static List<Card> cards = new ArrayList<Card>();
    private static Character[] shuffledChars;
    private static int attempts = 0;
    private static int foundPairs = 0;
    private static String warning = "";
    
    /// L'ajout d'un constructeur privé permet d'empêcher
    /// la création d'instances de la classe Board,
    /// dédiée à une utilisation statique
    private Board() {}

    public static int getAttempts() {
        return attempts;
    }

    public static void setAttempts(int _attempts) {
        attempts = _attempts;
    }

    public static int getFoundPairs() {
        return foundPairs;
    }

    public static void setFoundPairs(int _foundPairs) {
        foundPairs = _foundPairs;
    }

    /**
     * Affiche l'écran d'accueil
     */
    public static void generateStartScreen() {
        System.out.println("+==============================+");
        System.out.println("|    M E M O R Y    G A M E    |");
        System.out.println("+==============================+");
        System.out.println("|                              |");
        System.out.println("|      Appuyez sur ENTRER      |");
        System.out.println("|        pour commencer        |");
        System.out.println("|                              |");
        System.out.println("+==============================+");
    }

    /**
     * Crée les cartes du plateau et mélange les symboles
     */
    public static void generateCards() {
        cards.clear();
        // Création d'un tableau de caractères
        Character[] chars = {'&', '&', '#', '#', '@', '@', '€', '€', '$', '$', '£', '£', '¤', '¤', '%', '%'};
        // Création d'une liste reprenant les éléments du tableau de caractères
        List<Character> charsList = Arrays.asList(chars);
        // Mélange de la liste de caractères
        Collections.shuffle(charsList);
        // Attribution des éléments de la liste mélangée au tableau statique
        shuffledChars = charsList.toArray(new Character[charsList.size()]);
        // Création des cartes (symboles cachés)
        for(int i = 0; i < shuffledChars.length; i++)
            cards.add(new Card(' '));
    }

    /**
     * Affiche le plateau
     */
    public static void generateBoard() {
        System.out.println("     ESSAIS : " + attempts);
        System.out.println("                    +----------+");
        System.out.println("    1   2   3   4   | "+foundPairs+" SUR 8  |");
        System.out.println("  +---+---+---+---+ | - & : "+validatedSymbol('&')+"  |");
        System.out.println("A | "+cards.get(0).getSymbol()+" | "+cards.get(1).getSymbol()+" | "+cards.get(2).getSymbol()+" | "+cards.get(3).getSymbol()+" | | - # : "+validatedSymbol('#')+"  |");
        System.out.println("  +---+---+---+---+ | - @ : "+validatedSymbol('@')+"  |");
        System.out.println("B | "+cards.get(4).getSymbol()+" | "+cards.get(5).getSymbol()+" | "+cards.get(6).getSymbol()+" | "+cards.get(7).getSymbol()+" | | - € : "+validatedSymbol('€')+"  |");
        System.out.println("  +---+---+---+---+ | - $ : "+validatedSymbol('$')+"  |");
        System.out.println("C | "+cards.get(8).getSymbol()+" | "+cards.get(9).getSymbol()+" | "+cards.get(10).getSymbol()+" | "+cards.get(11).getSymbol()+" | | - £ : "+validatedSymbol('£')+"  |");
        System.out.println("  +---+---+---+---+ | - ¤ : "+validatedSymbol('¤')+"  |");
        System.out.println("D | "+cards.get(12).getSymbol()+" | "+cards.get(13).getSymbol()+" | "+cards.get(14).getSymbol()+" | "+cards.get(15).getSymbol()+" | | - % : "+validatedSymbol('%')+"  |");
        System.out.println("  +---+---+---+---+ +----------+");
        System.out.println();
        if(warning == "")
            System.out.println();
        else
            System.out.println("/!\\ " + warning);
        System.out.println();
    }

    /**
     * Compte le nombre de carte retournées sur le plateau (isShown == true)
     * @return Nombre de cartes retournées sur le plateau (paires trouvées exclues)
     */
    public static int shownCardsCount() {
        int count = 0;
        for (Card card : cards) 
            if(card.getIsShown())
                count++;
        return count;
    }

    /**
     * Compte le nombre de paires trouvées sur le plateau (isFound == true)
     * @return Nombre de paires trouvées
     */
    public static int foundPairsCount() {
        int count = 0;
        for (Card card : cards) 
            if(card.getIsFound())
                count++;
        return count / 2;
    }

    /**
     * Retourne une carte face cachée du plateau
     * @param position : Position de la carte à retourner
     */
    public static boolean showCard(String position) {
        int index;
        switch(position) {
            case "A1": case "a1": index = 0; break;
            case "A2": case "a2": index = 1; break;
            case "A3": case "a3": index = 2; break;
            case "A4": case "a4": index = 3; break;
            case "B1": case "b1": index = 4; break;
            case "B2": case "b2": index = 5; break;
            case "B3": case "b3": index = 6; break;
            case "B4": case "b4": index = 7; break;
            case "C1": case "c1": index = 8; break;
            case "C2": case "c2": index = 9; break;
            case "C3": case "c3": index = 10; break;
            case "C4": case "c4": index = 11; break;
            case "D1": case "d1": index = 12; break;
            case "D2": case "d2": index = 13; break;
            case "D3": case "d3": index = 14; break;
            case "D4": case "d4": index = 15; break;
            default: index = -1;
        }
        if(index == -1)
            warning = "Cette carte n'existe pas";
        else if(cards.get(index).getIsShown() == false && cards.get(index).getIsFound() == false)
        {
            warning = "";
            cards.get(index).setIsShown(true);
            cards.get(index).setSymbol(shuffledChars[index]);
            return true;
        }
        else
            warning = "Cette carte est déjà retournée";
        return false;
    }

    /**
     * Vérifie si un symbole correspond à une paire de cartes trouvée
     * @param symbol : Symbole de la paire de cartes à vérifier
     * @return "o" si la paire de carte correspondant au symbole est trouvée, "x" sinon
     */
    public static String validatedSymbol(char symbol) {
        for (int i = 0; i < cards.size(); i++)
            if(shuffledChars[i] == symbol)
                if(cards.get(i).getIsFound() == true)
                    return "o";
        return "x";
    }

    /**
     * Détermine si les cartes retournées constituent une paire
     * @return true si les cartes retournées constituent une paire, false sinon
     */
    public static boolean isPairFound() {
        char[] bothChars = new char[2];
        int i = 0;
        for (int j = 0; j < cards.size(); j++)
            if(cards.get(j).getIsShown() == true)
            {
                bothChars[i] = shuffledChars[j];
                if(i != 1)
                    i++;
                else {
                    i = 0;
                    break;
                }
            }  
        if(bothChars[0] == bothChars[1])
        {
            for (Card card : cards) 
                if(card.getIsShown() == true)
                {
                    card.setIsFound(true);
                    card.setIsShown(false);
                    if(i != 1)
                        i++;
                    else
                        break;
                }
            return true;
        }
        else
            return false;
    }

    /**
     * Masque toutes les cartes du plateau dont les paires n'ont pas été trouvées
     */
    public static void hideAllNotFoundCards() {
        int i = 0;
        for (Card card : cards) {
            if(!card.getIsFound() && card.getIsShown())
            {
                card.setIsShown(false);
                card.setSymbol(' ');
                if(i == 1)
                    break;
                else
                    i++;
            }
        }
    }
}