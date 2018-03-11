# -*- coding: utf-8 -*-
from faker import Faker
from random import Random
fake = Faker()
rand = Random()

professors_amount = 10
students_amount = 100
groups_amount = 10
marks_amount = 1000
users_amount = 10
studies_amount = 30

group_fmt = "insert into groups(group_number, avg_mark) values ('%s', '%s');"

group_numbers = [100000 + x for x in range(10)]

def gen_group():
    return group_fmt % (rand.choice(group_numbers),rand.randint(2,10)) 

prof_fmt = "insert into professors(first_name, second_name, father_name, birth_date, avg_name) values ('%s', '%s', '%s', '%s', '%s');"

def gen_prof():
    return prof_fmt % (
            fake.first_name(), 
            fake.last_name(), 
            "", 
            fake.date_between(start_date="-60y", end_date="today"),
            0
            )


studies = ["Основы алгоритмизации и программирования",
"Основы объектно-ориентированного  программирования",
"Базы данных",
"Основы сетевых технологий",
"Программирование сетевых приложений",
"Проектирование информационных систем ",
"Распределенные системы обработки информации ",
"Корпоративные информационные системы",
"Основы защиты информации",
"Метрология, стандартизация и сертификация в информационных технологиях",
"Маркетинговые дисциплины",

"Основы маркетинга",
"Информационные технологии в маркетинге",
"Товарная политика и бренд-менеджмент",
"Маркетинговые исследования ",
"Маркетинговые коммуникации ",
"Ценовая политика",
"Каналы дистрибуции и маркетинговая логистика",
"Стратегический маркетинг",
"Интернет-маркетинг и электронная коммерция ",
"Технологии продаж, деловых переговоров и презентаций",
"Математические методы и модели принятия маркетинговых решений",
"Промышленный маркетинг",
"Международный маркетинг и внешнеэкономическая деятельность",
"Поведение потребителей",
"Отраслевой маркетинг",
"Маркетинг программного продукта и услуг",
"Экономические дисциплины",

"Основы экономики",
"Макроэкономика",
"Микроэкономика",
"Экономика организации (предприятия)",
"Бухгалтерский учет",
"Финансовая математика и финансовый менеджмент",
"Инновационный менеджмент",
"Естественнонаучные и общепрофессиональные дисциплины",

"Математика",
"Общая теория статистики",
"Теория вероятностей и математическая статистика",
"Иностранный язык",
"Безопасность жизнедеятельности человека",
"Белорусский язык (культура речи)",
"Инженерная компьютерная графика",
"Правое регулирование маркетинговой деятельности",
"Курсовые работы и проекты",

"Курсовая работа по учебной дисциплине «Основы маркетинга»",
"Курсовая работа по учебной дисциплине «Товарная политика и бренд-менеджмент»",
"Курсовая работа по учебной дисциплине «Маркетинговые исследования»",
"Курсовой проект по учебной дисциплине «Маркетинговые коммуникации»",
"Курсовой проект по учебной дисциплине «Распределенные системы обработки информации»",
"Курсовая работа по учебной дисциплине «Интернет-маркетинг и электронная коммерция»",
"Дипломный проект (в последнем семестре)",

"Интегрированный модуль «Философия»",
"Социология",
"Интегрированный модуль «Политология»",
"Интегрированный модуль «История»",
"Специализированный модуль по интегрированному модулю «Философия»",
"Специализированный модуль по интегрированному модулю «История»",
"Специализированный модуль по интегрированному модулю «Экономика»",
"Специализированный модуль по интегрированному модулю «Политология»",

"Физическая культура"]

student_fmt = "insert into students(first_name, second_name, group_number) values ('%s', '%s', '%s');"

def gen_student():
    return student_fmt % (fake.first_name(), fake.last_name(), rand.choice(group_numbers))

studies_fmt = "insert into studies(name, hours, professor_id, avg_mark) values ('%s', '%s', '%s', '%s');"

def gen_studie():
    return studies_fmt % (rand.choice(studies), rand.randint(1,10) * 2 * 4, rand.randint(1,professors_amount), 0)

user_fmt = "insert into users(username, password, role) values ('%s', '%s', '%s');"

roles = ["ADMIN", "STUDENT", "PROFESSOR"]

def gen_user():
    return user_fmt % (fake.user_name(), fake.password(), rand.choice(roles))

mark_fmt = "insert into marks(study_id, student_id, date, professor_id, mark, comments) values ('%s', '%s', '%s', '%s', '%s', '%s');"

def gen_mark():
    return mark_fmt % (rand.randint(1,studies_amount), 
            rand.randint(1,students_amount), 
            fake.date_between(start_date="-60y", end_date="today"),
            rand.randint(1,professors_amount),
            rand.randint(2,10),
            fake.text())

for x in range(professors_amount):
    print gen_prof()

for x in group_numbers:
    print group_fmt % (x, 0)

for x in range(students_amount):
    print gen_student()

for x in range(users_amount):
    print gen_user()

for x in range(studies_amount):
    print gen_studie()

for x in range(marks_amount):
    print gen_mark()
