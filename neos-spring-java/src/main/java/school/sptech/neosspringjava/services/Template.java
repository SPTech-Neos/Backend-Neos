package school.sptech.neosspringjava.services;

public class Template {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Template{" +
                "image='" + image + '\'' +
                '}';
    }
}
