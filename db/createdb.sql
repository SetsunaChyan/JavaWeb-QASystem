use QASystemDB;

drop table ban,reply,question,teach,curriculum,teacher,department,users;

create table users
(
    u_name     nvarchar(40),
    u_password nvarchar(40),
    u_type     nvarchar(40),
    primary key (u_name)
);

insert into users
values ('Setsuna', 'admin', 'admin');
insert into users
values ('Akyuu', '123456', 'student');
insert into users
values ('佟大为', '123456', 'teacher');
insert into users
values ('田所浩二', '123456', 'teacher');
insert into users
values ('rhy', '123456', 'teacher');


create table department
(
    dept_name nvarchar(40),
    inf       nvarchar(1023),
    primary key (dept_name)
);

insert into department(dept_name, inf)
values ('光电信息与计算机工程学院', '光电信息与计算机工程学院由原光学与电子信息工程学院（1999年2月经市政府批准由原仪器仪表学院和上海光学仪器研究所合并组建）和原计算机与电气工程学院于2008年10月合并组建。');
insert into department(dept_name, inf)
values ('理学院', '上海理工大学理学院的历史可以追溯到1916年沪江时期的格致科，百年来学院几经更迭，从沪江格致科到基础教学部，到2002年上海理工大学再次成立理学院。');
insert into department(dept_name, inf)
values ('管理学院',
        '管理学院前身为我国最早成立的系统工程系和系统工程研究所（1979年），1992年5月系所合并成立系统科学与系统工程学院，1999年5月更名为管理学院，2004年原上海医疗专科学校管理系划归管理学院，2006年1月与商学院合并重组成立新的管理学院（Business School）。2009年我校城建学院交通工程研究所并入我院。汪应洛院士任名誉院长、院务委员会主任。');
insert into department(dept_name, inf)
values ('能源与动力学院',
        '能源与动力工程学院是目前国内从事能源、动力领域人才培养和科学研究工作的重要单位之一。二十世纪10年代设置蒸汽机室和内燃机室，20年代设置机械电气科，开设汽机等课程，30年代设置机电专业开展蒸汽机、煤气发动机、水利发动机等课程；50年代设置锅炉制造、汽轮机制造等科；1960年设置动力机械工程系。1986年9月，经原机械工业部批准成立上海机械学院动力工程学院，2008年12月动力工程学院更名为能源与动力工程学院。');
insert into department(dept_name, inf)
values ('外语学院',
        '上海理工大学外语学院是一所具有文理工融合学术传统的人文社科学院。学院的使命与目标是：凝聚师生校友，共筑家国梦想，培养具备优秀的多语言沟通能力与专业相关核心学术能力的国际化、创新型人才，开展中外语言、文化领域相关高水平人文社会科学研究，促进中外优秀文化交流互鉴、社会和谐发展。');
insert into department(dept_name, inf)
values ('机械与工程学院',
        '上海理工大学机械工程学院建立于1999年5月，由上海理工大学机械工程系和上海理工大学复兴路校区机械工程系合并组建。上海理工大学机械专业的办学历史，可追溯到1912年的同济德文医工学堂机电科及暨后的各个时期。上海理工大学机械学科经过近九十年的建设和发展，已经成为国家和上海市培养先进制造领域人才和科学研究的重要基地。');
insert into department(dept_name, inf)
values ('麻省理工学院',
        'I AK IOI');

create table teacher
(
    te_name nvarchar(40),
    inf     nvarchar(1023),
    title   nvarchar(40),
    primary key (te_name),
    foreign key (te_name) references users (u_name)
);

insert into teacher
values ('田所浩二', '♂', '野兽先辈');
insert into teacher
values ('佟大为', 'Deep Dark Fantasy', '教授');
insert into teacher
values ('rhy', '我 AK IOI', '讲师');

create table curriculum
(
    cur_name  nvarchar(40),
    inf       nvarchar(1023),
    dept_name nvarchar(40),
    primary key (cur_name),
    foreign key (dept_name) references department (dept_name)
);

insert into curriculum
values ('体育课', '这是一门体育课', '理学院');
insert into curriculum
values ('数字图像处理', '这是一门数字图像处理', '光电信息与计算机工程学院');
insert into curriculum
values ('JAVA WEB', '这是一门JAVA WEB', '光电信息与计算机工程学院');
insert into curriculum
values ('软件工程', 'SE', '光电信息与计算机工程学院');
insert into curriculum
values ('数据库原理', 'DataBase', '光电信息与计算机工程学院');
insert into curriculum
values ('数据结构', 'DataStructure', '光电信息与计算机工程学院');

create table teach
(
    te_name  nvarchar(40),
    cur_name nvarchar(40),
    primary key (te_name, cur_name),
    foreign key (cur_name) references curriculum (cur_name),
    foreign key (te_name) references teacher (te_name)
);

insert into teach
values ('田所浩二', '体育课');
insert into teach
values ('rhy', '数据结构');
insert into teach
values ('rhy', '数字图像处理');
insert into teach
values ('rhy', 'JAVA WEB');
insert into teach
values ('佟大为', '数据库原理');

create table question
(
    qid       int,
    cur_name  nvarchar(40),
    username  nvarchar(40),
    timestamp nvarchar(40),
    context   nvarchar(1023),
    picPath   nvarchar(1023),
    solved    int,
    title     nvarchar(1023),
    foreign key (cur_name) references curriculum (cur_name),
    primary key (qid)
);

create table reply
(
    qid       int,
    rid       int,
    picPath   nvarchar(1023),
    username  nvarchar(40),
    context   nvarchar(1023),
    timestamp nvarchar(40),
    primary key (rid),
    foreign key (qid) references question (qid)
);

create table ban
(
    cur_name nvarchar(40),
    username nvarchar(40),
    primary key (cur_name, username),
    foreign key (cur_name) references curriculum (cur_name),
    foreign key (username) references users (u_name)
);
