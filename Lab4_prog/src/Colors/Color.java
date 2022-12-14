package Colors;

public enum Color {
    BLACK("чёрный"),
    GREEN("зелёный"),
    BLUE("голубой"),
    EMERALDGREEN("изумрудно-зелёный"),
    WHITE("белый"),
    BRIGHTWHITE("ярко-белый"),
    RED("красный"),
    LightCherry("светло-вишнёвый"),
    DARKPURPLE("тёмно-фиолетовый"),
    BRIGHTBLUE("светло-голубой"),
    PURPLE("пурпурный");

    private String name;
    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class War {
        public String action(Color opp1, Color opp2) {
            return "Происходит борьба двух цветов: " + opp1.getName() + " и " + opp2.getName();
        }
    }
}
