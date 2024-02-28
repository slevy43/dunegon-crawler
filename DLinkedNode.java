public class DLinkedNode<T> {

    // Instance variables
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;

    // Constructors
    public DLinkedNode () {
        dataItem = null;  // Empty node
        priority = 0.0;   // Priority set to zero
        next = null;
        prev = null; }

    public DLinkedNode (T data, double prio) {
        dataItem = data;  // Given data
        priority = prio;  // Given priority
        next = null;
        prev = null; }

    // Getters: retrieving instance variables
    public double getPriority() {
        return priority;}

    public T getDataItem() {
        return dataItem;}

    public DLinkedNode<T> getNext() {
        return next;}

    public DLinkedNode<T> getPrev() {
        return prev;}

    // Setters: modifying instance variables
    public void setPriority(double newPriority) {
        priority = newPriority;}

    public void setDataItem(T newDataItem) {
        dataItem = newDataItem;}

    public void setNext(DLinkedNode<T> newNode) {
        next = newNode;}

    public void setPrev(DLinkedNode<T> newNode) {
        prev = newNode;}

    // Helper: Clarifying toString for future classes
    public String toString() {
     return dataItem.toString();}


} // DLinkedNode