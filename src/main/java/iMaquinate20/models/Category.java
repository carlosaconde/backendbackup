package iMaquinate20.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
    @Table (name = "category")
    public class Category {
    public Category(Integer id) {
        this.id = id;
    }

    public Category(String categoryName, String description, String imageUrl) {

        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Category() {
    }

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "category_name")
        private @NotNull String categoryName;

        private @NotNull String description;

        @Column(name = "image_url")
        private @NotNull String imageUrl;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }


