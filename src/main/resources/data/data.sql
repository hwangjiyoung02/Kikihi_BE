INSERT INTO members (email, password, login_type, name, image_url) VALUES
                                                                       ('user1@example.com', 'password1', 'KAKAO', 'User One', 'https://example.com/image1.jpg'),
                                                                       ('user2@example.com', 'password2', 'GOOGLE', 'User Two', 'https://example.com/image2.jpg'),
                                                                       ('user3@example.com', 'password3', 'NAVER', 'User Three', 'https://example.com/image3.jpg'),
                                                                       ('user4@example.com', 'password4', 'KAKAO', 'User Four', 'https://example.com/image4.jpg'),
                                                                       ('user5@example.com', 'password5', 'GOOGLE', 'User Five', 'https://example.com/image5.jpg'),
                                                                       ('user6@example.com', 'password6', 'ORIGINAL', 'User Six', 'https://example.com/image6.jpg'),
                                                                       ('user7@example.com', 'password7', 'KAKAO', 'User Seven', 'https://example.com/image7.jpg'),
                                                                       ('user8@example.com', 'password8', 'GOOGLE', 'User Eight', 'https://example.com/image8.jpg'),
                                                                       ('user9@example.com', 'password9', 'KAKAO', 'User Nine', 'https://example.com/image9.jpg'),
                                                                       ('user10@example.com', 'password10', 'KAKAO', 'User Ten', 'https://example.com/image10.jpg');
INSERT INTO categories (category_id, name) VALUES
                                               (1, '뷰티'),
                                               (2, 'K-POP'),
                                               (3, 'K-콘텐츠'),
                                               (4, '음식'),
                                               (5, '문화재'),
                                               (6, '패션'),
                                               (7, '게임');

INSERT INTO tags (name, usage_frequency)
VALUES ('K-콘텐츠', 16),
       ('커뮤니티', 15),
       ('메이크업', 12),
       ('음악', 11),
       ('K-팝', 11),
       ('K-패션', 11),
       ('전통 음식', 11),
       ('뷰티', 10),
       ('게임', 10),
       ('문화재', 10),
       ('디자인', 10),
       ('클래스', 9),
       ('팝업 스토어', 8),
       ('한식', 8),
       ('전시회', 7),
       ('레시피', 7),
       ('인플루언서', 7),
       ('K-드라마', 7),
       ('공연', 7),
       ('OST', 7),
       ('전통 의상', 6),
       ('전통 패션', 6),
       ('제작', 6),
       ('지속 가능', 6),
       ('피부 관리', 6),
       ('체험', 6),
       ('아이돌', 6),
       ('보호', 6),
       ('문화유산', 6),
       ('박람회', 5),
       ('김치', 5),
       ('교육', 5),
       ('전통 게임', 5),
       ('트렌드', 5),
       ('브랜드', 5),
       ('다큐멘터리', 4),
       ('VR', 4),
       ('차', 4),
       ('스토리', 4),
       ('댄스', 4),
       ('디저트', 4),
       ('컨퍼런스', 4),
       ('장류', 4),
       ('리메이크', 4),
       ('헤어', 3),
       ('K-영화', 3),
       ('아이돌댄스', 3),
       ('웹툰', 3),
       ('캠페인', 2),
       ('요리', 2),
       ('온라인 축제', 2),
       ('공예', 1),
       ('개발', 1),
       ('패션', 1),
       ('전통 무용', 1),
       ('대회', 1),
       ('떡', 1),
       ('산업', 1),
       ('전통 건축', 1),
       ('예능', 1),
       ('역사', 1),
       ('애니메이션', 1),
       ('식기', 1),
       ('스트릿 패션', 1),
       ('화장품', 1);

