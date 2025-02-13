import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import dsa.iface.IBinarySearchTree;
import dsa.iface.IIterator;
import dsa.iface.INode;
import dsa.impl.AVLTree;
import dsa.impl.BinarySearchTree;
import dsa.impl.SplayTree;
import dsa.util.TreePrinter;

public class Main {
	public static void main(String[] args ) {
		   /*
		    * Here is a test for AVL-Tree
		    * 
		    * The test contains three main parts:
		    * 	- Simple Test
		    * 	- Complex Test
		    * 	- More Complex Test
		    * 
		    * 
		    * */
		   System.out.println("---- ---- AVL Tree ---- ----");
		   IBinarySearchTree<Integer> avlt=new AVLTree<Integer>();
		   // The simple test:
		   System.out.println("---- START SIMPLE TEST -----");
		   //the test on insert() method:
		   System.out.println("test on insert() method");
		   
		   //Single Rotate Left Situation
		   		//unbalanced node on the root
		   System.out.println("Simple-Insert-SingleRotateLeft-OnRoot");
		   avlt.insert(10);
		   avlt.insert(20);
		   avlt.insert(30);
		   TreePrinter.printTree(avlt);
		   
		   		//unbalanced node not on the root 
		   System.out.println("Simple-Insert-SingleRotateLeft-NotOnRoot");
		   avlt=new AVLTree<Integer>();
		   avlt.insert(30);
		   avlt.insert(10);
		   avlt.insert(80);
		   avlt.insert(15);
		   avlt.insert(20);
		   TreePrinter.printTree(avlt);
		   	
		   	//Double Rotate Left Situation
		   		//unbalanced node on the root
		   System.out.println("Double-Insert-DoubleRotateLeft-OnRoot");
		   	avlt=new AVLTree<Integer>();
		   	avlt.insert(10);
		   	avlt.insert(20);
		   	avlt.insert(15);
		   	TreePrinter.printTree(avlt);
		   	
		   		//unbalanced node not on the root 
		   	System.out.println("Double-Insert-DoubleRotatedLeft-NotOnRoot");
		   	avlt=new AVLTree<Integer>();
		   	avlt.insert(50);
		   	avlt.insert(10);
		   	avlt.insert(80);
		   	avlt.insert(20);
		   	avlt.insert(15);
		   	TreePrinter.printTree(avlt);
		   	
		   	//Single Rotate Right Situation
		   		//unbalanced node on the root
			System.out.println("Simple-Insert-SingleRotateRight-OnRoot");
		   	avlt=new AVLTree<Integer>();
		   	avlt.insert(30);
		   	avlt.insert(20);
		   	avlt.insert(10);
		   	TreePrinter.printTree(avlt);
		   		//unbalanced node not on the root 
		   	System.out.println("Simple-Insert-SingleRotateRight-NotOnRoot");
		   	avlt=new AVLTree<Integer>();
		   	avlt.insert(50);
		   	avlt.insert(80);
		   	avlt.insert(30);
		   	avlt.insert(20);
		   	avlt.insert(10);
		   	TreePrinter.printTree(avlt);
			
			//Double Rotate Right Situation
		   		//unbalanced node on the root
		   	System.out.println("Simple-Insert-DoubleRotateLeft-OnRoot");
			avlt=new AVLTree<Integer>();
			avlt.insert(50);
			avlt.insert(30);
			avlt.insert(40);
			TreePrinter.printTree(avlt);
		   		//unbalanced node not on the root 
			System.out.println("Simple-Insert-DoubleRotateLeft-NotOnRoot");
			avlt=new AVLTree<Integer>();
			avlt.insert(10);
			avlt.insert(5);
			avlt.insert(50);
			avlt.insert(30);
			avlt.insert(40);
			TreePrinter.printTree(avlt);

			System.out.println();
		   	
		   	//the test on contains() method
		   	System.out.println("test on contains() method");
		   	avlt=new AVLTree<Integer>();
		   	avlt.insert(50);
		   	avlt.insert(10);
		   	avlt.insert(80);
		   	avlt.insert(20);
		   	avlt.insert(15);
		   	//if the node is in the tree
		   	System.out.println("Expected: true");
		   	System.out.print("The output is:");
		   	System.out.println(avlt.contains(50));
		   	
		   	//if the node not in the tree
		   	System.out.println("Expected:false");
		   	System.out.print("The output is:");
		   	System.out.println(avlt.contains(45));
		   	
		   	System.out.println();
		   	
		   	//the test on remove() method
		   	System.out.println("test on remove() method");
		   	//remove the node has two external nodes
		   	System.out.println("Simple-Remove-TwoExternalNode");
		   	avlt=new AVLTree<Integer>();
		   	avlt.insert( 44 );
		   	avlt.insert( 17 );
		   	avlt.insert( 62 );
		   	avlt.insert( 32 );
		   	avlt.insert( 50 );
		   	avlt.insert( 78 );
		   	avlt.insert( 48 );
		   	avlt.insert( 54 );
		   	avlt.insert( 88 );
		   	avlt.remove( 32 );
		   	TreePrinter.printTree(avlt);
		   	
		   	//remove the node has just one external node
		   	System.out.println("Simple-Remove-OneExternalNode");
		   	avlt=new AVLTree<Integer>();
		   	avlt.insert( 50 );
		   	avlt.insert( 25 );
		   	avlt.insert( 80 );
		   	avlt.insert( 10 );
		   	avlt.insert( 75 );
		   	avlt.insert( 85 );
		   	avlt.insert( 70 );
		   	avlt.insert( 90 );
		   	avlt.remove( 25 );
		   	TreePrinter.printTree(avlt);
		   	
		   	//remove the node doesn't has external node
		    System.out.println("Simple-Remove-NoExternalNode");
		   avlt=new AVLTree<Integer>();
		   avlt.insert( 50 );
		   avlt.insert( 25 );
		   avlt.insert( 80 );
		   avlt.insert( 10 );
		   avlt.insert( 20 );
		   avlt.insert( 75 );
		   avlt.insert( 85 );
		   avlt.insert( 70 );
		   avlt.insert( 90 );
		   avlt.remove( 25 );
		   TreePrinter.printTree(avlt);
		  
		   System.out.println();	
		   System.out.println("---- FINISH SIMPLE TEST -----");
		   
		   //complex test
		   System.out.println("---- START COMPLEX TEST -----");
		   //Input Tree	
		   AVLTree<Integer> avlt1=new AVLTree<Integer>();
		   	avlt1.insert( 50 );
		   	avlt1.insert( 25 );
		   	avlt1.insert( 80 );
		   	avlt1.insert( 10 );
		   	avlt1.insert( 75 );
		   	avlt1.insert( 85 );
		   	avlt1.insert( 70 );
		   	avlt1.insert( 90 );
		   	avlt1.remove( 25 );
		   	
		   //Expected Tree
		   	BinarySearchTree<Integer> bst=new BinarySearchTree<>();
		   	//AVLTree<Integer> avlt2=new AVLTree<Integer>();
		   	bst.insert(50);
		   	bst.insert(20);
		   	bst.insert(80);
		   	bst.insert(10);
		   	bst.insert(75);
		   	bst.insert(85);
		   	bst.insert(70);
		   	bst.insert(90);
		   System.out.println("Expected Output: true");
		   System.out.println("Reality output: "+compareTrees(avlt1,bst));
		   System.out.println("---- FINISH COMPLEX TEST -----");
		   
		   //more complex test
		   System.out.println("---- START MORE COMPLEX TEST -----");
		   moreComplexTestAVL();
		   System.out.println("---- FINISH MORE COMPLEX TEST -----");
		  
		   
		   /*
		    * Here is a test for SplayTree
		    * 
		    * The test contains three main parts:
		    * 	Simple Test
		    * 	Complex Test
		    * 	More Complex Test
		    * */
		   System.out.println("---- ---- Splay Tree ---- ----");
		   
	      IBinarySearchTree<Integer> st = new SplayTree<Integer>();
	      st.insert( 10 );
	      st.insert( 20 );
	      //zig
	      TreePrinter.printTree( st );
	      //zig-zig
	      st.insert( 40 );
	      TreePrinter.printTree( st );
	      st.remove( 20 );
	      //zig-zag
	      TreePrinter.printTree( st );
	      st.contains( 10 );
	      //test for contains method
	      TreePrinter.printTree( st );
	      st.contains( 30 );
	      TreePrinter.printTree( st );
	      //test insert method
	      st.insert( 30 );
	      TreePrinter.printTree( st );
	      st.insert( 80 );
	      TreePrinter.printTree( st );
	      st.insert( 20 );
	      TreePrinter.printTree(st);
	      st.remove(20);
	      //test remove method
	      TreePrinter.printTree( st );
	      
	      
		   System.out.println();	
		   System.out.println("---- FINISH SIMPLE TEST -----");
		   
		   //complex test
		   System.out.println("---- START COMPLEX TEST -----");
		   BinarySearchTree<Integer> bst1=new BinarySearchTree<>();
		   bst1.insert(40);
		   bst1.insert(30);
		   bst1.insert(80);
		   bst1.insert(10);
		   
		   System.out.println("Expected Output: true");
		   System.out.println("Reality output: "+compareTrees(st,bst1));
		   System.out.println("---- FINISH COMPLEX TEST -----");
		   
		   //more complex test
		   System.out.println("---- START MORE COMPLEX TEST -----");
		   moreComplexTestSplay();
		   System.out.println("---- FINISH MORE COMPLEX TEST -----");
		  
	   }
	   
