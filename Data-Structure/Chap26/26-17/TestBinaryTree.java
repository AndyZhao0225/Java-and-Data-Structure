import java.util.*;

public class TestBinaryTree {
  public static void main(String[] args) {
    // Create a BinaryTree
    Integer[] numbers = {60,55,100,45,57,59,67,107,101};
    BinaryTree<Integer> tree = new BinaryTree<Integer>(numbers);
    
    Integer[] numbers2 = {10,6,14,16,8,12,11,4,3};
    BinaryTree<Integer> tree2 = new BinaryTree<Integer>(numbers2);
    
//    tree.insert("George");
//    tree.insert("Michael");
//    tree.insert("Tom");
//    tree.insert("Adam");
//    tree.insert("Jones");
//    tree.insert("Peter");
//    tree.insert("Daniel");

    // Traverse tree
    System.out.print("Inorder (sorted): \n");
    tree2.inorder();
    System.out.print("\nPostorder: \n");
    tree2.postorder();
    System.out.print("\nPreorder: \n");
    tree2.preorder();
    System.out.print("\nBreadthFirstTraversal(): \n");
    tree.breadthFirstTraversal();
    
    BinaryTree.TreeNode<Integer> node = new BinaryTree.TreeNode<Integer>(10);
    if(tree2.search(node.element))
      {
          BinaryTree.TreeNode nodeParent = tree2.getParent(node);
          if(nodeParent==null) System.out.println("\n\n" + node.getElement() + " in tree2 has no parent");
          else System.out.println("\n\n" + node.getElement() + "'s parent in tree2 is " + nodeParent.getElement());
          
          ArrayList<BinaryTree.TreeNode<Integer>> list = tree2.getPath(node);
          for( int i=0; i<=list.size()-1; i++)
              System.out.println(list.get(i).element);
      }
    else System.out.println("\n\nThere isn't such node in tree2");
    
    BinaryTree<Integer> tree3 = new BinaryTree<Integer>();
    for( int i=1; i<=100; i++)
        tree3.insert(i);
    BinaryTree.InorderIterator iterator = tree3.inorderIterator();
    while(iterator.hasNext())
      {
         node = (BinaryTree.TreeNode<Integer>)iterator.next();
         if(tree3.isLeaf(node)) 
            {
               ArrayList<BinaryTree.TreeNode<Integer>> list = tree3.getPath(node); 
               System.out.println(node.element + "'s path");
               for( int j=0; j<=list.size()-1; j++)
                   System.out.print(list.get(j).element + " ");
               System.out.println();       
            }
      }
//    for( int i=1; i<=100; i++)
//       {
//          
//          node = new BinaryTree.TreeNode<Integer>(i);
//          if(tree3.isLeaf(node)) 
//            {
//               ArrayList<BinaryTree.TreeNode<Integer>> list = tree3.getPath(node); 
//               System.out.println(node.element + "'s path");
//               for( int j=0; j<=list.size()-1; j++)
//                   System.out.print(list.get(j).element + " ");
//               System.out.println();       
//            }
//       }      
//    
    
    System.out.print("\nThe number of nodes is " + tree.getSize() + "\n");
    System.out.print("\nThe height of the tree is " + tree.height() + "\n");
    System.out.print("\nThe number of non-leaves is " + tree.getNumberOfNonLeaves() + "\n");
    System.out.print("\nThe number of leaves is " + tree.getNumberOfLeaves() + "\n");
//    System.out.print("\nThe number of non-leaves is " + tree2.getNumberOfNonLeaves() + "\n");
    System.out.print("\nThe number of leaves is " + tree2.getNumberOfLeaves() + "\n");
    
    System.out.print("\nIs tree a complete binary tree? " + tree.isCompleteBinaryTree() + "\n");
    System.out.print("\nIs tree a complete binary tree2? " + tree2.isCompleteBinaryTree() + "\n");

//    // Search for an element
//    System.out.print("\nIs Peter in the tree? " + 
//      tree.search("Peter"));

    // Get a path from the root to Peter
//    System.out.print("\nA path from the root to Peter is: ");
//    java.util.ArrayList<BinaryTree.TreeNode<String>>  path 
//      = tree.path("Peter");
//    for (int i = 0; path != null && i < path.size(); i++)
//      System.out.print(path.get(i).element + " ");
//
//    Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
//    BinaryTree<Integer> intTree = new BinaryTree<Integer>(numbers);
//    System.out.print("\nInorder (sorted): ");
//    intTree.inorder();
  }
}
