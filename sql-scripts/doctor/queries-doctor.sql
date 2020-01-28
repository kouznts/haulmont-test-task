-- отображение списка врачей
select * from doctor;

-- добавление нового врача
insert into doctor
(forename, patronymic, surname, specialization_id)
values ('ИМЯ', 'ОТЧЕСТВО', 'ФАМИЛИЯ', 0);

-- редактирование врача
update doctor
set
    forename = 'Добрыня',
    patronymic = 'Никитич',
    surname = 'Богатырёв',
    specialization_id = 0
where id = 0;

-- удаление пациента
delete from doctor where id = 0;