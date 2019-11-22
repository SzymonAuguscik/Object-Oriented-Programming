package lab4;

import java.io.PrintStream;

class ListItem {

    String content;

    ListItem(String s) {this.content = s;}

    void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>\n",content);
    }
}
