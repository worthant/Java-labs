import Colors.Color;
import CosmicObjects.*;
import Exceptions.SoundException;
import Exceptions.UncheckedIOException;
import Exceptions.WeightException;
import Humans.Astronauts;
import Humans.Human;
import Instruments.*;
import Ore.*;
import PlanetObjects.*;
import Sky.Sky;

public class Main {
    public static void main(String[] args) {

        /** Generating my damn world */

        try {
            System.out.println("\nGenerating Universe...\n");
            // cosmic objects (huge!)
            Earth earth = new Earth("Земля", 1, 2, 6371, 5.97 * (10 ^ 6), "day");
            Firmament firmament = new Firmament("Небесный свод", 2);
            Moon moon = new Moon("Луна", 4, 1, 1737.4, 7.36 * (10 ^ 4), "day");
            Sun sun = new Sun("Солнце", 5, 50, 696340, 1.99 * (10 ^ 12), "day");
            Constellations constellations = new Constellations("Созвездие", 6);
            CosmicBeam cosmicBeam = new CosmicBeam("Космический луч", 3);

            // planet objects and sky
            System.out.println("\nGenerating World...\n");
            Sky sky = new Sky();
            Cave cave = new Cave("Пещера", 71, Color.BLACK);
            Rocket rocket = new Rocket("Ракета", 72, Color.BRIGHTBLUE);
            SpaceSuit spaceSuit = new SpaceSuit("Скафандр", 73, Color.GREEN);
            Mountains mountains = new Mountains("Горы", 74, Color.RED);

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


            System.out.println("\n--- going places ---\n");
            System.out.println(per6.say(per2.feelBad() + ", поэтому его надо отправить в ракету и освободиться от скафандра"));
            astronauts.sendTo(per2, rocket);
            per2.setPlanetLocation(rocket);
            System.out.println(per2.takeOff(spaceSuit.getName()));
            System.out.println(per2.putOn("Сандалии"));
            System.out.println(per2.feelGood());

            // check affect() logic
            System.out.println("\n--- affection ---\n");
            System.out.println(astronauts.stay(cave));
            System.out.println(cosmicBeam.affect(0, moon));

            // journey
            System.out.println("\n--- jorney ---\n");
            System.out.println(per3.goOutside(cave));
            System.out.println(per3.go("Обратный путь"));
            System.out.println(per1.go("Обратный путь"));
            System.out.println(per4.go("Обратный путь"));
            System.out.println(per5.go("Обратный путь"));

            // world's equilibrium
            System.out.println("\n--- world's equilibrium ---\n");
            astronauts.someone();
            moon.setTime("night");
            System.out.println("На " + moon.getName() + " наступила " + moon.getTime());
            System.out.println("На " + moon.getName() + " " + moon.getBrightness());
            System.out.println();
            System.out.println(moon.light(10) + " на " + earth.getName());
            System.out.println(earth.light(10) + "на " + moon.getName());
            System.out.println();
            System.out.println(moon.getName() + " размером:");
            moon.getRadius();
            System.out.println("Кажется небольшой круглой тарелкой в сравнении с размером " + earth.getName() + ":");
            earth.getRadius();
            System.out.println();
            System.out.println(sun.light(300) + " на " + earth.getName());
            System.out.println(earth.reflect(90) + " на " + moon.getName());
            System.out.println(moon.light(1) + " на " + earth.getName());
            System.out.println();

            astronauts.draw("тригон-триоктаэдр");
            astronauts.write("пентагон-додекаэдр");
            per4.read("reflection API");
            System.out.println(per4.feelBad());

            // check see logic
            System.out.println("\n--- see? i told you ---\n");
            per1.see(new Sky(), firmament);
            per5.see(new Sky(), constellations);
            sky.getPicture("Небо было цвета " + Color.BLACK + " и на нём был " + earth.diskShine() + " цвета " + Color.BRIGHTWHITE + " и " + Color.BRIGHTBLUE);

            // shining
            System.out.println("\n--- contemplation ---\n");
            System.out.println(mountains.getView());
            System.out.println("Горы были " + mountains.getColor().getName() + " цвета: от " + Color.LightCherry + " до " + Color.PURPLE + " или " + Color.DARKPURPLE);
            System.out.println("Всё, что оставалось в тени, где " + cosmicBeam.affect(0, earth) + " светилось " + Color.EMERALDGREEN + " цветом");
            System.out.println("Это из-за того, что " + cosmicBeam.affect(17, moon));

            // борьба цветов
            System.out.println("\n--- war of colors ---\n");
            Color.War war = new Color.War();
            System.out.println(war.action(Color.RED, Color.GREEN));
            System.out.println(rocket.getView());
            System.out.println("Ракета светилась " + rocket.getColor().getName() + " цветом");

            // toString
            System.out.println("\n--- toString() ---");
            System.out.println("\n" + per1 + "\n" + per2);
            System.out.println("\n" + rocket + "\n" + cave);

            // let's mine some ore
            System.out.println("\n--- minecraft ---");
            astronauts.mine(astronauts.everyone(), lunit);
            astronauts.mine(per2.getName(), antiLunit);
            astronauts.mine(per6.getName(), lunit);
            astronauts.mine(per4.getName(), lunit);
            astronauts.mine(per5.getName(), antiLunit);
            System.out.println(iceAxe.bang(mountains));
            System.out.println(geoHammer.bang(mountains));

            // exception!!!
            spaceSuit.setWeight(0);
            moon.sound();



        } catch (SoundException e) {
            System.err.println(e.getMessage());
        } catch (WeightException e) {
            try {
                System.err.println(e.getMessage());
                throw new UncheckedIOException(e);
            } catch (UncheckedIOException ex) {
                System.err.println(ex.getMessage());
            }
        } finally {
            System.out.println("\nSound exception works, holy moly what a disaster could've happened\n");
        }
    }
}