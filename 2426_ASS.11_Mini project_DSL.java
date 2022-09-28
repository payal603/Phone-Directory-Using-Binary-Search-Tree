

/******************************************************************************
NAME:Payal Ravindra Gaikwad
Roll:2426
division:B2

*******************************************************************************/
import java.util.Scanner;
import java.io.*;

class BST {
Scanner sc = new Scanner(System.in);

   public node root, temp, ptr;

   BST() {

       node root = null;
   }

   public void create() {
   String Name, Number,Email,Address;
       String ch;
   
           System.out.println("Enter the name:");
           Name = sc.nextLine();
           sc.nextLine();
           System.out.println("Enter the number:");
           Number = sc.nextLine();
           if(Number.length()!=10)
           {
          System.out.println("Please enter a valid 10 digit mobile number:");
          Number=sc.nextLine();

           }
           System.out.println("Enter the Email:");
           Email = sc.nextLine();
         
           System.out.println("Enter the Address:");
           Address = sc.nextLine();
           node temp = new node(Name,Number,Email,Address );
           if (root == null) {
               root = temp;
               System.out.println("Root is added");
           } else {
               node ptr = root;
               while (ptr != null) {
                   if (ptr.name.compareTo(temp.name) > 0) {
                       if (ptr.left == null) {
                           ptr.left = temp;
                           System.out.println("NOde is added");
                           break;
                       } else {
                           ptr = ptr.left;
                       }
                   } else {
                       {
                           if (ptr.right == null) {
                               ptr.right = temp;
                               System.out.println("NOde is added");
                               break;
                           } else {
                               ptr = ptr.right;
                           }
                       }
                   }
               }
           }
         
   }


   public node SearchByName() {
       int flag = 0;
       String word1;
       System.out.println("Enter the name to search:");
       word1 = sc.next();
       node ptr = root;
       while (ptr != null) {
           if (word1.equals(ptr.name)) {
               flag = 1;
             
               break;
           } else if (word1.compareTo(ptr.name) < 0) {
               ptr = ptr.left;
           } else {
               ptr = ptr.right;
           }
       }
       if (flag == 1) {
           System.out.println("data found ");
           System.out.print("\nName: " + ptr.name + " " + "\nNumber: " + ptr.number+" "+"\nEmail :"+ptr.email+" "+"\nAddress: "+ptr.address+" ");

           return ptr;
       } else {
           System.out.println("data not found ");
           return null;
       }
   }
   
 
public node find(String key) {
int flag = 0;
 
    node ptr = root;
    while (ptr != null) {
        if (key.equals(ptr.name)) {
            flag = 1;
           
            break;
        } else if (key.compareTo(ptr.name) < 0) {
            ptr = ptr.left;
        } else {
            ptr = ptr.right;
        }
    }
    if (flag == 1) {
       
        return ptr;
    } else {
     
        return null;
    }
}
   public void update() {
       node flag;
       String key;
       System.out.println("Enter the contact name to update ");
       key=sc.next();
       flag = find(key);
       if (flag != null) {

           System.out.println("Enter the new number:");
           sc.nextLine();
           flag.number = sc.nextLine();
           System.out.println("Number updated");
       } else {
           System.out.println("Data not found");
       }
   }

