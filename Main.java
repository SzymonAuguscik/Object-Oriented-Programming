package adminLevels;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AdminUnitList unit = new AdminUnitList("admin-units.csv");

        unit.list(System.out,4452,11);

        unit.selectByName("gmina Trzyciąż",false).list(System.out);
        unit.selectByName("Sokołów Podlaski",false).list(System.out);
    }
}