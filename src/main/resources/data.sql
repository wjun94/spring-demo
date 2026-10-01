INSERT INTO `user` (`name`, `age`, `email`)
SELECT seed.`name`, seed.`age`, seed.`email`
FROM (
    SELECT '张三' AS `name`, 20 AS `age`, 'zhangsan@example.com' AS `email`
    UNION ALL
    SELECT '李四', 21, 'lisi@example.com'
    UNION ALL
    SELECT '王五', 22, 'wangwu@example.com'
    UNION ALL
    SELECT '赵六', 23, 'zhaoliu@example.com'
    UNION ALL
    SELECT '钱七', 24, 'qianqi@example.com'
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM `user`);
