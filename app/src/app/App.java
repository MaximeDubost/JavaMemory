/**
 * @author Maxime DUBOST
 * Le 26/03/2020
 */
package app;

import java.util.Scanner;

public class App {

    private static String capture;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        welcome();
        start();
    }

    /**
     * Génère le message d'accueil, puis mélange
     * et répartie les cartes sur le plateau
     */
    private static void welcome() {
        clear();
        Board.generateStartScreen();
        System.out.println();
        capture = sc.nextLine();
        capture = null;
        Board.generateCards();
    }

    /**
     * Méthode appelée à chaque tour permettant de demander au joueur
     * les deux cartes à retourner, puis appelle les fonctions de la 
     * classe statique Board pour déterminer si les cartes choisies
     * constituent une paire ou non et affiche les messages correspondants
     */
    private static void start() {
        do
        {
            clear();
            Board.generateBoard();
            System.out.println("1. Saisir la position de la première carte à retourner (ex: A1)");
            capture = sc.nextLine();
        }
        while (Board.showCard(capture) == false);

        do
        {
            clear();
            Board.generateBoard();
            System.out.println("2. Saisir la position de la deuxième carte à retourner (ex: B2)");
            capture = sc.nextLine();
        }
        while (Board.showCard(capture) == false);

        clear();
        Board.setAttempts(Board.getAttempts() + 1);
        String result;
            if(Board.isPairFound())
            {
                result = "Nouvelle paire trouvée !";
                Board.setFoundPairs(Board.getFoundPairs() + 1);
            }
            else
                result = "Aucune paire trouvée.";
        
        Board.generateBoard();
        if(Board.foundPairsCount() == 8)
        {
            System.out.println("Félicitations ! Vous avez trouvé toutes les paires en " + Board.getAttempts() + " essais. \n");
            System.out.println("Appuyez sur ENTRER pour quitter le jeu.");
            capture = sc.nextLine();
            capture = null;
            sc.close();
        }
        else
        {
            System.out.println(result + " Appuyez sur ENTRER pour continuer.");
            capture = sc.nextLine();
            capture = null;
            Board.hideAllNotFoundCards();
            start();
        }   
    }

    /**
     * Crée des saut de lignes dans la console pour 
     * laisser place à l'affichage d'un nouveau plateau
     */
    private static void clear() {
        for(int i = 0; i < 50; i++)
            System.out.println("\n");
    }

}