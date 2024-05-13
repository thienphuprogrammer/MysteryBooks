CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(255) UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL,
    bio TEXT,
    interests TEXT,
    profile_picture VARCHAR(255),
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    password VARCHAR(255) NOT NULL
);
ALTER TABLE users ADD COLUMN address VARCHAR(255) AFTER phone_number;
ALTER TABLE users MODIFY profile_picture VARCHAR(255) NULL DEFAULT 'src/images/avatar/default.png';

ALTER TABLE users MODIFY date_of_birth DATE NULL;

CREATE TABLE `groups` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `creator_id` INT NOT NULL,
    visibility VARCHAR(255) NOT NULL,
    FOREIGN KEY (creator_id) REFERENCES users(id),
    UNIQUE (name)
);

CREATE TABLE user_groups (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    group_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (group_id) REFERENCES `groups`(id)
);

CREATE TABLE posts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    group_id INT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    visibility VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (group_id) REFERENCES `groups`(id)
);

CREATE TABLE comments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    content TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (post_id) REFERENCES posts(id)
);

CREATE TABLE emotions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    emotion VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (post_id) REFERENCES posts(id)
);

CREATE TABLE Images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE contents (
    id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE shares (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    content TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (post_id) REFERENCES posts(id)
);

-- Triggers when deleting posts, deletes all comments, emotions, images, contents and shares associated with the post being deleted --
CREATE TRIGGER delete_post_images
    BEFORE DELETE ON posts
    FOR EACH ROW
    BEGIN
        DELETE FROM images WHERE post_id = OLD.id;
        DELETE FROM contents WHERE post_id = OLD.id;
        DELETE FROM comments WHERE post_id = OLD.id;
        DELETE FROM emotions WHERE post_id = OLD.id;
        DELETE FROM shares WHERE post_id = OLD.id;
    END;

CREATE TABLE friends (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    friend_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (friend_id) REFERENCES users(id)
);

CREATE TABLE friend_requests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    sent_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    accepted_date TIMESTAMP,
    accepted BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

-- get all public posts or posts from groups the user is in or posts from friends have the visibility set to friends, or posts from the user himself --
-- sorted creation date primarily by posts from the user himself, then by posts from groups, then by posts from friends, then by public posts --
SELECT * FROM posts
WHERE visibility = 'public'
OR group_id IN (SELECT group_id FROM user_groups WHERE user_id = 11)
OR id IN (SELECT id FROM posts
            WHERE visibility = 'friends'
            AND user_id IN (SELECT friend_id FROM friends WHERE user_id = 11))
OR user_id = 11
ORDER BY
    user_id = 11 DESC,
    group_id DESC,
    user_id IN (SELECT friend_id FROM friends WHERE user_id = 11) DESC,
    creation_date DESC;


-- Automatically creates a friend after a friend request is accepted --
CREATE TRIGGER accept_friend_request
    AFTER UPDATE ON friend_requests
    FOR EACH ROW
    BEGIN
        IF NEW.accepted = TRUE THEN
            INSERT INTO friends (user_id, friend_id) VALUES (NEW.sender_id, NEW.receiver_id);
            INSERT INTO friends (user_id, friend_id) VALUES (NEW.receiver_id, NEW.sender_id);
        END IF;
    END;

CREATE TRIGGER delete_friend
    AFTER DELETE ON friends
    FOR EACH ROW
    BEGIN
        DELETE FROM friend_requests WHERE sender_id = OLD.user_id AND receiver_id = OLD.friend_id;
        DELETE FROM friend_requests WHERE sender_id = OLD.friend_id AND receiver_id = OLD.user_id;
    END;

CREATE TABLE messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    content TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

CREATE TABLE notifications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255) NOT NULL,
    post_id INT,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    content TEXT NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    seen_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

ALTER TABLE notifications
ADD COLUMN post_id INT AFTER type;

ALTER TABLE notifications
ADD FOREIGN KEY (post_id) REFERENCES posts(id);

-- Automatically creates a notification after a friend request is sent, or a message is sent, or a post is shared, or a comment is made, or an emotion is made --
DROP TRIGGER create_notification_friend_request;
CREATE TRIGGER create_notification_friend_request
    AFTER INSERT ON friend_requests
    FOR EACH ROW
    BEGIN
        DECLARE full_name VARCHAR(255);
        SELECT users.full_name INTO full_name FROM users WHERE id = NEW.sender_id LIMIT 1;
        INSERT INTO notifications (type, sender_id, receiver_id, content)
        VALUES ('friend_request', NEW.sender_id, NEW.receiver_id, CONCAT(full_name, ' sent you a friend request.'));
    END;

INSERT INTO messages (sender_id, receiver_id, content) VALUES (12, 11, 'Hello');
CREATE TRIGGER create_notification_message
    AFTER INSERT ON messages
    FOR EACH ROW
    BEGIN
        DECLARE full_name VARCHAR(255);
        SELECT users.full_name INTO full_name FROM users WHERE id = NEW.sender_id LIMIT 1;
        IF full_name IS NOT NULL THEN
            INSERT INTO notifications (type, sender_id, receiver_id, content)
            VALUES ('message', NEW.sender_id, NEW.receiver_id, CONCAT('You have a new message from ', full_name, '.'));
        ELSE
            INSERT INTO notifications (type, sender_id, receiver_id, content)
            VALUES ('message', NEW.sender_id, NEW.receiver_id, CONCAT('You have a new message.'));
        END IF;
    END;
DELIMITER ;

CREATE TRIGGER create_notification_comment
    AFTER INSERT ON comments
    FOR EACH ROW
    BEGIN
        DECLARE sender_id_ INT;

        SELECT posts.user_id INTO sender_id_ FROM posts WHERE id = NEW.post_id LIMIT 1;
        INSERT INTO notifications (type, sender_id, receiver_id, content, post_id)
        VALUES ('comment', NEW.user_id, sender_id_, CONCAT('You have a new comment.'),NEW.post_id);
    END;

CREATE TRIGGER create_notification_emotion
    AFTER INSERT ON emotions
    FOR EACH ROW
    BEGIN
        DECLARE sender_id_ INT;

        SELECT posts.user_id INTO sender_id_ FROM posts WHERE id = NEW.post_id LIMIT 1;
        INSERT INTO notifications (type, sender_id, receiver_id, content, post_id)
        VALUES ('emotion', NEW.user_id, sender_id_, CONCAT('You have a new emotion.'), NEW.post_id);
    END;

DROP TRIGGER delete_notification_friend_request;
CREATE TRIGGER delete_notification_friend_request
    AFTER DELETE ON friend_requests
    FOR EACH ROW
    BEGIN
        DELETE FROM notifications WHERE type = 'friend_request' AND sender_id = OLD.sender_id AND receiver_id = OLD.receiver_id;
    END;

CREATE TRIGGER delete_notification_message
    AFTER DELETE ON messages
    FOR EACH ROW
    BEGIN
        DELETE FROM notifications WHERE type = 'message' AND sender_id = OLD.sender_id AND receiver_id = OLD.receiver_id;
    END;

CREATE TRIGGER delete_notification_comment
    AFTER DELETE ON comments
    FOR EACH ROW
    BEGIN
        DELETE FROM notifications WHERE type = 'comment' AND sender_id = OLD.user_id AND post_id = OLD.post_id;
    END;

CREATE TRIGGER delete_notification_emotion
    AFTER DELETE ON emotions
    FOR EACH ROW
    BEGIN
        DELETE FROM notifications WHERE type = 'emotion' AND sender_id = OLD.user_id AND post_id = OLD.post_id;
    END;