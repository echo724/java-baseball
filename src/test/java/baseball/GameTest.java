package baseball;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import camp.nextstep.edu.missionutils.test.NsTest;

import java.util.ArrayList;
import java.util.List;


import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.*;

public class GameTest extends NsTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @DisplayName("컴퓨터의 수 생성 테스트")
    @Test
    void setComputerNumberTest() {
        assertRandomNumberInRangeTest(() -> {
            game.setComputerNumber();
            List<Integer> testNumber = new ArrayList<>();
            testNumber.add(1);
            testNumber.add(2);
            testNumber.add(3);
            assertThat(game.getComputerNumber()).isEqualTo(testNumber);
        }, 1, 2, 3);
    }

    @DisplayName("유저 입력 메세지 테스트")
    @Test
    void printUserInputPromptTest() {
        game.printUserInputPrompt();
        assertThat(output()).isEqualTo("숫자를 입력해주세요 :");
    }

    @DisplayName("유저 입력 리스트 변환 테스트")
    @Test
    void parseUserInputTest() {
        String testInput = "123";
        List<Integer> testNumber = new ArrayList<>();
        testNumber.add(1);
        testNumber.add(2);
        testNumber.add(3);
        assertThat(game.parseUserInput(testInput)).isEqualTo(testNumber);
    }

    @DisplayName("유저 입력 예외 검사 테스트1 - 입력이 3자리 이상일 경우")
    @Test
    void isUserNumberValidExceptionTest1() {
        String testInput = "1234";
        List<Integer> testNumber = game.parseUserInput(testInput);
        assertThatThrownBy(() -> game.isUserNumberValid(testNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유저 입력 예외 검사 테스트2 - 1~9 사이의 숫자가 아닐 경우")
    @Test
    void isUserNumberValidTest2() {
        String testInput = "012";
        List<Integer> testNumber = game.parseUserInput(testInput);
        assertThatThrownBy(() -> game.isUserNumberValid(testNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유저 입력 예외 검사 테스트3 - 중복된 숫자가 있을 경우")
    @Test
    void isUserNumberValidTest3() {
        String testInput = "332";
        List<Integer> testNumber = game.parseUserInput(testInput);
        assertThatThrownBy(() -> game.isUserNumberValid(testNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    }

    @Override
    public void runMain() {
        game.start();
    }
}
