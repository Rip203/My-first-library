import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> list = new ArrayList<>();
        F:
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出程序");
            String next = sc.next();
            switch (next) {
                case "1" -> up(list);
                case "2" -> add(list);
                case "3"->forgetPassword(list);
                case "4" -> {
                    System.out.println("欢迎下次使用！！！");
                    break F;
                }
                default -> System.out.println("输入不符合规范，请重新输入");
            }
        }
    }

    public static void add(ArrayList<User> list) {
        User u = new User();
        Scanner sc = new Scanner(System.in);
        //添加身份证号
        while (true) {
            System.out.println("请输入身份证号：");
            String id = sc.next();
            if (u.judgeId(id)) {
                u.setId(id);
                System.out.println("身份证号添加成功！");
                break;
            } else {
                System.out.println("身份证号不符合规范，请重新输入！");
            }
        }
        //添加姓名
        while (true) {
            System.out.println("请输入姓名：(长度在3~15之间，只能是字母加数字，不能是纯数字)");
            String name = sc.next();
            if (judgeName(u, list, name)) {
                System.out.println("姓名添加成功！");
                break;
            } else {
                System.out.println("姓名不符合规范或该姓名已存在，请重新输入！");
            }
        }
        //添加电话号码
        while (true) {
            System.out.println("请输入电话号：");
            String phoneNumber = sc.next();
            if (u.judgePhone(phoneNumber)) {
                u.setPhoneNumber(phoneNumber);
                System.out.println("电话号码添加成功！");
                break;
            } else {
                System.out.println("输入错误，请重新输入！");
            }
        }
        System.out.println("请输入密码：");
        String passWord = sc.next();
        u.setPassword(passWord);
        //确认密码
        while (true) {
            System.out.println("请确认密码：");
            String password = sc.next();
            if (u.getPassword().equals(password)) {
                list.add(u);
                break;
            } else {
                System.out.println("密码输入错误，请重新输入！！！");
            }
        }
        System.out.println("用户注册成功！！！");
    }

    //判断姓名长度和是否全为数字，检测姓名是否重复
    public static boolean judgeName(User u, ArrayList<User> list, String name) {
        boolean judge = false;
        if (list.size() == 0 && u.ifMember(name) && u.nameSize(name)) {
            u.setName(name);
            return true;
        }
        u.setName(name);
        for (User user : list) {
            if (user.getName().equals(u.getName())) {
                return false;
            } else if (u.ifMember(name) && u.nameSize(name)) {
                judge = true;
            }
        }
        return judge;
    }

    //用户登录
    public static void up(ArrayList<User> list) {
        if (list.size() == 0) {
            System.out.println("当前程序没有用户请创建后，进行登录");
            add(list);
        } else {
            System.out.println("请输入登录人员账号、密码和验证码(区分大小写)");
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入姓名：");
            String name = sc.next();
            int i = 0;
            int k;
            boolean j = false;
            for (; i < list.size(); i++) {
                if (list.get(i) != null && name.equals(list.get(i).getName())) {
                    j = true;
                    break;
                }
            }
            if (j) {
                k = i;
            } else {
                System.out.println("用户名不存在，请注册");
                add(list);
                return;
            }

            while (true) {
                String a = VerificationCode();
                System.out.println("请输入验证码：" + a);
                String y = sc.next();
                if (a.equals(y)) {
                    System.out.println("验证成功！");
                    break;
                } else {
                    System.out.println("输入有误，请重新输入！");
                }
            }

            int num = 0;
            int x = 3;
            System.out.println("请输入密码：(密码确认共有三次机会)");
            while (true) {
                String password = sc.next();
                if (list.get(k).getPassword().equals(password)) {
                    System.out.println("登陆成功！！！");
                    return;
                } else {
                    num++;
                    System.out.println("密码输入错误，请重新输入！！！");
                    x--;
                    System.out.println("剩余" + x + "次机会");
                }
                if (num == 3) {
                    System.out.println("密码输入次数已用尽，请找管理员解锁");
                    return;
                }
            }
        }
    }

    //忘记密码
    public static void forgetPassword(ArrayList<User> list){
        if (list.size() == 0) {
            System.out.println("当前程序没有用户请创建后，进行登录");
            add(list);
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入姓名：");
            String name = sc.next();
            int i = 0;
            boolean j = false;
            for (; i < list.size(); i++) {
                if (list.get(i) != null && name.equals(list.get(i).getName())) {
                    j = true;
                    break;
                }
            }
            int k = i;
            if (!j) {
                System.out.println("用户名不存在，请先注册！");
                add(list);
            } else {
                System.out.println("请输入身份证号：");
                String id = sc.next();
                System.out.println("请输入手机号码：");
                String phone = sc.next();
                if(list.get(k).getId().equals(id)&&list.get(k).getPhoneNumber().equals(phone)){
                    System.out.println("信息匹配成功，请输入新的密码：");
                    String newPassWord = sc.next();
                    list.get(k).setPassword(newPassWord);
                }else {
                    System.out.println("账号信息不匹配，修改失败！");
                }
            }
        }
    }

    //获取验证码
    public static String VerificationCode() {
        String[] s = new String[52];
        for (int i = 0; i < 26; i++) {
            s[i] = (char) ('a' + i) + "";
        }
        for (int i = 0, j = 26; i < 26; i++, j++) {
            s[j] = s[i].toUpperCase();
        }
        Random r = new Random();
        int y = r.nextInt(10);
        String[] news = new String[5];
        for (int i = 0; i < 4; i++) {
            int x = r.nextInt(53);
            news[i] = s[x];
        }
        news[4] = y + "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < news.length; i++) {
            int h = r.nextInt(5);
            String a = news[i];
            news[i] = news[h];
            news[h] = a;
        }
        for (String value : news) {
            sb.append(value);
        }
        return sb.toString();
    }

}