package test;

public class Answer {
    public static void main(String[] args) {
        System.out.println(exists("","123abc22323"));
        System.out.println(exists("abc","123"));
    }
    public static boolean exists(String sub, String src) {
        if (sub == null || src == null || sub.length() > src.length()) {
            return false;
        }
        for (int i = 0; i <= src.length() - sub.length(); i++) {
            int j;
            for (j = 0; j < sub.length(); j++) {
                if (src.charAt(i + j) != sub.charAt(j)) {
                    break;
                }
            }
            if (j == sub.length()) {
                return true;
            }
        }
        return false;
    }
}
