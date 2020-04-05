package com.example.myapplication;

public class Tolk_item {
    boolean bot =true; // 챗봇이 말할 때
    String tolk;
    String[] items;

    public Tolk_item(Boolean bot, String tolk, String[] items){
        this.bot = bot;
        this.tolk = tolk;
        this.items=items;
    }
    public Tolk_item(Boolean bot, String tolk){
        this.bot = bot;
        this.tolk = tolk;
    }
    public String[] getItems() {
        return items;
    }
    public void setItems(String[] items) {
        this.items = items;
    }
    public boolean isBot() {
        return bot;
    }
    public void setBot(boolean bot) {
        this.bot = bot;
    }
    public String getTolk() {
        return tolk;
    }
    public void setTolk(String tolk) {
        this.tolk = tolk;
    }
}
