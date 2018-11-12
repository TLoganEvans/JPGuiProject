package GuiProject;

/**
 * Author: Trevor Evans
 * Date: 12-Nov-18
 * Time: 4:58 PM
 * Description:
 */
public class Operator {

    private String name;
    private String imagePath;

    public Operator(){
        name = "Unnamed";
        imagePath = "No Path";
    }

    public Operator(String name, String path){
        this.name = name;
        this.imagePath = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
