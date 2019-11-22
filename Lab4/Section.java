package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Section {

    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(String s) {
        this.title = s;
    }

    Section setTitle(String stringTitle) {
        title = stringTitle;
        return this;
    }

    Section addParagraph(String paragraphText) {
        Paragraph p = new Paragraph(paragraphText);
        paragraphs.add(p);
        return this;
    }

    Section addParagraph(Paragraph p) {
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<h2>%s</h2>\n",this.title);

        for(Paragraph x : this.paragraphs) {
            x.writeHTML(out);
        }
    }
}
