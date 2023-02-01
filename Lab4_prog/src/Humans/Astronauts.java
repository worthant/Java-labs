package Humans;

import java.lang.Math;
import CosmicObjects.CosmicObject;
import Interfaces.Imagine;
import Ore.Ore;
import PlanetObjects.PlanetObject;

public class Astronauts extends Human {
    public Astronauts(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
        super(name, id, cosmicLocation, planetLocation);
    }

    private boolean flagLunit = false;
    private boolean flagAntiLunit = false;
    public String everyone() {
        return "Все";
    }

    /** {@link Hey} {@link look!} This is <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html">
     *      <cite>anonymous class</cite></a> */
    public static void someone() {
        Imagine someone = new Imagine() {
            @ Override
            public void imagine() {
                System.out.println("Кто-то воображает");
            }
        };
        someone.imagine();
    }

    public String sendTo(Human per, PlanetObject destination) {
        return per.getName() + " отправляет в " + destination.getName();
    }

    public void mine(String per, Ore someOre) {
        System.out.println(per + " принялся за добычу " + someOre.getType());
        if (!flagLunit && someOre.getType() == "лунит") {
            flagLunit = true;
            System.out.println("Получено достижение 'АЛМАЗЫ!'");
        }
        if (!flagAntiLunit && someOre.getType() == "антилунит") {
            flagAntiLunit = true;
            System.out.println("Получено достижение 'Две стихии'");
        }
    }

    /** {@link Hey} {@link look!} All classes down below are <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html">
     *      <cite>non-static nested classes</cite></a> */
    public class Fuksy extends Human {
        public Fuksy(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
            super(name, id, cosmicLocation, planetLocation);
            System.out.println(name + " появился на свет");
        }
    }

    public class ProfessorZvizdochkin extends Human {
        public ProfessorZvizdochkin(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
            super(name, id, cosmicLocation, planetLocation);
            System.out.println(name + " появился на свет");
        }
    }

    public class Seledochka extends Human {
        public Seledochka(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
            super(name, id, cosmicLocation, planetLocation);
            System.out.println(name + " появился на свет");
        }
    }

    public class Znaika extends Human {
        public Znaika(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
            super(name, id, cosmicLocation, planetLocation);
            System.out.println(name + " появился на свет");
        }
    }

    public class Neznaika extends Human {
        public Neznaika(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
            super(name, id, cosmicLocation, planetLocation);
            System.out.println(name + " появился на свет");
        }
    }

    public class DoctorPillulkin extends Human {
        public DoctorPillulkin(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
            super(name, id, cosmicLocation, planetLocation);
            System.out.println(name + " появился на свет");
        }
    }
}
