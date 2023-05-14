package dss.caballerosde.lamesaredonda;

public class KnightOfTheRoundTable implements Knight {
    private String name;
    private Quest<Treasure> quest;

    public KnightOfTheRoundTable(String name, Quest<Treasure> quest) {
        this.name = name;
        this.quest = quest;
    }


    public void setQuest(Quest<Treasure> quest) {
        this.quest = quest;
    }


    @Override
    public Treasure embarkOnQuest() {
        try {
            return quest.embark();
        } catch (QuestFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
