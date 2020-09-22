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






public class Main
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter an infix expression: ");

        String infixExpression = input.nextLine();

        System.out.println(infixExpression);
    }
}
