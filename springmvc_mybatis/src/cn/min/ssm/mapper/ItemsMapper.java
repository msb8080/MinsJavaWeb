package cn.min.ssm.mapper;

import cn.min.ssm.po.Items;
import cn.min.ssm.po.ItemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 
* @ClassName: ItemsMapper 
* @Description: 商品信息 mapper接口
* @author min
* @date 2018年5月26日 下午7:25:38 
*
 */
public interface ItemsMapper {
    int countByExample(ItemsExample example);

    int deleteByExample(ItemsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Items record);

    int insertSelective(Items record);

    List<Items> selectByExampleWithBLOBs(ItemsExample example);

    List<Items> selectByExample(ItemsExample example);

    Items selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByExampleWithBLOBs(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByExample(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByPrimaryKeySelective(Items record);

    int updateByPrimaryKeyWithBLOBs(Items record);

    int updateByPrimaryKey(Items record);
}