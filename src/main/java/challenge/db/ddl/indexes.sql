CREATE INDEX `get_messages` ON `Message` (`mid` DESC,`receive_uid` );

CREATE INDEX `get_usr` ON `User` (`name` ,`pass` );

CREATE INDEX `get_token` ON `Auth` (`uid` )