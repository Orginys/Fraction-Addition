class Solution {
    public String fractionAddition(String expression) {
        int numerator = 0, denominator = 1;
        int index = 0, n = expression.length();

        while (index < n) {
            // Step 1: Parse the numerator
            int sign = 1;
            if (expression.charAt(index) == '+' || expression.charAt(index) == '-') {
                sign = expression.charAt(index) == '-' ? -1 : 1;
                index++;
            }

            int num = 0;
            while (index < n && Character.isDigit(expression.charAt(index))) {
                num = num * 10 + (expression.charAt(index) - '0');
                index++;
            }
            num *= sign;

            // Step 2: Parse the denominator
            index++; // skip the '/'
            int denom = 0;
            while (index < n && Character.isDigit(expression.charAt(index))) {
                denom = denom * 10 + (expression.charAt(index) - '0');
                index++;
            }

            // Step 3: Update the current numerator and denominator
            numerator = numerator * denom + num * denominator;
            denominator *= denom;

            // Step 4: Reduce the fraction by calculating the GCD
            int gcd = gcd(Math.abs(numerator), denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        return numerator + "/" + denominator;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}