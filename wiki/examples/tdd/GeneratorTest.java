package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class GeneratorTest() {

    @Test
    public void whenProduceSuccess() {
        String in = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Petr Arsentev, Who are you?";
        Generator g = new DefaultGenerator();
        Map<String, String> replaces = new HashMap();
        replaces.put("name", "Petr Arsentev");
        replaces.put("subject", "you");
        String result = g.produce(in, replaces);
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMissingReplace() {
        String in = "I am a ${name}, Who are ${subject}?";
        Generator g = new DefaultGenerator();
        Map<String, String> replaces = new HashMap();
        replaces.put("name", "Petr Arsentev");
        String result = g.produce(in, replaces);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMissingTemplateKey() {
        String in = "I am a ${name}, Who are you?";
        Generator g = new DefaultGenerator();
        Map<String, String> replaces = new HashMap();
        replaces.put("name", "Petr Arsentev");
        replaces.put("subject", "you");
        String result = g.produce(in, replaces);
    }
}