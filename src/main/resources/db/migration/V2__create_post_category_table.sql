CREATE TABLE post_category (
                               post_category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL,
                               create_date TIMESTAMP,
                               modified_date TIMESTAMP,
                               parent_category_id BIGINT,
                               FOREIGN KEY (parent_category_id) REFERENCES post_category(post_category_id)
);

CREATE INDEX idx_parent_category_id ON post_category (parent_category_id);
