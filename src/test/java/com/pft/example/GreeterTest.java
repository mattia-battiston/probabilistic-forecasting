package com.pft.example;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreeterTest {

    @Test
    public void example() throws Exception {
        Greeter greeter = new Greeter();

        String result = greeter.greet("Mattia");

        assertThat(result).isEqualTo("Hello Mattia!");
    }

}