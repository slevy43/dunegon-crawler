public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

    // Instance variables
    private DLinkedNode<T> front;
    private DLinkedNode<T> rear;
    private int count;

    // Constructor
    public DLPriorityQueue() {
        front = null;
        rear = null;
        count = 0; }

    // Additional method
    public DLinkedNode<T> getRear() {
        return rear; }

    // Implemented methods
    @Override
    public void add(T dataItem, double priority) {
        // Making new node to be added
        DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);

        // If Queue is empty
        if (isEmpty()) {
            front = newNode;
            rear = newNode;

        // If Queue has items
        } else {
            DLinkedNode<T> current = front; // starting loop at front

            // Traversing to the appropriate node
            while (current != null && priority >= current.getPriority()) {
                current = current.getNext();}

            // 1: If the new node belongs at the front of the list
            if (current == front) {
                newNode.setNext(front);
                front.setPrev(newNode);
                front = newNode; }

            //  2: If the new node belongs after the last node
            else if (current == null) {
                rear.setNext(newNode);
                newNode.setPrev(rear);
                rear = newNode; }

            // 3: Otherwise, inserting node in appropriate place
            else {
            newNode.setNext(current);             // a) newNode Next-points to node it is behind
            newNode.setPrev(current.getPrev());   // b) newNode Prev-points to node it is after
            current.setPrev(newNode);             // c) node it is behind Prev-points to newNode
            newNode.getPrev().setNext(newNode);}  // d) node before newNode now Next-points to newNode

        } // else: isEmpty() == false

        count++;

    } // add() end


    @Override
    public T removeMin() throws EmptyPriorityQueueException {
        // If the queue is empty:
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Priority queue is empty."); }

        // Node to be returned
        T minimumPriority = front.getDataItem();

        // Checking if there is a node after head:
        if (front.getNext() != null) {      //              node after head: front → node1 → node2
            front = front.getNext(); // [front] now refers to the next node: node1 → front → node2
            front.setPrev(null);     // setting null behind new [front]:     null → front → node

        // If there is only one node in the queue
        } else {//          only node: [front →] node1 [← rear] → null
            front = null;
            rear = null;}      // now: [front →] null [← rear] → null

        count--;
        return minimumPriority; }

    @Override
    public void updatePriority(T data, double newPriority) throws InvalidElementException {
        DLinkedNode<T> current = front; // starting loop at front
        boolean nodeExists = false;

        // Traversing to the appropriate node
        while (current != null) {

            // Found the node to be updated
            if (current.getDataItem() == data || current.getDataItem().equals(data)) {

                // 1: Node is only node in the list
                if (current == front && current == rear) {
                    nodeExists = true;
                    front = null;
                    rear = null; }

                // 2: Node is the head
                else if (current == front) {
                    nodeExists = true;
                    front = current.getNext();
                    front.setPrev(null); }

                // 3: Node is the rear
                else if (current == rear) {
                    nodeExists = true;
                    rear = current.getPrev();
                    rear.setNext(null); }

                // 4: Node is at some point in the list
                else {
                    nodeExists = true;
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev()); }

            } // found node

            // If not found, go to the next node
            current = current.getNext(); }

        // If the node wasn't found, throw exception
        if (!nodeExists) {
            throw new InvalidElementException("given dataItem is not in the priority queue.");}

        // Adding node back in, in appropriate order
        add(data, newPriority);
        count--;

    } // update() end

    @Override
    public boolean isEmpty() {
        // Checking if queue has no nodes
        return front == null;}

    @Override
    public int size() {
        return count;}

    @Override
    public String toString() {
        String stringRep = "";
        DLinkedNode<T> current = front; // starting loop at front

        // Traversing to the appropriate node
        while (current != null) {
            stringRep += current.toString();
            current = current.getNext();}

        // Returning string of concatenated objects
        return stringRep;}

} // DLPriorityQueue