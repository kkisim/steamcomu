package steam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "games")
public class Game {

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("releaseDate")
    private String releaseDate;

    @Field("developer")
    private String developer;

    @Field("image")
    private String image;

    @Field("categories")
    private List<String> categories;

    @Field("platforms")
    private List<String> platforms;

    @Field("country")
    private String country;

    @Field("tags")
    private List<String> tags;

    @Field("createdAt")
    private String createdAt;

    public Game() {}

    public Game(String title, String description, String releaseDate, String developer, String image,
                List<String> categories, List<String> platforms, String country,
                List<String> tags, String createdAt) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.image = image;
        this.categories = categories;
        this.platforms = platforms;
        this.country = country;
        this.tags = tags;
        this.createdAt = createdAt;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
