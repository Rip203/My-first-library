public class User {
    private String name;
    private String id;
    private String phoneNumber;
    private String password;

    public User() {
    }

    public User(String name, String id, String phoneNumber, String password) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //判断手机号码是否符合
    public boolean judgePhone(String s){
        boolean judge = true;
        if(s.length()!=11||s.charAt(0)==48){
            return false;
        }else {
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)<48||s.charAt(i)>57){
                    judge = false;
                    break;
                }
            }
        }
        return judge;
    }

    //判断身份证号是否符合
    public boolean judgeId(String s) {
        boolean judge = false;
        if (s.length() != 18 || s.charAt(0) == 48) {
            return false;
        } else {
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                    return false;
                }
            }
        }
        if ((s.charAt(s.length() - 1) >= 48 && s.charAt(s.length() - 1) <= 57) || s.charAt(s.length() - 1) == 120 || s.charAt(s.length() - 1) == 88) {
            judge = true;
        }
        return judge;
    }

    //判断姓名长度在3~15之间
    public boolean nameSize(String s) {
        return s.length() >= 3 && s.length() <= 15;
    }

    //判断是否为纯数字
    public boolean ifMember(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 48;
            if (num <= 9 && num >= 0) {
                x++;
            }
        }
        return x != s.length();
    }
}
