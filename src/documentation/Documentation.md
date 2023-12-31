# DOCUMENTATION FOR COMPLEX DATA STRUCTURES

--------------------------------------------------------------------------------------------------------------------------
# Table of Contents

## Data structures
1. [MyArrayList](#myarraylist)
    1. [Description](#description)
    2. [Constructors](#constructors)
    3. [Methods](#methods)
    4. [Space Complexity](#space-complexity)

2. [MyLinkedList](#mylinkedlist)
    1. [Description](#description-1)
    2. [Constructors](#constructors-1)
    3. [Methods](#methods-1)
    4. [Node Class](#node-class)
    5. [Additional Methods](#additional-methods)

3. [MyDoublyLinkedList](#mydoublylinkedlist)
    1. [Description](#description-2)
    2. [Constructors](#constructors-2)
    3. [Methods](#methods-2)
    4. [Node Class](#node-class-1)
    5. [Iterator Class](#iterator-class)

4. [MyGraph](#mygraph)
    1. [Description](#description-3)
    2. [Constructors](#constructors-3)
    3. [Methods](#methods-3)
    4. [Edge Class](#edge-class)
    5. [Internal Methods](#internal-methods)

5. [AVLTree](#avltree)
    1. [Description](#description-4)
    2. [Class: AVLTree<T extends Comparable<T>>](#class-avltreet-extends-comparablet)
        1. [Constructors](#constructors-4)
        2. [Methods](#methods-4)
    3. [Inner Class: Node](#inner-class-node)
        1. [Fields](#fields)
        2. [Constructor](#constructor)
    4. [Private Methods](#private-methods)

6. [MyHashMap](#myhashmap)
    1. [Description](#description-5)
    2. [Class: MyHashMap<K, V> implements CustomMap<K, V>](#class-myhashmapk-v-implements-custommapk-v)
        1. [Constructors](#constructors-5)
        2. [Methods](#methods-5)
    3. [Inner Class: MapNode<K, V>](#inner-class-mapnodek-v)
        1. [Fields](#fields-1)
        2. [Constructor](#constructor-1)
        3. [Methods](#methods-6)
    4. [Interface: CustomMap<K, V>](#interface-custommapk-v)
        1. [Methods](#methods-7)
        2. [Additional Methods](#additional-methods-1)

7. [BinarySearchTree](#binarysearchtree)
    1. [Description](#description-6)
    2. [Class: BinarySearchTree<T extends Comparable<T>>](#class-binarysearchtreet-extends-comparablet)
        1. [Fields](#fields-2)
        2. [Constructors](#constructors-6)
        3. [Methods](#methods-8)
    3. [Inner Interface: NodeAction<T> (Functional Interface)](#inner-interface-nodeactiont-functional-interface)
        1. [Methods](#methods-9)

8. [MinHeap](#minheap)
    1. [Description](#description-7)
    2. [Class: MinHeap<T extends Comparable<T>>](#class-minheapt-extends-comparablet)
        1. [Fields](#fields-3)
        2. [Constructors](#constructors-7)
        3. [Methods](#methods-10)
    3. [Additional Note](#additional-note)

## Algorithms

9. [MergeSort](#mergesort)
    1. [Description](#description-8)
    2. [Class: MergeSort<T extends Comparable<T>>](#class-mergesortt-extends-comparablet)
        1. [Constructors](#constructors-8)
        2. [Methods](#methods-11)
    3. [Time Complexity and Space Complexity](#time-complexity-and-space-complexity)

10. [Dijkstra Algorithm](#dijkstra-algorithm)
    1. [Description](#description-9)
    2. [Class: DijkstraAlgorithm<T>](#class-dijkstraalgorithmt)
        1. [Fields](#fields-4)
        2. [Constructors](#constructors-9)
        3. [Methods](#methods-12)
    3. [Time Complexity and Space Complexity](#time-complexity-and-space-complexity-1)
    4. [Efficiency](#efficiency)

11. [A* Algorithm](#a-algorithm)
    1. [Description](#description-10)
    2. [Class: AStarAlgorithm<T>](#class-astar-algorithmt)
        1. [Fields](#fields-5)
        2. [Nested Class: Node<K>](#nested-class-nodek)
        3. [Constructors](#constructors-10)
        4. [Methods](#methods-13)
    3. [Time Complexity and Space Complexity](#time-complexity-and-space-complexity-2)
    4. [Efficiency](#efficiency-1)

12. [Kruskal Algorithm](#kruskal-algorithm)
    1. [Description](#description-11)
    2. [Class: KruskalAlgorithm<T>](#class-kruskal-algorithmt)
        1. [Nested Class: Edge<U>](#nested-class-edgeu)
        2. [Constructors](#constructors-11)
        3. [Methods](#methods-14)
    3. [Time Complexity and Space Complexity](#time-complexity-and-space-complexity-3)
    4. [Efficiency](#efficiency-2)

13. [Linear Search (`SearchStationByNameLinear()`)](#linear-search-searchstationbynamelinear)
    1. [Time Complexity and Space Complexity](#time-complexity-and-space-complexity-4)

14. [Binary Search (`SearchStationByNameBinary()`)](#binary-search-searchstationbynamebinary)
    1. [Time Complexity and Space Complexity](#time-complexity-and-space-complexity-5)

## Additional Notes and Overall Summary:

15. [Overall Note](#overall-note)
    1. [Additional Note](#additional-note-1)

--------------------------------------------------------------------------------------------------------------------------

# DATA STRUCTURES:

--------------------------------------------------------------------------------------------------------------------------

# MyArrayList 

## Description:

`MyArrayList` is an implementation of the `List` interface that utilizes an internal `ArrayList`. It provides a dynamic array-like data structure where elements can be added, removed, and accessed efficiently.

## Constructors

### `public MyArrayList()`

- Initializes a new `MyArrayList` with an internal `ArrayList`.

## Methods

### `public void add(E element)`

- Adds the specified element to the end of the list.

### `public void add(int index, E element)`

- Inserts the specified element at the specified index in the list.
- **Time Complexity**: O(n) where n is the size of the list.

### `public void remove(E element)`

- Removes the first occurrence of the specified element from the list.
- **Time Complexity**: O(n) where n is the size of the list.

### `public void remove(int index)`

- Removes the element at the specified index from the list.
- **Time Complexity**: O(n) where n is the size of the list.

### `public E get(int index)`

- Retrieves the element at the specified index in the list.
- **Time Complexity**: O(1)

### `public boolean contains(E element)`

- Checks if the list contains the specified element.
- **Time Complexity**: O(n) where n is the size of the list.

### `public int size()`

- Returns the number of elements in the list.
- **Time Complexity**: O(1)

### `public boolean isEmpty()`

- Checks if the list is empty.
- **Time Complexity**: O(1)

### `public void clear()`

- Removes all elements from the list.
- **Time Complexity**: O(1)

### `public void set(int index, E element)`

- Sets the element at the specified index in the list.
- **Time Complexity**: O(1)

## Space Complexity

The space complexity of `MyArrayList` is primarily determined by the internal `ArrayList` implementation. The additional space required by `MyArrayList` itself is negligible compared to the space required by the elements stored in the list.


--------------------------------------------------------------------------------------------------------------------------


# MyLinkedList 

## Description:

`MyLinkedList` is an implementation of the `List` interface and `Iterable` interface, providing a linked list data structure. It allows for dynamic insertion, deletion, and retrieval of elements.

## Constructors

### `public MyLinkedList()`

- Constructs an empty linked list.

## Methods

### `public void add(T element)`

- Adds the specified element to the end of the linked list.

### `public void add(int index, T element)`

- Inserts the specified element at the specified index in the linked list.
- **Time Complexity**: O(n) where n is the size of the list.

### `public void remove(T element)`

- Removes the first occurrence of the specified element from the linked list.

### `public void remove(int index)`

- Removes the element at the specified index from the linked list.
- **Time Complexity**: O(n) where n is the size of the list.

### `public T get(int index)`

- Retrieves the element at the specified index from the linked list.
- **Time Complexity**: O(n) where n is the size of the list.

### `public boolean contains(T element)`

- Checks if the linked list contains the specified element.
- **Time Complexity**: O(n) where n is the size of the list.

### `public int size()`

- Returns the number of elements in the linked list.
- **Time Complexity**: O(1)

### `public boolean isEmpty()`

- Checks if the linked list is empty.
- **Time Complexity**: O(1)

### `public void clear()`

- Clears the linked list.

### `public void set(int index, T element)`

- Sets the element at the specified index in the linked list.
- **Time Complexity**: O(n) where n is the size of the list.

### `public Iterator<T> iterator()`

- Returns an iterator over the elements in the linked list.

### `public MyLinkedList<T> reverse()`

- Reverses the linked list.
- **Time Complexity**: O(n) where n is the size of the list.

## Node Class

### `private static class Node<T>`

- Node class for the linked list.

## Additional Methods

### `public String toString()`

- Returns a string representation of the linked list.

-----------------------------------------------------------------------------------------------------------

# MyDoublyLinkedList 

## Description:

`MyDoublyLinkedList` is an implementation of the `List` interface and `Iterable` interface, providing a doubly linked list data structure. It allows for dynamic insertion, deletion, and retrieval of elements with bidirectional traversal capabilities.

## Constructors

### `public MyDoublyLinkedList()`

- Constructs an empty doubly linked list.

## Methods

### `public void add(E element)`

- Adds the specified element to the end of the doubly linked list.

### `public void add(int index, E element)`

- Inserts the specified element at the specified index in the doubly linked list.

### `public void remove(E element)`

- Removes the first occurrence of the specified element from the doubly linked list.

### `public void remove(int index)`

- Removes the element at the specified index from the doubly linked list.

### `public E get(int index)`

- Retrieves the element at the specified index from the doubly linked list.

### `public boolean contains(E element)`

- Checks if the doubly linked list contains the specified element.

### `public int size()`

- Returns the number of elements in the doubly linked list.

### `public boolean isEmpty()`

- Checks if the doubly linked list is empty.

### `public void clear()`

- Clears all elements from the doubly linked list.

### `public void set(int index, E temp)`

- Sets the element at a specific index in the doubly linked list.

### `public Iterator<E> iterator()`

- Provides an iterator for the doubly linked list.

### `public void swap(int index1, int index2)`

- Swaps the elements at the specified indices in the doubly linked list.

## Node Class

### `public class Node<E>`

- Node class for the doubly linked list.

## Iterator Class

### `private class LinkedListIterator implements Iterator<E>`

- Iterator implementation for the doubly linked list.

---------------------------------------------------------------------------------------------------------
# MyGraph

## Description:

`MyGraph` is a graph implementation that conforms to the `Graph` interface. It provides functionality for creating and manipulating graphs with vertices and edges. The graph can be directed, undirected, weighted, or unweighted.

## Constructors

### `public MyGraph()`

- Constructs a new `MyGraph` with default properties.

### `public MyGraph(boolean isDirected, boolean isWeighted)`

- Constructs a new `MyGraph` with specified properties.

## Methods

### `public boolean areConnected(T value1, T value2)`

- Checks if two vertices are connected in the graph.

### `public boolean contains(T value)`

- Checks if the graph contains a given vertex.

### `public int getVertexCount()`

- Gets the count of vertices in the graph.

### `public boolean isEmpty()`

- Checks if the graph is empty.

### `public void addVertex(T value)`

- Adds a vertex to the graph.

### `public void connect(T value1, T value2)`

- Connects two vertices in the graph.

### `public void connect(T value1, T value2, double weight)`

- Connects two vertices in the graph with a specified weight.

### `public double getWeight(T value1, T value2)`

- Gets the weight of the connection between two vertices.

### `public Iterable<T> getNeighbours(T value)`

- Gets the neighbors of a vertex in the graph.

### `public boolean isDirected()`

- Checks if the graph is directed.

### `public boolean isWeighted()`

- Checks if the graph is weighted.

### `public Collection<T> getVertices()`

- Gets a collection of all vertices in the graph.

### `public void addConnection(Connection connection)`

- Adds a connection to the graph using a `Connection` object.

### `public void printGraph()`

- Prints the adjacency list representation of the graph.

### `public boolean containsVertex(Object vertex)`

- Checks if a vertex contains any neighbors in the graph.

### `public MyLinkedList<GraphWeight.Node<T>> getConnection(T current)`

- Gets the connections of a vertex in the graph.

### `public void addEdge(T a, T b, double weight)`

- Adds a weighted edge to the graph.

### `public double getHeuristicCostToDestination(T start, T goal, Function<T, Double> heuristicFunction)`

- Computes the heuristic cost from the start to the goal using a specified heuristic function.

### `public Double getCostBetweenNodes(T current, T neighbor)`

- Gets the cost of the connection between two nodes.

### `public List<T> BFS(T startVertex)`

- Performs a breadth-first search starting from a given vertex.

### `public Stack<T> DFS(T startVertex)`

- Performs a depth-first search starting from a given vertex.

### `public List<Station> findStationsInRectangle(double startLatitude, double startLongitude, double endLatitude, double endLongitude)`

- Performs a breadth-first search to find stations within a rectangle defined by GPS coordinates.

## Edge Class

### `class Edge<T>`

- Represents an edge between two vertices.

## Internal Methods

- Internal methods such as `addVertexIfNotExist`, `bfsForRectangle`, and `isStationInRectangle` are used for specific functionalities and are not intended for external use.

---------------------------------------------------------------------------------------------------------
# AVLTree

## Description:

`AVLTree` is an implementation of a self-balancing binary search tree known as an AVL tree. It ensures that the tree remains balanced after insertions and deletions, maintaining a logarithmic height.

## Class: AVLTree<T extends Comparable<T>>

### Constructors

- `public AVLTree()`
    - Constructs a new AVL tree.

### Methods

#### `public void insert(T key)`

- Inserts a key into the AVL tree while maintaining balance.

#### `public void remove(T key)`

- Removes a key from the AVL tree while maintaining balance.

#### `public List<T> inOrderTraversal()`

- Performs an in-order traversal of the AVL tree, returning a list of keys.

#### `public boolean isBalanced()`

- Checks if the AVL tree is balanced.

#### `public Node getRoot()`

- Gets the root node of the AVL tree.

### Inner Class: Node

- `private class Node`
    - Represents a node in the AVL tree.

  #### Fields

    - `T key`: The key of the node.
    - `int height`: The height of the node.
    - `Node left`: Reference to the left child of the node.
    - `Node right`: Reference to the right child of the node.

  #### Constructor

    - `Node(T value)`: Constructs a new node with the specified key.

### Private Methods

#### `private Node insert(Node node, T key)`

- Inserts a key into the AVL tree (helper method).

#### `private Node remove(Node node, T key)`

- Removes a key from the AVL tree (helper method).

#### `private void inOrderTraversal(Node node, List<T> result)`

- Performs an in-order traversal of the AVL tree (helper method).

#### `private boolean isBalanced(Node node)`

- Checks if the AVL tree is balanced (helper method).

#### `private Node findMin(Node node)`

- Finds the node with the minimum key value in the AVL tree (helper method).

#### `private int height(Node node)`

- Gets the height of a node.

#### `private void updateHeight(Node node)`

- Updates the height of a node.

#### `private int getBalance(Node node)`

- Gets the balance factor of a node.

#### `private Node rotateRight(Node y)`

- Performs a right rotation around the given node.

#### `private Node rotateLeft(Node x)`

- Performs a left rotation around the given node.

## Additional Note:

- The AVL tree ensures logarithmic height, providing efficient operations.
- Time complexities: Insertion and removal - O(log N), Traversal - O(N).


---------------------------------------------------------------------------------------------------------

# MyHashMap 

## Description:

`MyHashMap` is a custom implementation of a hash map. It uses an array of linked lists to handle collisions and allows for efficient key-value mappings.

## Class: MyHashMap<K, V> implements CustomMap<K, V>

### Constructors

- `public MyHashMap()`

    - Constructs a new `MyHashMap` with the default capacity.

- `public MyHashMap(int initialCapacity)`

    - Constructs a new `MyHashMap` with the specified initial capacity.

### Methods

#### `public void put(K key, V value)`

- Associates the specified value with the specified key in this map.

#### `public V get(K key)`

- Returns the value to which the specified key is mapped.

#### `public boolean containsKey(K key)`

- Returns true if this map contains a mapping for the specified key.

#### `public boolean containsValue(V value)`

- Returns true if this map maps one or more keys to the specified value.

#### `public void remove(K key)`

- Removes the mapping for the specified key from this map if present.

#### `public int size()`

- Returns the number of key-value mappings in this map.

#### `public boolean isEmpty()`

- Returns true if this map contains no key-value mappings.

#### `public void clear()`

- Removes all of the mappings from this map.

#### `public Iterable<K> keySet()`

- Returns an iterable containing all the keys in this map.

#### `public boolean contains(K key)`

- Returns true if this map contains the specified key.

#### `public V getOrDefault(K key, V defaultValue)`

- Returns the value to which the specified key is mapped, or a default value if this map contains no mapping for the key.

#### `public MyLinkedList<V> searchBySubstring(K searchSubstring)`

- Searches for values in the hashmap whose key contains the specified substring. Returns a linked list containing values whose key contains the specified substring.

### Private Methods

#### `private int getBucketIndex(K key)`

- Calculates the bucket index for a given key based on its hash code.

#### `private void resize()`

- Resizes the hashmap when the load factor exceeds the threshold. Rehashes all existing entries to new buckets.

## Inner Class: MapNode<K, V>

- `MapNode` is a node class for a generic map, representing a key-value pair.

### Fields

- `private K key`: The key of the key-value pair.
- `private V value`: The value of the key-value pair.
- `private MapNode<K, V> next`: Reference to the next `MapNode` in the linked list.

### Constructor

- `public MapNode(K key, V value)`: Constructs a `MapNode` with the specified key and value.

### Methods

- `public K getKey()`: Gets the key of the key-value pair.
- `public V getValue()`: Gets the value of the key-value pair.
- `public MapNode<K, V> getNext()`: Gets the next `MapNode` in the linked list.
- `public void setNext(MapNode<K, V> next)`: Sets the next `MapNode` in the linked list.
- `public void setValue(V value)`: Sets the value of the key-value pair.

## Interface: CustomMap<K, V>

- `CustomMap` interface represents a simple key-value mapping.

### Methods

- `void put(K key, V value)`: Associates the specified value with the specified key in this map.
- `V get(K key)`: Returns the value to which the specified key is mapped.
- `boolean containsKey(K key)`: Returns true if this map contains a mapping for the specified key.
- `boolean containsValue(V value)`: Returns true if this map maps one or more keys to the specified value.
- `void remove(K key)`: Removes the mapping for the specified key from this map if present.
- `int size()`: Returns the number of key-value mappings in this map.
- `boolean isEmpty()`: Returns true if this map contains no key-value mappings.
- `void clear()`: Removes all of the mappings from this map.
- `Iterable<K> keySet()`: Returns an iterable containing all the keys in this map.
- `boolean contains(K key)`: Returns true if this map contains the specified key.
- `V getOrDefault(K key, V defaultValue)`: Returns the value to which the specified key is mapped, or a default value if this map contains no mapping for the key.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------


# BinarySearchTree 

## Description:

`BinarySearchTree` is a generic implementation of a binary search tree (BST). It allows the insertion, search, and deletion of elements in a way that preserves the order of elements.

## Class: BinarySearchTree<T extends Comparable<T>>

### Fields

- `private Node<T> root`: The root of the binary search tree.

### Constructors

- `public BinarySearchTree()`

    - Constructs a new empty `BinarySearchTree`.

### Methods

#### `public void insert(T value)`

- Inserts a value into the BST.

#### `private Node<T> insertRec(Node<T> root, T value)`

- Recursive helper method for inserting a value into the BST.

#### `public boolean search(T value)`

- Searches for a value in the BST.

#### `private boolean searchRec(Node<T> root, T value)`

- Recursive helper method for searching a value in the BST.

#### `public void inOrderTraversal()`

- Performs an in-order traversal of the BST.

#### `public void inOrderTraversalRecursive(NodeAction<T> action)`

- Performs an in-order traversal of the BST with a custom action for each node.

#### `private void inOrderTraversalRecursive(Node<T> root, NodeAction<T> action)`

- Recursive helper method for in-order traversal with a custom action.

#### `public void delete(T value)`

- Deletes a value from the BST.

#### `private Node<T> deleteRec(Node<T> root, T value)`

- Recursive helper method for deleting a value from the BST.

#### `private T minValue(Node<T> root)`

- Finds the value of the node with the minimum value in the given subtree.

#### `public MyLinkedList<Station> searchBySubstring(String searchSubstring)`

- Searches for values in the BST whose key (Station name) contains the specified substring.

#### `private void searchBySubstringRecursive(Node<T> root, String searchSubstring, MyLinkedList<Station> result)`

- Recursive helper method for searching values by substring.

### Inner Interface: NodeAction<T> (Functional Interface)

- `NodeAction` is a functional interface representing the action to be performed on each node during traversal.

#### Methods

- `void perform(T data)`: Performs an action on the given data.

### Inner Class: Node<T>

- `Node` is a private static class representing a node in the binary search tree.

#### Fields

- `T data`: The data of the node.
- `Node<T> left, right`: References to the left and right child nodes.

#### Constructor

- `Node(T value)`: Constructs a new node with the given value.

---------------------------------------------------------------------------------------------------------------------------

# MinHeap 

## Description:

`MinHeap` is a generic implementation of a min-heap. It stores elements of type T, maintaining the heap property where the parent is smaller than or equal to its children.

## Class: MinHeap<T extends Comparable<T>>

### Fields

- `private final int DEFAULT_CAPACITY = 10`: The default initial capacity of the heap.
- `private Object[] arr`: The array used to store elements in the heap.
- `private int size`: The current number of elements in the heap.
- `private final Comparator<T> comparator`: The comparator to determine the order of elements.

### Constructors

- `public MinHeap(Comparator<T> comparator)`

    - Constructs a new `MinHeap` with the specified comparator.

- `public MinHeap()`

    - Constructs a new `MinHeap` using natural ordering.

### Methods

#### `public void insert(T element)`

- Inserts an element into the heap.

#### `public void push(T element)`

- Pushes an element onto the heap.

#### `public T pop()`

- Removes and returns the minimum element from the heap.

#### `public T peek()`

- Returns the minimum element in the heap without removing it.

#### `public boolean isEmpty()`

- Checks if the heap is empty.

#### `public int size()`

- Returns the number of elements in the heap.

#### `private void heapifyUp()`

- Performs the heapify-up operation to maintain the heap property.

#### `private void heapifyDown()`

- Performs the heapify-down operation to maintain the heap property.

#### `private void swap(int index1, int index2)`

- Swaps elements at the given indices in the array.

#### `private boolean hasParent(int index)`

- Checks if an element has a parent in the heap.

#### `private int getParentIndex(int index)`

- Returns the index of the parent of an element.

#### `private T getParent(int index)`

- Returns the parent of an element.

#### `private boolean hasLeftChild(int index)`

- Checks if an element has a left child in the heap.

#### `private int getLeftChildIndex(int index)`

- Returns the index of the left child of an element.

#### `private T getLeftChild(int index)`

- Returns the left child of an element.

#### `private boolean hasRightChild(int index)`

- Checks if an element has a right child in the heap.

#### `private int getRightChildIndex(int index)`

- Returns the index of the right child of an element.

#### `private T getRightChild(int index)`

- Returns the right child of an element.

#### `private T getElement(int index)`

- Returns the element at the specified index in the array.

#### `private int compare(T obj1, T obj2)`

- Compares two elements using the provided comparator or natural ordering.

#### `private void resizeArray()`

- Resizes the internal array to accommodate more elements.

### Note

- The class utilizes a generic array to store elements, which may raise unchecked cast warnings. This is handled with the `@SuppressWarnings("unchecked")` annotation where necessary.


---------------------------------------------------------------------------------------------------------------------------

# MergeSort 

## Description:

`MergeSort` is a generic implementation of the merge sort algorithm. It is a divide-and-conquer sorting algorithm that divides the input array into two halves, recursively sorts each half, and then merges the sorted halves.

## Class: MergeSort<T extends Comparable<T>>

### Constructors

- `public MergeSort()`

    - Constructs a new instance of `MergeSort`.

### Methods

#### `public T[] sort(T[] array)`

- Sorts the input array using the merge sort algorithm.

    - **Parameters:**
        - `array`: The array to be sorted.

    - **Returns:**
        - A sorted array.

#### `private void merge(T[] array, T[] left, T[] right)`

- Merges two sorted arrays into a single sorted array.

    - **Parameters:**
        - `array`: The array to merge into.
        - `left`: The left sorted array.
        - `right`: The right sorted array.

### Time Complexity: O(n log n)

- The time complexity of the merge sort algorithm is O(n log n), where n is the number of elements in the array.

### Space Complexity: O(n)

- The space complexity of the merge sort algorithm is O(n) due to the additional space required for merging two halves.

### Efficiency:

- Merge sort is efficient for large datasets and provides stable sorting. It is particularly useful for linked lists and external sorting scenarios. However, it may have a higher space requirement compared to in-place sorting algorithms.

---------------------------------------------------------------------------------------------------------------------------


# Dijkstra Algorithm 

## Description:

`DijkstraAlgorithm<T>` is a class that provides a method to find the shortest path in a weighted graph using Dijkstra's algorithm. Dijkstra's algorithm is a popular algorithm for finding the shortest path between nodes in a graph with non-negative edge weights.

## Class: DijkstraAlgorithm<T>

### Fields

- `private MyHashMap<T, Double> distances`

    - A map to store the distances from the source vertex to each vertex in the graph.

### Constructors

- `public DijkstraAlgorithm()`

    - Constructs a new instance of `DijkstraAlgorithm`.

### Methods

#### `public MyLinkedList<T> findShortestPath(MyGraph<T> graph, T source, T destination)`

- Finds the shortest path in a weighted graph using Dijkstra's algorithm.

    - **Parameters:**
        - `graph`: The weighted graph.
        - `source`: The source vertex.
        - `destination`: The destination vertex.

    - **Returns:**
        - A linked list representing the shortest path from the source to the destination.

#### `private void relaxNeighbours(MyGraph<T> graph, T current, MyHashMap<T, Double> distances, MyHashMap<T, T> previousVertices, PriorityQueue<Node<T>> priorityQueue, Set<T> settled)`

- Relaxes the distances of neighboring vertices during Dijkstra's algorithm.

#### `private MyLinkedList<T> constructPath(T source, T destination, MyHashMap<T, T> previousVertices)`

- Constructs the shortest path from the source to the destination.

#### `public MyHashMap<T, Double> getDistances()`

- Returns the map of distances from the source to each vertex.

#### `public String getDistancesToString()`

- Returns a string representation of distances from the source to each vertex.

### Time Complexity: O((V + E) * log(V))

- The time complexity of Dijkstra's algorithm depends on the number of vertices (V) and edges (E) in the graph. Using a priority queue for the vertex with the minimum distance results in a time complexity of O((V + E) * log(V)).

### Space Complexity: O(V + E)

- The space complexity of Dijkstra's algorithm is O(V + E), where V is the number of vertices and E is the number of edges.

### Efficiency:

- Dijkstra's algorithm is efficient for finding the shortest paths in graphs with non-negative weights. It uses a priority queue to efficiently select the vertex with the minimum distance at each step.


---------------------------------------------------------------------------------------------------------------------------
# A* Algorithm

## Description:

`AStarAlgorithm<T>` is a class that provides a method to find the shortest path in a weighted graph using the A* algorithm. A* is a popular pathfinding and graph traversal algorithm that combines the benefits of Dijkstra's algorithm and a heuristic to efficiently find the shortest path in a graph.

## Class: AStarAlgorithm<T>

### Fields

- `private MyHashMap<T, Double> gScores`

    - A map to store the cost from the start to each node.

- `private MyHashMap<T, Double> fScores`

    - A map to store the combined cost (gScore + heuristic cost) to each node.

### Nested Class: Node<K>

- A static nested class representing a node in the A* algorithm with a vertex, gScore, and fScore.

    - **Constructor:**
        - `public Node(K vertex, double gScore, double fScore)`

### Constructors

- `public AStarAlgorithm(MyGraph<T> graph)`

    - Constructs a new instance of `AStarAlgorithm` with the specified graph.

### Methods

#### `public List<T> findShortestPath(T start, T goal)`

- Finds the shortest path in a weighted graph using the A* algorithm.

    - **Parameters:**
        - `start`: The start vertex.
        - `goal`: The goal vertex.

    - **Returns:**
        - A list representing the shortest path from the start to the goal.

#### `private List<T> reconstructPath(MyHashMap<T, T> cameFrom, T current)`

- Reconstructs the path from the `cameFrom` map.

#### `private double getHeuristicCostToDestination(T start, T goal)`

- Calculates the heuristic cost (estimated cost) from the start to the goal using the Haversine formula.

### Time Complexity: O((V + E) * log(V))

- The time complexity of A* algorithm depends on the number of vertices (V) and edges (E) in the graph. Using a priority queue for the vertex with the minimum fScore results in a time complexity of O((V + E) * log(V)).

### Space Complexity: O(V + E)

- The space complexity of A* algorithm is O(V + E), where V is the number of vertices and E is the number of edges.

### Efficiency:

- A* algorithm is efficient for finding the shortest paths in graphs with non-negative weights. It uses a combination of the actual cost from the start (gScore) and an estimated cost to the destination (heuristic) to guide the search efficiently.

---------------------------------------------------------------------------------------------------------------------------

# Kruskal Algorithm 

## Description:

`KruskalAlgorithm<T>` is a class that provides a method to find the minimum spanning tree of a connected, undirected graph using Kruskal's algorithm. Kruskal's algorithm is a greedy algorithm that finds a minimum spanning tree for a connected weighted graph.

## Class: KruskalAlgorithm<T>

### Nested Class: Edge<U>

- A public static nested class representing an edge in the graph with a source vertex, destination vertex, and weight.

    - **Constructor:**
        - `public Edge(U source, U destination, double weight)`

    - **Methods:**
        - `public U getSource()`: Returns the source vertex of the edge.
        - `public U getDestination()`: Returns the destination vertex of the edge.
        - `public double getWeight()`: Returns the weight of the edge.
        - `@Override public String toString()`: Returns a string representation of the edge.

### Constructors

- `public KruskalAlgorithm()`

    - Constructs a new instance of `KruskalAlgorithm`.

### Methods

#### `public List<Edge<T>> findMinimumSpanningTree(MyGraph<T> graph)`

- Finds the minimum spanning tree of a connected, undirected graph using Kruskal's algorithm.

    - **Parameters:**
        - `graph`: The input graph.

    - **Returns:**
        - A list of edges forming the minimum spanning tree.

#### `private boolean containsNegativeWeights(MyGraph<T> graph)`

- Checks if the graph contains edges with negative weights.

    - **Parameters:**
        - `graph`: The input graph.

    - **Returns:**
        - `true` if the graph contains edges with negative weights, `false` otherwise.

### Time Complexity: O(E * log(V))

- The time complexity of Kruskal's algorithm is dominated by the sorting of edges, which takes O(E * log(E)) time. In a connected graph, E is at most O(V^2), so the time complexity is O(E * log(V)).

### Space Complexity: O(V + E)

- The space complexity of Kruskal's algorithm is O(V + E), where V is the number of vertices and E is the number of edges.

### Efficiency:

- Kruskal's algorithm is efficient for finding the minimum spanning tree in connected graphs with non-negative weights. It prioritizes the smallest edges while avoiding cycles, resulting in a tree that spans all vertices with the minimum total edge weight.


---------------------------------------------------------------------------------------------------------------------------

## Linear Search (`SearchStationByNameLinear()`)

- **Time Complexity:**
    - O(n) - Linear time complexity.
    - The algorithm iterates through the entire `stationArrayList`, where 'n' is the number of stations.

- **Space Complexity:**
    - O(1) - Constant space complexity.
    - The algorithm uses a constant amount of space regardless of the size of the input.

## Binary Search (`SearchStationByNameBinary()`)

- **Time Complexity:**
    - O(log n) - Logarithmic time complexity.
    - The binary search reduces the search space by half in each step. The algorithm assumes that the `allStations` array is sorted before performing the search.

- **Space Complexity:**
    - O(1) - Constant space complexity.
    - The algorithm uses a constant amount of space for variables like `low`, `high`, `middle`, and `searchSubstring`.

### Overall Note:

- Binary search provides a more efficient time complexity compared to linear search when the dataset is sorted.
- Sorting the array using merge sort (`mergeSortStation`) adds an additional O(n log n) time complexity to the binary search, but this cost is usually outweighed by the faster search time.

### Additional Note:

- The space complexity is constant for both algorithms as they use a fixed amount of memory regardless of the input size.
- The binary search algorithm requires a sorted array, and the time complexity becomes significant when considering both sorting and searching.

---------------------------------------------------------------------------------------------------------------------------

### Final Notes:

- I really hope this documentation has been sufficient if not a bit excessive.
- The Astar and Dijkstra algorithms sometimes come out with different paths. I discussed this with mister Frederik Bonte and he said thats fine because of the way the heuristic uses the Haversine formula.
- This code used to provide a visual representation of the netherlands with the stations, but since i changed tha manager, it no longer works. The fragments of the previous Manager and Jframe class can be found in the "ForgottenClasses" directory.
- Due to my job I did't get to finish this project in time so i did not get to submit it. I've had no notes or feedback to lead me for the retake. this is the first submission.


- Finally i'd like to wish Happy Holidays to whoever is reading this :D







