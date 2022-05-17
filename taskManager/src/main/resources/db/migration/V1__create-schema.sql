CREATE TABLE programming_language(id INT PRIMARY KEY AUTO_INCREMENT, program_language VARCHAR(30));
CREATE TABLE system(id INT PRIMARY KEY AUTO_INCREMENT, system_name VARCHAR(30), start_date DATE, end_date DATE,  language_id INT);

ALTER TABLE system ADD CONSTRAINT FK_SYSTEM_LANGUAGE_ID FOREIGN KEY (language_id) REFERENCES programming_language;

