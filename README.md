# course_project

1. Скачать и установить СУБД (MySQL или PostreSQL)
2. Поменять настройки в файле src/main/resources/**application.properties** значение `spring.datasource.url=jdbc:mysql` и `spring.datasource.driver-class-name=com.mysql`(mysql для MySQL/postresql для PostreSQL)
3. В том же файле значение `spring.jpa.database-platform = org.hibernate.dialect.MySQL5Dialect` для MySQL, `spring.jpa.database-platform = org.hibernate.dialect.PostgreSQLDialect` для PostreSQL
4. Запустить файл src/main/resources/**database.sql**, заранее поменяв имя базы данных с evdokimov_r_d на свое
5. Это имя поменять также в src/main/resources/**application.properties** `spring.datasource.url=jdbc:mysql://localhost:3306/evdokimov_r_d`
6. В том файле поменять `spring.datasource.username=root` и `spring.datasource.password=root` на те значения, которые были использованы при создании СУБД
7. Зайти в проект, открыть там терминал (командная строка) и запустить - `mvn spring-boot:run`
8. Проверить, что все запустилось
9. Перейти в папку frontend и запустить - `npm start`
10. Перейти по адресу - `localhost:8080`
