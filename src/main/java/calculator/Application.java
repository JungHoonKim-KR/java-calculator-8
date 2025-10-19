package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    // 커스텀 구분자 패턴: "//(구분자)\n(숫자)"
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");

    // 기본 구분자 (쉼표 또는 콜론)
    private static final String DEFAULT_DELIMITERS = ",|:";

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculate(input);
        System.out.println(result);
    }
    public static int calculate(String input) {
        // 1. "" 또는 null 입력 시 0 반환
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiters = DEFAULT_DELIMITERS;
        String numbersInput = input;

        // 2. 커스텀 구분자 확인
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (matcher.find()) {
            // Pattern.quote()를 사용하여 구분자가 정규식 특수 문자일 경우 이스케이프 처리
            delimiters = Pattern.quote(matcher.group(1)); // group(1): 커스텀 구분자
            numbersInput = matcher.group(2);            // group(2): 숫자 문자열
        }

        // 3. 구분자를 기준으로 문자열 분리 및 합산
        return sumNumbers(numbersInput.split(delimiters));
    }

    private static int sumNumbers(String[] numberTokens) {
        int sum = 0;
        for (String token : numberTokens) {
            // "1,,2" 같이 빈 토큰이 발생하는 경우 무시
            if (token.isEmpty()) {
                continue;
            }
            sum += parseAndValidate(token);
        }
        return sum;
    }
    private static int parseAndValidate(String token) {
        int number;
        try {
            number = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            // 4. 숫자가 아닌 값이 입력된 경우 예외 발생
            throw new IllegalArgumentException("입력 문자열에 숫자가 아닌 문자가 포함되어 있습니다.");
        }

        // 4. 음수가 입력된 경우 예외 발생
        if (number < 0) {
            throw new IllegalArgumentException("입력 문자열에 음수가 포함되어 있습니다.");
        }

        return number;
    }
}
