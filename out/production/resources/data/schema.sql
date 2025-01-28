-- 1. members 테이블 생성
CREATE TABLE members (
                         member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL ,
                         name VARCHAR(255)
);

-- 2. categories 테이블 생성
CREATE TABLE categories (
                            category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) UNIQUE NOT NULL
);

-- 3. tags 테이블 생성
CREATE TABLE tags (
                      tag_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) UNIQUE NOT NULL,
                      usage_frequency INT DEFAULT 0
);

-- 4. projects 테이블 생성
CREATE TABLE projects (
                          project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          member_id BIGINT NOT NULL,
                          category_id BIGINT,  -- category_id를 NULL 허용하도록 변경
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
                          FOREIGN KEY (member_id) REFERENCES members (member_id) ON DELETE CASCADE,
                          FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE SET NULL
);

-- 5. project_tags 테이블 생성
CREATE TABLE project_tags (
                              project_tag_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              project_id BIGINT NOT NULL,
                              tag_id BIGINT NOT NULL,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (project_id) REFERENCES projects (project_id) ON DELETE CASCADE,
                              FOREIGN KEY (tag_id) REFERENCES tags (tag_id) ON DELETE CASCADE
);
-- 기존 테이블에 social_id 컬럼 추가
ALTER TABLE members
    ADD COLUMN social_id VARCHAR(255) DEFAULT NULL;
