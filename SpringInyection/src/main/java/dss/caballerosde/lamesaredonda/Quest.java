package dss.caballerosde.lamesaredonda;

public interface Quest<Treasure> { abstract Treasure embark() throws QuestFailedException;

}