   public void display() {
       //Display tree in non-recursive inorder
       StackTree stk = new StackTree();
       if (root == null)
       {
           System.out.println("There are no contacts to display");
           return;
       }
       ptr = root;

       // traverse the tree
       while (ptr != null || !stk.isEmpty()) {

           /* Reach the left most Node of the
           curr Node */
           while (ptr != null) {
               /* place pointer to a tree node on
                  the stack before traversing
                 the node's left subtree */
               stk.push(ptr);
               ptr = ptr.left;
           }

           /* Current must be NULL at this point */
           ptr = stk.pop();

           System.out.print("\nName: " + ptr.name + " " + "\nNumber:" + ptr.number+" "+"\nEmail:"+ptr.email+" "+"\nAddress:"+ptr.address+" ");
           System.out.println("--------------------------------------------------------- ");

           /* we have visited the node and its
              left subtree.  Now, it's right
              subtree's turn */
           ptr = ptr.right;
       }

   }
   node deleteNode(node n1, String key) {
       // Base case
       node parent =n1; // pointer to store the parent of the current node
       node curr = n1; // start with the root node

       while (!curr.name.equals(key)) {
           // Recursive calls for ancestors of node to be deleted
           // update the parent to the current node
           parent = curr;
        // if the given key is less than the current node, go to the left subtree;
       // otherwise, go to the right subtree
           if (root.name.compareTo(key) > 0) {
               root.left = deleteNode(root.left, key);
               return root;
           } else if (root.name.compareTo(key) < 0) {
               root.right = deleteNode(root.right, key);
               return root;
           }
           if (curr == null)
               return null;
           // We reach here when root is the node
           // to be deleted.
       }
       // Case 1: node to be deleted has no children, i.e., it is a leaf node
       if (curr.left == null && curr.right == null) { // if the node to be deleted is not a root node, then set its
//parent left/right child to null
           if (curr == root)
               root = null;
           else if(parent.left!=null)
               parent.left = null;
           else
               parent.right = null;
       }
//node to be deleted has a children (left/right)
       else if (curr.right == null)//have left child
           if(curr == root)
               root = curr.left;
           else if (parent.left!=null)
               parent.left = curr.left;
           else
               parent.right = curr.left;
       else if (curr.left == null)//have right child
           if(curr == root)
               root = curr.right;
           else if (parent.left!=null)
               parent.left = curr.right;
           else
               parent.right = curr.right;
       else {
//find its inorder successor (OR Predecessor) node
           node successor = inOrderSuccessor(root, curr);
           if(curr == root) {
               root = successor;
           }
           else if (parent.left!=null) {
               parent.left = successor;
           }
           else
               parent.right = successor;

//store successor value
           String val = successor.name;
           System.out.println(val);
           successor.left = curr.left;

       }
       return null;
   }
   node inOrderSuccessor(node root, node n)
   {
       node key=n;
       node[] insucc=new node[100];
       node succ=null;

       //Display tree in non-recursive inorder
       StackTree stk = new StackTree();
       if (root == null)
           return null;
       ptr = root;

       // traverse the tree
       while (ptr != null || !stk.isEmpty()) {

           /* Reach the left most Node of the
           curr Node */
           while (ptr != null) {
               /* place pointer to a tree node on
                  the stack before traversing
                 the node's left subtree */
               stk.push(ptr);
               ptr = ptr.left;
           }

           /* Current must be NULL at this point */
           ptr = stk.pop();

           for(int i=0;i<=stk.top;i++) {
               insucc[i]=ptr;
           }


           /* we have visited the node and its
              left subtree.  Now, it's right
              subtree's turn */
           ptr = ptr.right;
       }
       for(int i=0;i<insucc.length;i++) {
           if(key==insucc[i]) {
               succ=insucc[i+1];
           }
       }

       return succ;
   }
   
 
class StackTree {
   int top;
   int MAXSIZE=50;
   node st[];

   public StackTree() {
       this.top = -1;
       this.st = new node[MAXSIZE];
   }
   public void push(node pushedElement)
   {
       if (!isFull()) {
           top++;
           st[top] = pushedElement;
       } else {
           System.out.println("Stack is full !");
       }
   }
   public node pop()

   {

       if (!isEmpty())

       {
           int returnedTop = top;
           top--;
           return st[returnedTop];

       }
       else {
           System.out.println("Stack is empty !");
       }
       return null;
   }
   public boolean isEmpty()
   {
       if (top==-1)
           return true;
       return false;
   }
   public boolean isFull() {
       if (top==MAXSIZE-1)
           return true;
       return false;
   }
}
 
   
}
public class Contact {
public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       String TempName;
       System.out.println("\nPlease enter your name");
       TempName=sc.next();
       
