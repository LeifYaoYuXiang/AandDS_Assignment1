package dsa.impl;

import dsa.iface.INode;

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	public void insert( T value ) {
		   super.insert(value);
		   INode<T> n = find(this.root(),value); 
		   splay(n);
		   size+=2;
	      // TODO: Implement the insert(...) method.
	   }

	   public boolean contains( T value ) {
		   if(super.contains(value)) {
			   INode<T> n = find(this.root(),value); 
			   splay(n);
		   }
	      // TODO: Implement the contains(...) method.
	      return super.contains(value);
	   }

	   public void remove( T value ) {
		   INode<T> n = find(this.root(),value); 
		   if(isInternal(left(n))&&isInternal(right(n))) {
			   INode<T> t = n;
			   n = right(n);
			   while(isInternal(left(n))) {
				   n = left(n);
			   }
			   T temp = t.element();
			   replace(t,n.element());
			   replace(n,temp);
			   t = parent(n);
			   super.remove(n);
			   splay(t);
			   size-=2;
		   }
		   else {
			   if(isInternal(left(n))) {
				   n = left(n);
				   super.remove(value);
				   splay(parent(n)); 
				   size-=2;
			   }
			   else {
				   n = right(n);
				   super.remove(value);
				   splay(parent(n)); 
				   size-=2;
			   }
		   }
	      // TODO: Implement the remove(...) method.
	   }

	   private void splay( INode<T> n ) {
		   BTNode no = (BTNode) n;
		   while(!isRoot(n)) {
			   if(isRoot(parent(n))) {//zig
				   if(n==left(parent(n))) {//zig left
					   BTNode pno = (BTNode) parent(n);
					   BTNode rno = (BTNode) right(n);
					   pno.parent = no;
					   no.parent = null;
					   no.right = pno;
					   pno.left = rno;
					   rno.parent = pno;
					   root = no;
				   }
				   else if(n==right(parent(n))) {//zig right
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
			   else {//zig-zig or zig-zag
				   if(n==left(parent(n)) && parent(n)==left(parent(parent(n)))){//left zig-zig
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
				   else if(n==right(parent(n)) && parent(n)==right(parent(parent(n)))){//right zig-zig
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
				   else if(n==left(parent(n)) && parent(n)==right(parent(parent(n)))) {//zig-zag
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
				   else if(n==right(parent(n)) && parent(n)==left(parent(parent(n)))) {//reverse zig-zag
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
	      // TODO: Implement the splay(...) method.
	   }
}
