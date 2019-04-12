  public class MyLinkedList<E> extends MyAbstractList<E> 
{
  private Node<E> head, tail;

  /** Create a default list */
  public MyLinkedList() {
  }

  /** Create a list from an array of objects */
  public MyLinkedList(E[] objects) {
    super(objects);
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    }
    else {
      return head.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    }
    else {
      return tail.element;
    }
  }

  /** Add an element to the beginning of the list */
    public void addFirst(E e) 
  {
      Node<E> newNode = new Node<E>(e); // Create a new node
      newNode.next = head; // link the new node with the head
      newNode.previous = null;
      if(size>=1) head.previous = newNode;
      head = newNode; // head points to the new node
      size++; // Increase list size

      if(tail==null) tail = head;
  }

  /** Add an element to the end of the list */
    public void addLast(E e) 
  {
      Node<E> newNode = new Node<E>(e); // Create a new for element e
      if(tail==null) head = tail = newNode; // The new node is the only node in list
      else 
        {
           tail.next = newNode; // Link the new with the last node
           newNode.previous = tail;
           tail = tail.next; // tail now points to the last node
        }

      size++; // Increase size
  }


  /** Add a new element at the specified index in this list
   * The index of the head element is 0 */
    public void add(int index, E e) 
  {
      if(index==0) addFirst(e);
      else if(index>=size) addLast(e);
      else 
        {
           Node<E> current;
           Node<E> newNode = new Node<E>(e);
           if(index<=size/2)
             { 
                current = head;
                for( int i=1; i<=index-1; i++) 
                    current = current.next;
                Node<E> temp = current.next;
                current.next = newNode;
                newNode.previous = current;
                newNode.next = temp;
                temp.previous = newNode;
             }
           else
             {
                current = tail;
                for( int i=size; i>=index+1; i--) 
                    current = current.previous;
                Node<E> temp = current.previous;
                current.previous = newNode;
                newNode.next = current;
                newNode.previous = temp;
                temp.next = newNode;
             }
           size++;
        }
  }

  /** Remove the head node and
   *  return the object that is contained in the removed node. */
    public E removeFirst() 
  {
      if(size==0) return null;   
      else 
        {
           Node<E> temp = head;
           head = head.next;
           if(size>1) head.previous = null;
           size--;
           if(head==null) tail = null;
           return temp.element;
        }
  }

  /** Remove the last node and
   * return the object that is contained in the removed node. */
    public E removeLast() 
  {
      if(size==0) return null;
      else if(size==1) 
             {
                Node<E> temp = head;
                head = tail = null;
                size = 0;
                return temp.element;
             }
      else 
        {
           Node<E> temp = tail;
           tail = tail.previous;
           tail.next = null;
           size--;
           return temp.element;
        }
  }

  /** Remove the element at the specified position in this list.
   *  Return the element that was removed from the list. */
    public E remove(int index) 
  {
      if(index<0 || index>=size) return null;
      else if(index==0) return removeFirst();    
      else if(index==size-1) return removeLast();
      else 
        {
           Node<E> current;
           Node<E> temp;
           if(index<=size/2)
             { 
                current = head;
                for( int i=1; i<=index-1; i++) 
                    current = current.next;
                temp = current.next;
                current.next = (current.next).next;
                ((current.next).next).previous = current;
                
             }
           else
             {
                current = tail;
                for( int i=size; i>=index+1; i--) 
                    current = current.previous;
                temp = current.previous;
                current.previous = (current.previous).previous;
                ((current.previous).previous).next = current;
             }
           size--;
           return temp.element;
        }    
  }

  /** Override toString() to return elements in the list */
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      }
      else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  /** Clear the list */
  public void clear() {
    head = tail = null;
  }

  /** Return true if this list contains the element o */
    public boolean contains(E e) 
  {
      boolean found = false;  
      Node<E> current = head;
      while(current!=null)
           {
              if(current.element.equals(e))
                {
                   found = true;
                   break;
                }          
              current = current.next;
           }
      return found;
  }

  /** Return the element from this list at the specified index */
    public E get(int index) 
  {
      if(index<0 || index>=size) return null;
      if(index==0) return getFirst();
      if(index==size-1) return getLast();
      Node<E> current = head;
      for( int i=0; i <=index-1; i++) 
          current = current.next;
      return current.element;
  }

  /** Return the index of the head matching element in this list.
   *  Return -1 if no match. */
    public int indexOf(E e) 
  {
      Node<E> current = head;
      int index = 0;
      while(current!=null)
           {
              if(current.element.equals(e)) return index;        
              current = current.next;
              index++;
           }
      return -1;
  }

  /** Return the index of the last matching element in this list
   *  Return -1 if no match. */
    public int lastIndexOf(E e) 
  {
      Node<E> current = head;
      int index = 0;
      int maxIndex = -1;
      while(current!=null)
           {
              if(current.element.equals(e)) maxIndex = index;      
              current = current.next;
              index++;
           }
      return maxIndex;
  }

  /** Replace the element at the specified position in this list
   *  with the specified element. */
    public E set(int index, E e) 
  {
      E temp;
      if(index<0 || index>=size) return null;
      if(index==0) 
        {
           temp = head.element;
           head.element = e;
           return temp;
        }
      if(index==size-1) 
        {
           temp = tail.element;
           tail.element = e;
           return temp;
        }
      Node<E> current = head.next;
      for( int i=1; i <=index-1; i++) 
          current = current.next;
      temp = current.element;
      current.element = e;
      return temp;
  }

    public class Node<E> 
  {
      E element;
      Node<E> next;
      Node<E> previous; 

      public Node(E element) 
    {
        this.element = element;
    }
  }
}
