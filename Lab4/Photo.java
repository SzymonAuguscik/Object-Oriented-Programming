package lab4;

import java.io.PrintStream;

class Photo {

    String url;

    Photo(String url){
        this.url = url;
    }

    void writeHTML(PrintStream out) {
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"250\" width=\"250\"/>\n",url);
    }
}
