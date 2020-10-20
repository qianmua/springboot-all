package pres.qm.aqs.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qm.aqs.current.CustomLock;

import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/20  1:07
 * @description :
 */
@RestController
@RequestMapping("/shop")
public class AQSController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    CustomLock customLock = new CustomLock();

    @GetMapping("/t")
    public String decStockNoLock(){

        // lock
        customLock.lock();

        Integer stock;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select stock from shop_order where id = 1");

        if (maps == null || (stock = (Integer) maps.get(0).get("stock") ) <= 0){
            System.out.println("no number");
            // unlock

            customLock.unLock();

            return "fail null";
        }
        // volatile
        stock ++;
        jdbcTemplate.update("update shop_order set stock=? where id= 1" , stock);
        System.out.println("success -> " + stock);
        //unlock

        customLock.unLock();

        return "success ->" + stock;
    }




}
