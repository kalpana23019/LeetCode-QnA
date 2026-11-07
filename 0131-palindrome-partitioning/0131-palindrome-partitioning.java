class Solution {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        List<String> current = new ArrayList<>();

        backtrack(0, s, current, result);

        return result;
    }

    private void backtrack(
        int start,
        String s,
        List<String> current,
        List<List<String>> result
    ) {

        // All characters are processed
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible substring
        for (int end = start; end < s.length(); end++) {

            String substring = s.substring(start, end + 1);

            if (isPalindrome(substring)) {

                current.add(substring);

                backtrack(end + 1, s, current, result);

                // Backtracking
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {

            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}