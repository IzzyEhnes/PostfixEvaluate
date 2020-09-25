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



    void pop()
    {
        if (!this.isEmpty())
        {
            top = top.getNext();
        }
    }



    String peek()
    {
        if (top == null)
        {
            return null;
        }

        else
        {
            return Character.toString(top.getValue());
        }
    }



    boolean isEmpty()
    {
        if (top == null)
        {
            return true;
        }

        else
        {
            return false;
        }
    }



    void traverse()
    {
        Node current = new Node();

        for (current = top; current != null; current = current.getNext())
        {
            System.out.println(current.getValue());
        }
    }
}






class Queue
{
    private Node head;

    public Queue()
    {

    }



    void enqueue(char inChar)
    {
        Node nn = new Node();

        nn.setValue(inChar);

        nn.setNext(null);

        if (head == null)
        {
            head = nn;
        }

        else
        {
            Node current = head;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }

            current.setNext(nn);
        }
    }



    void dequeue()
    {
        if (head != null)
        {
            head = head.getNext();
        }
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



    boolean isEmpty()
    {
        if (head == null)
        {
            return true;
        }

        else
        {
            return false;
        }
    }



    void traverse()
    {
        Node current = new Node();

        for (current = head; current != null; current = current.getNext())
        {
            System.out.println(current.getValue());
        }
    }
}






class Expression
{
    private String expression;



    public Expression()
    {

    }


    public Expression(String inExpression)
    {
        expression = inExpression;
    }



    boolean isOperator(char inChar)
    {
        switch (inChar)
        {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;

            default:
                return false;
        }
    }



    boolean isOperand(char inChar)
    {
        switch (inChar)
        {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
            case ' ':
                return false;

            default:
                return true;
        }
    }



    int precedenceCheck(char inOperator)
    {
        if (inOperator == '*' || inOperator == '/')
        {
            return 2;
        }

        else if (inOperator == '+' || inOperator == '-')
        {
            return 1;
        }

        else
        {
            return 0;
        }
    }



    Queue infixToPostfix()
    {
        String inExpression = this.expression;

        Stack operatorStack = new Stack();

        Queue postfixQueue = new Queue();

        for (int i = 0; i < inExpression.length(); i++)
        {
            char currentChar = inExpression.charAt(i);

            if (currentChar == '(')
            {
                operatorStack.push(currentChar);
            }

            else if (isOperand(currentChar))
            {
                postfixQueue.enqueue(currentChar);
            }

            else if (currentChar == ')')
            {
                while (operatorStack.peek().charAt(0) != '(' && !operatorStack.isEmpty())
                {
                    postfixQueue.enqueue(operatorStack.peek().charAt(0));

                    operatorStack.pop();
                }

                operatorStack.pop();
            }

            else if (isOperator(currentChar))
            {
                while (!operatorStack.isEmpty() &&
                        precedenceCheck(operatorStack.peek().charAt(0)) >= precedenceCheck(currentChar) &&
                            operatorStack.peek().charAt(0) != '(' &&
                                operatorStack.peek().charAt(0) != ')')
                {
                    postfixQueue.enqueue(operatorStack.peek().charAt(0));
                    operatorStack.pop();
                }

                operatorStack.push(currentChar);
            }
        }

        while (!operatorStack.isEmpty())
        {
            postfixQueue.enqueue(operatorStack.peek().charAt(0));
            operatorStack.pop();
        }

        postfixQueue.traverse();

        return postfixQueue;
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

        /*
        Stack myStack = new Stack();

        System.out.println(myStack.peek());
        myStack.push('K');
        myStack.push('o');
        System.out.println(myStack.peek());
        myStack.push('n');
        myStack.push('a');
        System.out.println(myStack.peek());
        myStack.traverse();
        myStack.pop();
        myStack.traverse();
         */

        /*
        Queue myQ = new Queue();

        myQ.enqueue('K');
        myQ.enqueue('o');
        myQ.enqueue('n');
        myQ.enqueue('a');

        myQ.traverse();

        myQ.dequeue();

        myQ.traverse();

        myQ.enqueue('K');

        System.out.println();

        myQ.traverse();

         */

        Expression ex = new Expression(infixExpression);

        ex.infixToPostfix();
    }
}
