package yukaixin.crudTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import yukaixin.mapper.BrandMapper;
import yukaixin.pojo.Brand;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TestCrud {
    @Test
    /**
     * 查找Brand中的所有信息，并输出
     */
    public void testFindAll() throws IOException {
        //加载mybatis的核心配置文件，获得sqlsessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql语句
        //先获取mapper对象，并用mapper对象调用方法
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.findAll();

        System.out.println(brands);
    }


    /***
     * 根据id进行查找
     */
    @Test
    public void testFindBrandByID() throws IOException {
        //加载mybatis的核心配置文件，获得sqlsessionFactory
        int id = 1;
        String source = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(source);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //取得mapper对象，使用mapper对象调用修改方法
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.findByID(id);
        System.out.println(brand);
    }


    /***
     * 多条件查询，同时成立
     */
    @Test
    public void testFindByConditions() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        //加载mybatis配置核心文件，获得sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //取得mapper对象，通过mapper对象调用查找方
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.findByConditions(status, brandName, companyName);
        System.out.println(brands);
    }

    /***
     * 多条件动态查询同时成立（用brand对象做参数）
     */
    @Test
    public void testFindByConditions2() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        //封装为一个对象，将对象作为方法参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);

        //加载mybatis配置核心文件，获得sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //取得mapper对象，通过mapper对象调用查找方
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.findByConditions2(brand);
        System.out.println(brands);
    }

    /***
     * 多条件动态查询同时成立（用map做参数）
     */
    @Test
    public void testFindByConditions3() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        //封装为一个对象，将对象作为方法参数
        Map map = new HashMap();
        map.put("status", status);
        map.put("companyName", companyName);
        map.put("brandName", brandName);

        //加载mybatis配置核心文件，获得sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //取得mapper对象，通过mapper对象调用查找方
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.findByConditions3(map);
        System.out.println(brands);
    }

    /***
     * 动态查找信息，即三个线索不一定都成立
     */
    @Test
    public void testFindByConditionsDynamic() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);

        //加载mybatis核心配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper对象，并用对象调用修改方法
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.findByConditionsDynamic(brand);
        System.out.println(brands);
    }


    /***
     * 单条件动态查询
     */
    @Test
    public void testFindByConditionSingle() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);

        //加载mybatis配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.findByConditionSingle(brand);
        System.out.println(brands);
    }

    /**
     * 往brand插入记录
     */
    @Test
    public void testAddBrand() throws IOException {
        int status = 1;
        String companyName = "小米公司";
        String brandName = "小米手机";
        int ordered = 1000;
        String description = "小米手机，为发烧而生~~";
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);

        //加载mybatis核心配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.addBrand(brand);
        System.out.println(count);
    }

    /***
     * 修改brand
     */
    @Test
    public void testUpdateBrand() throws IOException {
        int id = 5;
        int status = 0;
        String companyName = "小米有限公司";
        String brandName = "小米手环";
        int ordered = 20;
        String description = "小米手环，检测心率~~";
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setId(id);

        //加载mybatis核心配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.updateBrand(brand);
        System.out.println(count);
    }

    /***
     * 动态添加
     */
    @Test
    public void testUpdateDynamic() throws IOException {
        int id = 4;
        int status = 0;
        String companyName = "三星有限公司";
        String brandName = "三星手机";
        int ordered = 80;
        String description = "三星手机，屏幕非常清晰！！";
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setOrdered(ordered);
//        brand.setDescription(description);
//        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setId(id);

        //加载mybatis核心配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.updateBrandDynamic(brand);
        System.out.println(count);
    }

    /**
     * 删除记录
     */
    @Test
    public void testDeleteBrandByID() throws IOException {
        int id = 5;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.deleteBrandByID(id);
        System.out.println(count);
    }

    /***
     * 批量删除
     */
    @Test
    public void testDeleteBrands() throws IOException {
        int[] ids = {6, 7, 8, 9};
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.deleteBrands(ids);
        System.out.println(count);
    }
}
