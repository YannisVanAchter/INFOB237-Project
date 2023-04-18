# Annotation for project

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

### Output description
- -1 if there is no solution
- n° beer if there is at least one solution (if many, return the best solution)


### Description file patern
- N° of map description in the current file
- parameter of the current map
- Map where all numbers are the beer quantities

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

WAIT FOR SIMON 

## Complexity of the naïve solution

O(n^3)

## Optimal sub-structure of the solution
Start from the end of the path

Go one step upwards at a time until the solution (be back to the beginning)

At each step, find the place where there is less beer

## Recursive equation

?? HOW TO DO THIS ??
