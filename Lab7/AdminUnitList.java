package adminLevels;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();

    AdminUnitList(String filename) throws IOException {
        read(filename);
    }

    AdminUnitList() {}

    /**
     * Czyta rekordy pliku i dodaje do listy
     * @param filename nazwa pliku
     */
    public void read (String filename) throws IOException {
        HashMap<Long,AdminUnit> long2UnitID = new HashMap<>();
        HashMap<AdminUnit,Long> unit2LongID = new HashMap<>();
        HashMap<AdminUnit,Long> unit2ParentID = new HashMap<>();
        HashMap<Long,List<AdminUnit>> parentID2Child = new HashMap<>();

        CSVReader reader = new CSVReader(filename,",",true);
        while (reader.next()) {
            long unitID = reader.getLong(0);
            long parentID = -1;
            if (!reader.get(1).equals(""))
                parentID = reader.getLong(1);

            String name = reader.get(2);

            int adminLevel = 0;
            if (!reader.get(3).equals(""))
                adminLevel = reader.getInt(3);
            double population = 0;
            if (!reader.get(4).equals(""))
                population = reader.getDouble(4);
            double area = 0;
            if (!reader.get(5).equals(""))
                area = reader.getDouble(5);
            double density = 0;
            if (!reader.get(6).equals(""))
                density = reader.getDouble(6);

            AdminUnit unit = new AdminUnit(name, adminLevel, population, area, density);

            units.add(unit);
            long2UnitID.put(unitID, unit);
            unit2LongID.put(unit,unitID);

            List<AdminUnit> children = new ArrayList<>();

            if (parentID != -1) {
                unit2ParentID.put(unit, parentID);

                if (parentID2Child.get(parentID) == null) {
                    children.add(unit);
                    parentID2Child.put(parentID, children);
                }
                else {
                    parentID2Child.get(parentID).add(unit);
                }
            }
            else
                unit2ParentID.put(null,parentID);
        }

        for(var unit : units) {
            unit.parent = long2UnitID.get(unit2ParentID.get(unit));
            unit.children = parentID2Child.get(unit2LongID.get(unit));
        }

        for(var unit : units) {
            if (unit.density == 0 || unit.population == 0)
                unit.fixMissingValues();
        }
    }

    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     * @param out
     */
    void list(PrintStream out){
        for (var unit : units) {
            out.println(unit.toString());
        }
    }

    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     * @param out - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out,int offset, int limit ){
        for (int i = offset - 1; i < limit + offset - 1; i++)
            out.println(units.get(i).toString());
    }

    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();

        if (regex) {
            for (var unit : units)
                if (unit.name.matches(pattern))
                    ret.units.add(unit);
        }

        else {
            for (var unit : units)
                if (unit.name.contains(pattern))
                    ret.units.add(unit);
                }

        return ret;
    }
}

