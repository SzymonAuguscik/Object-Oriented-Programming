package lab4;

import java.io.PrintStream;

class Paragraph {

    String content;

    Paragraph(String s) {
        setContent(s);
    }

    Paragraph setContent(String c) {
        this.content = c;
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>\n",this.content);
    }
}
