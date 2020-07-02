package fun.gengzi.codecopy.business.subdata.strategy.range;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Range;
import fun.gengzi.codecopy.business.subdata.strategy.precise.InDateShardingStrategyConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * <h1>范围值控制分库分表</h1>
 *
 * @author gengzi
 * @date 2020年7月2日11:07:15
 */
public class InDateDataBaseRangeShardingConfiguration implements PreciseShardingAlgorithm<Date>, RangeShardingAlgorithm<Date> {

    private Logger logger = LoggerFactory.getLogger(InDateShardingStrategyConfiguration.class);

    public InDateDataBaseRangeShardingConfiguration() {
        logger.info("初始化 -> [{}]", "InDateDataBaseRangeShardingConfiguration ----- 根据年份范围分片算法-启用");
    }

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Date> range = shardingValue.getValueRange();

        Date lowerDate = range.lowerEndpoint();
        Date upperDate = range.upperEndpoint();

        logger.info("lowerDate : {} ", DateUtil.formatDateTime(lowerDate));
        logger.info("upperDate : {} ", DateUtil.formatDateTime(upperDate));

        Integer low = Integer.valueOf(DateUtil.format(lowerDate, "yyyy"));
        Integer upper = Integer.valueOf(DateUtil.format(upperDate, "yyyy"));


        for (int i = low; i <= upper; i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data source or table's name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        // shardingValue 分片的值 日期，2020-06-06 15:12:00  根据年份分表，获取年份最小的，扩展年份最大的
        // 假如只扩展两年的 2020  2021。
        //配置的分片的sharding-column对应的值
        Date timeValue = shardingValue.getValue();
        String time = DateUtil.formatDate(timeValue);
        logger.info("time: {}", time);
        //分库时配置的sharding-column
        String timeColumn = shardingValue.getColumnName();
        //需要分库的逻辑表
        String tableName = shardingValue.getLogicTableName();
        logger.info("分库策略： 分片值 : {} , 分片列: {} , 逻辑表: {} ", timeValue, timeColumn, tableName);
        availableTargetNames.forEach(name -> {
            logger.info("availableTargetName ：{} ", name);
        });

        //按年路由
        for (String each : availableTargetNames) {
            String value = StringUtils.substring(time, 0, 4); //获取到年份
            if (each.endsWith(value)) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
