public class JoinedRecord {
    private String nrKarty;
    private String datad;
    private String kod;
    private float masa;
    private String jedn;
    private int firma;
    private char typ;
    private String nazwSkr;
    private int nrMag;
    private String opis;

    public JoinedRecord(String nrKarty, String datad, String kod, float masa, String jedn,
            int firma, char typ, String nazwSkr, int nrMag, String opis) {
        this.nrKarty = nrKarty;
        this.datad = datad;
        this.kod = kod;
        this.masa = masa;
        this.jedn = jedn;
        this.firma = firma;
        this.typ = typ;
        this.nazwSkr = nazwSkr;
        this.nrMag = nrMag;
        this.opis = opis;
    }

    public String getNrKarty() {
        return nrKarty;
    }

    public String getDatad() {
        return datad;
    }

    public String getKod() {
        return kod;
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

    public char getTyp() {
        return typ;
    }

    public String getNazwSkr() {
        return nazwSkr;
    }

    public int getNrMag() {
        return nrMag;
    }

    public String getOpis() {
        return opis;
    }

    // Konwersja rekordu na wiersz CSV
    public String toCsvRow() {
        return nrKarty + ";" + datad + ";" + kod + ";" + masa + ";" + jedn + ";" +
                firma + ";" + typ + ";" + nazwSkr + ";" + nrMag + ";" + opis;
    }
}
