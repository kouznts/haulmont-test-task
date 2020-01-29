﻿-- отображение списка врачей
select * from doctor;

-- добавление
insert into doctor
(forename, patronymic, surname, specialization_id)
values ('ИМЯ', 'ОТЧЕСТВО', 'ФАМИЛИЯ', 0);

-- редактирование
update doctor
set
    forename = 'Добрыня',
    patronymic = 'Никитич',
    surname = 'Богатырёв',
    specialization_id = 0
where id = 0;

-- удаление
delete from doctor where id = 0;