package com.cybertek.day5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1() {

        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));
        assertThat(5+5, is(equalTo(10)));

        assertThat(5+5, not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5, is(not(equalTo(9))));

       //number comparison
       // greaterThan()
       // greaterThanOrEqualTo()
       // lessThan()
       // lessThanOrEqualTo()
        assertThat(5+5, is(greaterThan(9)));
        assertThat(5+5, is(greaterThanOrEqualTo(10)));
        assertThat(5+5, is(lessThan(99)));
        assertThat(5+5, is(lessThanOrEqualTo(11)));

    }

    @Test
    public void stringHamcrest() {

        String text = "B22 is learning Hamcrest";

        assertThat(text, is("B22 is learning Hamcrest"));
        assertThat(text, is(equalTo("B22 is learning Hamcrest")));
        assertThat(text, equalTo("B22 is learning Hamcrest"));

        //check if this text starts with B22
        assertThat(text, startsWith("B22"));

        //do it in case-insensitive manner
        assertThat(text, startsWithIgnoringCase("b22"));

        //endswith
        assertThat(text, endsWith("rest"));

        //check if text contaisn String learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LEARNING"));


        String str =" ";

        //check if String is blank
        assertThat(str, blankString());

        //check if trimmed string is empty
        assertThat(str.trim(), emptyString());

    }

    @Test
    public void testCollection() {

        List<Integer> listOfNumbers = Arrays.asList(1,4,8,12,24,38,69,77,99,111);

        //check size of the list
        assertThat(listOfNumbers, hasSize(10));

        assertThat(listOfNumbers, hasItem(69));

        assertThat(listOfNumbers, hasItems(12,38,99));

        //check if all numbers greater than 0
        assertThat(listOfNumbers, everyItem(greaterThan(0)));


    }


}
