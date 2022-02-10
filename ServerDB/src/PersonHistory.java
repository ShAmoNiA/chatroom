public class PersonHistory {
    private String chatHistory;
    private String username1;
    private String username2;

    public PersonHistory(String chatHistory, String username1,String username2){
        this.username1=username1;
        this.username2=username2;
        this.chatHistory=chatHistory;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }
}
