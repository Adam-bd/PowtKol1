import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<DeathCauseStatistic> statistics = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("zgony.csv"))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1) continue; // pomiń nagłówek
                if (line.startsWith("OGÓŁEM")) continue; // pomiń podsumowanie

                DeathCauseStatistic stat = DeathCauseStatistic.fromCsvLine(line);
                statistics.add(stat);
            }

            // Przykładowe wypisanie wyników
            for (DeathCauseStatistic stat : statistics) {
                System.out.println("Linia nr " + lineNumber);
                System.out.println("Kod ICD-10: " + stat.getIcd_10());
                System.out.println("Zgony w grupie 0–4 lata: " + stat.deathCountByAge[0] + "\n");
            }

        } catch (IOException e) {
            System.out.println("Błąd wczytywania pliku: " + e.getMessage());
        }
    }
}