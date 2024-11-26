CREATE TABLE `message_broker` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                  `content` varchar(255) DEFAULT NULL COMMENT '消息详情',
                                  `msg_type` int(1) DEFAULT NULL COMMENT '消息类型 0文本 1图片',
                                  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                                  `del_flag` bit(1) DEFAULT b'0' COMMENT '逻辑删除 0未删除 1删除',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;