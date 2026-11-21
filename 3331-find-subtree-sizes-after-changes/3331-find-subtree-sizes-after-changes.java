import java.util.*;

class Solution {
    int[] newParent;
    int[] last = new int[26];
    List<Integer>[] oldTree;
    List<Integer>[] newTree;
    int[] size;
    int[] parent;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.parent = parent;
        newParent = new int[n];
        size = new int[n];
        Arrays.fill(last, -1);

        oldTree = new ArrayList[n];
        newTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            oldTree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            oldTree[parent[i]].add(i);
        }

        findNewParents(0);

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        calcSize(0);
        return size;
    }

    private void findNewParents(int u) {
        int c = s.charAt(u) - 'a';
        int prev = last[c];

        if (u != 0) {
            // same char ancestor mila to wo, warna original parent
            newParent[u] = (prev != -1) ? prev : parent[u];
        }

        last[c] = u;
        for (int v : oldTree[u]) {
            findNewParents(v);
        }
        last[c] = prev; // backtrack
    }

    private int calcSize(int u) {
        size[u] = 1;
        for (int v : newTree[u]) {
            size[u] += calcSize(v);
        }
        return size[u];
    }
}