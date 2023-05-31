import java.util.Stack;
import java.util.Scanner;

/**
 * Project 2 - CSC 201 Spring 2023
 * Honor Pledge:
 * The code submitted for this project was developed by
 * RUTE AYALEW without outside assistance or consultation
 * other than that allowed by the instructions for this project.
 */
public class Main{
    // NOTE to students:  This method should not need to be modified.
    public int evaluateExpression(String exp) throws InvalidExpressionException {
        Stack<Integer> operands = new Stack<>();      //Operand stack
        Stack<Character> operators = new Stack<>();   //Operator stack

        // first character must be a digit or '('
        if(!Character.isDigit(exp.charAt(0)) && (exp.charAt(0) != '('))
            throw new InvalidExpressionException("Expression must begin with a digit or (.");

        // loop through the expression String one character at a time
        for(int i=0; i<exp.length(); i++){
            char c = exp.charAt(i);
            if(Character.isDigit(c)){
                // Next character is a Digit, so we loop through
                // the expression to figure out the value of the number
                int num = 0;
                while(Character.isDigit(c) && (i < exp.length())){
                    num = num * 10 + (c - '0'); // standard programming hack
                    if(++i < exp.length()){
                        c = exp.charAt(i);
                    }
                }
                i--; // scanned one character too far, backoff one
                // push the operand
                operands.push(num);
            } else if(c == '(') {
                operators.push(c);  // always push '('
            } else if(c == ')') {   // evaluate sub-expression inside ( )
                // if there's no '(' on operator stack there's a problem
                if(operators.isEmpty() || (operators.search('(') == -1))
                   throw new InvalidExpressionException("Unbalanced parentheses.");
                // perform operations on stack until '(' is found
                while(!operators.isEmpty() && operators.peek() != '(') {
                    int value = performOperation(operands, operators);
                    operands.push(value);
                };
                operators.pop(); // pops off '('
            } else if(isOperator(c)) {  // current character is operator
                while(!operators.isEmpty() && (precedence(c) <= precedence(operators.peek()))){
                    int value = performOperation(operands, operators);
                    operands.push(value);   //push result back to stack
                }
                operators.push(c);   //push the current operator to stack
            } else {
                throw new InvalidExpressionException("Expression contains invalid character " + c);
            }
        }

        while(!operators.isEmpty()){
            int value = performOperation(operands, operators);
            operands.push(value);   //push final result back to stack
        }
        return operands.pop();
    }

    // return the precedence of the operator passed as a parameter
    static int precedence(char c){
        switch (c){
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return -1;
    }

    // pop two operands and an operator and apply the operator
    // then push the result back on the operand stack
    public int performOperation(Stack<Integer> operands, Stack<Character> operators)
            throws InvalidExpressionException{

        if(operands.size() < 2)
            throw new InvalidExpressionException("Trying to pop from empty operand stack.");
        int a = operands.pop();
        int b = operands.pop();

        if(operators.isEmpty())
            throw new InvalidExpressionException("Trying to pop from empty operators stack.");
        char operation = operators.pop();

        switch (operation){
            case '^':
                if(a<0){
                    throw new InvalidExpressionException("Trying to exponentiate with a negative value returns non-integer.");
                }
                return (int) Math.pow(b,a);
            case '/':
                if(a==0){
                    throw new InvalidExpressionException("Trying to divide by 0.");
                }
                return b/a;
            case '*':
                return a*b;
            case '+':
                return a + b;
            case '-':
                return b - a;
        }
        return 0;
    }

    // returns true if c is a valid operator
    public boolean isOperator(char c){

        return (c=='+'|| c=='-'||c=='*'||c=='/'||c=='^');
    }

    // interact with user to read expressions and evaluate them
    // InvalidExpression Exceptions are reported and program continues
    // NOTE to students:  This method should not need to be modified
    public static void main(String[] args){
        Main mainObj = new Main();

        Scanner kb = new Scanner(System.in);
        System.out.print("Do you have an expression you'd like to evaluate (Yes/No)? ");
        String userInput = kb.nextLine().toLowerCase().strip();

        while(userInput.equals("yes")){
            System.out.print("Enter an infix expression to be evaluated:  ");
            String infixExpression = kb.nextLine();
            try {
                int value = mainObj.evaluateExpression(infixExpression.replaceAll("\\s+", ""));
                System.out.printf("The expression %s evaluates to %d\n", infixExpression, value);
            } catch(InvalidExpressionException exp) {
                System.out.println("Illformed expression. " + exp.getMessage());
            }
            System.out.print("Would you like to evaluate another expression (Yes/No)? ");
            userInput = kb.nextLine().toLowerCase().strip();
        }
    }
}

class InvalidExpressionException extends Exception {
    public InvalidExpressionException(String s){
        // Call constructor of parent
        super(s);
    }
}
