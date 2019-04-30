package dsa.impl;

import dsa.iface.INode;

/*Basic Idea:
 * - 1. When we insert one node, we use the insert method in binary search tree to put the element into the tree, 
 * 		 then splay the tree.
 * 
 * - 2. When we check whether the tree contains a value, we use contains method in the binary search tree to judge.
 * 		 If exist, splay the element; if not, splay the external node's parent.
 * 
 * - 3. When we remove one node, we find its position after removing. If it has two internal children, swap its 
 * 		 value with the next biggest node and then delete the next biggest node. Splay the actually deleted node's 
 * 		 parent after previous steps.	 
 */

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	
	/*@para:
	 * - value: the value we want to insert 
	 */
	public void insert( T value ) {
		   super.insert(value);
		   
		   //find the inserted node
		   INode<T> n = find(this.root(),value); 
		   splay(n);
		   size+=2;
	   }

	
	   /*@para:
		* - value: the value we want to check whether contained in the tree
		*/
	   public boolean contains( T value ) {
		   
		   //if contained, splay the node
		   if(super.contains(value)) {
			   INode<T> n = find(this.root(),value); 
			   splay(n);
		   }
		   
		   //if not, splay the node's parent
		   else {
			   INode<T> n = parent(find(this.root(),value)); 
			   splay(n);
		   }
	      return super.contains(value);
	   }

	   /*@para:
		* - value: the value we want to remove
		*/
	   public void remove( T value ) {
		   
		   INode<T> n = find(this.root(),value); 
		   if(this.isExternal(n)) {
			   System.err.println("Trying to delete an invalid element");
		   }else {
			 //if the node has two internal children
			   if(isInternal(left(n))&&isInternal(right(n))) {
				   
				   //get next biggest node
				   INode<T> t = n;
				   n = right(n);
				   while(isInternal(left(n))) {
					   n = left(n);
				   }
				   
				   //swap the element of the node and its next biggest node
				   T temp = t.element();
				   replace(t,n.element());
				   replace(n,temp);
				   t = parent(n);
				   
				   //remove the 'next biggest' node now containing the value we want to remove and splay its parent
				   super.remove(n);
				   splay(t);
				   size-=2;
			   }
			   
			   //if the node has one or less internal child
			   else {
				   if(!isRoot(n)) {
					   //if left node is internal
					   if(isInternal(left(n))) {
						   n = left(n);
						   super.remove(value);
						   splay(parent(n)); 
						   size-=2;
					   }
				   
					   //if right node is internal or there is no internal child
					   else {
						   n = right(n);
						   super.remove(value);
						   splay(parent(n)); 
						   size-=2;
					   }
				   }
				   else {
					   super.remove(value);
					   size-=2;
				   }
			   }
		   }
		   
	   }

	   
	   /*@para:
		* - n: the node where splaying starts
		*/
	   private void splay( INode<T> n ) {
		   BTNode no = (BTNode) n;
		   while(!isRoot(n)) {
			   
			   //zig
			   if(isRoot(parent(n))) {
				   
				   //zig left
				   if(n==left(parent(n))) {
					   BTNode pno = (BTNode) parent(n);
					   BTNode rno = (BTNode) right(n);
					   pno.parent = no;
					   no.parent = null;
					   no.right = pno;
					   pno.left = rno;
					   rno.parent = pno;
					   root = no;
				   }
				   
				   //zig right
				   else if(n==right(parent(n))) {
					   BTNode pno = (BTNode) parent(n);
					   BTNode lno = (BTNode) left(n);
					   pno.parent = no;
					   no.parent = null;
					   no.left = pno;
					   pno.right = lno;
					   lno.parent = pno;
					   root = no;
				   }
			   }
			   
			   //zig-zig or zig-zag
			   else {
				   
				   //left zig-zig
				   if(n==left(parent(n)) && parent(n)==left(parent(parent(n)))){
					   BTNode pno = (BTNode) parent(n);
					   BTNode rno = (BTNode) right(n);
					   BTNode ppno = (BTNode) parent(parent(n));
					   BTNode rpno = (BTNode) right(parent(n));
					   if(!isRoot(parent(parent(n)))) {
						   BTNode pppno = (BTNode) parent(parent(parent(n)));
						   no.parent = pppno;
						   if(ppno==pppno.left) {
							   pppno.left = no;
						   }
						   else {
							   pppno.right = no;
						   }
					   }
					   else {
						   no.parent = null;
						   root = no;
					   }
					   no.right = pno;
					   pno.parent = no;
					   pno.left = rno;
					   rno.parent = pno;
					   pno.right = ppno;
					   ppno.parent = pno;
					   ppno.left = rpno;
					   rpno.parent = ppno;
				   }
				   
				   //right zig-zig
				   else if(n==right(parent(n)) && parent(n)==right(parent(parent(n)))){
					   BTNode pno = (BTNode) parent(n);
					   BTNode lno = (BTNode) left(n);
					   BTNode ppno = (BTNode) parent(parent(n));
					   BTNode lpno = (BTNode) left(parent(n));
					   if(!isRoot(parent(parent(n)))) {
						   BTNode pppno = (BTNode) parent(parent(parent(n)));
						   no.parent = pppno;
						   if(ppno==pppno.left) {
							   pppno.left = no;
						   }
						   else {
							   pppno.right = no;
						   }
					   }
					   else {
						   no.parent = null;
						   root = no;
					   }
					   no.left = pno;
					   pno.parent = no;
					   pno.right = lno;
					   lno.parent = pno;
					   pno.left = ppno;
					   ppno.parent = pno;
					   ppno.right = lpno;
					   lpno.parent = ppno;
				   }
				   
				   //zig-zag
				   else if(n==left(parent(n)) && parent(n)==right(parent(parent(n)))) {
					   BTNode pno = (BTNode) parent(n);
					   BTNode lno = (BTNode) left(n);
					   BTNode rno = (BTNode) right(n);
					   BTNode ppno = (BTNode) parent(parent(n));
					   if(!isRoot(parent(parent(n)))) {
						   BTNode pppno = (BTNode) parent(parent(parent(n)));
						   no.parent = pppno;
						   if(ppno==pppno.left) {
							   pppno.left = no;
						   }
						   else {
							   pppno.right = no;
						   }
					   }
					   else {
						   no.parent = null;
						   root = no;
					   }
					   no.left = ppno;
					   ppno.parent = no;
					   ppno.right = lno;
					   lno.parent = ppno;
					   no.right = pno;
					   pno.parent = no;
					   pno.left = rno;
					   rno.parent = pno;
				   }
				   
				   //reverse zig-zag
				   else if(n==right(parent(n)) && parent(n)==left(parent(parent(n)))) {
					   BTNode pno = (BTNode) parent(n);
					   BTNode lno = (BTNode) left(n);
					   BTNode rno = (BTNode) right(n);
					   BTNode ppno = (BTNode) parent(parent(n));
					   if(!isRoot(parent(parent(n)))) {
						   BTNode pppno = (BTNode) parent(parent(parent(n)));
						   no.parent = pppno;
						   if(ppno==pppno.left) {
							   pppno.left = no;
						   }
						   else {
							   pppno.right = no;
						   }
					   }
					   else {
						   no.parent = null;
						   root = no;
					   }
					   no.left = pno;
					   pno.parent = no;
					   pno.right = lno;
					   lno.parent = pno;
					   no.right = ppno;
					   ppno.parent = no;
					   ppno.left = rno;
					   rno.parent = ppno;
				   }
			   }
		   }
	   }
}
