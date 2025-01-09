-- members 테이블 생성
CREATE TABLE members (
                         member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255),
                         login_type VARCHAR(255),
                         name VARCHAR(255),
                         image_url VARCHAR(255)
);

-- categories 테이블 생성
CREATE TABLE categories (
                            category_id BIGINT PRIMARY KEY,
                            name VARCHAR(255) UNIQUE NOT NULL
);

-- projects 테이블 생성
CREATE TABLE projects (
                          project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          member_id BIGINT,
                          category_id BIGINT,
                          title VARCHAR(255),
                          description VARCHAR(255),
                          description_detail TEXT,
                          start_date DATETIME,
                          end_date DATETIME,
                          target_funding BIGINT,
                          funds_receive BIGINT DEFAULT 0,
                          supporter_cnt BIGINT DEFAULT 0,
                          view_cnt BIGINT DEFAULT 0,
                          like_cnt BIGINT DEFAULT 0,
                          thumbnail_url VARCHAR(255),
                          submit_at DATETIME,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          deleted_at DATETIME,
                          FOREIGN KEY (member_id) REFERENCES members (member_id),
                          FOREIGN KEY (category_id) REFERENCES categories (category_id)
);

-- project_tags 테이블 생성
CREATE TABLE project_tags (
                              project_tag_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              project_id BIGINT,
                              tag_id BIGINT,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (project_id) REFERENCES projects (project_id),
                              FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);

-- tags 테이블 생성
CREATE TABLE tags (
                      tag_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      usage_frequency INT DEFAULT 0
);
