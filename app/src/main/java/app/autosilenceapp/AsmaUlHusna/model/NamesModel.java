package app.autosilenceapp.AsmaUlHusna.model;

public class NamesModel {
    private String description;
    private int imgId;
    private String name;
    private String meaning;

    public NamesModel(String description, String name, int imgId,String meaning) {
        this.description = description;
        this.name = name;
        this.imgId = imgId;
        this.meaning = meaning;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}