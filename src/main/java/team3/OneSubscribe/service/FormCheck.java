package team3.OneSubscribe.service;

public class FormCheck {

    private static final int minLEN = 6;
    private static final int maxLEN = 15;

    //id형식검사. 통과하면 0. 통과못하면 상황에 따른 int반환.
    public static int idFormCheck(String loginId) {
        if (loginId.length() < minLEN || loginId.length() > maxLEN) {
            return 1;
        }
        if (!isEngish(loginId.charAt(0))) {//첫글자가 영어가 아니라면 탈락.
            return 2;
        }

        for (int i = 0; i < loginId.length(); ++i) {
            char ch = loginId.charAt(i);
            if (!(isNumber(ch) || isEngish(ch) || ch == '_')) { //영어도 숫자도 '_'도 아니면 false
                return 3;
            }
        }
        return 0;//다 통과하면 true


    }

    //pw형식검사
    //통과하면 0 아니면 다른 숫자.
    public static int pwFormCheck(String loginPw) {

        if (loginPw.length() < minLEN || loginPw.length() > maxLEN) {
            return 1;
        }

        for (int i = 0; i < loginPw.length(); ++i) {
            char ch = loginPw.charAt(i);
            if (!(isNumber(ch) || isEngish(ch) || ch == '~' || ch == '!' || ch == '@')) { //영어도 숫자도, ~!@도 아니면 false
                return 3;//idFormCheck와 맞추기 위해 3을 반환.
            }
        }

        return 0;
    }

    public static boolean isEngish(char ch) {
        return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z');
    }

    public static boolean isNumber(char ch) {
        return '0' <= ch && ch <= '9';
    }

}
