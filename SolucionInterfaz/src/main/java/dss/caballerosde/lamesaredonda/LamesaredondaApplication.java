package dss.caballerosde.lamesaredonda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LamesaredondaApplication {

    public static void main(String[] args) {
        Knight knight = new KnightOfTheRoundTable("Lancelot", new HolyGrailQuest());
        knight.embarkOnQuest();
        //test if the grail found is the holy grail or the fake one
        Treasure grail = knight.embarkOnQuest();
        if (grail.isHoly()) {
            System.out.println("The grail found is the holy grail");
        } else {
            System.out.println("The grail found is the Evil Devil Canibal grail");
        }

    }

}
