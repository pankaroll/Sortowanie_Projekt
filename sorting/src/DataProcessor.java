import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataProcessor {
    public static void main(String[] args) {
        String sortKey = "FIRMA"; // Można zmienić na NRKARTY, KOD lub FIRMA
        String kodK = "010203";  // przykładowy kod
        int nrKlienta = 3127;  // przykładowy klient
        int firma = 0;  // przykładowa firma

        

        CsvReader reader = new CsvReader();
        List<Record_Dostawcy> dostawcy = reader.readCsvDostawcy("sorting/dane/Dostawcy.csv");
        List<Record_Magazyn> magazyn = reader.readCsvMagazyn("sorting/dane/Magazynp.csv");
        List<Record_Slownik> slownik = reader.readCsvSlownik("sorting/dane/Slownik.csv");

        // Mapy do szybkiego wyszukiwania
        Map<Integer, Record_Dostawcy> dostawcyMap = dostawcy.stream()
                .collect(Collectors.toMap(Record_Dostawcy::getNrKlienta, d -> d));

        Map<Integer, Record_Slownik> slownikMap = slownik.stream()
                .collect(Collectors.toMap(Record_Slownik::getNrOdp, s -> s));

        // Tworzenie listy połączonych danych
        List<JoinedRecord> joinedRecords = new ArrayList<>();
        for (Record_Magazyn mag : magazyn) {
            Record_Dostawcy dostawca = dostawcyMap.get(mag.getNrKlienta());
            Record_Slownik slowo = slownikMap.get(mag.getNrOdp());

            if (dostawca != null && slowo != null) {
                String kod = generateKod(slowo);
                joinedRecords.add(new JoinedRecord(
                        mag.getNrKarty(), mag.getDate(), kod, mag.getMasa(), mag.getJedn(),
                        mag.getFirma(), slowo.getTyp(), dostawca.getNazwSkr(),
                        mag.getNrMag(), slowo.getOpis()));
            }
        }

        // Sortowanie
        Comparator<JoinedRecord> comparator;
        switch (sortKey) {
            case "KOD":
                comparator = Comparator.comparing(JoinedRecord::getKod);
                break;
            case "FIRMA":
                comparator = Comparator.comparing(JoinedRecord::getFirma);
                break;
            default: // "NRKARTY"
                comparator = Comparator.comparing(JoinedRecord::getNrKarty);
        }

        joinedRecords.sort(comparator);

        // Eksport do CSV
        String outputFilePath = "sorting/dane/Wynik_FIRMA.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            writer.write("NRKARTY;DATAD;KOD;MASA;JEDN;FIRMA;TYP;NAZWA_SKROCONA;NR_MAG;OPIS\n");
            for (JoinedRecord record : joinedRecords) {
                writer.write(record.toCsvRow() + "\n");
            }
            System.out.println("Plik wynikowy zapisano w: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Błąd zapisu pliku: " + e.getMessage());
        }

        float sumaMasy = sumaMasyDlaKoduIKlienta(joinedRecords, kodK, nrKlienta, firma);
        System.out.println("Suma masy dla kodu " + kodK + ", klienta " + nrKlienta + " i firmy " + firma + ": " + sumaMasy);
    }

    private static String generateKod(Record_Slownik slowo) {
        return formatTwoDigits(slowo.getGr()) +
                formatTwoDigits(slowo.getPodGr()) +
                formatTwoDigits(slowo.getRodz());
    }

    // Pomocnicza funkcja do formatu dwucyfrowego
    private static String formatTwoDigits(Integer value) {
        if (value == null) {
            return "00";
        }
        return String.format("%02d", value);
    }

    public static float sumaMasyDlaKoduIKlienta(List<JoinedRecord> joinedRecords, String kod, int nrKlienta, int firma) {
        return (float) joinedRecords.stream()
                .filter(r -> r.getKod().equals(kod) && r.getFirma() == firma && r.getNrKarty().startsWith(String.valueOf(nrKlienta))) 
                .mapToDouble(JoinedRecord::getMasa)
                .sum();
    }
    
}
