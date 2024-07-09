# Risk Map Traversal

Welcome to the Risk Map Traversal repository! This project involves implementing a graph-based system to simulate routing and traversal across a simplified Risk game map.

## Map Description

The Risk map is a simplified version of the world map with 42 countries and 6 continents: Europe, North America, South America, Africa, Asia, and Australia. Countries are connected by edges representing borders or water links.

## Graph Representation

A graph data structure is used to model the map:
- Each country is represented as a node (vertex).
- Connections between countries (neighborhood relations) are represented as edges.
- The graph is symmetric; if country A is a neighbor of country B, then country B is also a neighbor of country A.

## Commands

The system is controlled through a menu of commands, which can be selected by typing the command code and any required arguments. The available commands are:

- `INFO_COUNTRY`    [no args]       Get info of country
- `ROUTE`           [no args]       Get shortest path
- `HELP`            [no args]       Print usage
- `EXIT`            [no args]       Exit the application

## Installation

1. Clone this repository to your local machine:
   ```sh
   git clone https://github.com/your-username/risk-map-traversal.git
   ```
2. Navigate to the project directory:
   ```sh
   cd risk-map-traversal
   ```
3. Ensure you have Java and Maven installed. Use the Maven wrapper to compile and run the application:

   If on Windows:
   ```sh
   .\mvnw.cmd clean compile exec:java@run
   ```

   If on Unix/Mac OS:
   ```sh
   ./mvnw clean compile exec:java@run
   ```

## Usage

1. Start the application by running the Maven wrapper command appropriate for your operating system.
2. The terminal will display the command menu.
3. Type the command code and any required arguments to perform an action.
4. Follow any additional prompts to complete the command.

## Implementation Details

The implementation involves:
- Loading the map data into a graph structure.
- Implementing methods to fetch country information and calculate shortest paths using graph algorithms (e.g., Dijkstra's algorithm).
- Handling input and output to provide a user-friendly interface for querying country information and routing.
