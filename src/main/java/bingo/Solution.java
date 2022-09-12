package bingo;

import bingo.model.Strip;
import bingo.service.RowTemplatesGenerator;
import bingo.service.StripGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {

    private static final int ATTEMPTS = 100;
    private static final int STRIPS_COUNT = 10000;

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solve();
    }

    public List<Strip> solve() {
        var stripGenerator = new StripGenerator(new RowTemplatesGenerator());
        var r = new Random();
        var totalTime = 0l;
        List<Strip> generatedStrips = new ArrayList<>();
        for (int i = 0; i < ATTEMPTS; i++) {
            var start = System.currentTimeMillis();
            var generated = stripGenerator.generateStrips(STRIPS_COUNT);
            var end = System.currentTimeMillis();
            totalTime += end - start;
            generatedStrips.addAll(generated);
        }
        System.out.println("attempts: " + ATTEMPTS);
        System.out.println("totalTime: " + totalTime);
        System.out.println("avgTime for 1 strip: " + totalTime / ATTEMPTS);
        System.out.println("random strip: ");
        generatedStrips.get(r.nextInt(ATTEMPTS * STRIPS_COUNT)).sout();
        return generatedStrips;
    }
}
