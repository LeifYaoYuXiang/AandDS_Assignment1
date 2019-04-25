package dsa.impl;
import dsa.iface.INode;

/*Basic Idea:
 * - 1. We create a class called AVLNode which extends BTNode, such that it can contains the height information
 * 
 * - 2. We override some methods, e.g. find(),left(),right(), which can help us not to check instanceof each time
 * 
 * - 3.When we insert one node, first we insert it into the tree as we do in the Binary Search Tree normally,
 * 		as well as we change the height information in the related nodes.
 * 		Then we restructure this tree to check whether it is balanced or not.
 * 		If not, we check which node is unbalanced,and restructure the node, and calculate the height again.
 * 		We do the above until we reach the root
 * 
 * - 4.When we check the tree contain a node or not, we use the "contain" method extended from superclass 
 * 
 * - 5.When we remove a node from AVLTree, then we need to find the position of the node, then remove it as we 
 * 		do like we do in the Binary Search Tree, then change the height of nodes of parents of ancestors of this
 * 		node. After that, we check the tree to see whether there are any unbalanced nodes 
 * 		We restructure unbalanced nodes, and calculate the height again.
 * 		We do the above until we reach the root
 * */

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	
	//Here is AVLClass which extends BTNode, which contains height information
	public class AVLNode extends BTNode{
		int height=0;
		public AVLNode(T e, AbstractBinaryTree<T>.BTNode p) {
			super(e, p);
		}
		
		public AVLNode(T e, AbstractBinaryTree<T>.BTNode p, AbstractBinaryTree<T>.BTNode l,
				AbstractBinaryTree<T>.BTNode r) {
			super(e, p, l, r);
		}

		public int getHeight() {
			return this.height;
		}
		
		public void setHeight(int height) {
			this.height=height;
		}
		
	}
	
	//Constructor of AVLTree
	public AVLTree() {
		this.root=new AVLNode(null,null,null,null);
	}
	
	/*@para:
	 * - element: the element we want to insert
	 * */
   @Override
   public void insert( T element ) {   
	   if(this.size==1) {
		   //it contains no element
		   this.root.element=element;
		   this.root.left=new AVLNode(null,this.root,null,null);
		   this.root.right=new AVLNode(null,this.root,null,null);
		   this.size+=2;
		   
		   this.changeHeight((AVLTree<T>.AVLNode) this.root);
	   }else {
		   //it has already contained some nodes
		   BTNode temp=(AbstractBinaryTree<T>.BTNode) this.find(root, element);
		   temp.element=element;
		   temp.left=new AVLNode(null,temp,null,null);
		   temp.right=new AVLNode(null,temp,null,null);
		   
		   //change the height of the ancestors of temp
		   this.changeHeight((AVLTree<T>.AVLNode) temp);
			   
		   this.size+=2;
	   }
	   //find the node containing inserted element and reconstruct the tree
	   INode<T> temp=super.find(this.root(), element);
	   this.restructure(temp);
   }
  
   /*@para:
	 * - start: the node we start our searching
	 * - value: the value we search for
	 * */
   @Override
   protected INode<T> find(INode<T> start, T value) {
	   if(isExternal(start)) {
	    	  return start;
	   }else {
	    	if(start.element().compareTo(value)>0) {
	    	  return this.find(left(start), value);
	    	}
	    	if(start.element().compareTo(value)<0) {
	    		return this.find(right(start), value);
	    	}else {
	    		return start;
	    	}
	   }
   }

   @Override
   public AVLNode left(INode iNode) {
	   if(iNode instanceof AVLTree.AVLNode) {
		   return (AVLTree<T>.AVLNode) ((AVLTree.AVLNode) iNode).left;
	   }else {
		   System.err.println("CAST ERROR in find left");
		   return null;
	   } 
   }
   
   @Override
   public AVLNode right(INode iNode) {
	   if(iNode instanceof AVLTree.AVLNode) {
		   return (AVLTree<T>.AVLNode) ((AVLTree.AVLNode) iNode).right;
	   }else {
		   System.err.println("CAST ERROR in find right");
		   return null;
	   } 
   }
   
   @Override
   public AVLNode parent(INode iNode) {
	   if(iNode instanceof AVLTree.AVLNode) {
		   return (AVLTree<T>.AVLNode) ((AVLTree.AVLNode) iNode).parent;
	   }else {
		   System.err.println("CAST ERROR in find parent");
		   return null;
	   } 
   }

   @Override
   public boolean contains( T element ) {
	   return super.contains(element);
   }
   
   /*@para:
	 * - element: the element we want to remove
	 * */
   @Override
   public void remove( T element ) {
	   INode<T> temp=find(this.root(),element);
	   AVLNode restructureStartNode=this.right(temp);
	   if(!this.isExternal(this.left(temp))&&!this.isExternal(this.right(temp))) {
		   //it doesn't have any external children
		   restructureStartNode=this.parent(this.nextBigger(restructureStartNode));
	   }else {
		   //it has one/two external children
		   restructureStartNode=this.parent(temp);
	   }
	   
	   super.remove(element);
	   
	   //change the height of ancestors of removed nodes and restructure the tree
	   this.changeHeight(restructureStartNode);
	   this.restructure(restructureStartNode);
   }
    
   /*@para:
	 * - restructureStartNode: whose parent 's next bigger node is finding
	 * */
   //nextBigger() is used to find the node whose element is next bigger than the paramater's parent
   private INode<T> nextBigger(INode<T> restructureStartNode) {
	   if(this.left(restructureStartNode).element==null) {
		   return restructureStartNode;
	   }else {
		   return this.nextBigger(this.left(restructureStartNode));
	   }
   }

   /*@para:
	 * - x: the node which we want to check whether it is unbalanced or root-reached
	 * */
  //check() is used to determine whether the node is needed to rotate. If so, which rotation type will be applied 
   private AVLNode check(INode<T> x) {
	   AVLNode temp=null;
	   if(x instanceof AVLTree.AVLNode) {
		   temp=(AVLTree<T>.AVLNode) x; 
		  
		   if(!this.isBanlanced(temp)) {
			   //check whether it is balanced or not
			   
			   int type=this.typeRotate(temp);
			   //check the rotate type
			   
			   boolean tempInLeft=false;
			   boolean reachRoot=false;
			   if(this.root==x) {
				   reachRoot=true;
			   }
			   if(!reachRoot) {
				   if(this.left(this.parent(temp))==temp) {
					   tempInLeft=true;
				   }
			   }
			   
			   if(type==1) {
				   this.singleRotateLeft(temp, reachRoot, tempInLeft);
			   }
			   if(type==2) {
				   this.rightLeftRotate(temp, reachRoot, tempInLeft);
			   }
			   if(type==3) {
				   this.leftRightRotate(temp, reachRoot, tempInLeft);
			   }
			   if(type==4) {
				   this.singleRotateRight(temp, reachRoot, tempInLeft);
			   }
		   }
	   }else {
		   System.err.println("CAST ERROR");
	   }
	   
	   if(temp.parent instanceof AVLTree.AVLNode) {
		   return (AVLTree<T>.AVLNode) temp.parent;
	   }else {
		   if(temp.element==this.root.element) {
			   
		   }else {
			   System.err.println("CAST ERROR");
		   }
		   return null;
	   }
   }
   
   /*@para:
	 * - x: the node which we start our restructure journey
	 * */
   //check() is used to determine whether a restructure is needed in our tree by using recursive method 
   private void restructure( INode<T> x ) {
	   if(this.root==x) {
		   //reach the root
		   check(x);
		   return;
	   }
	   AVLNode nextCheck=check(x);
	   this.restructure(nextCheck);
   }
   
   /*@para:
	 * - x: the node which is used to check it is balanced or not
	 * */
   //isBalanced() is used to check whether it is balanced or not
   private boolean isBanlanced(AVLNode x) {
	   if(this.left(x) instanceof AVLTree.AVLNode&&this.right(x) instanceof AVLTree.AVLNode ) {
		   if(Math.abs(((AVLNode)this.left(x)).height-((AVLNode)this.right(x)).height)<=1) {
			   return true;
		   }else {
			   return false;
		   }
	   }else {
		   System.err.println("Error Cast");
		   return false;
	   }	   
   }
   
   /*@para:
	 * - x: the node which is used to decide which rotate type will be applied
	 * */
  //typeRotate() is used to find the node which type of rotation will be applied
   private int typeRotate(INode<T> x) {
	   BTNode temp=(AbstractBinaryTree<T>.BTNode) x;
	   boolean firstChangeinLeft=false;
	   boolean secondChangeinLeft=false;
	      
	   int leftHeight=this.left(temp).height;
	   
	   int rightHeight=this.right(temp).height;

	   if(leftHeight>rightHeight) {
		   firstChangeinLeft=true;
		   
		   leftHeight=this.left(this.left(temp)).height;
		   rightHeight=this.right(this.left(temp)).height;

		   if(leftHeight>rightHeight) {
			   secondChangeinLeft=true;
		   }
	   }else {
		   leftHeight=this.left(this.right(temp)).height;
		   rightHeight=this.right(this.right(temp)).height;
			   
		   if(leftHeight>rightHeight) {
			   secondChangeinLeft=true;
		   }
			   
	   }
		if(firstChangeinLeft&&secondChangeinLeft) {
		   return 1;
		}
		if(!firstChangeinLeft&&secondChangeinLeft) {
		   return 2;
		}
		if(firstChangeinLeft&&!secondChangeinLeft) {
		   return 3;
		}
		if(!firstChangeinLeft&&!secondChangeinLeft) {
		   return 4;
		}  
	  return 0;
   }
      
   /*@para:
	 * - temp: the node which is used to decide which rotate type will be applied
	 * - reachRoot:whether the node is root or not
	 * - tempInLeft:whether the node is on the left of its parent
	 * */
   //singleRotateRight() is used to commit a single right rotation
   private INode<T> singleRotateRight(BTNode temp,boolean reachRoot,boolean tempInLeft) {
	   BTNode y=(AbstractBinaryTree<T>.BTNode) this.right(temp);
	   BTNode z=y.right;
	   if(!reachRoot) {
		   y.parent=(AbstractBinaryTree<T>.BTNode) this.parent(temp);
		   if(tempInLeft) {
			   y.parent.left=y;
		   }else {
			   y.parent.right=y;
		   }
	   }else {
		   this.root=y;
		   // y becomes the root
	   }
	   
	   temp.right=y.left;
	   temp.right.parent=temp;
	   //T2 is the right of x
	   
	   y.left=temp;
	   y.left.parent=y;
	   
	   //change the height
	   if(temp instanceof AVLTree.AVLNode && y instanceof AVLTree.AVLNode) {
		   ((AVLTree.AVLNode) temp).height=Math.max(this.left(temp).height,this.right(temp).height)+1;
		   ((AVLTree.AVLNode) y).height=Math.max(this.left(y).height, this.right(y).height)+1;
	   }else {
		   System.err.println("CAST ERROR");
	   }
	   
	   return y.parent;
   }
   
   /*@para:
	 * - temp: the node which is used to decide which rotate type will be applied
	 * - reachRoot:whether the node is root or not
	 * - tempInLeft:whether the node is on the left of its parent
	 * */
  //singleRotateLeft() is used to commit a single left rotation
   private INode<T> singleRotateLeft(BTNode temp, boolean reachRoot, boolean tempInLeft){
	   BTNode y=(AbstractBinaryTree<T>.BTNode) this.left(temp);
	   BTNode z=y.left;

	   if(!reachRoot) {
		   y.parent=(AbstractBinaryTree<T>.BTNode) this.parent(temp);
		   if(tempInLeft) {
			   y.parent.left=y;
		   }else {
			   y.parent.right=y;
		   }
	   }else {
		   this.root=y;
	   }

	   temp.left=y.right;
	   temp.left.parent=temp;
	   //T3 on the left of x
	   y.right=temp;
	   temp.parent=y;
	   //x on the right of y
	   
	   if(temp instanceof AVLTree.AVLNode && y instanceof AVLTree.AVLNode) {
		   ((AVLTree.AVLNode) temp).height=Math.max(this.left(temp).height,this.right(temp).height)+1;
		   ((AVLTree.AVLNode) y).height=Math.max(this.left(y).height, this.right(y).height)+1;
	   }else {
		   System.err.println("CAST ERROR");
	   }
	   
	   return y.parent;
   }
   
   /*@para:
	 * - temp: the node which is used to decide which rotate type will be applied
	 * - reachRoot:whether the node is root or not
	 * - tempInLeft:whether the node is on the left of its parent
	 * */
   //rightLeftRotate() is used to commit a right-left rotation
   private INode<T> rightLeftRotate(BTNode temp, boolean reachRoot, boolean tempInLeft){
	   BTNode y=(AbstractBinaryTree<T>.BTNode) this.right(temp);
	   BTNode z=y.left;
 
	   if(!reachRoot) {
		    z.parent=(AbstractBinaryTree<T>.BTNode) this.parent(temp);
		    if(tempInLeft) {
		    	z.parent.left=z;
		    }else {
		    	z.parent.right=z;
		    }
	   }else {
		   this.root=z;
	   }
	   //z on the top
	   
	   temp.right=z.left;
	   temp.right.parent=temp;
	   //T2 on the right of x
	   
	   y.left=z.right;
	   y.left.parent=y;
	   //T3 on the left of y
	   
	   z.left=temp;
	   z.left.parent=z;
	   //x on the left of z
	   
	   z.right=y;
	   z.right.parent=z;
	   //y is on the right of z
	   
	   if(temp instanceof AVLTree.AVLNode && y instanceof AVLTree.AVLNode && z instanceof AVLTree.AVLNode) {
		   ((AVLTree.AVLNode) temp).height=Math.max(this.left(temp).height,this.right(temp).height)+1;
		   ((AVLTree.AVLNode) y).height=Math.max(this.left(y).height, this.right(y).height)+1;
		   ((AVLTree.AVLNode) z).height=Math.max(this.left(z).height, this.right(z).height)+1;
	   }else {
		   System.err.println("CAST ERROR");
	   }
	   
	   return z.parent;
   }
    
   /*@para:
  	 * - temp: the node which is used to decide which rotate type will be applied
  	 * - reachRoot:whether the node is root or not
  	 * - tempInLeft:whether the node is on the left of its parent
  	 * */
     //leftRightRotate() is used to commit a left-right rotation
   private INode<T> leftRightRotate(BTNode temp, boolean reachRoot, boolean tempInLeft){
	   BTNode y=(AbstractBinaryTree<T>.BTNode) this.left(temp);
	   BTNode z=y.right;
	   if(!reachRoot) {
		   z.parent=(AbstractBinaryTree<T>.BTNode) this.parent(temp); 
		   if(tempInLeft) {
			   z.parent.left=z;
		   }else {
			   z.parent.right=z;
		   }
	   }else {
		   this.root=z;
	   }
	  //z on the top
	   
	   y.right=z.left;
	   y.right.parent=y;
	   //T2 on the right of y
	   
	   temp.left=z.right;
	   temp.left.parent=temp;
	   //T3 on the left on X
	  
	   z.right=temp;
	   temp.parent=z;
	   //x on the right of Z
	   
	   z.left=y;
	   y.parent=z;
	   //y on the left of z
	   
	   if(temp instanceof AVLTree.AVLNode && y instanceof AVLTree.AVLNode && z instanceof AVLTree.AVLNode) {
		   ((AVLTree.AVLNode) temp).height=Math.max(this.left(temp).height,this.right(temp).height)+1;
		   ((AVLTree.AVLNode) y).height=Math.max(this.left(y).height, this.right(y).height)+1;
		   ((AVLTree.AVLNode) z).height=Math.max(this.left(z).height, this.right(z).height)+1;
	   }else {
		   System.err.println("CAST ERROR");
	   }
	   
	   return z.parent;
   }
 
   /*@para:
 	 * - temp: the node which is used to start to change height of its ancestors
 	 * */
    //changeHeight() is used to change the related nodes' height information while inserting or removing elements
   private void changeHeight(AVLNode temp) {
	   temp.height=Math.max(this.left(temp).getHeight(),this.right(temp).getHeight())+1;
	   if(temp.element==this.root.element) {
		   return;
	   }
	   if(temp.parent instanceof AVLTree.AVLNode) {
		   this.changeHeight((AVLTree<T>.AVLNode) temp.parent);  
	   }else {
		   System.err.println("CAST ERROR");
	   }	   
   }
   
}
