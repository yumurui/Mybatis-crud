package yukaixin.mapper;

import org.apache.ibatis.annotations.Param;
import yukaixin.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    List<Brand> findAll();
    Brand findByID(int id);
    List<Brand> findByConditions(@Param("status") int status, @Param("brandName") String brandName,
                                 @Param("companyName") String companyName);
    List<Brand> findByConditions2(Brand brand);
    List<Brand> findByConditions3(Map map);
    List<Brand> findByConditionsDynamic(Brand brand);
    List<Brand> findByConditionSingle(Brand brand);
    int addBrand(Brand brand);
    int updateBrand(Brand brand);
    int updateBrandDynamic(Brand brand);
    int deleteBrandByID(int id);
    int deleteBrands(@Param("ids") int[] ids);
}
