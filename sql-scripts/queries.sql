-- ПАЦИЕНТЫ
------------------------------------------------------
-- отображение списка пациентов
select * from patient

-- добавление нового пациента
insert into patient
(forename, patronymic, surname, phone)
values ('ИМЯ', 'ОТЧЕСТВО', 'ФАМИЛИЯ', '00000000000')

-- редактирование пациента
-- имя (id, forename)
update patient set forename = 'Добрыня' where id = 0
-- отчество (id, patronymic)
update patient set patronymic = 'Никитич' where id = 0
-- фамилия (id, surname)
update patient set surname = 'Богатырёв' where id = 0
-- номер телефона (id, phone)
update patient set phone = '0123456789' where id = 0

-- удаление пациента (id)
delete from patient where id = 0
------------------------------------------------------
------------------------------------------------------