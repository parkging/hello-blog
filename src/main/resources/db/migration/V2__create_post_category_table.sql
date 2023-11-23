-- V2__create_post_category_table.sql
-- Create post_category table
CREATE TABLE post_category (
                               post_category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL,
                               create_date TIMESTAMP,
                               modified_date TIMESTAMP,
                               parent_category_id BIGINT,
                               FOREIGN KEY (parent_category_id) REFERENCES post_category(post_category_id)
);

-- Index for performance optimization
CREATE INDEX idx_parent_category_id ON post_category (parent_category_id);
