package dss.caballerosde.lamesaredonda;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AppConfig.class)
public class KnightOfTheRoundTableTest{

    @Test
    public void testEmbarkOnQuest() throws QuestFailedException {
            KnightOfTheRoundTable knight =  new KnightOfTheRoundTable("CruzadoMagico", new HolyGrailQuest());
            Treasure grail = knight.embarkOnQuest();
            assertNotNull(grail);
            assertTrue(grail.isHoly());
        }
    }
