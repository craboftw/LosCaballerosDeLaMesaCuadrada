package dss.caballerosde.lamesaredonda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Knight knight() {
        return new KnightOfTheRoundTable("Sir Lancelot", quest());
    }

    @Bean
    public Quest<Treasure> quest() {
        return new HolyGrailQuest();
    }

    @Bean
    public HolyGrailQuest holyGrailQuest() {
        return new HolyGrailQuest();
    }

    @Bean
    public Treasure treasure() {
        return new UnholyGrail();
    }

    //


}
