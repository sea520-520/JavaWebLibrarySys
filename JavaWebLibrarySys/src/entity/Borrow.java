package entity;

public class Borrow {
    private String username;
    private String relname;
    private String bookname;
    private String dataime;
    private int flag;

    public void Borrow(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRelname() {
        return relname;
    }

    public void setRelname(String relname) {
        this.relname = relname;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getDataime() {
        return dataime;
    }

    public void setDataime(String dataime) {
        this.dataime = dataime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
