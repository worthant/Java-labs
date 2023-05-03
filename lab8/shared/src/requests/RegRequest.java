package requests;

public class RegRequest extends BaseRequest {
    private final String name;
    private final char[] passwd;
    public RegRequest(String name, char[] passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public char[] getPasswd() {
        return passwd;
    }
}
