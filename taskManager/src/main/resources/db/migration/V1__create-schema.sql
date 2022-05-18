CREATE TABLE programming_language(id INT PRIMARY KEY AUTO_INCREMENT, program_language VARCHAR(30));

CREATE TABLE system(id INT PRIMARY KEY AUTO_INCREMENT, system_name VARCHAR(30), start_date DATE, end_date DATE,  language_id INT);

CREATE TABLE task(id INT PRIMARY KEY AUTO_INCREMENT, feature_number VARCHAR(30), overview VARCHAR(30), start_date DATE, end_date DATE, report VARCHAR(121), system_id INT); 

ALTER TABLE system ADD CONSTRAINT FK_SYSTEM_LANGUAGE_ID FOREIGN KEY (language_id) REFERENCES programming_language;

ALTER TABLE task ADD CONSTRAINT FK_TASK_SYSTEM_ID FOREIGN KEY (system_id) REFERENCES system;

