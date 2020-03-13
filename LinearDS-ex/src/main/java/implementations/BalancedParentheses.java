package implementations;

import interfaces.Solvable;

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

        if (this.parentheses.length() % 2 != 0){
            return false;
        }

        for (int i = 0; i < (this.parentheses.length()) / 2; i++) {
            char char1 = this.parentheses.charAt(i);
            char char2 = this.parentheses.charAt(this.parentheses.length() - 1 - i);

            switch (char1){

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