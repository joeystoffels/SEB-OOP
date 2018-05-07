package nl.han.ica.airspaceinvaders.level;

public class LevelLoadStatus {

    private int loaded = 0;
    private int total = 0;

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
