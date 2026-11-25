class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();

        // Step 1: Insert all numbers into the trie (bit by bit, from bit 30 down to 0)
        for (int num : nums) {
            TrieNode node = root;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new TrieNode();
                }
                node = node.children[bit];
            }
        }

        // Step 2: For each number, find the best partner that gives the max XOR
        int max = 0;
        for (int num : nums) {
            TrieNode node = root;
            int currXor = 0;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int want = 1 - bit; // opposite bit gives a 1 in the XOR

                if (node.children[want] != null) {
                    currXor |= (1 << i);
                    node = node.children[want];
                } else {
                    node = node.children[bit];
                }
            }
            max = Math.max(max, currXor);
        }

        return max;
    }
}