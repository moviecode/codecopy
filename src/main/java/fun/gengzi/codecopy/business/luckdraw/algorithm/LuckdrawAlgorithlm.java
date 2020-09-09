package fun.gengzi.codecopy.business.luckdraw.algorithm;

/**
 * 抽奖算法接口
 */
public interface LuckdrawAlgorithlm<T> {


    /**
     * 抽奖算法
     * @param t 影响因素算法实体
     * @return 奖项级别
     */
    Integer algorithlm(T t);

    /**
     * 项目上线后，运营上线了积分可兑换的奖品，不同产品对应不同积分值兑换，设置了转盘可中奖的不同积分值。
     * 但上线两周后，运营发现数据不是自己想要的，虽然拉新促活的目的达到了，用户抽奖参与度也很高，但因为每天设置的抽奖次数过多，每次抽奖消耗的积分过少，每个用户每日中奖的积分数量增长过高，奖品兑换率过高。
     * 于是有了以下思考：应该设置用户每日抽奖几次、每次抽奖消耗多少积分才能合理达到运营想要效果。
     * （3）在此我们假设所有的奖项都是积分数值
     * 讨论的前提是当前项目中每个奖品的中奖概率相等的情况，设奖品有m项、每项积分数分别为A1、A2、…、Am。
     * 每日可抽奖次数为n，每次抽奖消耗的积分为a，根据数学知识我们知道抽奖次数样本足够大时，抽奖几率应该趋近于相等。所以每轮抽奖（n次为一轮）可获得的积分数：Sn=（A1+A2+…+Am）-n*a
     * 【所有奖品抽到一次获得的总和-抽奖n次消耗的积分数】
     * 显然易见：若想用户抽奖积分为负增长，只需Sn<0即可。若想用户抽奖积分为正增长，则只需Sn>0即可当每天可抽奖n次时，每天可获得的积分和平均值=Sn*n/m。
     * 活动的最终目的还是促活、吸引更多的人来参与活动兑换奖品。那么假设兑换的奖品所需积分数为Y，想要用户兑换一个奖品的日活天数为d，使Y/d=（A1+A2+…+Am）*n/m即可。
     * 【每个奖品需要的分数/想要用户获得的天数=用户每天可获得积分数量】
     * Y可根据奖品的平均积分数或最小积分数来计算，以此达到运营拉新促活的目的。
     */

}
