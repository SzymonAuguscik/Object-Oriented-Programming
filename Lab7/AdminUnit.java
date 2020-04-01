package adminLevels;

import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    List<AdminUnit> children;
    BoundingBox bbox = new BoundingBox();

    AdminUnit(String name1, int adminLevel1, double population1, double area1, double density1) {
        name = name1;
        adminLevel = adminLevel1;
        population = population1;
        area = area1;
        density = density1;
    }

    public String toString() {
        String str = "Name: " + name + "\n";
        if (parent != null)
            str += "Admin parent: " + parent.name + "\n";
        if (children != null) {
            for (int i = 0; i < children.size(); i++) {
                if (i == 0) {
                    str += "Admin children: " + children.get(i).name + "\n";
                    i++;
                }
                else
                    str += "                " + children.get(i).name + "\n";
            }
        }
        str += "Population: " + population + "\n";
        str += "Area: " + area + "\n";
        str += "Density: " + density +"\n";
        return str;
    }

    public void fixMissingValues() {
        if (parent.density != 0) {
            density = parent.density;
            population = density*area;
        }

        else {
            parent.fixMissingValues();
            fixMissingValues();
        }
    }
}
