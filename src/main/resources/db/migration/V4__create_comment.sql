CREATE TABLE `comment`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `body`       varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `created_at` datetime(6)                              DEFAULT NULL,
    `bug_id`     bigint(20)                               DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKj24ngr2my3x7cxqxo63yaf3ui` (`bug_id`),
    CONSTRAINT `FKj24ngr2my3x7cxqxo63yaf3ui` FOREIGN KEY (`bug_id`) REFERENCES `bug` (`id`)
)
