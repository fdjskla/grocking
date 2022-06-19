import java.util.Stack;
import java.util.Set;
import java.util.Map;

public class Chapter03 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        testRoundBrackets();
    }
    
    
    public static void testRoundBrackets(){
        
        String string1 = "sdfsd (вы(апп)уув)";
        System.out.println(string1 + " - " + checkRoundBrackets(string1));
        string1 = "sdfsd (вы(апп)уув";
        System.out.println(string1 + " - " + checkRoundBrackets(string1));
        string1 = "sdfsd (вы(апп)уув))";
        System.out.println(string1 + " - " + checkRoundBrackets(string1));
        string1 = "sdfsd";
        System.out.println(string1 + " - " + checkRoundBrackets(string1));
        string1 = "sdfsd п)уув)(вы(ап";
        System.out.println(string1 + " - " + checkRoundBrackets(string1));
        string1 = "{sdfsd (вы[(апп)]уув)}";
        System.out.println(string1 + " - " + checkRoundBrackets(string1));


    }
    
    
    public static boolean checkRoundBrackets(String stringToCheck){
        Stack<Character> stack = new Stack<>();
        for (char ch: stringToCheck.toCharArray()) {
            if (OPEN.contains(ch)) {
                stack.push(ch);
            } else if (CLOSE.contains(ch)) {
                if (stack.empty()){
                    return false;
                }
                Character onStack = stack.peek();
                Character opening = MATCH.get(ch);
                if (opening != null && opening.equals(onStack)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (!stack.empty()) {
            return false;
        }
        return true;
    }
    
    public static final Set<Character> OPEN = Set.of('[', '{', '(');
    public static final Set<Character> CLOSE = Set.of(']', '}', ')');
    public static final Map<Character, Character> MATCH = Map.of(')', '(', '}', '{', ']', '[');
}
