package dss.caballerosde.lamesaredonda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LamesaredondaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LamesaredondaApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Knight knight = context.getBean(Knight.class);
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
