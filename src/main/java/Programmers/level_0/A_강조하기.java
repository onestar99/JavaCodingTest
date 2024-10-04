package Programmers.level_0;


public class A_강조하기 {

    /*
    * 문자열 myString이 주어집니다. myString에서 알파벳 "a"가 등장하면 전부 "A"로 변환하고,
    * "A"가 아닌 모든 대문자 알파벳은 소문자 알파벳으로 변환하여 return 하는 solution 함수를 완성하세요.
    *
    * */

    public static String solution(String myString) {

        // #1: 전부 소문자 알파벳으로 변환한다.
        myString = myString.toLowerCase();
        char[] cArr = myString.toCharArray();


        // #2: 알파벳 'a'를 찾아서 'A' 로 바꾼다.
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == 'a') {
                cArr[i] = 'A';
            }
        }

        return String.valueOf(cArr);
    }


    public static void main (String[] args) {

        String yString = "abstract algebra";

        System.out.println(solution(myString));

        String myString2 = "PrOgRaMmErS";

        System.out.println(solution(myString2));




        // 	"AbstrAct AlgebrA"

    }
}
