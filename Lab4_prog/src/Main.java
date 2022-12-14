import Colors.Color;
import CosmicObjects.*;
import Humans.Astronauts;
import Humans.Human;
import Instruments.*;
import Ore.*;
import PlanetObjects.*;
import Sky.Sky;

public class Main {
    public static void main(String[] args) {
//        Color.War war = new Color.War();
//        System.out.println(war.action(Color.RED, Color.GREEN));

        /** Generating my damn world */

        System.out.println("\nGenerating Universe...\n");
        // cosmic objects (huge!)
        Earth earth = new Earth("Земля", 1, 2, 6371, 5.97 * (10 ^ 6), "day");
        Firmament firmament = new Firmament("Небесный свод", 2);
        Moon moon = new Moon("Луна", 4, 1, 1737.4, 7.36 * (10 ^ 4), "day");
        Sun sun = new Sun("Солнце", 5, 50, 696340, 1.99 * (10 ^ 12), "day");
        Constellations constellations = new Constellations("Созвездие", 6);
        CosmicBeam cosmicBeam = new CosmicBeam("Космический луч", 3);

        // planet objects
        System.out.println("\nGenerating World...\n");
        Cave cave = new Cave("Пещера", 71);
        Rocket rocket = new Rocket("Ракета", 72);
        SpaceSuit spaceSuit = new SpaceSuit("Скафандр", 73);
        Mountains mountains = new Mountains("Горы", 74);

        // guys minding their own business (they all spawn on Moon)
        System.out.println("\nGenerating villagers...\n");
        Astronauts astronauts = new Astronauts("Космонавты", 10, moon, cave);
        Astronauts.Fuksy per1 = astronauts.new Fuksy("Фукки", 11, moon, cave);
        Astronauts.Neznaika per2 = astronauts.new Neznaika("Незнайка", 12, moon, cave);
        Astronauts.Znaika per3 = astronauts.new Znaika("Знайка", 13, moon, cave);
        Astronauts.Seledochka per4 = astronauts.new Seledochka("Селёдочка", 14, moon, cave);
        Astronauts.ProfessorZvizdochkin per5 = astronauts.new ProfessorZvizdochkin("Профессор звёздочкин", 15, moon, cave);
        Astronauts.DoctorPillulkin per6 = astronauts.new DoctorPillulkin("Доктор пилюлькин", 16, moon, cave);

        // Generating THE ROCK
        System.out.println("\nGenerating THE ROCK...\n");
        Ore lunit = new Lunit("лунит");
        Ore antiLunit = new AntiLunit("антилунит");

        // Generating tools
        System.out.println("\nGenerating tools...\n");
        Instruments geoHammer = new GeoHammer("геологический молоток");
        Instruments iceAxe = new IceAxe("ледоруб");


        /** FINALLY, THE FUN PART BEGINS! */


        System.out.println("\n--- going places ---");
        System.out.println(per6.say(per2.feelBad() + ", поэтому его надо отправить в ракету и освободиться от скафандра"));
        per2.setPlanetLocation(rocket);
        System.out.println(per2.takeOff(spaceSuit.getName()));
        System.out.println(per2.putOn("Сандалии"));
        System.out.println(per2.feelGood());

        System.out.println("\nCommanding those filthy slaves\n");
        per3.sendTo();

        // let's mine some ore
        System.out.println("\n--- minecraft ---");
        System.out.println(iceAxe.bang(mountains));
        System.out.println(geoHammer.bang(mountains));
        astronauts.mine(astronauts.everyone(), lunit);
        astronauts.mine(per2.getName(), antiLunit);
        astronauts.mine(per6.getName(), lunit);
        astronauts.mine(per4.getName(), lunit);
        astronauts.mine(per5.getName(), antiLunit);

        // check affect() logic
        System.out.println("\n--- affection ---");
        System.out.println(cosmicBeam.affect(17, moon));
        System.out.println(cosmicBeam.affect(94, earth));

        // check see logic
        System.out.println("\n--- see? i told you ---");
        per1.see(new Sky(), firmament);
        per5.see(new Sky(), constellations);
        per3.see(new Sky(), sun);

        // check getView() logic
        System.out.println("\n--- contemplation ---");
        System.out.println(rocket.getView());
        System.out.println(mountains.getView());

        // toString
        System.out.println("\n" + per1 + "\n" + per2);
        System.out.println("\n" + rocket + "\n" + cave);

    }
}