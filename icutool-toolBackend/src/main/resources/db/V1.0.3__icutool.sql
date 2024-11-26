ALTER TABLE `icutool`.`weekly_report`
    MODIFY COLUMN `content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作详情' AFTER `subject`;

ALTER TABLE `icutool`.`message_broker`
    MODIFY COLUMN `content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息详情' AFTER `id`;