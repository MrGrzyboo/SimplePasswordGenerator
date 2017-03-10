
enum CharactersType {
    LowerCase(40, "Lowercase letters"),
    UpperCase(20, "Uppercase letters"),
    Numbers(20, "Numbers"),
    Symbols(20, "Symbols");

    private float percent;
    private String name;

    CharactersType(float percent, String name) {
        this.percent = percent;
        this.name = name;
    }

    public float getPercent() {
        return percent;
    }

    public String getName() {
        return name;
    }


}
