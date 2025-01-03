ALTER TABLE doctors ADD active tinyint;
UPDATE doctors SET active = 1;