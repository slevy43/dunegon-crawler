public class FindShortestPath {

    // Helper method
    public static boolean neighborsDragon(Hexagon currentChamber) {
        // Looping 6 times, 1 for each potential neighbour
        for (int i = 0; i < 6; i++) {
            Hexagon neighbor = currentChamber.getNeighbour(i); // Current neighbour of corresponding [i] value

            // If there is a chamber there:
            if (neighbor != null) {
                // ...And if that chamber is a dragon: return True
                if (neighbor.isDragon()) {return true;} } }

        return false; } // Otherwise, return false

    // Main function of class
    public static void main (String[] args) {
        try {
            // Checks if main is called with at least 1 argument
            if (args.length < 1) {
                throw new Exception("No input file specified");};

            // Gets the name of the input file from command-line argument
            String dungeonFileName = args[0];

            // Creating [Dungeon] object, with args[0] parameter
            Dungeon theDungeon = new Dungeon(dungeonFileName);

            // First, create an empty priority queue of [Hexagon]'s
            DLPriorityQueue<Hexagon> priorityQueue = new DLPriorityQueue<>();

            // Get starting chamber from [Dungeon] and enqueue
            Hexagon startingChamber = theDungeon.getStart();
            startingChamber.markEnqueued();

            // Add starting chamber to the priority queue with priority = zero
            priorityQueue.add(startingChamber, 0.0);

            // Checking current chamber for cases:
            Hexagon currentChamber = theDungeon.getStart();

            // "While": (1) the priority queue is not empty, && the exit has not been found
            boolean exitFound = false;
            while (!priorityQueue.isEmpty() && !exitFound) {

                // Taking first chamber form priority queue
                currentChamber = priorityQueue.removeMin();

                // Mark lowest priority as dequeued
                currentChamber.markDequeued();

                    // 1: If [current] is the exit
                    if (currentChamber.isExit()) {
                        currentChamber.markExit();
                        exitFound = true;}

                    // 2: If [current] is a dragon chamber or neighbors one
                        if (currentChamber.isDragon() || neighborsDragon(currentChamber)) {
                            continue;}

                // Consider neighbours that aren't: null, wall, dequeued
                for (int i = 0; i < 6; i++) {

                    // Current neighbour of corresponding [i] value
                    Hexagon neighbor = currentChamber.getNeighbour(i);

                    // If the current neighbour satisfies the criteria:
                    if (neighbor != null && !neighbor.isWall() && !neighbor.isMarkedDequeued() && !neighbor.isDragon() && !neighborsDragon(neighbor)) {

                        // d: [current]'s distance from initial chamber + 1
                        int d = (1 + currentChamber.getDistanceToStart());

                        // 1: If distance of [neighbour] to initial chamber is larger than [d]
                            if (neighbor.getDistanceToStart() > d) {      // if [neighbour]'s distance > d
                                neighbor.setDistanceToStart(d);           // [neighbour]'s distance set to d
                                neighbor.setPredecessor(currentChamber);} // [current] as [neighbour]'s predecessor

                            // 2: If neighbour = enqueued, + distance from start was modified
                            if (neighbor.isMarkedEnqueued() && neighbor.getDistanceToStart() == d) {
                                double newPriority = (neighbor.getDistanceToStart() + neighbor.getDistanceToExit(theDungeon));
                                priorityQueue.updatePriority(neighbor, newPriority);}

                            // 3: Otherwise: neighbour != enqueued
                            if (!neighbor.isMarkedEnqueued()) {
                                double newPriority = (neighbor.getDistanceToStart() + neighbor.getDistanceToExit(theDungeon));
                                priorityQueue.add(neighbor, newPriority);
                                neighbor.markEnqueued();}

                    } // if: criteria

                } // for: neighbours

            } // while: exit not found

            // Counter for each chamber in final path
            int pathLength = 0;

            // If an end is never reached
            if (!exitFound) {
                System.out.println("No path found");}

            // If an end is reached
            else {
                while (!currentChamber.isStart()) { // End loop at starting tile
                    currentChamber = currentChamber.getPredecessor();
                    ++pathLength;} // +1 for every tile iterated over
                System.out.println("Path of length " + (pathLength+1) + " found.");}

            // Catching exception with message from [if] statement
        } catch (Exception e) {
            throw new RuntimeException(e);}

    } // main()

} // FindShortestPath
