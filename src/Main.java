import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import dsa.iface.IBinarySearchTree;
import dsa.impl.AVLTree;
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
							System.out.println("AVLTree has "+element);
						}else {
							System.out.println("AVLTree doesn't have "+element);
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
	   
	   
//   public static void main(String[] args ) {
//	   /*
//	    * Here is a test for AVL-Tree
//	    * 
//	    * The test contains three main parts:
//	    * 	- Simple Test
//	    * 	- Complex Test
//	    * 	- More Complex Test
//	    * 
//	    * 
//	    * */
//	   System.out.println("---- ---- AVL Tree ---- ----");
//	   IBinarySearchTree<Integer> avlt=new AVLTree<Integer>();
//	   // The simple test:
//	   System.out.println("---- START SIMPLE TEST -----");
//	   //the test on insert() method:
//	   System.out.println("test on insert() method");
//	   
//	   //Single Rotate Left Situation
//	   		//unbalanced node on the root
//	   System.out.println("Simple-Insert-SingleRotateLeft-OnRoot");
//	   avlt.insert(10);
//	   avlt.insert(20);
//	   avlt.insert(30);
////	   TreePrinter.printTree(avlt);
////	   
////	   		//unbalanced node not on the root 
////	   System.out.println("Simple-Insert-SingleRotateLeft-NotOnRoot");
////	   avlt=new AVLTree<Integer>();
////	   avlt.insert(30);
////	   avlt.insert(10);
////	   avlt.insert(80);
////	   avlt.insert(15);
////	   avlt.insert(20);
////	   TreePrinter.printTree(avlt);
////	   	
////	   	//Double Rotate Left Situation
////	   		//unbalanced node on the root
////	   System.out.println("Double-Insert-DoubleRotateLeft-OnRoot");
////	   	avlt=new AVLTree<Integer>();
////	   	avlt.insert(10);
////	   	avlt.insert(20);
////	   	avlt.insert(15);
////	   	TreePrinter.printTree(avlt);
////	   	
////	   		//unbalanced node not on the root 
////	   	System.out.println("Double-Insert-DoubleRotatedLeft-NotOnRoot");
////	   	avlt=new AVLTree<Integer>();
////	   	avlt.insert(50);
////	   	avlt.insert(10);
////	   	avlt.insert(80);
////	   	avlt.insert(20);
////	   	avlt.insert(15);
////	   	TreePrinter.printTree(avlt);
////	   	
////	   	//Single Rotate Right Situation
////	   		//unbalanced node on the root
////		System.out.println("Simple-Insert-SingleRotateRight-OnRoot");
////	   	avlt=new AVLTree<Integer>();
////	   	avlt.insert(30);
////	   	avlt.insert(20);
////	   	avlt.insert(10);
////	   	TreePrinter.printTree(avlt);
////	   		//unbalanced node not on the root 
////	   	System.out.println("Simple-Insert-SingleRotateRight-NotOnRoot");
////	   	avlt=new AVLTree<Integer>();
////	   	avlt.insert(50);
////	   	avlt.insert(80);
////	   	avlt.insert(30);
////	   	avlt.insert(20);
////	   	avlt.insert(10);
////	   	TreePrinter.printTree(avlt);
////		
////		//Double Rotate Right Situation
////	   		//unbalanced node on the root
////	   	System.out.println("Simple-Insert-DoubleRotateLeft-OnRoot");
////		avlt=new AVLTree<Integer>();
////		avlt.insert(50);
////		avlt.insert(30);
////		avlt.insert(40);
////		TreePrinter.printTree(avlt);
////	   		//unbalanced node not on the root 
////		System.out.println("Simple-Insert-DoubleRotateLeft-NotOnRoot");
////		avlt=new AVLTree<Integer>();
////		avlt.insert(10);
////		avlt.insert(5);
////		avlt.insert(50);
////		avlt.insert(30);
////		avlt.insert(40);
////		TreePrinter.printTree(avlt);
////
////		System.out.println();
////	   	
////	   	//the test on contains() method
////	   	System.out.println("test on contains() method");
////	   	avlt=new AVLTree<Integer>();
////	   	avlt.insert(50);
////	   	avlt.insert(10);
////	   	avlt.insert(80);
////	   	avlt.insert(20);
////	   	avlt.insert(15);
////	   	//if the node is in the tree
////	   	System.out.println("Expected: true");
////	   	System.out.print("The output is:");
////	   	System.out.println(avlt.contains(50));
////	   	
////	   	//if the node not in the tree
////	   	System.out.println("Expected:false");
////	   	System.out.print("The output is:");
////	   	System.out.println(avlt.contains(45));
////	   	
////	   	System.out.println();
////	   	
////	   	//the test on remove() method
////	   	System.out.println("test on remove() method");
////	   	//remove the node has two external nodes
////	   	System.out.println("Simple-Remove-NoExternalNode");
////	   	avlt=new AVLTree<Integer>();
////	   	avlt.insert( 44 );
////	   	avlt.insert( 17 );
////	   	avlt.insert( 62 );
////	   	avlt.insert( 32 );
////	   	avlt.insert( 50 );
////	   	avlt.insert( 78 );
////	   	avlt.insert( 48 );
////	   	avlt.insert( 54 );
////	   	avlt.insert( 88 );
////	   	avlt.remove( 32 );
////	   	TreePrinter.printTree(avlt);
////	   	
////	   	//remove the node has just one external node
////	   	System.out.println("Simple-Remove-OneExternalNode");
////	   	avlt=new AVLTree<Integer>();
////	   	avlt.insert( 50 );
////	   	avlt.insert( 25 );
////	   	avlt.insert( 80 );
////	   	avlt.insert( 10 );
////	   	avlt.insert( 75 );
////	   	avlt.insert( 85 );
////	   	avlt.insert( 70 );
////	   	avlt.insert( 90 );
////	   	avlt.remove( 25 );
////	   	TreePrinter.printTree(avlt);
////	   	
////	   	//remove the node doesn't has external node
////	    System.out.println("Simple-Remove-OneExternalNode");
////	   avlt=new AVLTree<Integer>();
////	   avlt.insert( 50 );
////	   avlt.insert( 25 );
////	   avlt.insert( 80 );
////	   avlt.insert( 10 );
////	   avlt.insert( 20 );
////	   avlt.insert( 75 );
////	   avlt.insert( 85 );
////	   avlt.insert( 70 );
////	   avlt.insert( 90 );
////	   avlt.remove( 25 );
////	   TreePrinter.printTree(avlt);
////	  
////	   System.out.println();	
////	   System.out.println("---- FINISH SIMPLE TEST -----");
////	   
////	   //complex test
////	   System.out.println("---- START COMPLEX TEST -----");
////	   
////	   System.out.println("---- FINISH COMPLEX TEST -----");
////	   
//	   //more complex test
//	   System.out.println("---- START MORE COMPLEX TEST -----");
//	   moreComplexTestAVL();
//	   System.out.println("---- FINISH MORE COMPLEX TEST -----");
////	  
////	   
////	   /*
////	    * Here is a test for SplayTree
////	    * 
////	    * The test contains three main parts:
////	    * 	Simple Test
////	    * 	Complex Test
////	    * 	More Complex Test
////	    * */
////	   System.out.println("---- ---- Splay Tree ---- ----");
////	   
////      IBinarySearchTree<Integer> st = new SplayTree<Integer>();
////      st.insert( 10 );
////      st.insert( 20 );
////      //zig
////      TreePrinter.printTree( st );
////      //zig-zig
////      st.insert( 40 );
////      TreePrinter.printTree( st );
////      st.remove( 20 );
////      //zig-zag
////      TreePrinter.printTree( st );
////      st.contains( 10 );
////      //test for contains method
////      TreePrinter.printTree( st );
////      st.contains( 30 );
////      TreePrinter.printTree( st );
////      //test insert method
////      st.insert( 30 );
////      TreePrinter.printTree( st );
////      st.insert( 80 );
////      TreePrinter.printTree( st );
////      st.insert( 20 );
////      TreePrinter.printTree(st);
////      st.remove(20);
////      //test remove method
////      TreePrinter.printTree( st );
////      
////      
////	   System.out.println();	
////	   System.out.println("---- FINISH SIMPLE TEST -----");
////	   
////	   //complex test
//	   System.out.println("---- START COMPLEX TEST -----");
//	   
//	   System.out.println("---- FINISH COMPLEX TEST -----");
//	   
//	   //more complex test
//	   System.out.println("---- START MORE COMPLEX TEST -----");
//	   moreComplexTestSplay();
//	   System.out.println("---- FINISH MORE COMPLEX TEST -----");
//	  
//   }
//   
//   
//   private static void moreComplexTestAVL() {
//	   IBinarySearchTree<Integer> avlt=new AVLTree<Integer>();
//	   try(BufferedReader br =new BufferedReader(new FileReader("textData.txt"))){
//			String str=br.readLine();
//			while(str!=null) {
//				@SuppressWarnings("resource")
//				Scanner scan=new Scanner(str);
//				int element=scan.nextInt();
//				String operation=scan.next();
//				if(operation.equals("i")) {
//					//i standing for insert
//					avlt.insert(element);
//				}
//				if(operation.equals("c")) {
//					//c standing for contains
//					if(avlt.contains(element)) {
//						System.out.println("AVLTree has "+element);
//					}else {
//						System.out.println("AVLTree doesn't have "+element);
//					}
//				}
//				if(operation.equals("r")) {
//					//r standing for remove
//					avlt.remove(element);
//				}
//				if(operation.equals("")){
//					System.out.println("All data has been read ");
//				}
//				str=br.readLine();
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	   TreePrinter.printTree(avlt);
//   }
//   
//   private static void moreComplexTestSplay() {
//	   IBinarySearchTree<Integer>st=new SplayTree<Integer>();
//	   try(BufferedReader br =new BufferedReader(new FileReader("textData.txt"))){
//			String str=br.readLine();
//			while(str!=null) {
//				@SuppressWarnings("resource")
//				Scanner scan=new Scanner(str);
//				int element=scan.nextInt();
//				String operation=scan.next();
//				if(operation.equals("i")) {
//					//i standing for insert
//					st.insert(element);
//				}
//				if(operation.equals("c")) {
//					//c standing for contains
//					if(st.contains(element)) {
//						System.out.println("AVLTree has "+element);
//					}else {
//						System.out.println("AVLTree doesn't have "+element);
//					}
//				}
//				if(operation.equals("r")) {
//					//r standing for remove
//					st.remove(element);
//				}if(operation.equals("")){
//					System.out.println("All data has been read ");
//				}
//				str=br.readLine();
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	   TreePrinter.printTree(st);
//   }

}
