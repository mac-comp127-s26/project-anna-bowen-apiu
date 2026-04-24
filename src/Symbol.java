import edu.macalester.graphics.Image;

public class Symbol {
    private Image image;

    public Symbol(int x, int y,String imageFile) {
        image = new Image(x,y,imageFile);
    }

    public Image getImage() {
        return image;
    }
}