	private static void moreComplexTestAVL() {
		   IBinarySearchTree<Integer> avlt=new AVLTree<Integer>();
		   try(BufferedReader br =new BufferedReader(new FileReader("textData.txt"))){
				String str=br.readLine();
				while(str!=null) {
					@SuppressWarnings("resource")
					Scanner scan=new Scanner(str);
					int element=scan.nextInt();
					String operation=scan.next();
					if(operation.equals("i")) {
						//i standing for insert
						avlt.insert(element);
					}
					if(operation.equals("c")) {
						//c standing for contains
						if(avlt.contains(element)) {
							System.out.println("AVLTree has "+element);
						}else {
							System.out.println("AVLTree doesn't have "+element);
						}
					}
					if(operation.equals("r")) {
						//r standing for remove
						avlt.remove(element);
					}
					if(operation.equals("")){
						System.out.println("All data has been read ");
					}
					str=br.readLine();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		   TreePrinter.printTree(avlt);
	   }
	   
	private static void moreComplexTestSplay() {
		   IBinarySearchTree<Integer>st=new SplayTree<Integer>();
		   try(BufferedReader br =new BufferedReader(new FileReader("textData.txt"))){
				String str=br.readLine();
				while(str!=null) {
					@SuppressWarnings("resource")
					Scanner scan=new Scanner(str);
					int element=scan.nextInt();
					String operation=scan.next();
					if(operation.equals("i")) {
						//i standing for insert
						st.insert(element);
					}
					if(operation.equals("c")) {
						//c standing for contains
						if(st.contains(element)) {
							System.out.println("SplayTree has "+element);
						}else {
							System.out.println("SplayTree doesn't have "+element);
						}
					}
					if(operation.equals("r")) {
						//r standing for remove
						st.remove(element);
					}if(operation.equals("")){
						System.out.println("All data has been read ");
					}
					str=br.readLine();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		   TreePrinter.printTree(st);
	   }
	   
	public static boolean compareTrees(IBinarySearchTree<Integer> bt1,IBinarySearchTree<Integer> bt2) {
		boolean equal=true;
		IIterator t1Children=bt1.children(bt1.root());
		IIterator<?> t2Children=bt2.children(bt2.root());
		while(t1Children.hasNext() && t2Children.hasNext()) {
			INode<Integer> temp1=(INode) t1Children.next();
			INode<Integer> temp2=(INode) t2Children.next();
			if(temp1.element()!=temp2.element()||//t1Children.next().equals(t2Children.next())||
			bt1.parent(temp1).element()!=bt2.parent(temp2).element()||
			bt1.right(temp1).element()!=bt2.right(temp2).element()||
			bt1.left(temp1).element()!=bt2.left(temp2).element()) {
				equal=false;
				break;
			}
		}
		return equal;
	}

}
