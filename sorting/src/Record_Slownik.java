public class Record_Slownik {
    private int gr;
    private Integer podGr;
    private Integer rodz;
    private char typ;
    private String opis;
    private int nrOdp;

    public Record_Slownik(int gr, Integer podGr, Integer rodz, char typ, String opis, int nrOdp) {
        this.gr = gr;
        this.podGr = podGr;
        this.rodz = rodz;
        this.typ = typ;
        this.opis = opis;
        this.nrOdp = nrOdp;
    }

    public int getGr() {
        return gr;
    }

    public Integer getPodGr() {
        return podGr;
    }

    public Integer getRodz() {
        return rodz;
    }

    public char getTyp() {
        return typ;
    }

    public String getOpis() {
        return opis;
    }

    public int getNrOdp() {
        return nrOdp;
    }

}
