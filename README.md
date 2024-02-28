Graphical program that uses a doubly linked, priority queue  data structure to implement an algorithm that 
finds the shortest path to a target node while avoiding obstacles.

The program's theme is a "dungeon crawler" where a grid of hexagons appears on the screen. The premise is that a "knight"
traverses empty tiles to reach the end tile, while making sure to steer clear of at least 1 tile away from dragons.

It chooses the optimal path towards the end goal based on criteria that dictates the priority of the nearest tiles.
Basically, it judges which tiles and paths are the optimal path that will ensure the shortest path to the end,
while avoiding places it has already been, dead ends, and dragons.
