# 232GraphLab

This lab explores two types of searches, as well as building a minimum spanning tree. 

Depth-first search picks a starting point, and follows a path to unvisited vertices as long as possible until it hits a dead end. Then, go back to previous spot and hit unvisited vertices until every path is a dead end. This algorithm goes as far as possible in every scenario.

Breadth-first search finds all vertices which can be accessed from the starting point. Instead of going as far as possible until finding a dead end, this algorithm visits all of the closest vertices first, then braches further out once all of the closest vertices have been visited.

A minimum spanning tree takes a graph and reduces the number of edges to the minimum number required to span all vertices. If a depth-first search has been executed, the minimum spanning tree is already computed. Just save the edges as the depth-first search completes.
