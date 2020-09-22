import java.util.Scanner;



class Node
{
    private char value;

    private Node next;


    public Node()
    {
    }



    public Node(char inValue)
    {
        this.value = inValue;
    }



    char getValue()
    {
        return value;
    }



    void setValue(char inValue)
    {
        this.value = inValue;
    }



    Node getNext()
    {
        return next;
    }



    void setNext(Node inNode)
    {
        next = inNode;
    }
}






class Stack
{
    private Node top;



    public Stack()
    {

    }



    void push(char inChar)
    {
        Node nn = new Node(inChar);

        nn.setNext(top);

        top = nn;
    }



    String peek()
    {
        if (head == null)
        {
            return null;
        }

        else
        {
            return Character.toString(head.getValue());
        }
    }
}






public class Main
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter an infix expression: ");

        String infixExpression = input.nextLine();

        System.out.println(infixExpression);

        // Stack class tests

        Stack myStack = new Stack();

        System.out.println(myStack.peek());
        myStack.push('K');
        myStack.push('o');
        System.out.println(myStack.peek());
        myStack.push('n');
        System.out.println(myStack.peek());
        myStack.push('a');
    }
}
