CREATE TABLE `User` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`username`	TEXT NOT NULL UNIQUE,
	`password`	TEXT NOT NULL,
	`created`	NUMERIC NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE `Token` (
	`uid`	INTEGER NOT NULL,
	`token`	BLOB NOT NULL,
	`expiration`	NUMERIC NOT NULL DEFAULT (datetime('now', '+60 minutes')),
	PRIMARY KEY(uid,token),
	FOREIGN KEY(`uid`) REFERENCES `User`(`uid`)
);


CREATE TABLE `Message` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`send_uid`	INTEGER NOT NULL,
	`receive_uid`	INTEGER NOT NULL,
	`content_type`	TEXT NOT NULL,
	`content_text`	BLOB NOT NULL,
	`sent_at`	NUMERIC NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(`send_uid`) REFERENCES `User`(`uid`),
	FOREIGN KEY(`receive_uid`) REFERENCES `User`(`uid`)
);