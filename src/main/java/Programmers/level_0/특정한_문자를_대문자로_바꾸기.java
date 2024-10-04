package Programmers.level_0;

public class 특정한_문자를_대문자로_바꾸기 {

    public static String solution(String my_string, String alp) {
        String answer;

        my_string = my_string.replace(alp, alp.toUpperCase());


        return my_string;
    }

    public static void main(String[] args) {

        String my_string = "programmers";
        String alp = "p";
        System.out.println(solution(my_string, alp));

    }
}