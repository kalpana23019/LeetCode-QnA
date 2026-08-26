class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int right = 0;
        int ones = 0;

        String answer = "";

        while (right < s.length()) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            while (ones > k || (left < right && s.charAt(left) == '0')) {

                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }

            right++;

            if (ones == k) {

                String current = s.substring(left, right);

                if (answer.equals("")) {
                    answer = current;
                }

                else if (current.length() < answer.length()) {
                    answer = current;
                }

                else if (current.length() == answer.length()
                        && current.compareTo(answer) < 0) {
                    answer = current;
                }
            }
        }

        return answer;
    }
}