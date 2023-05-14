package dss.caballerosde.lamesaredonda;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TransferQueue;

public class HolyGrailQuest implements Quest<Treasure> {

    //make the trasure a bean and inject it into the quest
    @Autowired
    private Treasure treasure;
    public HolyGrailQuest() {
        //...
    }
    public Treasure embark() throws QuestFailedException {
        //...
        return treasure;
    }


}
