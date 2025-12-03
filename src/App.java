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
                case "7": return; //Exit
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

    //Submenu Handlers
    
    private void handleAddGame() {
        System.out.println("Add Game Submenu (enter '<' at any point to return):");

        String category = readChoice("Select platform category [PC, PlayStation, Nintendo]: ",
                                     new String[]{"PC", "PlayStation", "Nintendo"});
        if (isReturn(category)) return;

        String platform;
        switch (normalize(category)) {
            case "pc":
                platform = "PC";
                break;
            case "playstation":
                platform = chooseFrom("Choose PlayStation platform [PS1, PS2, PS3, PS4, PS5]: ",
                                      new String[]{"PS1", "PS2", "PS3", "PS4", "PS5"});
                if (isReturn(platform)) return;
                break;
            case "nintendo":
                String nCat = readChoice("Nintendo type [Handheld, Console]: ",
                                         new String[]{"Handheld", "Console"});
                if (isReturn(nCat)) return;
                if (normalize(nCat).equals("handheld")) {
                    platform = chooseFrom("Choose Nintendo handheld [GameBoy, DS, SwitchLite]: ",
                                          new String[]{"GameBoy", "DS", "SwitchLite"});
                    if (isReturn(platform)) return;
                } else {
                    platform = chooseFrom("Choose Nintendo console [GameCube, Wii, Switch/Switch2]: ",
                                          new String[]{"GameCube", "Wii", "Switch/Switch2"});
                    if (isReturn(platform)) return;
                }
                break;
            default:
                System.out.println("Unexpected category. Returning to main menu.");
                return;
        }

        String title = readRequiredText("Enter title: ");
        if (isReturn(title)) return;

        Integer releaseYear = readOptionalYear("Enter release year (press Enter to skip): ");
        if (releaseYear == null && wasReturnTriggered) return;

        String maxPlayers = readOptionalMaxPlayers("Enter max players (numeric, 'Single Player', or 'MMO'; press Enter to skip): ");
        if (maxPlayers == null && wasReturnTriggered) return;

        String genre = readRequiredText("Enter genre: ");
        if (isReturn(genre)) return;

        boolean added = inventoryManager.addGame(title, platform, releaseYear, maxPlayers, genre);
        System.out.println(added ? "Game added successfully!" : "A game with that title already exists.");
    }

    private void handleViewGame() {
        System.out.println("View Game Submenu (enter '<' at any point to return):");
        String title = readRequiredText("Enter title to view: ");
        if (isReturn(title)) return;

        String details = inventoryManager.viewGameDetails(title);
        System.out.println(details == null ? "Game not found." : details);
    }

    private void handleUpdateGame() {
        System.out.println("Update Game Submenu (enter '<' at any point to return):");
        String title = readRequiredText("Enter title to update: ");
        if (isReturn(title)) return;

        if (!inventoryManager.exists(title)) {
            System.out.println("Game not found.");
            return;
        }

        while (true) {
            System.out.println("Update Fields:");
            System.out.println("1. Platform");
            System.out.println("2. Release Year");
            System.out.println("3. Max Players");
            System.out.println("4. Genre");
            System.out.println("5. Done");
            String choice = readLine("Select an option: ");
            if (isReturn(choice)) return;
            if (choice == null) continue;

            switch (choice.trim()) {
                case "1":
                    String newPlatform = readRequiredText("Enter new platform: ");
                    if (isReturn(newPlatform)) return;
                    System.out.println(inventoryManager.updatePlatform(title, newPlatform) ? "Platform updated." : "Update failed.");
                    break;
                case "2":
                    Integer newYear = readOptionalYear("Enter new release year (press Enter to clear): ");
                    if (newYear == null && wasReturnTriggered) return;
                    System.out.println(inventoryManager.updateReleaseYear(title, newYear) ? "Release year updated." : "Update failed.");
                    break;
                case "3":
                    String newMax = readOptionalMaxPlayers("Enter new max players (press Enter to clear): ");
                    if (newMax == null && wasReturnTriggered) return;
                    System.out.println(inventoryManager.updateMaxPlayers(title, newMax) ? "Max players updated." : "Update failed.");
                    break;
                case "4":
                    String newGenre = readRequiredText("Enter new genre: ");
                    if (isReturn(newGenre)) return;
                    System.out.println(inventoryManager.updateGenre(title, newGenre) ? "Genre updated." : "Update failed.");
                    break;
                case "5":
                    System.out.println("Finished updating.");
                    return;
                default:
                    System.out.println("Invalid selection. Choose 1-5.");
            }
        }
    }

    private void handleDeleteGame() {
        System.out.println("Delete Game Submenu (enter '<' at any point to return):");
        String title = readRequiredText("Enter title to delete: ");
        if (isReturn(title)) return;

        boolean deleted = inventoryManager.deleteGame(title);
        System.out.println(deleted ? "Game deleted." : "Game not found.");
    }

    private void handleListAllGames() {
        System.out.println("List All Games Submenu (enter '<' at any point to return):");
        String ack = readLine("Press Enter to list, or '<' to return: ");
        if (isReturn(ack)) return;

        String list = inventoryManager.listAllGames();
        System.out.println((list == null || list.isEmpty()) ? "No games in inventory." : list);
    }

    private void handleSearchByFirstLetter() {
        System.out.println("Search Submenu (enter '<' at any point to return):");
        String letter = readRequiredText("Enter the first letter of the game title: ");
        if (isReturn(letter)) return;
        char first = Character.toLowerCase(letter.trim().charAt(0));
        String results = inventoryManager.searchByFirstLetter(first);
        System.out.println((results == null || results.isEmpty()) ? 
            "No games found starting with '" + letter.charAt(0) + "'." : results);
    }

    //Input Helpers

    private boolean wasReturnTriggered = false;

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private boolean isReturn(String input) {
        if (input == null) return false;
        boolean ret = input.trim().equals("<");
        wasReturnTriggered = ret;
        return ret;
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    private String readChoice(String prompt, String[] allowed) {
        while (true) {
            String input = readLine(prompt);
            if (isReturn(input)) return input;
            for (String option : allowed) {
                if (normalize(option).equals(normalize(input))) {
                    return option;
                }
            }
            System.out.println("Invalid choice. Allowed options: " + String.join(", ", allowed));
        }
    }

    private String chooseFrom(String prompt, String[] options) {
        return readChoice(prompt, options);
    }

    private String readRequiredText(String prompt) {
        while (true) {
            String input = readLine(prompt);
            if (isReturn(input)) return input;
            if (!input.trim().isEmpty()) return input.trim();
            System.out.println("This field is required. Please enter a value or '<' to return.");
        }
    }

    private Integer readOptionalYear(String prompt) {
        String input = readLine(prompt);
        if (isReturn(input)) return null;
        if (input.trim().isEmpty()) return null;
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Please enter a number or press Enter to skip.");
            return readOptionalYear(prompt);
        }
    }

    private String readOptionalMaxPlayers(String prompt) {
        String input = readLine(prompt);
        if (isReturn(input)) return null;
        if (input.trim().isEmpty()) return null;
        return input.trim();
    }
}
