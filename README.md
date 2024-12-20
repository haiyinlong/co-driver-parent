# co-driver

DW数据处理服务，负责对数据的清洗并生成对应的主题数据。

通过Seatunnel CDC 同步数据到ODS层

基于Seatunnel CDC 监控ods_user数据将实时变更数据写入rabbitmq. 再消费rabbitmq数据，进行数据清洗，生成对应DIM数据。

<img alt="图片" height="447" src=".\doc\img\CDC.png" width="527"/>

*数据处理层分层说明*

* ODS: ODS层，存储原始数据，不做任何处理；
* DIM: DIM层，对ODS层数据进行维度处理，生成维度表，方便DWD层、DWS层、ADS层使用；
* DWD: DWD层，对原始数据进行清洗，生成事实宽表，含需要统计的维度和指标数据方便DWS层使用；
* DWS: DWS层，对DWD层数据进行汇总，生成业务宽表，进行轻量级数据维度汇总。汇总统计维度的金额、数量等信息；
* ADS: ADS层，对DWS层数据进行汇总，生成主题汇总表，可以直接对外使用；

> 有新的主题需求时，优先使用现有的DWS层数据，不满足时再提供新的DWS层数据

## 代办事项

* DWD层获取用户维度统计改为从用户维表获取
* DWS层从ODS层获取数据改为从DWD层获取

## 定期执行任务

1. `cdc-myql` 数据库数据清理，每月定时删除，并进行空间创建；
    1. ods层数据保存一个月内的数据
    2. dwd层数据保存一个月内的数据
    3. binlog 数据清理
2. 应用日志清理，每月清理
    1. `co-driver-start` 数据处理服务日志清理
    2. `seatunnel` 数据同步服务日志清理

## 遗留问题

DwsDailyPkgMiniGameMapper 数据暂时停止统计，数据量大时会有影响；
DwdUserOnlineServiceImpl 统计耗时，需要优化
