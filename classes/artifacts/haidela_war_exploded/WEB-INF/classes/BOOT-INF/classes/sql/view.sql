-- ----------------------------
-- View structure for lyx_pay_individual_view
-- ----------------------------
DROP VIEW IF EXISTS `lyx_pay_individual_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `lyx_pay_individual_view` AS select
`pay`.`tran_flow` AS `pay_tran_flow`,
`pay`.`amount` AS `pay_amount`,
`pay`.`pay_type` AS `pay_pay_type`,
`pay`.`merchant_id` AS `pay_merchant_id`,
`pay`.`merchant_no` AS `pay_merchant_no`,
`pay`.`buyer_id` AS `pay_buyer_id`,
`pay`.`modify_time` AS `pay_modify_time`,
`pay`.`status` AS `pay_status`,
`pay`.`comp_iD` AS `pay_comp_iD`,
`individual`.`merchant_no` AS `merchant_no`,
`individual`.`merchant_short_name` AS `merchant_short_name`,
`individual`.`account_code` AS `account_code`,
`individual`.`account_name` AS `account_name`
from (
`lyx_pay_customer` `pay` join `lyx_individual_customer` `individual`
)
where (`pay`.`merchant_no` = `individual`.`merchant_no`);

-- ----------------------------
-- View structure for lyx_repay_individual_view
-- ----------------------------
DROP VIEW IF EXISTS `lyx_repay_individual_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `lyx_repay_individual_view` AS select
 `repay`.`tran_flow` AS `repay_tran_flow`,
 `repay`.`repay_tran_flow` AS `repay_repay_tran_flow`,
 `repay`.`amount` AS `repay_amount`,
 `repay`.`pay_type` AS `repay_pay_type`,
 `repay`.`merchant_no` AS `repay_merchant_no`,
 `repay`.`status` AS `repay_status`,
 `repay`.`comp_iD` AS `repay_comp_iD`,
 `repay`.`create_time` AS `repay_create_time`,
 `individual`.`merchant_no` AS `individual_merchant_no`,
 `individual`.`merchant_short_name` AS `individual_merchant_short_name`,
 `individual`.`account_code` AS `individual_account_code`,
 `individual`.`account_name` AS `individual_account_name`
 from (`lyx_repay_customer` `repay` join `lyx_individual_customer` `individual`)
  where (`repay`.`merchant_no` = `individual`.`merchant_no`);

SET FOREIGN_KEY_CHECKS = 1;
