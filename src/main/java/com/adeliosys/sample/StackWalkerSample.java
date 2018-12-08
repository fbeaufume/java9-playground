package com.adeliosys.sample;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class StackWalkerSample {

    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        method2();
    }

    private static void method2() {
        method3();
    }

    private static void method3() {
        method4();
    }

    private static void method4() {
        method5();
    }

    private static void method5() {
        System.out.println("Caller class: " + StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass());

        // Java 5
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // Java 9, to process all stack frames
        StackWalker.getInstance()
                .forEach(System.out::println); // Consumes StackFrame instances

        // Prints:
        // com.adeliosys.sample.StackWalkerSample.method5(StackWalkerSample.java:37)
        // com.adeliosys.sample.StackWalkerSample.method4(StackWalkerSample.java:26)
        // com.adeliosys.sample.StackWalkerSample.method3(StackWalkerSample.java:22)
        // com.adeliosys.sample.StackWalkerSample.method2(StackWalkerSample.java:18)
        // com.adeliosys.sample.StackWalkerSample.method1(StackWalkerSample.java:14)
        // com.adeliosys.sample.StackWalkerSample.main(StackWalkerSample.java:10)

        // Java 9, to efficiently process selected stack frames
        List<StackWalker.StackFrame> frames = StackWalker.getInstance()
                .walk(s -> s.limit(2).collect(Collectors.toList())); // Keep current and parent methods

        frames.forEach(System.out::println);

        // Walk uses a function, instead of returning the Stream<StackFrame>,
        // to get a stable view of a thread's control stack.

        // Prints:
        // com.adeliosys.sample.StackWalkerSample.method5(StackWalkerSample.java:41)
        // com.adeliosys.sample.StackWalkerSample.method4(StackWalkerSample.java:26)
    }
}
