package wami.ikechukwu.kanu;

public class dataModel {

    private String Title;
    private String Image;
    private String Descrip;

    public dataModel() {

    }

    public dataModel(String Title, String Image, String Descrip) {

        this.Title = Title;
        this.Image = Image;
        this.Descrip = Descrip;
    }

    public String getImage() {

        return Image;
    }

    public void setImage(String Image) {

        this.Image = Image;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String Title) {

        this.Title = Title;
    }

    public String getDescrip() {

        return Descrip;
    }

    public void setDescrip(String Descrip) {

        this.Descrip = Descrip;
    }


}
