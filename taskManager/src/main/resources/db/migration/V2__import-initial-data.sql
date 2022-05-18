INSERT INTO programming_language (program_language) VALUES ('Java');
INSERT INTO programming_language (program_language) VALUES ('C#');
INSERT INTO programming_language (program_language) VALUES ('Javascript');
INSERT INTO programming_language (program_language) VALUES ('PHP');
INSERT INTO programming_language (program_language) VALUES ('Python');
INSERT INTO programming_language (program_language) VALUES ('Ruby');
INSERT INTO programming_language (program_language) VALUES ('Go');

INSERT INTO system (system_name, start_date, end_date, language_id) VALUES ('taskManager', '2022-5-1', null, 1);
INSERT INTO system (system_name, start_date, end_date, language_id) VALUES ('2DRPG', '2022-5-2',  null, 2);

INSERT INTO task (feature_number, overview, start_date, end_date, report, system_id) VALUES ('0001', 'データベース設計と新規ページの作成', '2022-5-1', null, null, 1); 


