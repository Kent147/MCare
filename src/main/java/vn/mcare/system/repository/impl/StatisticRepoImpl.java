package vn.mcare.system.repository.impl;

import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.month;
import static org.jooq.impl.DSL.now;
import static org.jooq.impl.DSL.sum;
import static org.jooq.impl.DSL.week;
import static org.jooq.impl.DSL.year;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Configuration;
import org.jooq.SQL;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EBillType;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.pojo.api.input.StatisticInput;
import vn.mcare.system.common.pojo.api.output.StatisticBillOutput;
import vn.mcare.system.common.pojo.api.output.StatisticOutput;
import vn.mcare.system.common.pojo.dto.BillStatisticDto;
import vn.mcare.system.common.pojo.dto.RevenueDto;
import vn.mcare.system.repository.intface.StatisticRepo;
import vn.mcare.system.repository.model.tables.Bill;
import vn.mcare.system.repository.model.tables.RegisterExamination;

@Slf4j
@Repository
public class StatisticRepoImpl implements StatisticRepo {

  @Autowired
  private Connection connection;

  @Qualifier("getConfiguration")
  @Autowired
  private Configuration configuration;

  private AutoCloseableDSLContext getAutoCloseableDSLContext() throws Exception {
    return new AutoCloseableDSLContext(configuration);
  }

  @Override
  public StatisticOutput getStatisticByWeek() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      RegisterExamination regis = RegisterExamination.REGISTER_EXAMINATION;

      StatisticOutput output = context
              .select(count(bill.BILL_ID).as("totalBill"), sum(bill.TOTAL_PRICE).as("totalIncome"))
              .from(bill)
              .where(week(bill.BILL_DATE).eq(week(now())))
              .fetchOneInto(StatisticOutput.class);

      Integer countRegister = context.selectCount().from(regis)
              .where(week(regis.CREATED_AT).eq(week(now()))).fetchOneInto(Integer.class);

      output.setCountRegister(countRegister);

      return output;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public StatisticOutput getStatisticByMonth() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      RegisterExamination regis = RegisterExamination.REGISTER_EXAMINATION;

      StatisticOutput output = context
              .select(count(bill.BILL_ID).as("totalBill"),
                      sum(bill.TOTAL_PRICE).as("totalIncome"))
              .from(bill)
              .where(month(bill.BILL_DATE).eq(month(now())).and(year(bill.BILL_DATE).eq(year(bill.BILL_DATE))))
              .fetchOneInto(StatisticOutput.class);

      Integer countRegister = context
              .selectCount()
              .from(regis)
              .where(month(regis.FROM_DATE).eq(month(now()))
                      .and(year(regis.FROM_DATE).eq(year(now()))))
              .fetchOneInto(Integer.class);

      output.setCountRegister(countRegister);

      return output;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public StatisticOutput getStatisticByYear() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      RegisterExamination regis = RegisterExamination.REGISTER_EXAMINATION;

      StatisticOutput output = context
              .select(count(bill.BILL_ID).as("totalBill"),
                      sum(bill.TOTAL_PRICE).as("totalIncome"))
              .from(bill)
              .where(year(bill.BILL_DATE).eq(year(now())))
              .fetchOneInto(StatisticOutput.class);

      Integer countRegister = context
              .selectCount()
              .from(regis)
              .where(year(regis.FROM_DATE).eq(year(now())))
              .fetchOneInto(Integer.class);

      output.setCountRegister(countRegister);

      return output;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public StatisticBillOutput getStatisticBills(StatisticInput input) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;

      LocalDateTime fromDate = DateHelper.toStartLocalDateTime(input.getFromDate());
      LocalDateTime toDate = DateHelper.toEndLocalDateTime(input.getToDate());

