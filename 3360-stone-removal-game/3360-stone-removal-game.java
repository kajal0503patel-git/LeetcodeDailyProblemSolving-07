class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean aliceTurn = true;

        while (remove > 0 && n >= remove) {
            n -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }

        // The player whose turn it is now cannot move, so they lose
        return !aliceTurn;
    }
}