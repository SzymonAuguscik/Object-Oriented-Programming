package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String t) {
        addTitle(t);
    }

    Document addTitle(String title) {
        this.title = title;
        return this;
    }

    Document addPhoto(String photoUrl) {
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle) {
        Section s = new Section(sectionTitle);
        this.sections.add(s);
        return s;
    }

    Document addSection(Section s) {
        sections.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        out.print("<?xml version=\"1.0\"?><!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
        out.print("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
        out.printf("<head>\n<title>%s</title>\n<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml;charset=UTF-8\" />\n</head>\n",this.title);
        out.print("<body>\n");
        out.printf("<h1>%s</h1>\n",this.title);
        this.photo.writeHTML(out);

        for (Section x : this.sections) {
            x.writeHTML(out);
        }

        out.print("</body>\n</html>\n");
    }
}
