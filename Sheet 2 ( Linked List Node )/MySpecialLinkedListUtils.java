public class MySpecialLinkedListUtils {
	public static double[] summary(LinkedListNode head) {
		int size = 0;
		LinkedListNode node2 = head ;
		double [] calculations = {0,0,0,0,0} ; 
		if(node2.getNext() != null){
			node2 = node2.getNext() ;
			calculations[3] = node2.getValue();
			calculations[4] = node2.getValue();	
		}else return calculations ;
		node2 = head ;
		while (node2.getNext() != null) {
			node2 = node2.getNext() ;
			size++;
			calculations[0] += node2.getValue();
			if(node2.getValue() > calculations[3])calculations[3] = node2.getValue();
			if(node2.getValue() < calculations[4])calculations[4] = node2.getValue();
		}
		calculations[1]= calculations[0]/size ;



		node2 = head ;
		int median =0; 
		if(size%2== 0) {
			median = size/2;
			for(int i=0; i<median ; i++) {
				node2 = node2.getNext();
			}
			calculations[2] = (node2.getValue() + node2.getNext().getValue())/2 ;
		}
		else { median = size /2 +1 ;
		for(int i=0; i<median ; i++) {
			node2 = node2.getNext();
		}
		calculations[2] = node2.getValue();
		}
		return calculations ;
	}



	public static LinkedListNode reverse(LinkedListNode head) {
		LinkedListNode node1 = head ;
		LinkedListNode node2 =node1.getNext() ;
		LinkedListNode node3 = node2;
		while (node3 != null) {
			node3=node2.getNext();
			if(node1 == head) {
				node1.next=null;node2.next=null;
			}else node2.next=node1;
			node1=node2;
			node2=node3;
		}
		if(node1 != head) {
			LinkedListNode node4 = new LinkedListNode();
			node4.next=node1;
			return node4;
		}
		else return node1;
	}
	public static LinkedListNode evenIndexedElements(LinkedListNode head) {
		LinkedListNode node1 = head ;
		LinkedListNode node2 =node1.getNext() ;
		LinkedListNode node3 = node2;
		int counter =0 ;
		while (node2 != null) {
			if (counter%2 == 0) {
				node1.next=node2;
				node1=node2;
				counter++;
				node2= node2.getNext();
			}else {
				counter++;
				node3 =node2;
				node2= node2.getNext();
				node3.next=null;
			}
		}node1.next=node2;
		return head ;
	}
	/* insertion sort explained*/
	// node1 : the node before 2 that may contain number bigger than node2 carries .
	// node2 : the node after 1 that may contain number smaller than node1 carries .
	// node3 : the node that every time starts from beginning of the list to search for the first node that 
	//         contains number bigger than node2 , which will be smaller than node1 or = node1 .
	// node4 : contains node before 3 which is smaller than node2 .


	/* example : head 5 3 6 2 4 7 null
	 * node1 = 5 , node2 = 3 , node3 = 5 , node4 = head
	 * change node1's next to the node after node2(IDK this node value) ,
	 * node4's next to node2(node4 contains number smaller than node2) ,
	 * node2's next is changed to node3(node3 contains number bigger than node2) 
	 * node1 is still the same , node2 = next of node1 .
	 * after changing :
	 * list = head 3 5 6 2 4 7 null 
	 * node1 =5 , node2 = 6
	 */

	public static LinkedListNode insertionSort(LinkedListNode head) {
		LinkedListNode node1 = head.getNext() ;
		LinkedListNode node2 =new LinkedListNode() ;
		LinkedListNode node3 =head.getNext() ;
		LinkedListNode node4 =head ;
		if(node1 == null)return head;
		else {
			node2 =node1.getNext() ;
		}
		while (node1.getNext() != null) {
			if(node2.data < node1.data) {
				node4 = head;
				node3=head.getNext();
				while (node3.data < node2.data ) {
					node4 = node3;
					node3= node3.getNext() ;
				}
				node1.next=node2.getNext();
				node4.next=node2;
				node2.next=node3;
				node2 = node1.getNext();
			}
			else {
				node1 = node1.getNext() ;
				node2 = node1.getNext();

			}
		}
		return head ;
	}

	// Find size of the LinkedList
	public static int count (LinkedListNode nl ,LinkedListNode nr ) {
		int counter =1 ;
		if (nl != null )
			for (LinkedListNode i= nl ; i != nr && i.getNext() != null   ; i = i.getNext() ) {
				counter++;
			}
		else return 0 ;
		return counter ; 
	}
	public static void mergeSort(LinkedListNode head) {
		sort ( head.getNext() , null);
	}

	public static void sort( LinkedListNode head, LinkedListNode end) 
	{ 
		int counter = 0 ;
		LinkedListNode n = head ; 
		counter = count (head , end); 
		if (counter >0) {
			for (int i=1 ; i<counter/2 ; i++) {
				n= n.getNext();
			}
		}
		if (counter > 1) 
		{  
			sort( head , n); 
			sort( n.getNext(),end); 
			merge( head, n, end); 
		} 
	}
	public static void merge( LinkedListNode head , LinkedListNode middle , LinkedListNode end ) 
	{ 
		// Find sizes of two subarrays to be merged 
		int n1 = count(head,middle);
		int n2 = count(middle.getNext(),end); 
		LinkedListNode temp1 =head;
		LinkedListNode temp2 = middle.getNext() ;
		int L[] = new int [n1]; 
		int R[] = new int [n2]; 

		for (int i=0; i<n1; ++i) {
			L[i] = temp1.getValue();
			temp1= temp1.getNext();
		}
		for (int j=0; j<n2; ++j) {
			R[j] = temp2.getValue();
			temp2= temp2.getNext();
		}

		int i = 0, j = 0; 
		temp1 = head ;
		while (i < n1 && j < n2) 
		{ 
			if (L[i] <= R[j]) 
			{ 
				temp1.data = L[i]; 
				i++; temp1= temp1.getNext();
			} 
			else
			{ 
				temp1.data = R[j]; 
				j++; temp1= temp1.getNext();
			}  
		} 
		while (i < n1) 
		{ 
			temp1.data = L[i]; 
			i++; temp1= temp1.getNext();
		} 
		while (j < n2) 
		{ 
			temp1.data = R[j]; 
			j++; temp1= temp1.getNext();
		} 
	}

	public static LinkedListNode removeCentralNode(LinkedListNode head) {
		int counter1 = count(head.getNext() , null);
		int counter2 =0;
		LinkedListNode temp1 = head ; LinkedListNode temp2 = new LinkedListNode() ;
		if(counter1 == 0 )return null ;
		if (counter1%2 ==1 )counter2 = counter1/2 ;
		else counter2 = counter1/2-1 ;
		for(int i=0 ; i<counter2; i++)temp1=temp1.getNext() ;
		temp2 = temp1.getNext().getNext() ;
		temp1.getNext().next=null;
		temp1.next=temp2;
		return head ;
	}
	public static boolean palindrome(LinkedListNode head) {
		int xor = 0 ,counter = count (head.getNext(),null) , i =1;
		while (head.getNext() != null) {
			if(counter%2 == 1 && i == counter/2 +1 ) {head = head.getNext() ; i++; continue;}
			xor ^= head.getNext().getValue() ; 
			head = head.getNext(); i++;
		}
		if(xor == 0)return true ; 
		else return false ;
	}
	public static void main(String [] args) {
		boolean counter =false;
		double [] calculations = {0,0,0,0,0} ;
		LinkedLists list = new LinkedLists ();
		list.insert(4);
		list.insert(3);
		list.insert(1);
		list.insert(2);
		list.insert(4);
		//removeCentralNode (list.header);
		counter = palindrome(list.header);
		System.out.println(counter);
		//list.printList();
		//mergeSort(list.header);
		/*counter = count (list.header.getNext() , null);
		System.out.println(counter);*/

		/*calculations = summary(list.header);
		for (int i=0 ; i<calculations.length ; i++) {
			System.out.println(calculations[i]);
		}*/
		//list.header = reverse(list.header);
		//list.header= evenIndexedElements(list.header);
		//list.header = insertionSort(list.header);
		list.printList();

	}

}
