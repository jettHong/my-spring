INSERT INTO user (id, name, age, email) VALUES
(100, '张三', 48, 'test100@baomidou.com'),
(200, '李四', 50, 'test200@baomidou.com'),
(300, '王五', 48, 'test300@baomidou.com'),
(400, '赵六', 51, 'test400@baomidou.com');


-- 如果本文件优先于 002 文件，则数据都会不会是99
update user set age = 99 where id = '5';
