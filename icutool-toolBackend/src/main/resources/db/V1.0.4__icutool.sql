ALTER TABLE `icutool`.`sys_user`
    ADD COLUMN `api_key` varchar(255) DEFAULT NULL COMMENT '调用秘钥';
UPDATE `icutool`.`sys_user` SET `api_key` = '12fa2078-d66e-4980-ba15-95b73af4dbf6' WHERE `id` = 1;