## Ejercicio Knights of the Round Table

Para rediseñar la interfaz `Quest` del ejemplo `KnightOfTheRoundTable` para que la genericidad no se implemente con `java.lang.Object`, podemos crear una interfaz `Quest` que reciba objetos de otra interfaz llamada `Treasure`. `HolyGrail` será una implementación de `Treasure` y `HolyGrailQuest` será una implementación de `Quest<Treasure>`.

```java
public interface Knight {
    Object embarkOnQuest() throws QuestFailedException;
}

public interface Treasure {
    // Define methods for Treasure interface
}

public interface Quest<Treasure> {
    T embark() throws QuestFailedException;
}

public class HolyGrail implements Treasure {
    // Define methods for HolyGrail
}

public class HolyGrailQuest implements Quest<Treasure> {
    public HolyGrailQuest() {
        //...
    }

    public HolyGrail embark() throws QuestFailedException {
        HolyGrail grail = null;
        // Look for grail ...
        return grail;
    }
}

public class KnightOfTheRoundTable implements Knight {
    private String name;
    private Quest<Treasure> quest;

    public KnightOfTheRoundTable(String name,Quest<Trasure> quest) {
        this.name = name;
        this.quest = quest;
    }

    public Treasure embarkOnQuest() throws QuestFailedException {
        try{
            return quest.embark();
        }catch (QuestFailedException e)
        {
            throw new RuntimeException(e);
        }

    public void setQuest(Quest<Treasure> quest) {
        this.quest = quest;
    }
}
```

Con esta modificación, la interfaz `Quest` ahora es genérica y recibe objetos que extienden la interfaz `Treasure`. `HolyGrail` implementa la interfaz `Treasure` y `HolyGrailQuest` implementa la interfaz `Quest<HolyGrail>`. Además, en la clase `KnightOfTheRoundTable`, la variable `quest` ahora es del tipo `Quest<Treasure>`, lo que significa que puede contener cualquier tipo de objeto que Implemente `Treasure`.

Este diseño ofrece varias ventajas adicionales:

1. **Mejora la seguridad del tipo**: al usar un tipo genérico `Quest<Treasure>` en lugar de `Object` en la interfaz `Knight`, podemos asegurarnos de que el objeto que se pasa a `embarkOnQuest()` es compatible con la misión que se espera que el caballero realice. Esto ayuda a evitar errores de tiempo de ejecución causados por tipos incompatibles.

2. **Hace que el código sea más fácil de leer y entender**: al usar interfaces genéricas en lugar de tipos de objetos más generales como `Object`, el código se vuelve más legible y fácil de entender. Los nombres de las interfaces son más descriptivos, lo que hace que el código sea más fácil de seguir y entender.

3. **Mejora la reutilización de código**: la creación de interfaces genéricas como `Quest<Treasure>` hace que el código sea más modular y fácil de reutilizar en diferentes proyectos. Pueden crearse múltiples implementaciones de la interfaz `Treasure` y `Quest`, y el código que utiliza estas interfaces será compatible con cualquier implementación compatible.

4. **Facilita el mantenimiento del código**: cuando se utiliza código genérico, cualquier cambio en una implementación de la interfaz tendrá un impacto mínimo en el resto del código. Esto facilita la tarea de mantener el código a lo largo del tiempo.

## Ejercicio Inyección de dependencias

La solución propuesta utiliza el framework de inyección de dependencias de Spring para manejar las dependencias del ejemplo KnightsOfTheRoundTable. 

En el archivo de configuración `AppConfig`, se definen cuatro beans: `knight`, `quest`, `holyGrailQuest` y `treasure`. 

```java
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
}
```

El bean `knight` define un objeto `KnightOfTheRoundTable` que se inyecta con la dependencia `quest` utilizando el constructor. Esto significa que el objeto `KnightOfTheRoundTable` ya no necesita crear la instancia de la búsqueda `HolyGrailQuest` por sí mismo, sino que la instancia se proporciona mediante la inyección de dependencia.

Los beans `quest`, `holyGrailQuest` y `treasure` definen instancias de clases que implementan interfaces y se utilizan en otras partes del código. `quest` utiliza `holyGrailQuest` que es una instancia de `HolyGrailQuest` y `HolyGrailQuest` utiliza `treasure` que es una instancia de `UnholyGrail`.

Con la configuración de Spring, la instancia de `KnightOfTheRoundTable` ahora se crea con las dependencias inyectadas por el contenedor de Spring. Esto hace que el código sea más modular y fácil de mantener, ya que las dependencias se administran centralmente en lugar de estar dispersas en todo el código.

Para utilizar el contenedor de Spring, es necesario agregar las anotaciones `@Configuration` y `@Bean` a las clases y métodos correspondientes. Además, se debe configurar el contexto de Spring en la aplicación y cargar el archivo de configuración `AppConfig`. Esto se puede hacer mediante el uso de una clase `Main` que cargue el contexto de Spring y obtenga una instancia de `Knight` a través del contenedor de Spring.

```java
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
```