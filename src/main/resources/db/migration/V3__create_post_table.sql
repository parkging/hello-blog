CREATE TABLE post (
                      post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content LONGTEXT,
                      preview VARCHAR(255),
                      thumbnail_image_url VARCHAR(255),
                      post_category_id BIGINT,
                      member_id BIGINT,
                      create_date TIMESTAMP,
                      modified_date TIMESTAMP,
                      FOREIGN KEY (post_category_id) REFERENCES post_category(post_category_id),
                      FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE INDEX idx_post_category_id ON post (post_category_id);
CREATE INDEX idx_member_id ON post (member_id);
