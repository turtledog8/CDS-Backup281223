# Complex Data Structures

A university project for the Complex Data Structures course. It has my own implementations of common data structures and algorithms, and uses them on a real dataset: the train stations and tracks of the Netherlands and the countries around it.

The program can search stations, sort connections, find the stations inside an area and find the shortest path between two stations. It can be used from a console menu, from a window with a map, or from both at the same time.

## What is inside

**Data structures** (written by hand): array list, linked list, doubly linked list, hash map, binary search tree, AVL tree, min-heap and a graph.

**Algorithms:** merge sort, selection sort, binary search, BFS/DFS, Dijkstra, A* and Kruskal.

## Running it

Open the project in IntelliJ IDEA (Java 19 or newer) and run `Main` with the project folder as the working directory. `Main` asks how to start:

1. Console menu
2. Window with the map
3. Both

You can also give `console`, `gui` or `both` as the program argument to skip the question.

**Console menu:** search a station by name (linear and binary), sort the connections by distance (merge sort and selection sort), search with the binary search tree and with the hash map, find the stations inside the rectangle between two stations, and find the shortest path with Dijkstra or A*.

**Window:** the stations are shown on a map of the Netherlands. Enter two station codes, pick Dijkstra or A* and the path is drawn on the map. The map can be zoomed with the mouse wheel and moved by dragging, and clicking a station shows its name and code.

## How it is put together

```
src/
  Main.java              starts the program and asks for the mode
  Manager2.java          loads the data, builds the structures and runs the console menu
  MyJFrame.java          the window with the map
  csvreader/             reads the stations and the tracks from the CSV files
  model/                 Station and Connection
  list/  map/  graph/    the data structures
  tree/  AVLTree/
  sorting/  search/      the sorting and searching algorithms
  traversal/             Dijkstra, A* and Kruskal
  resources/             stations.csv, tracks.csv and the map image
  documentation/         the full documentation
test/                    JUnit tests
```

`Main` loads everything once through `Manager2`, and the console menu and the window both use that same data.

## Tests

The tests are in the `test/` folder and use JUnit 4 and JUnit 5. Run them from IntelliJ (right click the `test` folder and choose Run All Tests).

## Documentation

Everything else is in [`src/documentation/Documentation.md`](src/documentation/Documentation.md): every data structure and algorithm with its methods, time and space complexity, how the application works, and a description of every test.
