package io.sofastack.cloud.accounting.jobs;

import io.sofastack.cloud.accounting.dao.AccountingMapper;
import io.sofastack.cloud.accounting.dao.BillMapper;
import io.sofastack.cloud.accounting.dao.SerialOrderMapper;
import io.sofastack.cloud.common.enums.SerialEnums;
import io.sofastack.cloud.common.model.BillDetails;
import io.sofastack.cloud.common.model.SerialOrder;
import io.sofastack.cloud.common.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 3:43 PM
 * @since:
 **/
public class OrderJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderJob.class);

    @Autowired
    private SerialOrderMapper   serialOrderMapper;
    @Autowired
    private AccountingMapper    accountingMapper;
    @Autowired
    private BillMapper          billMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)

                                                                           throws JobExecutionException {
        LOGGER.info("Begin to executeInternal job...");
        Date start = DateUtils.getPreDate();
        Date end = DateUtils.getLastDay();
        List<Integer> userIds = accountingMapper.queryAllAccounts();
        userIds.forEach(item->{
            BillDetails bill = new BillDetails();
            bill.setCreateTime(new Date());
            bill.setUserId(item);
            List<SerialOrder> serialOrders = serialOrderMapper.querySerialOrdersOneDay(item, start, end);
            serialOrders.forEach(serialOrder->{
                if (SerialEnums.PAY.getType().equals(serialOrder.getSerialType())){
                    bill.setPay(bill.getPay()+1);
                    bill.setPayAmount(bill.getPayAmount().add(serialOrder.getMoney()));
                }else if (SerialEnums.INCOME.getType().equals(serialOrder.getSerialType())){
                    bill.setIncome(bill.getIncome()+1);
                    bill.setIncomeAmount(bill.getIncomeAmount().add(serialOrder.getMoney()));
                }
            });
            // 账单入库
            billMapper.save(bill);
        });
    }
}
