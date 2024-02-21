package queue;

import base.Selector;
import base.TestCounter;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class ArrayQueueTest {
    public static final Selector SELECTOR = new Selector(ArrayQueueTest.class)
            .variant("Base", variant(Queues.QueueModel.class, d -> () -> d))
            ;

    private ArrayQueueTest() {
    }

    public static void main(final String... args) {
        SELECTOR.main(args);
    }

    /* package-private */
    static <M extends Queues.QueueModel> Consumer<TestCounter> variant(
            final Class<M> type,
            final Queues.QueueChecker<M> tester,
            final Queues.Splitter<M> splitter
    ) {
        return new ArrayQueueTester<>(type, tester, splitter)::test;
    }

    /* package-private */
    static <M extends Queues.QueueModel> Consumer<TestCounter> variant(
            final Class<M> type,
            final Queues.QueueChecker<M> tester
    ) {
        return variant(type, tester, (t, q, r) -> List.of());
    }
}