      BillStatisticDto medBills = context
              .selectCount()
              .select(sum(bill.TOTAL_PRICE).as("sum_total_price"))
              .from(bill)
              .where(bill.BILL_TYPE.eq(EBillType.MEDICINE_EXPORT.getCode()))
              .and(bill.BILL_DATE.betweenSymmetric(fromDate, toDate))
              .fetchOneInto(BillStatisticDto.class);

      BillStatisticDto serviceBills = context
              .selectCount()
              .select(sum(bill.TOTAL_PRICE).as("sum_total_price"))
              .from(bill)
              .where(bill.BILL_TYPE.eq(EBillType.SERVICE_EXPORT.getCode()))
              .and(bill.BILL_DATE.betweenSymmetric(fromDate, toDate))
              .fetchOneInto(BillStatisticDto.class);

      return new StatisticBillOutput()
              .setFromDate(input.getFromDate())
              .setToDate(input.getToDate())
              .setCountMedBill(medBills.getCount())
              .setSumMedBillPrice(medBills.getSumTotalPrice())
              .setCountServiceBill(serviceBills.getCount())
              .setSumServiceBillPrice(serviceBills.getSumTotalPrice());

    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<RevenueDto> getStatisticRevenue(StatisticInput input) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      Long fromDate = DateHelper.toUnixTimeMySQL(input.getFromDate());
      Long toDate = DateHelper.toUnixTimeMySQL(input.getToDate());

      SQL sql = DSL.sql("SELECT UNIX_TIMESTAMP(d.date) * 1000 date,\n" +
              "IF(report.sub_price is null, 0, report.sub_price) sub_price,\n" +
              "IF(report.vat_price is null, 0, report.vat_price) vat_price,\n" +
              "IF(report.total_price is null, 0, report.total_price) total_price,\n" +
              "IF(report.cost_of_goods is null, 0, report.cost_of_goods) cost_of_goods\n," +
              "(total_price - cost_of_goods) revenue\n" +
              "FROM (\n" +
              "select date\n" +
              "from\n" +
              " (select adddate('1970-01-01',t4*10000 + t3*1000 + t2*100 + t1*10 + t0) date from\n" +
              " (select 0 t0 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,\n" +
              " (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,\n" +
              " (select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,\n" +
              " (select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,\n" +
              " (select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v\n" +
              "Where v.date between CONVERT(FROM_UNIXTIME(" + fromDate + "), DATE) and CONVERT(FROM_UNIXTIME(" + toDate + "), DATE)\n" +
              "    ) d\n" +
              "LEFT JOIN (\n" +
              "select DATE(bill_date) date, sum(sub_price) sub_price, sum(vat_price) vat_price, sum(total_price) total_price, sum(inv.import_price * mb.amount) cost_of_goods\n" +
              "from bill\n" +
              "left join medicine_bill mb\n" +
              "ON bill.bill_id = mb.bill_id\n" +
              "left join inventory inv\n" +
              "on mb.inventory_id = inv.inventory_id\n" +
              "and mb.medicine_id = inv.medicine_id\n" +
              "where bill_date BETWEEN CONVERT(FROM_UNIXTIME(" + fromDate + "), DATE) and CONVERT(FROM_UNIXTIME(" + toDate + "), DATE)" +
              "GROUP BY bill.bill_date) report\n" +
              "on d.date = report.date");

      return context
              .fetch(sql)
              .into(RevenueDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public StatisticOutput getStatisticBestSellers(StatisticInput input) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      RegisterExamination regis = RegisterExamination.REGISTER_EXAMINATION;

      StatisticOutput output = context
              .select(count(bill.BILL_ID).as("totalBill"),
                      sum(bill.TOTAL_PRICE).as("totalIncome"))
              .from(bill)
              .where(year(bill.BILL_DATE).eq(year(now())))
              .fetchOneInto(StatisticOutput.class);

      Integer countRegister = context
              .selectCount()
              .from(regis)
              .where(year(regis.FROM_DATE).eq(year(now())))
              .fetchOneInto(Integer.class);

      output.setCountRegister(countRegister);

      return output;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
