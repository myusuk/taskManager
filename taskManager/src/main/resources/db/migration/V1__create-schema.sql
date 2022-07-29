CREATE TABLE programming_language(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(30));

CREATE TABLE system(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(30), 
	start_date DATE, 
	end_date DATE,  
	language_id INT);

CREATE TABLE task(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	overview VARCHAR(30), 
	start_date DATE, 
	end_date DATE, 
	report VARCHAR(121), 
	system_id INT); 

ALTER TABLE system ADD CONSTRAINT FK_SYSTEM_LANGUAGE_ID FOREIGN KEY (language_id) REFERENCES programming_language;

ALTER TABLE task ADD CONSTRAINT FK_TASK_SYSTEM_ID FOREIGN KEY (system_id) REFERENCES system;

CREATE TABLE system_document(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	overview VARCHAR(30), 
	purpose VARCHAR(255), 
	function VARCHAR(255), 
	period VARCHAR(30),  
	system_id INT);
	
CREATE TABLE task_document(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	purpose VARCHAR(255), 
	function VARCHAR(255), 
	item VARCHAR(255), 
	period VARCHAR(30), 
	task_id INT);

ALTER TABLE system_document ADD CONSTRAINT FK_SYSTEM_DOCUMENT_SYSTEM_ID FOREIGN KEY (system_id) REFERENCES system;
ALTER TABLE task_document ADD CONSTRAINT FK_TASK_DOCUMENT_TASK_ID FOREIGN KEY (task_id) REFERENCES task;

