import java.util.*;

  public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E> 
{
  protected TreeNode<E> root;
  protected int size = 0;
  
  /** Create a default binary tree */
  public BinaryTree() {
  }

  /** Create a binary tree from an array of objects */
  public BinaryTree(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      insert(objects[i]);
  }

  /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return true; // Element is found
    }

    return false;
  }

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
    public boolean insert(E e) 
  {
      if(root==null)
        {
           root = createNewNode(e); // Create a new root
           root.parent = null;   
        }
      else 
        {
      // Locate the parent node
           TreeNode<E> parent = null;
           TreeNode<E> current = root;
           while(current!=null)
             {
                if(e.compareTo(current.element)<0) 
                  {
                     parent = current;
                     current = current.left;
                  }
                else if(e.compareTo(current.element)>0) 
                  {
                     parent = current;
                     current = current.right;
                  }
                else return false; // Duplicate node not inserted
             }
      // Create the new node and attach it to the parent node
           TreeNode<E> node = createNewNode(e);
           if(e.compareTo(parent.element)<0) parent.left = node;                            
           else parent.right = node;
           node.parent = parent;
        }

      size++;
      return true; // Element inserted
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<E>(e);
  }

    public void inorder() 
  {
      if(root==null) return;
      Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
      boolean fromStack = false;
      TreeNode<E> current = root;
      while(current!=null)
         {
            if(current.left!=null && !fromStack) 
              {
                 stack.push(current);
                 current = current.left;
                 fromStack = false;
              }
            else
              {
                 System.out.print(current.element + " ");
                 if(current.right!=null) 
                   {
                      current = current.right;
                      fromStack = false;
                   }
                 else if(stack.size()>0) 
                   {
                      current = stack.pop();
                      fromStack = true;
                   }
                 else current = null;
              }
         }
  }

    public void postorder() 
  {
      if(root==null) return;
      Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
      boolean fromStack = false;
      TreeNode<E> prior = null;
      TreeNode<E> current = root;
      while(true)
        {
           if(!fromStack)
             {
                if(current.left!=null) 
                  {
                     stack.push(current);
                     current = current.left;
                  }
                else if(current.right!=null)
                  {
                     stack.push(current);
                     current = current.right;
                  }
                else
                  {
                     System.out.print(current.element + " ");
                     if(stack.isEmpty()) return;
                     prior = current;
                     current = stack.pop();
                     fromStack = true;                
                  }
             }
           else
             {
                if(current.left==null || current.right==null)
                  {
                     System.out.print(current.element + " ");
                     if(stack.isEmpty()) return;
                     prior = current;
                     current = stack.pop();
                  }
                else
                  {
                     if(current.left==prior) 
                       {
                          stack.push(current);
                          current = current.right;
                          fromStack = false;
                       }
                     else if(current.right==prior)
                       {
                          System.out.print(current.element + " ");
                          if(stack.isEmpty()) return;
                          prior = current;
                          current = stack.pop();
                       }
                  }
             }
        }
  }

    public void preorder()
  {
      if(root==null) return;
      Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
      boolean fromStack = false;
      TreeNode<E> current = root;
      while(true)
         {
            if(!fromStack) 
              {
                 System.out.print(current.element + " ");
                 if(current.left!=null && current.right!=null)
                   {
                      stack.push(current);
                      current = current.left;
                   }
                 else if(current.left!=null) current = current.left;
                 else if(current.right!=null) current = current.right;
                 else 
                   {
                      if(stack.isEmpty()) return;
                      current = stack.pop();
                      fromStack = true;
                   }
              }
            else
              {
                 current = current.right;
                 fromStack = false;
              }
         }    
  }

  /** Inner class tree node */
  public static class TreeNode<E extends Comparable<E>> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;
    TreeNode<E> parent;

    public TreeNode(E e) {
      element = e;
    }
    
    public String getElement()
  {
      return element.toString();
  }
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode getRoot() {
    return root;
  }

  /** Returns a path from the root leading to the specified element */
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list =
      new java.util.ArrayList<TreeNode<E>>();
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      list.add(current); // Add the node to the list
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else
        break;
    }

    return list; // Return an array of nodes
  }

  /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // Element is in the tree pointed by current
    }

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left children
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;     
    }

    size--;
    return true; // Element inserted
  }

  /** Obtain an iterator. Use inorder. */
  public InorderIterator iterator() {
    return inorderIterator();
  }

  /** Obtain an inorder iterator */
  public InorderIterator inorderIterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  class InorderIterator implements java.util.Iterator {
    // Store the elements in a list
    private java.util.ArrayList<TreeNode<E>> list =
      new java.util.ArrayList<TreeNode<E>>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root*/
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null)return;
      inorder(root.left);
      //list.add(root.element);
      list.add(root);
      inorder(root.right);
    }

    /** Next element for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;

      return false;
    }

    /** Get the current element and move cursor to the next */
    public Object next() {
      return list.get(current++);
    }

    /** Remove the current element and refresh the list */
    public void remove() {
      delete(list.get(current).element); // Delete the current element
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }
  
    public void breadthFirstTraversal()
  {
      if(root==null) return;
      Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
      queue.offer(root);
      while(queue.size()!=0)
        {
           TreeNode<E> node = queue.remove();
           System.out.print(node.element + " ");
           if(node.left!=null) queue.offer(node.left);
           if(node.right!=null) queue.offer(node.right);
        }
  }
  
    public int height()
  {
      int height = 0;
      InorderIterator iterator = iterator();
      while(iterator.hasNext())
         {
            ArrayList<TreeNode<E>> currentList = path(((TreeNode<E>)iterator.next()).element);
            //ArrayList<TreeNode<E>> currentList = path((E)iterator.next());
            if(currentList.size()>height) height = currentList.size();
         }
      return height;
  }
    
    public int getNumberOfNonLeaves()
  {
      if(root==null) return 0;
      int count = 0;
      Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
      queue.offer(root);
      while(queue.size()!=0)
        {
           TreeNode<E> node = queue.remove();
           if(!isLeaf(node)) 
             {
                count++;
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
             }
        }
      return count;
  }
    
    public boolean isLeaf(TreeNode<E> node)
  {
      return node.left==null && node.right==null;
  }
    
    public int getNumberOfLeaves()
  {
      return getSize() - getNumberOfNonLeaves(); 
  }
    
    public boolean isCompleteBinaryTree()
  {
      if(root==null) return true;
      boolean leafStarted = false;
      Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
      queue.offer(root);
      while(queue.size()!=0)
        {
           TreeNode<E> node = queue.remove();
           if(node.left!=null && node.right!=null)
             {
                if(leafStarted) return false;
                else 
                  {
                    queue.offer(node.left);
                    queue.offer(node.right);  
                  }
             }
           else if(node.left==null && node.right!=null) return false; 
           else if(node.left!=null && node.right==null)
             {
                if(leafStarted) return false;
                else
                  {
                     leafStarted = true;
                     queue.offer(node.left);
                  }
             }
           else
             {
                if(!leafStarted) leafStarted = true;
             }
             
        }
      return true;
  }
    
    public TreeNode<E> getParent(TreeNode<E> node)
  {
      E e = node.element;
      TreeNode<E> current = root; // Start from the root
      while(current!=null) 
        {
           if(e.compareTo(current.element)<0) current = current.left;
           else if(e.compareTo(current.element)>0) current = current.right;
           // element matches current.element
           else return current.parent; // Element is found
        }

      return null;
  }    
    
    public ArrayList<TreeNode<E>> getPath(TreeNode<E> node)
  { 
      return path(node.element);
  }
}
