import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvReader {
    public List<Record_Dostawcy> readCsvDostawcy(String filePath) {
        List<Record_Dostawcy> dostawcy = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            br.readLine(); // Pominięcie pierwszej linii (nagłówka)

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                dostawcy.add(new Record_Dostawcy(
                        Integer.parseInt(values[0].replace(" ", "")),
                        values[1],
                        values[2],
                        values[3]));
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas czytania pliku: " + e.getMessage());
        }
        return dostawcy;
    }

    public List<Record_Magazyn> readCsvMagazyn(String filePath) {
        List<Record_Magazyn> magazyn = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            br.readLine(); // Pominięcie pierwszej linii (nagłówka)

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                magazyn.add(new Record_Magazyn(
                        values[0], values[1],
                        Float.parseFloat(values[2]),
                        values[3],
                        Integer.parseInt(values[4].replace(" ", "")),
                        Integer.parseInt(values[5].replace(" ", "")),
                        Integer.parseInt(values[6].replace(" ", "")),
                        Integer.parseInt(values[7].replace(" ", ""))));
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas czytania pliku: " + e.getMessage());
        }
        return magazyn;
    }

    public List<Record_Slownik> readCsvSlownik(String filePath) {
        List<Record_Slownik> slownik = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            br.readLine(); // Pominięcie pierwszej linii (nagłówka)

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                slownik.add(new Record_Slownik(
                        Integer.parseInt(values[0]), // gr
                        parseNullableInt(values[1]), // podGr (obsługa .0 i null)
                        parseNullableInt(values[2]), // rodz (obsługa .0 i null)
                        values[3].isEmpty() ? ' ' : values[3].charAt(0), // typ jako pojedynczy znak
                        values[4], // opis
                        Integer.parseInt(values[5].replaceAll(" ", "")) // nrOdp bez spacji
                ));
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas czytania pliku: " + e.getMessage());
        }
        return slownik;
    }

    private Integer parseNullableInt(String value) {
        if (value.isEmpty())
            return null; // Obsługa pustych wartości jako null
        return Integer.parseInt(value.replace(".0", "")); // Usunięcie .0 przed konwersją
    }
}
