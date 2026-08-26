class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int right = 0;
        int ones = 0;

        String answer = "";

        while (right < s.length()) {

            // Add current character to the window
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Shrink the window
            while (ones > k || (left < right && s.charAt(left) == '0')) {

                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }

            // Move right forward
            right++;

            // Check if current window is beautiful
            if (ones == k) {

                String current = s.substring(left, right);

                // First valid answer
                if (answer.equals("")) {
                    answer = current;
                }

                // Shorter answer
                else if (current.length() < answer.length()) {
                    answer = current;
                }

                // Same length but lexicographically smaller
                else if (current.length() == answer.length()
                        && current.compareTo(answer) < 0) {
                    answer = current;
                }
            }
        }

        return answer;
    }
}