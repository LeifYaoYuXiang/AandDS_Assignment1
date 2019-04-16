package dsa.impl;

import dsa.iface.INode;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

   @Override
   public void insert( T element ) {
	   super.insert(element);
	   INode<T> temp=super.find(this.root(), element);
	   this.restructure(temp);
	   
   }

   @Override
   public boolean contains( T element ) {
	   return super.contains(element);
   }

   @Override
   public void remove( T element ) {
	   INode<T> temp=find(this.root(),element);
	   INode<T> restructureStartNode=this.parent(temp);
	   if(!this.isExternal(this.left(temp))&&!this.isExternal(this.right(temp))) {
		   restructureStartNode=this.parent(this.nextBigger(this.right(restructureStartNode)));
	   }
	   super.remove(element);
	   this.restructure(restructureStartNode);
   }
   
   private INode<T> nextBigger(INode<T> restructureStartNode) {
	   if(this.isExternal(this.left(restructureStartNode))) {
		   return restructureStartNode;
	   }else {
		   return this.nextBigger(this.left(restructureStartNode));
	   }
   }

   private BTNode check(INode<T> x) {
	   BTNode temp=(AbstractBinaryTree<T>.BTNode) x;
	   if(!this.banlanced(temp)) {
		   int type=this.typeRotate(temp);

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
			   this.typeOne(temp, reachRoot, tempInLeft);
		   }
		   if(type==2) {
			   this.typeTwo(temp, reachRoot, tempInLeft);
		   }
		   if(type==3) {
			   this.typeThree(temp, reachRoot, tempInLeft);
		   }
		   if(type==4) {
			   this.typeFour(temp, reachRoot, tempInLeft);
		   }
	   }
	return temp.parent;
   }
   
   private void restructure( INode<T> x ) {
	   if(this.root==x) {
		   System.out.println("查找到根元素");
		   System.out.println("这次re的元素是"+x.element());
		   check(x);
		   System.out.println("restructure完成");
		   return;
	   }
	   System.out.println("这次re的元素是"+x.element());
	   BTNode  nextCheck=check(x);
	   this.restructure(nextCheck);
   }
   
   private int height( INode<T> x) {
	   if(this.isExternal(x)){
			return 0;
		}
		int leftheight=height(this.left(x));
		int rightheight=height(this.right(x));
		return Math.max(leftheight, rightheight)+1;
   }
   
   private boolean banlanced(INode<T> x) {
	   if(Math.abs(this.height(this.left(x))-this.height(this.right(x)))<=1) {
		   return true;
	   }else {
		   return false;
	   }
	   
   }
   
   
   private int typeRotate(INode<T> x) {
	   BTNode temp=(AbstractBinaryTree<T>.BTNode) x;
	   boolean firstChangeinLeft=false;
	   boolean secondChangeinLeft=false;
	      
	   int leftHeight=this.height(this.left(temp));
	   int rightHeight=this.height(this.right(temp));

	   if(leftHeight>rightHeight) {
		   firstChangeinLeft=true;
		   
		   leftHeight=this.height(this.left(this.left(temp)));
		   rightHeight=this.height(this.right(this.left(temp)));

		   if(leftHeight>rightHeight) {
			   secondChangeinLeft=true;
		   }
	   }else {
		   leftHeight=this.height(this.left(this.right(temp)));
		   rightHeight=this.height(this.right(this.right(temp)));
			   
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
   
   private INode<T> typeFour(BTNode temp,boolean reachRoot,boolean tempInLeft) {
	   BTNode y=(AbstractBinaryTree<T>.BTNode) this.right(temp);
	   BTNode z=y.right;
	   System.out.println("y element "+y.element);
	   System.out.println("z element"+z.element);
 
	   if(!reachRoot) {
		   y.parent=(AbstractBinaryTree<T>.BTNode) this.parent(temp);
		   if(tempInLeft) {
			   y.parent.left=y;
		   }else {
			   y.parent.right=y;
			   //y is on the top 
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
	   
	   return y.parent;
   }
   
   private INode<T> typeOne(BTNode temp, boolean reachRoot, boolean tempInLeft){
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
	   
	   System.out.println("Type1 !");
	   
	   return y.parent;
   }
   
   private INode<T> typeTwo(BTNode temp, boolean reachRoot, boolean tempInLeft){
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
	   
	   System.out.println("Type2 !");
	   
	   return z.parent;
   }
    
   private INode<T> typeThree(BTNode temp, boolean reachRoot, boolean tempInLeft){
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
	   
	   System.out.println("Type3 !");
	   
	   return z.parent;
   }
 
   
   
}
