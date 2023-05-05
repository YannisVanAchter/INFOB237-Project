# Annotation for project

## Summary
- [Annotation for project](#annotation-for-project)
  - [Summary](#summary)
  - [Understanding the current repository and its files](#understanding-the-current-repository-and-its-files)
  - [Analysis of the algorithm setpoints](#analysis-of-the-algorithm-setpoints)
    - [Algorithm goals](#algorithm-goals)
    - [Map patern](#map-patern)
    - [Move permissions](#move-permissions)
    - [Ambiguity](#ambiguity)
    - [Output description](#output-description)
    - [Description file patern (input file)](#description-file-patern-input-file)
  - [Invariant and loop variant](#invariant-and-loop-variant)
  - [Complexity of the naïve solution](#complexity-of-the-naïve-solution)
  - [Optimal sub-structure of the solution](#optimal-sub-structure-of-the-solution)
  - [Recursive equation](#recursive-equation)
  - [Complexity of the final solution](#complexity-of-the-final-solution)

<br /><br />

## Understanding the current repository and its files

[Mains.java](./Main.java) is the file with naïve solution. It is the first version of the project. It is not used anymore.

[Main.java](./Main.java) is the file with the final solution. It is the final version of the project.

In the both files, you will find the JML specification 

<br />

## Analysis of the algorithm setpoints

### Algorithm goals
- Finding the shortest path 
- At each node, drink the content before moving to the next one
- Total of drinkable is determined by a number include in the description file (limit set as K)

### Map patern
- Rectangle of the M x N defined in the description file

### Move permissions
- Move one node by one
- Move allow:
  - horizontally (right)
  - vertically (down)
  - diagonal (right and down)

### Ambiguity
- We don't want to find a solution where we drink less beer, but the most and stay under the limit

### Output description
- -1 if there is no solution
- n° beer if there is at least one solution (if many, return the solution solution that maximises the number of beer drinked)

### Description file patern (input file)
- N° of map description in the current file (first line)
- parameter of the current map (next line, and repeat after map is defined)
- Map where all numbers are the beer quantities (next lines, after map description)

Example:
```
4 // number of maps
5 3 10 // indicate map of 5 rows and 3 columns where the beer limit is 10
3 3 7
2 3 1
6 1 9
8 2 4
3 7 2 // will return 10
1 1 7 // new map of 1 row and 1 column where the beer limit is 7
8 // will return -1
3 3 7 // new map of 3 rows and 3 columns where the beer limit is 7
2 3 1
6 1 9
8 2 3 // will return 6
3 4 9 // new map of 3 rows and 4 columns where the beer limit is 9
2 3 4 1
6 5 5 3
5 2 3 4 // will return -1
```

## Invariant and loop variant

Concerning the loop invariants with their proofs, since the "findBestPath" method is recursive, it does not have any loop. As a consequence, it has no loop invariant. 

## Complexity of the naïve solution

The naïve solution is to try all the possible paths and to keep the one that maximizes the number of beers drank.

Note that `n` is the result of: Line * Column (number of nodes)

Complexity: O(3^n)

## Optimal sub-structure of the solution
Start from the end of the board

Go one step upwards at a time until the solution (be back to the beginning)

At each step, compute new sum of beers with previous sub-solution. Keep the best one at the beginning.

To save computing time, we check the cases where we are sure to go (the beginning and the end of the map), if one of them is greater than the beer limit, we return -1.

## Recursive equation

For final solution, we used generate and test. From that point, we can not define recursive equation.

<br />
Unimplemantable equation:

```
F(tab, line, column, maxBeer) = max(
    F(tab, line - 1, column, maxBeer - tab[line][column]), 
    F(tab, line, column - 1, maxBeer - tab[line][column]), 
    F(tab, line - 1, column - 1, maxBeer - tab[line][column])
    ) + tab[line][column]
```

As a generate and test algorithm, there is no equation that can be applied to the problem.

## Complexity of the final solution

loop over board and create summed board: n^2

Note that here `n` is the result of: Line * Column (number of nodes)

Once summed board is created, loop over it to find the best solution: n 

<br />

=> O(n^2 + n) = O(n^2)
