# Approximation of Eulerian-Cycle (Chinese-Postman-Problem)
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
In this problem, PA, SV, C and SC are the odd degree nodes because the number of edges connected to each of them is odd.The following diagram shows the three different possibilities of matchings and we choose C-SC, SV-PA combination which has minimum cost 17. (In implementing the problem, it is better to use the partition concept for the odd degree nodes to find the different matchings.)
<img width="477" alt="pm" src="https://cloud.githubusercontent.com/assets/18632383/26078364/4dd17a34-398d-11e7-983c-ae1d24ba8e87.png">.

Now,add C-SC and SV-PA to the MST tree.

<img width="459" alt="perfectmatching" src="https://cloud.githubusercontent.com/assets/18632383/26078999/485b9de4-398f-11e7-89f8-debcdf25e7ad.png">.

The above graph represents the Eulerian Cycle which has the total cost 38.

### 3. Approximation of Eulerian Cycle

