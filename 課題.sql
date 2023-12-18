ログイン用データベース
CREATE TABLE LOGIN(id varchar(30) PRIMARY KEY, password varchar(30) NOT NULL);

勤怠入力用データベース
CREATE TABLE ATTENDANCE(id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY, a_date date unique , working_hours timestamp, closing_time timestamp);

勤務場所用データベース
CREATE TABLE WORK_PLACE(id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY, w_date date unique, place varchar(50) NOT NULL);

連絡先用データベース
CREATE TABLE CONTACT_ADDRESS(name varchar(50) PRIMARY KEY, phone varchar(50) NOT NULL, address varchar(100) NULL);