package omnilibre;
public abstract class Materyal {
    private int id;
    private String ad;
    private String yazar;

    public Materyal(int id, String ad, String yazar) {
        this.id = id;
        this.ad = ad;
        this.yazar = yazar;
    }

    public abstract String getOzetBilgi();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public String getYazar() { return yazar; }
    public void setYazar(String yazar) { this.yazar = yazar; }
}
