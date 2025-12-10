# Project Name: Game Inventory System (GIS)
## Project Description
The Game Inventory System is a menu-driven Java application designed to manage a personal library of video games across multiple platforms (PC, PlayStation, Nintendo consoles and handhelds). It demonstrates core object-oriented programming principles such as abstraction, inheritance, encapsulation, and polymorphism, while providing a user-friendly interface for adding, viewing, updating, deleting, and searching games. The system is structured with clear separation of concerns: App.java handles user interaction, InventoryManager manages business logic, and DatabaseHandler prepares for SQLite integration to support persistent storage.
## Project Tasks
- **Task 1: Interface & Abstract Classes**
- Implemented GameInterface, AbstractGame, NintendoGame, and PlayerCount to enforce OOP principles.
- **Task 2: Platform Subclasses**
- Built PCGame, PSGame, NintendoConsole, and NintendoHandheld with constructors and polymorphism.
- **Task 3: Player Count Composition**
- Added PlayerCount factory with subclasses (SinglePlayer, MMO, NumericPlayerCount) for flexible input handling.
- **Task 4: Inventory Management**
- Developed InventoryManager to bridge CLI and database, formatting results and managing operations.
- **Task 5: Database Integration**
- Implemented DatabaseHandler with SQLite/JDBC for table creation and CRUD operations.
- **Task 6: Command-Line Interface**
- Created App.java with a menu-driven system for user input/output in the terminal.
- **Task 7: Documentation & Access Control**
- Added in-code comments and applied proper access specifiers for encapsulation.
- **Task 8: Testing & Validation**
- Verified add, view, update, delete, and search features with error handling and formatted output.
## Project Skills Learned
- Applied object-oriented programming principles (abstraction, inheritance, encapsulation, polymorphism) across multiple game classes.
- Designed and implemented interfaces and abstract classes to enforce consistency and extensibility.
- Built a menu-driven command-line interface with clear navigation, input handling, and error messages.
- Structured code with separation of concerns: UI (App.java), business logic (InventoryManager.java), and persistence (DatabaseHandler.java).
- Integrated SQLite database with JDBC for persistent storage.
- Implemented search and filtering logic (by title, by first letter) with formatted, numbered output.
- Strengthened exception handling and defensive programming to manage invalid or null inputs gracefully.
- Enhanced user experience design in a CLI environment with banners, dividers, and case-insensitive commands.
- Practiced modular, maintainable coding workflows to support future expansion (additional platforms, new player count types, database integration).
## Language Used
- **Java**: Primary programming language used to implement the application, demonstrating OOP principles (interfaces, abstract classes, inheritance, polymorphism...).
- **SQL (SQLite)**: Used in DatabaseHandler.java for database schema creation and CRUD operations, enabling persistent storage of game data.
## Development Process Used
 **Iterative Approach**: The project was developed using an iterative, modular approach. Core functionality was built first with an emphasis on object-oriented programming principles (interfaces, abstract classes, inheritance, polymorphism). The application was structured with clear separation of concerns:
    - User Interface Layer (App.java) for menu-driven input and output.
    - Business Logic Layer (InventoryManager.java) for managing operations and formatting results.
    - Persistence Layer (DatabaseHandler.java) for SQLite integration to support data storage.
  Development followed a step-by-step process:
    - Planning and design of class hierarchies and platform specialization.
    - Implementation of core features (CRUD and search functions).
    - Refinement of usability with numbered outputs, case-insensitive commands, and return navigation.
    - Database stubbing to ensure safe compilation and prepare for persistent storage.
    - Testing and debugging to validate menu flows, error handling, and extensibility.
This process ensured the system remained testable, maintainable, and ready for future expansion at any point in the process.
## Notes
- Application runs entirely in the terminal with a menu-driven interface.
- Entering '<' at any submenu prompt returns the user to the main menu.
- releaseYear and maxPlayers fields are optional; games can be added without them.
- Player count accepts numeric values or keywords like "Single Player" and "MMO".
- Searches are case-insensitive and results are formatted with numbered lists for readability.
- Database tables are created automatically on first run; no manual setup is required.
- Error messages are printed to the console to guide the user when invalid input is entered.
- The system is designed for easy expansion (new platforms, player count types, or features).
## Link to Project
(to be included)
