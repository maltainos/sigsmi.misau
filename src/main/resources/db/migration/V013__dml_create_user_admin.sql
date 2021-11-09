INSERT INTO users 
	(created_on, email, encrypted_password, first_name, last_name, user_id) 
	VALUES 
	(now(), "rony@rvw.com", "$2a$10$zhxGmBZoSSWhlFX6.47HROEL9KgZOS4NA2RCbCAkV4ERC4zzWlIxG", "Rony", "Zaona","2yEQpuy6r7NtVaVii1kNqSKTgi0pml");
	
INSERT INTO levels 
	(level_id, level_name, created_on) 
	VALUES ("2yEQpuy6r7NtVaVii1kNqSKTgi0pml", "Administrator",now());
	
INSERT INTO users_levels 
	(levels_id,users_id) 
	VALUES (1,1);