package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    // 커스텀 구분자 패턴: "//(구분자)\n(숫자들)"
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");
    private static final String DEFAULT_DELIMITERS = ",|:";

    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    public static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        input = input.replace("\\n", "\n");

        String delimiters = DEFAULT_DELIMITERS;
        String numbersInput = input;

        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (matcher.matches()) {
            String customDelimiter = Pattern.quote(matcher.group(1));
            delimiters = DEFAULT_DELIMITERS + "|" + customDelimiter;
            numbersInput = matcher.group(2);
        }

        return sumNumbers(numbersInput.split(delimiters));
    }

    private static int sumNumbers(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            sum += parseAndValidate(token);
        }
        return sum;
    }

    private static int parseAndValidate(String token) {
        int number;
        try {
            number = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력 문자열에 숫자가 아닌 문자가 포함되어 있습니다.");
        }

        if (number < 0) {
            throw new IllegalArgumentException("입력 문자열에 음수가 포함되어 있습니다.");
        }

        return number;
    }
}
