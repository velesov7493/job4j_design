package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessagesQuery query = (MessagesQuery) o;
        boolean datesEquals = dateInterval.length == query.dateInterval.length;
        if (datesEquals) {
            for (int i = 0; i < dateInterval.length; i++) {
                long time1 = dateInterval[i].getTime();
                long time2 = query.dateInterval[i].getTime();
                if (time1 != time2) {
                    datesEquals = false;
                    break;
                }
            }
        }
        return
                page == query.page && Objects.equals(chatId, query.chatId)
                && Objects.equals(actorId, query.actorId) && datesEquals
                && Objects.equals(reverseOrder, query.reverseOrder)
                && Objects.equals(searchText, query.searchText);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(chatId, actorId, reverseOrder, searchText, page);
        int datesHash = 0;
        for (Date entry : dateInterval) {
            datesHash ^= entry.hashCode();
        }
        result = 31 * result ^ datesHash;
        return result;
    }

    @Override
    public String toString() {
        return
                "MessagesQuery{"
                + "chatId=" + chatId
                + ", actorId=" + actorId
                + ", dateInterval=" + Arrays.toString(dateInterval)
                + ", reverseOrder=" + reverseOrder
                + ", searchText='" + searchText + '\''
                + ", page=" + page
                + '}';
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

    public JSONObject toJsonObjectDirect() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject result = new JSONObject();
        result.put("actorId", actorId);
        result.put("chatId", chatId);
        String[] dates = {f.format(dateInterval[0]), f.format(dateInterval[1])};
        JSONArray interval = new JSONArray(dates);
        result.put("dateInterval", interval);
        result.put("reverseOrder", reverseOrder);
        result.put("searchText", searchText);
        result.put("page", page);
        return result;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this);
    }

    public static MessagesQuery fromJsonObject(JSONObject obj) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        MessagesQuery result = new MessagesQuery();
        result.actorId = obj.getLong("actorId");
        result.chatId = obj.getLong("chatId");
        JSONArray dtInterval = obj.getJSONArray("dateInterval");
        try {
            result.dateInterval = new Date[]{
                    f.parse(dtInterval.getString(0)),
                    f.parse(dtInterval.getString(1))
            };
        } catch (ParseException ex) {
            result.dateInterval = new Date[2];
        }
        result.reverseOrder = (byte) obj.getInt("reverseOrder");
        result.searchText = obj.getString("searchText");
        result.page = obj.getInt("page");
        return result;
    }
}
