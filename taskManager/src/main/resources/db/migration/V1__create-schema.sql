

CREATE TABLE program_category(
	id INT(11) PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(31) NOT NULL UNIQUE
);

CREATE TABLE target_category(
	id INT(11) PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(31) NOT NULL UNIQUE
);

CREATE TABLE task_category(
	id INT(11) PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(31) NOT NULL UNIQUE
);

CREATE TABLE target(
	id INT(11) PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(31) NOT NULL, 
	start_date DATE NOT NULL, 
	end_date DATE DEFAULT NULL,  
	target_category_id INT(11) NOT NULL,
	program_category_id INT(11) DEFAULT NULL,
	FOREIGN KEY (target_category_id) REFERENCES target_category(id),
	FOREIGN KEY (program_category_id) REFERENCES program_category(id)
);

CREATE TABLE task(
	id INT(11) PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(31) NOT NULL, 
	start_date DATE NOT NULL, 
	end_date DATE DEFAULT NULL, 
	report VARCHAR(127) DEFAULT NULL, 
	target_id INT(11) NOT NULL,
	task_category_id INT(11) NOT NULL,
	FOREIGN KEY (target_id) REFERENCES target(id),
	FOREIGN KEY (task_category_id) REFERENCES task_category(id)
); 

CREATE TABLE target_document(
	id INT(11) PRIMARY KEY AUTO_INCREMENT, 
	overview VARCHAR(31) DEFAULT NULL, 
	purpose VARCHAR(255) DEFAULT NULL, 
	function VARCHAR(255) DEFAULT NULL, 
	period VARCHAR(31) DEFAULT NULL,  
	target_id INT(11) NOT NULL UNIQUE,
	FOREIGN KEY (target_id) REFERENCES target(id)
);
	
CREATE TABLE task_document(
	id INT(11) PRIMARY KEY AUTO_INCREMENT, 
	purpose VARCHAR(255) DEFAULT NULL, 
	function VARCHAR(255) DEFAULT NULL, 
	item VARCHAR(255) DEFAULT NULL, 
	period VARCHAR(31) DEFAULT NULL, 
	task_id INT(11) NOT NULL UNIQUE,
	FOREIGN KEY (task_id) REFERENCES task(id)
);