package wami.ikechukwu.kanu;

public class dataModel {

    private String Title;
    private String Image;

    public dataModel() {

    }

    public dataModel(String Title, String Image) {

        this.Title = Title;
        this.Image = Image;
    }

    public String getImage() {

        return Image;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String Title) {

        this.Title = Title;
    }

    public void setImage(String Image) {

        this.Image = Image;
    }


}
