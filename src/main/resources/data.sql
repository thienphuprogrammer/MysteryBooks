INSERT users (username, email, phone_number, date_of_birth, bio, interests, profile_picture, creation_date, last_login_date, password)
VALUES ('user1', 'user1@gmail.com', '1234567890', '1990-01-01', 'I am user1', 'I like to play games', 'user1.jpg', '2019-01-01', '2019-01-01', 'password1'),
    ('user2', 'user2@gmail.com', '1234567891', '1990-01-01', 'I am user2', 'I like to play games', 'user2.jpg', '2019-01-01', '2019-01-01', 'password2'),
    ('user3', 'user3@gmail.com', '1234567892', '1990-01-01', 'I am user3', 'I like to play games', 'user3.jpg', '2019-01-01', '2019-01-01', 'password3'),
    ('user4', 'user4@gmail.com', '1234567893', '1990-01-01', 'I am user4', 'I like to play games', 'user4.jpg', '2019-01-01', '2019-01-01', 'password4'),
    ('user5', 'user5@gmail.com', '1234567894', '1990-01-01', 'I am user5', 'I like to play games', 'user5.jpg', '2019-01-01', '2019-01-01', 'password5');

-- set profile picture for all users
UPDATE users SET profile_picture = 'src/images/avatar/default.png' WHERE id > 0;

SELECT * FROM users;
INSERT posts(user_id, title, content, creation_date, visibility)
VALUE ('11', 'Post 1', 'This is post 1', '2019-01-01', 'public'),
    ('11', 'Post 2', 'This is post 2', '2019-01-01', 'public'),
    ('11', 'Post 3', 'This is post 3', '2019-01-01', 'public'),
    ('11', 'Post 4', 'This is post 4', '2019-01-01', 'public'),
    ('11', 'Post 5', 'This is post 5', '2019-01-01', 'public'),
    ('12', 'Post 6', 'This is post 6', '2019-01-01', 'public'),
    ('12', 'Post 7', 'This is post 7', '2019-01-01', 'public'),
    ('12', 'Post 8', 'This is post 8', '2019-01-01', 'public'),
    ('12', 'Post 9', 'This is post 9', '2019-01-01', 'public'),
    ('12', 'Post 10', 'This is post 10', '2019-01-01', 'public'),
    ('13', 'Post 11', 'This is post 11', '2019-01-01', 'public'),
    ('13', 'Post 12', 'This is post 12', '2019-01-01', 'public'),
    ('13', 'Post 13', 'This is post 13', '2019-01-01', 'public'),
    ('13', 'Post 14', 'This is post 14', '2019-01-01', 'public'),
    ('13', 'Post 15', 'This is post 15', '2019-01-01', 'public'),
    ('14', 'Post 16', 'This is post 16', '2019-01-01', 'public'),
    ('14', 'Post 17', 'This is post 17', '2019-01-01', 'public'),
    ('14', 'Post 18', 'This is post 18', '2019-01-01', 'public'),
    ('14', 'Post 19', 'This is post 19', '2019-01-01', 'public'),
    ('14', 'Post 20', 'This is post 20', '2019-01-01', 'public'),
    ('15', 'Post 21', 'This is post 21', '2019-01-01', 'public'),
    ('15', 'Post 22', 'This is post 22', '2019-01-01', 'public'),
    ('15', 'Post 23', 'This is post 23', '2019-01-01', 'public'),
    ('15', 'Post 24', 'This is post 24', '2019-01-01', 'public'),
    ('15', 'Post 25', 'This is post 25', '2019-01-01', 'public');

INSERT messages(sender_id, receiver_id, content, creation_date)
VALUES   (11, 12, 'This is message 1', '2019-01-02'),
         (12, 11, 'This is message 2', '2019-01-02'),
         (11, 12, 'This is message 3', '2019-01-02'),
         (12, 11, 'This is message 4', '2019-01-02'),
         (11, 12, 'This is message 5', '2019-01-02'),
         (12, 11, 'This is message 6', '2019-01-02'),
         (11, 12, 'This is message 7', '2019-01-02'),
         (12, 11, 'This is message 8', '2019-01-02'),
         (11, 12, 'This is message 9', '2019-01-02'),
         (12, 11, 'This is message 10', '2019-01-02'),
         (11, 12, 'This is message 11', '2019-01-02'),
         (12, 11, 'This is message 12', '2019-01-02'),
         (11, 12, 'This is message 13', '2019-01-02'),
         (12, 11, 'This is message 14', '2019-01-02'),
         (11, 12, 'This is message 15', '2019-01-02'),
         (12, 11, 'This is message 16', '2019-01-02'),
         (11, 12, 'This is message 17', '2019-01-02'),
         (12, 11, 'This is message 18', '2019-01-02'),
         (11, 12, 'This is message 19', '2019-01-02'),
         (12, 11, 'This is message 20', '2019-01-02'),
         (11, 12, 'This is message 21', '2019-01-02'),
         (12, 11, 'This is message 22', '2019-01-02'),
         (11, 12, 'This is message 23', '2019-01-02'),
         (12, 11, 'This is message 24', '2019-01-02'),
         (11, 12, 'This is message 25', '2019-01-02');