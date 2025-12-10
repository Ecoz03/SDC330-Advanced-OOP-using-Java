public class InventoryManager {
    private final DatabaseHandler dbHandler;

    public InventoryManager() {
        dbHandler = new DatabaseHandler();
        dbHandler.createTables();
    }

    public boolean addGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) {
        return dbHandler.addGame(title, platform, releaseYear, maxPlayers, genre);
    }

    public String viewGameDetails(String title) {
        String details = dbHandler.infoGame(title);
        if (details == null || details.isEmpty()) {
            return "Game not found.\n";
        }
        return details + "\n"; // add spacing after details
    }

    public String listAllGames() {
        String allGames = dbHandler.infoAllGames();
        if (allGames == null || allGames.isEmpty()) {
            return "No games found.\n";
        }

        // Split into blocks and number them
        String[] blocks = allGames.split("\n\n");
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (String block : blocks) {
            if (!block.isEmpty()) {
                sb.append(count++).append(". ").append(block).append("\n\n");
            }
        }
        return sb.toString();
    }

    public String searchByFirstLetter(char firstLetter) {
        String results = dbHandler.searchByFirstLetter(firstLetter);
        if (results == null || results.isEmpty()) {
            return "No games found starting with '" + firstLetter + "'.\n";
        }

        // Number search results too
        String[] blocks = results.split("\n\n");
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (String block : blocks) {
            if (!block.isEmpty()) {
                sb.append(count++).append(". ").append(block).append("\n\n");
            }
        }
        return sb.toString();
    }

    public boolean updatePlatform(String title, String newPlatform) {
        return dbHandler.updatePlatform(title, newPlatform);
    }

    public boolean updateReleaseYear(String title, Integer newYear) {
        return dbHandler.updateReleaseYear(title, newYear);
    }

    public boolean updateMaxPlayers(String title, String newMaxPlayers) {
        return dbHandler.updateMaxPlayers(title, newMaxPlayers);
    }

    public boolean updateGenre(String title, String newGenre) {
        return dbHandler.updateGenre(title, newGenre);
    }

    public boolean deleteGame(String title) {
        return dbHandler.deleteGame(title);
    }
}