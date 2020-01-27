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

-- ВРАЧИ
------------------------------------------------------
-- отображение списка врачей
select * from doctor

-- добавление нового врача
insert into doctor
(forename, patronymic, surname, specialization_id)
values ('ИМЯ', 'ОТЧЕСТВО', 'ФАМИЛИЯ', 0)

-- редактирование врача
-- имя (id, forename)
update doctor set forename = 'Добрыня' where id = 0
-- отчество (id, patronymic)
update doctor set patronymic = 'Никитич' where id = 0
-- фамилия (id, surname)
update doctor set surname = 'Богатырёв' where id = 0
-- специализация (id, doctor_specialization_id)
update doctor set specialization_id = 0 where id = 0

-- удаление пациента (id)
delete from doctor where id = 0

-- отображение статистической информации по количеству рецептов,
-- выписанных врачами
select * from medical_prescription where doctor_id = 0
------------------------------------------------------
------------------------------------------------------