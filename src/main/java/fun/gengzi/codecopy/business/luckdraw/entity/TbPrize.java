package fun.gengzi.codecopy.business.luckdraw.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_prize")
public class TbPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //主键
    @Column(name = "id")
    private Integer id;
    //奖品名称
    @Column(name = "prize_name")
    private String prizeName;
    //奖品信息
    @Column(name = "prize_info")
    private String prizeInfo;
    //奖品数目
    @Column(name = "prize_num")
    private Integer prizeNum;
    //奖品cdk的类型
    @Column(name = "prize_cdktype")
    private String prizeCdktype;
    //奖品类型  1 优惠券 2 实物奖品 3 积分
    @Column(name = "prize_type")
    private Integer prizeType;
    //概率
    @Column(name = "probability")
    private Double probability;
    //活动id
    @Column(name = "activityid")
    private String activityid;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @Column(name = "createtime")
    private Date createtime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @Column(name = "updatetime")
    private Date updatetime;
}
