import CosmicObjects.*;
import Sky.Sky;
import characters.Fuksy;
import characters.ProfessorZvizdochkin;
import characters.Seledochka;
import characters.Znaika;

public class Main {
    public static void main(String[] args) {
        // создаю космические объекты
        Earth earth = new Earth("Земля", "1", 2, 6371, 5.97*(10^6), "day");
        Moon moon = new Moon("Луна", "2", 1, 1737.4, 7.36*(10^4), "day");
        Sun sun = new Sun("Солнце", "3", 50, 696340, 1.99*(10^12), "day");
        Firmament firmanent = new Firmament("Небесный свод");
        Constellations constellations = new Constellations("Создвездие");

        // создаю персонажей
        Fuksy per1 = new Fuksy("Фукки", "4", earth);
        ProfessorZvizdochkin per2 = new ProfessorZvizdochkin("ПрофессорЗвёздочкин", "5", earth);
        Seledochka per3 = new Seledochka("Селёдочка", "6", earth);
        Znaika per4 = new Znaika("Знайка", "7", earth);

        // демонстрация функционала моего микромирка
        System.out.println();
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

        // сложная(для меня) логика, сделал для интереса, яж программистом хочу стать или кем.
        per4.see(new Sky(), firmanent);
        per1.see(new Sky(), firmanent);
        per2.see(new Sky(), firmanent);
        per3.see(new Sky(), firmanent);
        System.out.println();
        per4.see(new Sky(), constellations);
        per1.see(new Sky(), constellations);
        per2.see(new Sky(), constellations);
        per3.see(new Sky(), constellations);

        //

    }
}