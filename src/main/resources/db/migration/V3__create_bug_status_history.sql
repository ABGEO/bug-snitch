CREATE TABLE `bug_status_history`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT,
    `new_status`        int(11)     DEFAULT NULL,
    `old_status`        int(11)     DEFAULT NULL,
    `status_updated_at` datetime(6) DEFAULT NULL,
    `bug_id`            bigint(20)  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKflgmpuv750uq1tjscqshfirnu` (`bug_id`),
    CONSTRAINT `FKflgmpuv750uq1tjscqshfirnu` FOREIGN KEY (`bug_id`) REFERENCES `bug` (`id`)
)
