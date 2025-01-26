public class Record_Magazyn {
    private String nrKarty;
    private String date;
    private float masa;
    private String jedn;
    private int firma;
    private int nrMag;
    private int nrOdp;
    private int nrKlienta;

    public Record_Magazyn(String nrKarty, String date, float masa, String jedn, int firma, int nrMag, int nrOdp,
            int nrKlienta) {
        this.nrKarty = nrKarty;
        this.date = date;
        this.masa = masa;
        this.jedn = jedn;
        this.firma = firma;
        this.nrMag = nrMag;
        this.nrOdp = nrOdp;
        this.nrKlienta = nrKlienta;
    }

    public String getNrKarty() {
        return nrKarty;
    }

    public String getDate() {
        return date;
    }

    public float getMasa() {
        return masa;
    }

    public String getJedn() {
        return jedn;
    }

    public int getFirma() {
        return firma;
    }

    public int getNrMag() {
        return nrMag;
    }

    public int getNrOdp() {
        return nrOdp;
    }

    public int getNrKlienta() {
        return nrKlienta;
    }

}
