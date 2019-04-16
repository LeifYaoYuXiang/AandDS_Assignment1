import dsa.iface.IBinarySearchTree;
import dsa.impl.AVLTree;
import dsa.impl.SplayTree;
import dsa.util.TreePrinter;

public class Main {
   public static void main(String[] args ) {
      
	   IBinarySearchTree<Integer> avlt=new AVLTree<Integer>();
	   avlt.insert( 44 );
	   avlt.insert( 17 );
	   avlt.insert( 62 );
	   avlt.insert( 32 );
	   avlt.insert( 50 );
	   avlt.insert( 78 );
	   avlt.insert( 48 );
	   avlt.insert( 54 );
	   avlt.insert( 88 );
	   TreePrinter.printTree( avlt );
	   avlt.remove( 32 );
	   //avlt.insert( 40 );
//	   avlt.insert( 40 );
//	   avlt.insert( 10 );
//	   avlt.insert( 30 );
//	   //avlt.insert( 10 );
//	   avlt.insert( 20 );
	   TreePrinter.printTree( avlt );
	   //avlt.insert( 40 );
	   //avlt.insert(50);
	   //TreePrinter.printTree(avlt);
//	   System.out.println(avlt.root().element());
//	   TreePrinter.printTree( avlt );
//	   avlt.insert( 30 );
//	   TreePrinter.printTree( avlt );
//	   avlt.insert( 80 );
	   //avlt.insert( 20 );
     
//	   TreePrinter.printTree( avlt );

//      IBinarySearchTree<Integer> st = new SplayTree<Integer>();
//      st.insert( 10 );
//      st.insert( 20 );
//      st.insert( 40 );
//      st.remove( 20 );
//      st.contains( 10 );
//      st.contains( 30 );
//      st.insert( 30 );
//      st.insert( 80 );
//      st.insert( 20 );
//      
//      TreePrinter.printTree( st );
   }
}
