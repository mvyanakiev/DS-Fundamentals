package implementations;

import interfaces.Solvable;

import java.util.ArrayList;
import java.util.List;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {


        if (this.parentheses.length() == 0) {
            return null;
        }

        String toCheck = this.parentheses.replaceAll("\\s+", "");
        int size = toCheck.length();


        if (size % 2 != 0) {
            char middleChar = toCheck.charAt(size / 2);

            if (    middleChar == '(' || middleChar == ')' ||
                    middleChar == '[' || middleChar == ']' ||
                    middleChar == '{' || middleChar == '}') {
                return false;
            }
        }

        for (int i = 0; i < size / 2; i++) {
            char char1 = toCheck.charAt(i);
            char char2 = toCheck.charAt(size - 1 - i);

            switch (char1) {

                case '(':
                    if (char2 != ')')
                        return false;
                    break;

                case '[':
                    if (char2 != ']')
                        return false;
                    break;

                case '{':
                    if (char2 != '}')
                        return false;
                    break;

                default:
                    return false;
            }
        }
        return true;
    }
}