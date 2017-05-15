# Eulerian-Cycle-Chinese-Postman-Problem-
## Problem
Suppose we have 5 cities(Cupertino, Mountain View, Palo Alto, Santa Clara, and Sunnyvale) to visit, the distance between two arbitrary cities are symmetric, i.e. d(x, y) = d(y, x) and it satisfy the triangle inequality. Find the Eulerian Cycle with the minimum cost(distance).

<img width="169" alt="input" src="https://cloud.githubusercontent.com/assets/18632383/26074307/40a49e3a-397f-11e7-9dfc-9c20568914af.png">.
### Input Graph
<img width="349" alt="graph" src="https://cloud.githubusercontent.com/assets/18632383/26076934/7115f8da-3988-11e7-8cf1-4f2823aeba56.png">.
We can verify that the triangular inequality is satisfied.
### 1. Run MST on the Input Graph
Use kruskal's or prim's algorithm to find the MST for the given input graph. Then we get the following tree of cost 21.
<img width="404" alt="mst" src="https://cloud.githubusercontent.com/assets/18632383/26077445/0c678398-398a-11e7-9358-2db83db5a08d.png">.
### 2. Perfect Matching
Find the odd degree vertices for the resultant MST graph. Now, we need to connect this odd degree nodes in pairs to make them even degree.
Then find the possible match for this odd degree nodes which has minimum cost.
In this problem, PA, SV, C and SC are the odd degree nodes because the number of edges connected to each of them is odd. Find all the possibilities of matchings and consider the one which has minimum cost. The following diagram shows the four different possibilities of matchings and we can pick the one that has cost 17 which is minimum.
