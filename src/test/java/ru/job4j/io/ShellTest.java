package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShellTest {

    @Test
    public void whenWindowsCdBack() {
        Shell shell = new Shell();
        shell.cd("D:\\sources\\job4j\\projects\\job4j_tracker");
        shell.cd("..\\job4j_design");
        assertThat(shell.pwd(), is("D:\\sources\\job4j\\projects\\job4j_design"));
    }

    @Test
    public void whenWindowsAbsolutePath() {
        Shell shell = new Shell();
        shell.cd("C:\\projects\\usersmail");
        shell.cd("C:\\projects\\job4j_tracker");
        assertThat(shell.pwd(), is("C:\\projects\\job4j_tracker"));
    }

    @Test
    public void whenWindowsCdRoot() {
        Shell shell = new Shell();
        shell.cd("C:\\");
        assertThat(shell.pwd(), is("C:\\"));
    }

    @Test
    public void whenWindowsCdUserLocal() {
        Shell shell = new Shell();
        shell.cd("C:\\Users\\Zver");
        shell.cd("Desktop");
        assertThat(shell.pwd(), is("C:\\Users\\Zver\\Desktop"));
    }

    @Test
    public void whenWindowsCdUserBack() {
        Shell shell = new Shell();
        shell.cd("C:\\Users");
        shell.cd("..");
        assertThat(shell.pwd(), is("C:\\"));
    }
}