package lab4;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class CV {
    public static void main (String [] args) throws IOException {
        Document cv = new Document("Taco Hemingway");
        cv.addPhoto("https://www.tuwroclaw.com/images/galbig3/news/43520/taco2_5b84037c41cab7_26170003.jpg.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("Wydział Antropologii Uniwersytetu Londyńskiego")
                .addParagraph("Uniwersytet Warszawski")
                .addParagraph("XXXIII Liceum Ogólnokształcące Dwujęzyczne im. Mikołaja Kopernika w Warszawie")
                .addParagraph("Prywatne Gimnazjum nr 1 w Warszawie")
                .addParagraph("Przedszkole w Chinach");
        cv.addSection("Umiejętności")
                .addParagraph("Płynne posługiwanie się językiem angielskim")
                .addParagraph("Szybkie mówienie")
                .addParagraph("Zjednywanie sobie milionów fanów");
        cv.addSection("Dyskografia")
                .addParagraph(new ParagraphWithList("Szprycer")
                        .addItemToList("Nostalgia")
                        .addItemToList("Chodź")
                        .addItemToList("Tlen")
                        .addItemToList("Głupi Byt")
                        .addItemToList("Dele")
                        .addItemToList("I.S.W.T.")
                        .addItemToList("35")
                        .addItemToList("Karimata")
                        .addItemToList("Saldo '07"))
                .addParagraph(new ParagraphWithList("Cafe Belga")
                        .addItemToList("Cafe Belga")
                        .addItemToList("ZTM")
                        .addItemToList("Wszystko na niby")
                        .addItemToList("Reżyseria: Kubrick")
                        .addItemToList("2031")
                        .addItemToList("Fiji")
                        .addItemToList("Abonent jest czasowo niedostępny")
                        .addItemToList("Motorola")
                        .addItemToList("Modigliani")
                        .addItemToList("Adieu")
                        .addItemToList("4 AM in Girona"));
        cv.writeHTML(new PrintStream("cv.html", StandardCharsets.UTF_8));
    }
}
