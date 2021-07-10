package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void whenBuySuccess() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void whenBuyFail() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, 1, 1, date);
        assertThat(ticket1, is(new Ticket3D()));
        assertNull(ticket2);
    }

    @Test
    public void whenFindSuccess() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenFindFail() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        List<Session> expected = new ArrayList();
        assertThat(sessions, is(expected));
    }

    @Test
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        Session3D expected = new Session3D();
        cinema.add(expected);
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(expected)));
    }
}