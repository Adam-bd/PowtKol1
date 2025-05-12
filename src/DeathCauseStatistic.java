public class DeathCauseStatistic {
    private String icd_10;
    int[] deathCountByAge = new int[19];

    public DeathCauseStatistic(String icd_10, int[] deathCountByAge) {
        this.icd_10 = icd_10;
        this.deathCountByAge = deathCountByAge;
    }

    public String getIcd_10() {
        return icd_10;
    }

    public static DeathCauseStatistic fromCsvLine(String line){
        String[] icd_10Code = line.split(" "); // program odczytuje przerwę jako spację nie jako tabulator
        String[] fields = line.split(",");
        int[] deaths = new int[19];

        for(int i = 0; i < 19; i++){
            String value = fields[i + 1];
            if(value.equals("-")){
                deaths[i] = 0;
            }else{
                try {
                    deaths[i] = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    deaths[i] = 0; // zabezpieczenie na wypadek błędnych danych
                }
            }
        }
        return new DeathCauseStatistic(icd_10Code[0], deaths);
    }
}