INSERT INTO projects (member_id, category_id, title, description, description_detail, start_date, end_date, target_funding, funds_receive, supporter_cnt, view_cnt, like_cnt, thumbnail_url, submit_at) VALUES
                                                                                                                                                                                                            (1, 1, 'Tech Project 1', 'Description 1', 'Detailed Description 1', '2024-01-01 00:00:00', '2024-06-01 00:00:00', 1000000, 0, 0, 0, 0, 'https://example.com/thumb1.jpg', '2024-01-01 12:00:00'),
                                                                                                                                                                                                            (2, 2, 'Art Project 1', 'Description 2', 'Detailed Description 2', '2024-02-01 00:00:00', '2024-07-01 00:00:00', 2000000, 100000, 10, 50, 5, 'https://example.com/thumb2.jpg', '2024-02-01 12:00:00'),
                                                                                                                                                                                                            (3, 3, 'Health Project 1', 'Description 3', 'Detailed Description 3', '2024-03-01 00:00:00', '2024-08-01 00:00:00', 3000000, 500000, 50, 200, 20, 'https://example.com/thumb3.jpg', '2024-03-01 12:00:00'),
                                                                                                                                                                                                            (4, 4, 'Science Project 1', 'Description 4', 'Detailed Description 4', '2024-04-01 00:00:00', '2024-09-01 00:00:00', 4000000, 1000000, 100, 300, 30, 'https://example.com/thumb4.jpg', '2024-04-01 12:00:00'),
                                                                                                                                                                                                            (5, 5, 'Education Project 1', 'Description 5', 'Detailed Description 5', '2024-05-01 00:00:00', '2024-10-01 00:00:00', 5000000, 1500000, 150, 400, 40, 'https://example.com/thumb5.jpg', '2024-05-01 12:00:00'),
                                                                                                                                                                                                            (6, 6, 'Sports Project 1', 'Description 6', 'Detailed Description 6', '2024-06-01 00:00:00', '2024-11-01 00:00:00', 6000000, 2000000, 200, 500, 50, 'https://example.com/thumb6.jpg', '2024-06-01 12:00:00'),
                                                                                                                                                                                                            (7, 7, 'Entertainment Project 1', 'Description 7', 'Detailed Description 7', '2024-07-01 00:00:00', '2024-12-01 00:00:00', 7000000, 2500000, 250, 600, 60, 'https://example.com/thumb7.jpg', '2024-07-01 12:00:00'),
                                                                                                                                                                                                            (8, 1, 'Business Project 1', 'Description 8', 'Detailed Description 8', '2024-08-01 00:00:00', '2025-01-01 00:00:00', 8000000, 3000000, 300, 700, 70, 'https://example.com/thumb8.jpg', '2024-08-01 12:00:00'),
                                                                                                                                                                                                            (9, 2, 'Travel Project 1', 'Description 9', 'Detailed Description 9', '2024-09-01 00:00:00', '2025-02-01 00:00:00', 9000000, 3500000, 350, 800, 80, 'https://example.com/thumb9.jpg', '2024-09-01 12:00:00'),
                                                                                                                                                                                                            (10, 3, 'Food Project 1', 'Description 10', 'Detailed Description 10', '2024-10-01 00:00:00', '2025-03-01 00:00:00', 10000000, 4000000, 400, 900, 90, 'https://example.com/thumb10.jpg', '2024-10-01 12:00:00');

INSERT INTO project_tags (project_id, tag_id, created_at) VALUES
                                                              (1, 1, '2024-01-01 12:00:00'),
                                                              (1, 2, '2024-01-01 12:00:00'),
                                                              (1, 3, '2024-01-01 12:00:00'),
                                                              (2, 1, '2024-02-01 12:00:00'),
                                                              (2, 4, '2024-02-01 12:00:00'),
                                                              (2, 5, '2024-02-01 12:00:00'),
                                                              (3, 2, '2024-03-01 12:00:00'),
                                                              (3, 6, '2024-03-01 12:00:00'),
                                                              (3, 7, '2024-03-01 12:00:00'),
                                                              (4, 3, '2024-04-01 12:00:00'),
                                                              (4, 8, '2024-04-01 12:00:00'),
                                                              (4, 9, '2024-04-01 12:00:00'),
                                                              (5, 5, '2024-05-01 12:00:00'),
                                                              (5, 10, '2024-05-01 12:00:00');
