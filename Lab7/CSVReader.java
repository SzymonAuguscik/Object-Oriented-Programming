package adminLevels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    /**
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     **/

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) parseHeader();
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }
    }


    boolean next() throws IOException {
        String line = reader.readLine();

        if (line == null)
            return false;

        current = line.split(delimiter);

        return true;
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing (int columnIndex) {
        if (getRecordLength() <= columnIndex || columnIndex < 0)
            return true;

        return current[columnIndex].equals("");
    }

    boolean isMissing(String columnLabel) {
        if (!getColumnLabels().contains(columnLabel))
            return true;
        int i = columnLabelsToInt.get(columnLabel);
        return current[i].equals("");
    }

    String get(int columnIndex) {
        if (isMissing(columnIndex))
            return "";
        return current[columnIndex];
    }

    String get(String columnLabel) {
        if (isMissing(columnLabel))
            return "";
        int i = columnLabelsToInt.get(columnLabel);
        return current[i];
    }

    int getInt(int columnIndex) {
        return Integer.parseInt(get(columnIndex));
    }

    int getInt(String columnLabel) {
        return Integer.parseInt(get(columnLabel));
    }

    long getLong(int columnIndex) {
        return Long.parseLong(get(columnIndex));
    }

    long getLong(String columnLabel) {
        return Long.parseLong(get(columnLabel));
    }

    double getDouble(int columnIndex) {
        return Double.parseDouble(get(columnIndex));
    }

    double getDouble(String columnLabel) {
        return Double.parseDouble(get(columnLabel));
    }
}
