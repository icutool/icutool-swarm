CREATE TABLE `weekly_report` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                 `subject` varchar(255) DEFAULT NULL COMMENT '工作主题',
                                 `content` varchar(255) DEFAULT NULL COMMENT '工作详情',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                                 `del_flag` bit(1) DEFAULT b'0' COMMENT '逻辑删除 0未删除 1删除',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;