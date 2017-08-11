package com.example.demo.util;

/**
 * Created by evan.qi on 6/14/2017.
 */
public class Test {
	public static void main(String[] args) {
		Node node = new Node();
		node.setAge(8);
		node = generator(node,4);
		getAge(node);
		node = revert2(node);
		System.out.println();
		getAge(node);



	}

	private static Node generator(Node node,int i){
		if(i>0){
			i--;
			Node next = new Node();
			next.setAge(i);
			Node tmp = generator(next,i);
			node.setNext(tmp);
		}

		return node;
	}



	private static void getAge(Node node){
		System.out.printf(node.getAge()+"->");
		if(node.getNext()!=null){
			getAge(node.getNext());
		}

	}
	//递归法

	private static Node revert(Node node){
		if(node.getNext()==null){
			return node;
		}
		 Node preNode = revert(node.getNext());
		 Node temp = node.getNext();
		 temp.setNext(node);
		 node.next = null;
		 return preNode;
	}
	//循环法

	private static Node revert2(Node node){
		Node next = null;
		Node pre = null;
		while(node!=null){
			next = node.getNext();
			node.next=pre;
			pre = node;
			node = next;
		}
		return pre;
	}
}
