public class Record_Dostawcy {
    private int nrKlienta;
    private String miejscowosc;
    private String ulica;
    private String nazwSkr;

    public Record_Dostawcy(int nrKlienta, String miejscowosc, String ulica, String nazwSkr) {
        this.nrKlienta = nrKlienta;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nazwSkr = nazwSkr;
    }

    public int getNrKlienta() {
        return nrKlienta;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public String getNazwSkr() {
        return nazwSkr;
    }

}