       BST b=new BST();
       int choice;
       do {
           System.out.println("\n-----------------------------------------");
           System.out.println("\nWelcome, "+TempName+"!!!");
           System.out.println("Menu:\n1)Add a new contact \n2)Display \n3)Search by name \n4)Update name\n5)Delete a contact\n6)Exit");
           System.out.println("\n------------------------------------------------");
           System.out.println("Enter your choice");
           choice=sc.nextInt();
           switch(choice) {
               case 1:
                   b.create();
                   break;
               case 2:
                   b.display();
                   break;
               case 3:
                   b.SearchByName();
                   break;
               
             
               case 4:
                   b.update();
                   break;
               case 5:
                   System.out.println("Enter the contact name to delete:");
                   sc.nextLine();
                   String word=sc.nextLine();
                   b.deleteNode(b.root,word);
                   System.out.println("contact "+word+" deleted");
                   break;
           }
       }while(choice!=6);
       System.out.println("\nThank-you ,"+TempName+" for using our contact management system !!!");



   }
}
class node {
String name,number,email,address;
   node left, right;
   public  node() {
       left = null;
       right = null;
   }
   public  node(String Name,String Number,String Email,String Address) {
       name = Name;
      number = Number;
      email=Email;
      address=Address;
       left = null;
       right = null;
   }
}
class StackTree {
int top;
  int MAXSIZE=100;
  node st[];

  public StackTree() {
      this.top = -1;
      this.st = new node[MAXSIZE];
  }
  public void push(node pushedElement)
  {
      if (!isFull()) {
          top++;
          st[top] = pushedElement;
      } else {
          System.out.println("Stack is full !");
      }
  }
  public node pop()

  {

      if (!isEmpty())

      {
          int returnedTop = top;
          top--;
          return st[returnedTop];

      }
      else {
          System.out.println("Stack is empty !");
      }
      return null;
  }
  public boolean isEmpty()
  {
      if (top==-1)
          return true;
      return false;
  }
  public boolean isFull() {
      if (top==MAXSIZE-1)
          return true;
      return false;
  }
}




/******************************************************************************

OUTPUT

Please enter your name
payal

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
1
Enter the name:
samruddhi

Enter the number:
9833
Please enter a valid 10 digit mobile number:
9823668140
Enter the Email:
sam@gmail.com
Enter the Address:
pune
Root is added

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
1
Enter the name:
payal

Enter the number:
9403980011
Enter the Email:
payal.com
Enter the Address:
boisar
NOde is added

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
1
Enter the name:
priya

Enter the number:
7588312636
Enter the Email:
priya.com
Enter the Address:
wardha
NOde is added

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
2

Name: payal 
Number:9403980011 
Email:payal.com 
Address:boisar --------------------------------------------------------- 

Name: priya 
Number:7588312636 
Email:priya.com 
Address:wardha --------------------------------------------------------- 

Name: samruddhi 
Number:9823668140 
Email:sam@gmail.com 
Address:pune --------------------------------------------------------- 

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
3
Enter the name to search:
rohan
data not found 

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
3
Enter the name to search:
priya
data found 

Name: priya 
Number: 7588312636 
Email :priya.com 
Address: wardha 
-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
4
Enter the contact name to update 
samruddhi
Enter the new number:
9823668888
Number updated

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
2

Name: payal 
Number:9403980011 
Email:payal.com 
Address:boisar --------------------------------------------------------- 

Name: priya 
Number:7588312636 
Email:priya.com 
Address:wardha --------------------------------------------------------- 

Name: samruddhi 
Number:9823668888 
Email:sam@gmail.com 
Address:pune --------------------------------------------------------- 

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
5
Enter the contact name to delete:
payal
contact payal deleted

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
2

Name: samruddhi 
Number:9823668888 
Email:sam@gmail.com 
Address:pune --------------------------------------------------------- 

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
5
Enter the contact name to delete:
samruddhi
contact samruddhi deleted

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
5
Enter the contact name to delete:
priya
contact priya deleted

-----------------------------------------

Welcome, p!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
2
There are no contacts to display

-----------------------------------------

Welcome, payal!!!
Menu:
1)Add a new contact 
2)Display 
3)Search by name 
4)Update name
5)Delete a contact
6)Exit

------------------------------------------------
Enter your choice
6

Thank-you ,payal for using our contact management system !!!
************************************************************/
