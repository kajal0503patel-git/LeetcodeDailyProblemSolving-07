class Solution {
    private int k, n;
    private int[] prod;      // prod[node] = product of segment mod k
    private int[][] mat;     // mat[node][s*k+t] = count of prefixes turning s -> t

    private static class Seg {
        int p;
        int[] m;
        Seg(int p, int[] m) { this.p = p; this.m = m; }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        prod = new int[4 * n];
        mat = new int[4 * n][k * k];
        build(1, 0, n - 1, nums);

        int qn = queries.length;
        int[] result = new int[qn];
        int s0 = 1 % k;

        for (int i = 0; i < qn; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);
            Seg res = query(1, 0, n - 1, start, n - 1);
            result[i] = res.m[s0 * k + x];
        }
        return result;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            setLeaf(node, nums[l]);
            return;
        }
        int mid = (l + r) >>> 1;
        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);
        pull(node);
    }

    private void setLeaf(int node, int val) {
        int v = val % k;
        prod[node] = v;
        int[] m = mat[node];
        for (int idx = 0; idx < m.length; idx++) m[idx] = 0;
        for (int s = 0; s < k; s++) {
            int t = (s * v) % k;
            m[s * k + t] = 1;
        }
    }

    private void pull(int node) {
        int left = node * 2, right = node * 2 + 1;
        prod[node] = (prod[left] * prod[right]) % k;
        int[] ml = mat[left], mr = mat[right], m = mat[node];
        int pl = prod[left];
        for (int s = 0; s < k; s++) {
            int sp = (s * pl) % k;
            for (int t = 0; t < k; t++) {
                m[s * k + t] = ml[s * k + t] + mr[sp * k + t];
            }
        }
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            setLeaf(node, val);
            return;
        }
        int mid = (l + r) >>> 1;
        if (idx <= mid) update(node * 2, l, mid, idx, val);
        else update(node * 2 + 1, mid + 1, r, idx, val);
        pull(node);
    }

    private Seg query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return new Seg(prod[node], mat[node]);
        }
        int mid = (l + r) >>> 1;
        if (qr <= mid) return query(node * 2, l, mid, ql, qr);
        if (ql > mid) return query(node * 2 + 1, mid + 1, r, ql, qr);
        Seg L = query(node * 2, l, mid, ql, qr);
        Seg R = query(node * 2 + 1, mid + 1, r, ql, qr);
        return combine(L, R);
    }

    private Seg combine(Seg L, Seg R) {
        int p = (L.p * R.p) % k;
        int[] m = new int[k * k];
        for (int s = 0; s < k; s++) {
            int sp = (s * L.p) % k;
            for (int t = 0; t < k; t++) {
                m[s * k + t] = L.m[s * k + t] + R.m[sp * k + t];
            }
        }
        return new Seg(p, m);
    }
}