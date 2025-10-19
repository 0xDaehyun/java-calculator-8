package calculator;
import camp.nextstep.edu.missionutils.Console;
import java.util.*;

public class Application {
    public static void main(String[] args) {

                char[] Spectator = new char[2]; // 커스텀 구분자 저장소
                Queue <Long> Number_save = new LinkedList<>(); // 숫자를 담는 자료구조
                long result = 0; // 숫자의 합을 저장하는 변수
                String num = "";

                System.out.println("덧셈할 문자열을 입력해 주세요.");
                String input = Console.readLine(); // 입력 방식.

                if(input == null || input.length() == 0) { // 아무것도 없는 문자열은 0으로 표시하기
                    System.out.println("결과 : 0");
                    return;
                }
                // 우선 문장 맨 처음 커스텀 구분자 있는지 확인하기. , Java 타이핑 경우의 수 고려.

        if (input.startsWith("//")) {
            if (input.length() >= 4 && input.charAt(3) == '\n') {
                Spectator[0] = input.charAt(2);
                input = input.substring(4);   // 개행 뒤부터 숫자들
            } else if (input.length() >= 5 && input.startsWith("\\n", 3)) {
                Spectator[0] = input.charAt(2);
                input = input.substring(5);   // "\n" 뒤부터 숫자들
            } else {
                throw new IllegalArgumentException(); // 헤더 형식 위반
            }
        }


                for (char c : input.toCharArray()) { // 연속된 숫자는 String 으로 더하고 , 구분자가 나타나면 그전까지 숫자를 int 로 변환후 큐에 삽입
                    if (Character.isWhitespace(c)) { // 스페이스 방지
                        throw new IllegalArgumentException();
                    }
                    if ('0' <= c && c <= '9') {// 숫자 처리기
                        num += c;
                    }
                    else if(c == ',') {
                        if(num.isEmpty()) {
                            throw new IllegalArgumentException(); // 구분자를 만났는데 숫자가 비어있다면 형식 위반 !
                        }
                        Number_save.add(parseOrIllegal(num));
                        num = "";
                    }
                    else if(c == ':') {
                        if(num.isEmpty()) {
                            throw new IllegalArgumentException(); // 구분자를 만났는데 숫자가 비어있다면 형식 위반 !
                        }
                        Number_save.add(parseOrIllegal(num));
                        num = "";
                    }
                    else { // 새로운 구분자 처리기
                        boolean isSeperator = false;
                        for(char check : Spectator) {
                            if(c == check){
                                if(num.isEmpty()) {
                                    throw new IllegalArgumentException(); // 구분자를 만났는데 숫자가 비어있다면 형식 위반 !
                                }
                                Number_save.add(parseOrIllegal(num));
                                num = "";
                                isSeperator = true;
                                break;
                            };
                        }
                        if(isSeperator == false) throw new IllegalArgumentException(); // 만약 엉뚱한 문자를 받으면 예외처리.
                    }
                }
                if (num.isEmpty()) throw new IllegalArgumentException();
                Number_save.add(parseOrIllegal(num));


                for(long  i : Number_save) {
                    result += i;
                }
                System.out.println("결과 : " + result);
}
    private static long parseOrIllegal(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}