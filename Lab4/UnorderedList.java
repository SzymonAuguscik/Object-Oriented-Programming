package lab4;

import java.io.PrintStream;
import java.util.ArrayList;

class UnorderedList {
    ArrayList<ListItem> items = new ArrayList<>();

    ListItem addItem(String s) {
        ListItem item = new ListItem(s);
        this.items.add(item);
        return item;
    }

    UnorderedList addItem(ListItem l) {
        this.items.add(l);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.print("<ul>\n");
        for (ListItem x : this.items)
        {
            x.writeHTML(out);
        }
        out.print("</ul>\n");
    }
}
