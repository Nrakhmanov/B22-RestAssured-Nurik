package com.cybertek.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //beforeClass is TestNG version of beforeAll of JUnit
    @BeforeAll
    public static void init() {
        System.out.println("Before all is running");

    }

    @BeforeEach
    public void initEach() {
        System.out.println("\tBefore each method is running");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("\tAfter each method is running");
    }

    @Disabled
    @Test
    public void test1() {

        System.out.println("Test 1 is runnung");
    }

    @Test
    public void test2() {
        System.out.println("Test 2 is runnung");
    }

    @AfterAll
    public static void close() {

        System.out.println("After all is running");
    }
}
