## `calculate(String input)`

메인 로직: 입/출력과 비즈니스 로직을 분리하는 '관문' 역할을 합니다.

입력 문자열이 null이거나 비어있는지 확인합니다.

CUSTOM_DELIMITER_PATTERN (정규식)을 사용해 커스텀 구분자가 있는지 확인합니다.

커스텀 구분자가 있으면 구분자(delimiters)와 숫자 문자열(numbersInput)을 분리하여 갱신합니다.

최종 결정된 구분자(delimiters)를 기준으로 numbersInput.split()을 호출하고, sumNumbers 메서드에 작업을 위임합니다.

## `sumNumbers(String[] numberTokens)`

합산 로직: split()으로 나뉜 문자열 배열을 받아 실제 합산을 수행합니다.

배열을 순회하며 각 토큰(token)을 parseAndValidate 메서드로 전달하여 숫자로 변환하고 검증합니다.

반환된 숫자들을 모두 더하여 최종 합계(sum)를 반환합니다.

## `parseAndValidate(String token)`

검증 로직: 문자열 토큰 1개를 숫자로 변환하고 유효성을 검사합니다.

Integer.parseInt(token)을 시도합니다.

NumberFormatException 발생 시: "숫자가 아닌 문자"로 간주하여 IllegalArgumentException을 발생시킵니다.

변환된 숫자가 0보다 작은 경우: "음수"로 간주하여 IllegalArgumentException을 발생시킵니다.

검증을 통과하면 변환된 int 값을 반환합니다.
