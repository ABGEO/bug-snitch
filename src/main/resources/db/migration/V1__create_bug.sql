CREATE TABLE `bug`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `title`      varchar(255)  COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `body`       varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `created_at` datetime(6)                             DEFAULT NULL,
    PRIMARY KEY (`id`)
)
