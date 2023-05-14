package dss.caballerosde.lamesaredonda;

public interface Treasure {

        default boolean isHoly()
        {
            return false;
        }
        default boolean isEvil()
        {
            return false;
        }
        default boolean isAncient()
        {
            return false;
        }

}
