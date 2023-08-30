package data;

public enum EventData {

   TYPE_EVENT("Открытый вебинар");
    private String title;

    EventData(String title) {
      this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
