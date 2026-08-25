package ai0825;

import java.util.Random;

public class LABRandomWiseSaying {
    public static void main(String[] args) {
        String[] wiseSay = {
                "너 자신을 알라. —소크라테스 ",
                "나는 생각한다, 고로 존재한다. —르네 데카르트 ",
                "삶은 뒤돌아볼 때 이해되지만, 앞으로 살아가야 한다. —쇠렌 키르케고르 ",
                "인간은 자유롭도록 선고받았다. —장폴 사르트르 ",
                "나를 죽이지 못하는 것은 나를 더 강하게 만든다. —프리드리히 니체 ",
                "행복은 우리 자신에게 달려 있다. —아리스토텔레스",
                "우리를 괴롭히는 것은 사물 자체가 아니라 그것에 대한 우리의 판단이다. —에픽테토스",
                "한 사람이 어떤 사람인지는 그가 권력을 가졌을 때 드러난다. —플라톤",
                "변화 외에 영원한 것은 없다. —헤라클레이토스",
                "삶의 의미를 묻는 대신, 삶이 우리에게 무엇을 요구하는지 물어야 한다. —빅터 프랭클"

        };

        Random random = new Random();
        int randomIndex = random.nextInt(10);
        System.out.println("오늘의 명언: " + wiseSay[randomIndex]);
    }
}
