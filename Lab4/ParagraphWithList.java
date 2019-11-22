package lab4;

import java.io.PrintStream;

class ParagraphWithList extends Paragraph {

    UnorderedList list = new UnorderedList();

    ParagraphWithList(String s){
        super(s);
        setContent(s);
    }

    ParagraphWithList addItemToList(String s) {
        ListItem item = new ListItem(s);
        this.list.addItem(item);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<h3>%s</h3>",this.content);
        this.list.writeHTML(out);
    }
}
