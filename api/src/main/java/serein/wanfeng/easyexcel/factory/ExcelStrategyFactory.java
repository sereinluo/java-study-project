package serein.wanfeng.easyexcel.factory;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import serein.wanfeng.easyexcel.stratery.ExcelRowHeightSettingStrategy;

/**
 * @Date: 2023-10-11 17:28
 * @Author: luozh
 * @Description: 单元格样式策略factory
 */

public class ExcelStrategyFactory {

    /**
     * 获取单元格样式：自动换行、垂直居中
     * @return 单元格样式
     */
    private static WriteCellStyle getAutoWrapWriteCellStyle(){
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置自动换行
        contentWriteCellStyle.setWrapped(Boolean.TRUE);
        //垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return contentWriteCellStyle;
    }

    /**
     * 获取自动换行和双线居中的单元格样式策略
     * @return 单元格样式策略
     */
    public static HorizontalCellStyleStrategy getAutoWrapStrategy(){

        WriteCellStyle writeCellStyle = getAutoWrapWriteCellStyle();

        //表头样式和表内容样式相同
        return new HorizontalCellStyleStrategy(writeCellStyle, writeCellStyle);
    }

    /**
     * 获取设置行高的策略
     * @param rowHeight 行高
     * @return 行高设置策略
     */
    public static ExcelRowHeightSettingStrategy getRowHeightSettingStrategy(Float rowHeight){
        return new ExcelRowHeightSettingStrategy(rowHeight);
    }


}