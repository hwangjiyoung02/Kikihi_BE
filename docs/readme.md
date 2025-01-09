//@manytomany

+-----------+         +------------------+         +---------+
|  Project  |         |   ProjectTag     |         |   Tag   |
|-----------|         |------------------|         |---------|
| projectId | <-----+ | project_id (FK)  | +-----> | tag_id  |
| project   |         | tag_id (FK)      |         | tagName |
| title     |         | createdAt        |         +---------+
| ...       |         +------------------+
+-----------+
^
|
| 1..* (One Project can have many ProjectTags)
|
+-----------+
| Project A |
+-----------+
| ID: 1     |
| Title: "Project A" |
+-----------+

      ^
      |
      | *..1 (Many Tags can belong to a Project)
      |
+-----------+
| Tag       |
+-----------+
| tagId: 1  | "Technology"
| tagId: 2  | "Education"
+-----------+
