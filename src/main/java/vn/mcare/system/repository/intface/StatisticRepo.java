package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.api.input.StatisticInput;
import vn.mcare.system.common.pojo.api.output.StatisticBillOutput;
import vn.mcare.system.common.pojo.api.output.StatisticOutput;
import vn.mcare.system.common.pojo.dto.RevenueDto;

public interface StatisticRepo {

  StatisticOutput getStatisticByWeek();

  StatisticOutput getStatisticByMonth();

  StatisticOutput getStatisticByYear();

  StatisticBillOutput getStatisticBills(StatisticInput input);

  List<RevenueDto> getStatisticRevenue(StatisticInput input);

  StatisticOutput getStatisticBestSellers(StatisticInput input);
}
