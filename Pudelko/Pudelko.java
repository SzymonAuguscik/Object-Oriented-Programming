public class Pudelko {
    private float height;
    private float length;
    private float width;
    float volume;

    Pudelko(float h, float l, float w){
        this.height = h;
        this.length = l;
        this.width = w;
        this.setVolume();
    }

    private void setVolume() {
        volume = this.height*this.length*this.width;
    }

    void sortBubble(Pudelko[] table) {
        for (int i = 0; i < table.length;i++)
            for (int j = 0; j < i; j++)
                if (table[i].volume < table[j].volume) {
                    float tmp = table[i].volume;
                    table[i].volume = table[j].volume;
                    table[j].volume = tmp;
                }
    }

    public static void main (String[] args) {
    }
}
