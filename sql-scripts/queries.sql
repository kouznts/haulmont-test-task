-- ВРАЧИ
------------------------------------------------------
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

-- отображение статистической информации по количеству рецептов,
-- выписанных врачами
select * from medical_prescription where doctor_id = 0;
------------------------------------------------------
------------------------------------------------------

-- РЕЦЕПТЫ
------------------------------------------------------
-- отображения списка рецептов
select * from medical_prescription;

-- добавление нового рецепта
insert into medical_prescription
(description, patient_id, doctor_id, creation_date, validity_date, priority)
VALUES ('ОПИСАНИЕ', 0, 0, CURRENT_TIMESTAMP, TIMESTAMP '2020-04-30 23:59:59', 3);

-- редактирование рецепта
update medical_prescription
set
    description = 'ОПИСАНИЕ',
    patient_id = 0,
    doctor_id = 0,
    creation_date = TIMESTAMP '2020-01-01 00:00:00',
    validity_date = TIMESTAMP '2020-04-30 23:59:59',
    priority = 3
where id = 0;

-- удаление рецепта
delete from medical_prescription where id = 0;

-- фильтрация списка рецептов по описанию, приоритету и пациенту
select * from medical_prescription
where
description like '%ОПИСАНИЕ%' and
patient_id = 0 and
priority = 3;
------------------------------------------------------
------------------------------------------------------