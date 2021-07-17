import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ProjectEuler23 {
    private static final int UPPER_BOUND_FOR_ABUNDANTS = 28123;

    public static void main(String[] args) {
        final List<Integer> oneThroughToAbundantsUpperBound =
                Stream.iterate(1, n -> n + 1).limit(UPPER_BOUND_FOR_ABUNDANTS).toList();

        final List<Integer> abundantNumbers = oneThroughToAbundantsUpperBound
                .parallelStream()
                .filter(testingForAbundance ->
                        testingForAbundance < Stream
                            .iterate(1, n -> n + 1)
                            .limit(testingForAbundance - 1)
                            .filter(divisor -> testingForAbundance % divisor == 0)
                            .mapToInt(Integer::intValue)
                            .sum()
                )
                .toList();

        final Set<Integer> sumsOfTwoAbundantInts = new HashSet<>();
        abundantNumbers.forEach(firstAbundantNumber ->
                abundantNumbers.forEach(secondAbundantNumber ->
                        sumsOfTwoAbundantInts.add(firstAbundantNumber + secondAbundantNumber)
                )
        );

        final List<Integer> allPositiveIntsNotTheSumOfTwoAbundantInts =
                oneThroughToAbundantsUpperBound
                .stream()
                .filter(integer -> !sumsOfTwoAbundantInts.contains(integer))
                .toList();

        final int grandSum = allPositiveIntsNotTheSumOfTwoAbundantInts.stream().mapToInt(Integer::intValue).sum();

        System.out.println(grandSum);
    }
}
