/*
Name: Jessie Sosniak
Date: 03 DEC 2025
Assignment: SDC330 Course Project
Details: Main application file. 
*/

import java.util.Scanner;

public class App {

    private static final String BANNER = "Jessie Sosniak SDC330 Course Project - Game Inventory System";
    private static final String DIVIDER = "------------------------------------------------------------";

    private final Scanner scanner = new Scanner(System.in);
    private final InventoryManager inventoryManager = new InventoryManager();

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        printBannerAndInstructions();
        mainMenuLoop();
        System.out.println("Goodbye!");
    }

    private void printBannerAndInstructions() {
        System.out.println(DIVIDER);
        System.out.println(BANNER);
        System.out.println(DIVIDER);
        System.out.println("Instructions:");
        System.out.println("Use the menu below to manage your game inventory.");
        System.out.println("Type '<' at any submenu prompt to return to the main menu.");
        System.out.println("Searches and platform choices are not case sensitive.");
        System.out.println(DIVIDER);
    }

    private void mainMenuLoop() {
        while (true) {
            printMainMenu();
            String choice = readLine("Select an option: ");
            if (choice == null) continue;
            switch (choice.trim()) {
                case "1": handleAddGame(); break;
                case "2": handleViewGame(); break;
                case "3": handleUpdateGame(); break;
                case "4": handleDeleteGame(); break;
                case "5": handleListAllGames(); break;
                case "6": handleSearchByFirstLetter(); break;
                case "7": return; // Exit
                default: System.out.println("Invalid selection. Please choose a menu option (1-7).");
            }
            System.out.println(DIVIDER);
        }
    }

    private void printMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Add Game");
        System.out.println("2. View Game");
        System.out.println("3. Update Game");
        System.out.println("4. Delete Game");
        System.out.println("5. List All Games");
        System.out.println("6. Search by First Letter");
        System.out.println("7. Exit");
        System.out.println(DIVIDER);
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.equals("<")) return null;
        System.out.println(); // spacing after input
        return input;
    }

    private void handleAddGame() {
        System.out.println(DIVIDER);
        System.out.println("Add Game Menu");
        System.out.println(DIVIDER);

        String title = readLine("Enter title: ");
        if (title == null) return;
        String platform = readLine("Enter platform: ");
        if (platform == null) return;
        String releaseYearStr = readLine("Enter release year: ");
        if (releaseYearStr == null) return;
        Integer releaseYear = Integer.valueOf(releaseYearStr);
        String maxPlayers = readLine("Enter max players: ");
        if (maxPlayers == null) return;
        String genre = readLine("Enter genre: ");
        if (genre == null) return;

        boolean success = inventoryManager.addGame(title, platform, releaseYear, maxPlayers, genre);
        System.out.println(success ? "Game added successfully.\n" : "Failed to add game.\n");
    }

    private void handleViewGame() {
        System.out.println(DIVIDER);
        System.out.println("View Game Menu");
        System.out.println(DIVIDER);

        String title = readLine("Enter title: ");
        if (title == null) return;

        System.out.println(inventoryManager.viewGameDetails(title));
    }
    private void handleUpdateGame() {
        System.out.println(DIVIDER);
        System.out.println("Update Game Menu");
        System.out.println(DIVIDER);

        String title = readLine("Enter title of game to update: ");
        if (title == null) return;

        System.out.println("1. Update Platform");
        System.out.println("2. Update Release Year");
        System.out.println("3. Update Max Players");
        System.out.println("4. Update Genre");
        System.out.println(DIVIDER);

        String choice = readLine("Select an option: ");
        if (choice == null) return;

        boolean success = false;
        switch (choice.trim()) {
            case "1":
                String newPlatform = readLine("Enter new platform: ");
                if (newPlatform == null) return;
                success = inventoryManager.updatePlatform(title, newPlatform);
                break;
            case "2":
                String newYearStr = readLine("Enter new release year: ");
                if (newYearStr == null) return;
                success = inventoryManager.updateReleaseYear(title, Integer.valueOf(newYearStr));
                break;
            case "3":
    		String newMaxPlayers = readLine("Enter new max players (numeric, Single Player, MMO): ");
    		if (newMaxPlayers == null) return;
    		success = inventoryManager.updateMaxPlayers(title, newMaxPlayers);
    		break;
            case "4":
                String newGenre = readLine("Enter new genre: ");
                if (newGenre == null) return;
                success = inventoryManager.updateGenre(title, newGenre);
                break;
            default:
                System.out.println("Invalid choice.\n");
        }
        System.out.println(success ? "Update successful.\n" : "Update failed.\n");
    }

    private void handleDeleteGame() {
        System.out.println(DIVIDER);
        System.out.println("Delete Game Menu");
        System.out.println(DIVIDER);

        String title = readLine("Enter title of game to delete: ");
        if (title == null) return;

        boolean success = inventoryManager.deleteGame(title);
        System.out.println(success ? "Game deleted successfully.\n" : "Failed to delete game.\n");
    }

    private void handleListAllGames() {
        System.out.println(DIVIDER);
        System.out.println("List All Games");
        System.out.println(DIVIDER);

        System.out.println(inventoryManager.listAllGames());
    }

    private void handleSearchByFirstLetter() {
        System.out.println(DIVIDER);
        System.out.println("Search by First Letter");
        System.out.println(DIVIDER);

        String input = readLine("Enter first letter: ");
        if (input == null || input.isEmpty()) return;
        char firstLetter = input.charAt(0);

        System.out.println(inventoryManager.searchByFirstLetter(firstLetter));
    }
}