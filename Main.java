import java.util.Scanner;



class Node<T>
{
    private T value;

    private Node next;


    public Node()
    {
    }



    public Node(T inValue)
    {
        this.value = inValue;
    }



    T getValue()
    {
        return value;
    }



    void setValue(T inValue)
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





class Stack<T>
{
    private Node<T> top;



    public Stack()
    {

    }



    void push(T inVal)
    {
        Node nn = new Node(inVal);

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



    T peek()
    {
        if (top == null)
        {
            return null;
        }

        else
        {
            return top.getValue();
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






class Queue<T>
{
    private Node<T> head;

    public Queue()
    {

    }



    void enqueue(T inVal)
    {
        Node nn = new Node();

        nn.setValue(inVal);

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



    T peek()
    {
        if (head == null)
        {
            return null;
        }

        else
        {
            return head.getValue();
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
            System.out.print(current.getValue());
        }
    }
}





class Postfix
{
    static boolean isOperator(char inChar)
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



    static boolean isOperand(char inChar)
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



    static int precedenceCheck(char inOperator)
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



    static int calculate(int leftOperand, int rightOperand, char symbol)
    {
        int result = 0;

        switch (symbol)
        {
            case '+':
                result = leftOperand + rightOperand;
                break;

            case '-':
                result = leftOperand - rightOperand;
                break;

            case '*':
                result = leftOperand * rightOperand;
                break;

            case '/':
                result = leftOperand / rightOperand;
                break;

            default:
                return -1;
        }

        return result;
    }



    static Queue infixToPostfix(String inExpression)
    {
        Stack<Character> operatorStack = new Stack();

        Queue<Character> postfixQueue = new Queue();

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
                while (operatorStack.peek() != '(' && !operatorStack.isEmpty())
                {
                    postfixQueue.enqueue(operatorStack.peek());

                    operatorStack.pop();
                }

                operatorStack.pop();
            }

            else if (isOperator(currentChar))
            {
                while (!operatorStack.isEmpty() &&
                        precedenceCheck(operatorStack.peek()) >= precedenceCheck(currentChar) &&
                        operatorStack.peek() != '(' &&
                        operatorStack.peek() != ')')
                {
                    postfixQueue.enqueue(operatorStack.peek());
                    operatorStack.pop();
                }

                operatorStack.push(currentChar);
            }
        }

        while (!operatorStack.isEmpty())
        {
            postfixQueue.enqueue(operatorStack.peek());
            operatorStack.pop();
        }

        return postfixQueue;
    }



    static int evaluatePostfix(Queue<Character> inQueue)
    {
        int answer = 0;

        Stack<Integer> evaluateStack = new Stack();

        while (!inQueue.isEmpty())
        {
            if (isOperand(inQueue.peek()))
            {
                evaluateStack.push(inQueue.peek() - '0');

                inQueue.dequeue();
            }

            else if (isOperator(inQueue.peek()))
            {
                char symbol = inQueue.peek();

                inQueue.dequeue();

                int leftOperand;
                int rightOperand;

                rightOperand = evaluateStack.peek();

                evaluateStack.pop();

                leftOperand = evaluateStack.peek();

                evaluateStack.pop();

                answer = calculate(leftOperand, rightOperand, symbol);

                evaluateStack.push(answer);
            }
        }

        return  answer;
    }
}






public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter an infix expression: ");

        String infixExpression = input.nextLine();

        Queue postfixQueue = Postfix.infixToPostfix(infixExpression);

        System.out.print("\nPostfix expression: ");
        postfixQueue.traverse();

        System.out.print("\nAnswer: ");
        System.out.println(Postfix.evaluatePostfix(postfixQueue));
    }
}
