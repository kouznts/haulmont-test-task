-- отображение списка пациентов
select * from patient;

-- добавление нового пациента
insert into patient
(forename, patronymic, surname, phone)
values ('ИМЯ', 'ОТЧЕСТВО', 'ФАМИЛИЯ', '00000000000');

-- редактирование пациента
update patient
set
    forename = 'Добрыня',
    patronymic = 'Никитич',
    surname = 'Богатырёв',
    phone = '0123456789'
where id = 0;

-- удаление пациента
delete from patient where id = 0;