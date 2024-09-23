import java.io.*;


public class Calculator {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Dynamic<>();  // Assuming Dynamic stack is implemented
        System.out.println("HP-35 pocket calculator");
        boolean run = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (run) {
            System.out.print(" > ");
            String input = br.readLine();
            if (input.isEmpty()) {  // Exit the loop if input is empty (just Enter key)
                break;
            }
            String[] tokens = input.split("\\s+");  // Split input by spaces

            for (String token : tokens) {
                switch (token) {
                    case "+":
                        
                        int n1 = stack.pop();
                        int n2 = stack.pop();
                        stack.push(n1 + n2);
                        break;

                    case "-":
                        
                        int sub1 = stack.pop();
                        int sub2 = stack.pop();
                        stack.push(sub2 - sub1);
                        break;

                    case "*":
                        
                        int mult1 = stack.pop();
                        int mult2 = stack.pop();
                        stack.push(mult1 * mult2);
                        break;

                    case "/":
                        
                        int div1 = stack.pop();
                        int div2 = stack.pop();
                        if (div1 == 0) {
                            // push them back
                            stack.push(div2);
                            stack.push(div1);
                            throw new ArithmeticException("Can't divide by 0");
                        }
                        stack.push(div2 / div1);
                        break;

                    case "":
                        run = false;
                        break;

                    default:
                        try {
                            Integer nr = Integer.parseInt(token);
                            stack.push(nr);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number or a valid operator.");
                        }
                        break;
                }
            }
        }

        // Safely pop and print the final result
        
            System.out.printf("The result is: %d\n\n", stack.pop());

        System.out.println("I love reverse Polish notation, don't you?");
    }
}

