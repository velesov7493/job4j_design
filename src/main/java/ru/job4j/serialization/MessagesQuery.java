package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class MessagesQuery {

    private Long chatId;
    private Long actorId;
    private Date[] dateInterval;
    private Byte reverseOrder;
    private String searchText;
    private int page;

    public MessagesQuery() { }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public Date[] getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(Date[] value) {
        if (value.length != 2) {
            return;
        }
        dateInterval = new Date[2];
        dateInterval[0] = value[0];
        dateInterval[1] = value[1];
    }

    public Byte getReverseOrder() {
        return reverseOrder;
    }

    public void setReverseOrder(Byte mode) {
        if (mode < 0 || mode > 1) {
            return;
        }
        reverseOrder = mode;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String toJson() {
        final Gson gson =
                new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson.toJson(this);
    }

    public static MessagesQuery fromJson(String json) {
        final Gson gson =
                new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson.fromJson(json, MessagesQuery.class);
    }
}
