package net.suncaper.ecommerceanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 从数据库获取到数据信息
 */
@Controller
public class UvController {

    //2. 注入一个jdbcTemplate，完成SQL 增删改查
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     *1.3统计男女购买商品的比率
     * */
    @GetMapping("/man-woman-rate")
    @ResponseBody
    public List<Map<String, Object>> get_man_women_rate() {//男女购买商品的比率
        return jdbcTemplate.queryForList("SELECT * FROM dws_sex_ratio");
    }
    /**
     *1.4 top 10 （分析销量top 10的商品/店铺/品牌）
     */
    @GetMapping("/top10-brand")
    @ResponseBody
    public List<Map<String, Object>> get_top10_brand() {//销量前10的品牌
        return jdbcTemplate.queryForList("SELECT * FROM dws_top10_brand order by brand_number desc");
    }
    @GetMapping("/top10-item")
    @ResponseBody
    public List<Map<String, Object>> get_top10_item() {//销量前10的商品
        return jdbcTemplate.queryForList("SELECT * FROM dws_top10_item order by item_number desc");
    }
    @GetMapping("/top10-merchant")
    @ResponseBody
    public List<Map<String, Object>> get_top10_merchant() {//销量前10的店铺
        return jdbcTemplate.queryForList("SELECT * FROM dws_top10_merchant order by merchant_number desc");
    }

    /**
     * 2、计算每周每月和每天的用户活跃数
     * */
    @GetMapping("/uv/week-report") //1. URL映射：映射URL地址 -> getWeekReport
    @ResponseBody
    public List<Map<String, Object>> getWeek_active_Report() {//用户周活跃数
        return jdbcTemplate.queryForList("SELECT * FROM dws_uv_detail_wk ORDER BY wk_num ASC limit 50");
    }
    @GetMapping("/uv/day-report")
    @ResponseBody
    public List<Map<String, Object>> get_day_active_Report() {//用户日活跃数
        return jdbcTemplate.queryForList("SELECT * FROM dws_uv_count_day");
    }

    @GetMapping("/uv/month-report")
    @ResponseBody
    public List<Map<String, Object>> get_month_active_Report() {//用户月活跃数
        return jdbcTemplate.queryForList("SELECT * FROM dws_uv_detail_month order by month");
    }


    /**
     * 4，新增用户专题：获取每天、每周、每月新增的用户数;路径名为 ua 是 user add 的缩写
     * */
    @GetMapping("/ua/day-report")
    @ResponseBody
    public List<Map<String, Object>> get_day_new_add_Report() {//获取每天新增用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_user_add_count_day order by dt ");
    }
    @GetMapping("/ua/week-report")
    @ResponseBody
    public List<Map<String, Object>> get_week_new_add_Report() {//获取每周新增用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_user_add_detail_week order by wk_num");
    }
    @GetMapping("/ua/month-report")
    @ResponseBody
    public List<Map<String, Object>> get_month_new_add_Report() {//获取每月新增用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_user_add_detail_month order by month_num");
    }
    /**
     *5、用户留存专题：每天计算前1、2、3、4、7、14天的留存率
     * */
    @GetMapping("/ur/one-day-report")
    @ResponseBody
    public List<Map<String, Object>> get_one_day_retention_Report() {//获取每月新增用户数
        return jdbcTemplate.queryForList("select * from dws_user_retension_one_day order by dt limit 20");
    }
    @GetMapping("/ur/two-day-report")
    @ResponseBody
    public List<Map<String, Object>> get_two_day_retention_Report() {//获取每月新增用户数
        return jdbcTemplate.queryForList("select * from dws_user_retension_two_day order by dt limit 20");
    }
    @GetMapping("/ur/three-day-report")
    @ResponseBody
    public List<Map<String, Object>> get_three_day_retention_Report() {//获取每月新增用户数
        return jdbcTemplate.queryForList("select * from dws_user_retension_three_day order by dt limit 20");
    }
    @GetMapping("/ur/four-day-report")
    @ResponseBody
    public List<Map<String, Object>> get_four_day_retention_Report() {//获取每月新增用户数
        return jdbcTemplate.queryForList("select * from dws_user_retension_four_day order by dt limit 20");
    }
    @GetMapping("/ur/seven-day-report")
    @ResponseBody
    public List<Map<String, Object>> get_seven_day_retention_Report() {//获取每月新增用户数
        return jdbcTemplate.queryForList("select * from dws_user_retension_seven_day order by dt limit 20");
    }
    @GetMapping("/ur/fourteen-day-report")
    @ResponseBody
    public List<Map<String, Object>> get_fourteen_day_retention_Report() {//获取每月新增用户数
        return jdbcTemplate.queryForList("select * from dws_user_retension_fourteen_day order by dt limit 20");
    }

    /**
     * 6、沉默用户专题：只在当天启动过，且启动时间是在一个月前
     * */
    @GetMapping("/silent-user-report")
    @ResponseBody
    public List<Map<String, Object>> get_silent_user_Report() {//获取每天的沉默用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_user_silent_detail_day");
    }

    /**
     * 7、流失用户专题，统计每天流失的用户数
     * */
    @GetMapping("/lost-user-report")
    @ResponseBody
    public List<Map<String, Object>> get_lost_user_Report() {//获取每天的沉默用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_user_waste_count");
    }


    /**
     * 8. 最近连续三周活跃的用户数
     * 1) 每周统计一次前三周活跃的用户数,
     * */
    @GetMapping("/three-continue-report")
    @ResponseBody
    public List<Map<String, Object>> get_three_week_active_Report() {//获取每天的沉默用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_user_three_week_active order by week");
    }

    /**
     * 9. 最近7天内连续三天活跃的用户数,
     * */
    @GetMapping("/three-seven-report")
    @ResponseBody
    public List<Map<String, Object>> get_continue_three_active_aweek_Report() {//获取每天的沉默用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_user_continue_uv_count order by dt");
    }

    /**
     * 10. 漏斗分析
     * 1) 分析浏览、加入购物车、成单的比率
     * */
    @GetMapping("/user-translate-rate-report")
    @ResponseBody
    public List<Map<String, Object>> get_translate_rate_Report() {//获取每天的沉默用户数
        return jdbcTemplate.queryForList("SELECT * FROM dws_uv_pv");
    }
}
