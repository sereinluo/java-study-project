package serein.wanfeng.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.entity.BorrowItem;
import serein.wanfeng.entity.ExportArchiveInfo;
import serein.wanfeng.valueobject.ArchiveType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023-08-18 11:40
 * @Author: luozh
 * @Description: 档案工厂
 */

public class ExampleDataFactory {

    public static final String BORROW_ITEM_PREFIX = "BI";

    private static final DateTimeFormatter SHORT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 生成样例档案集合
     * @return 档案集合
     */
    public static List<Archive> generateExampleArchiveList(){
        List<Archive> archiveList = new ArrayList<>();
        archiveList.add(new Archive("A001", "杭州亚运会城市建设规划方案", ArchiveType.RECORD));
        archiveList.add(new Archive("A002", "义乌世博会展览主建筑设计方案", ArchiveType.RECORD));
        archiveList.add(new Archive("A003", "关于促进台州府城墙旅游景区发展的决定（换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试）", ArchiveType.VOLUME));

        archiveList.add(new Archive(null, "证券日报2023-10-29", ArchiveType.RECORD));
        archiveList.add(new Archive("A005", null, ArchiveType.VOLUME));
        archiveList.add(new Archive("A006", "证券日报2023-11-05", null));

        archiveList.add(new Archive("", "证券日报2023-10-30", ArchiveType.RECORD));
        archiveList.add(new Archive("A007", "", ArchiveType.VOLUME));
        return archiveList;
    }

    public static List<BorrowItem> generateBorrowItemList(){
        List<BorrowItem> borrowItemList = new ArrayList<>();
        for(int index=0; index<5; index++){
            borrowItemList.add(
                    new BorrowItem(BORROW_ITEM_PREFIX + "-" + SHORT_DATE_TIME_FORMATTER.format(LocalDateTime.now()) + "-" + index, "A00" + index, "测试档案题名"+index, "案件")
            );
        }
        return borrowItemList;
    }

    public static List<ExportArchiveInfo> generateExportArchiveInfoList(){
        List<ExportArchiveInfo> archiveInfoList = new ArrayList<>();
        archiveInfoList.add(new ExportArchiveInfo("A001", "杭州亚运会城市建设规划方案1", "record"));
        archiveInfoList.add(new ExportArchiveInfo("A002", "杭州亚运会城市建设规划方案2", "volume"));
        archiveInfoList.add(new ExportArchiveInfo("A003", "杭州亚运会城市建设规划方案3", "volume_record"));
        return archiveInfoList;
    }
}
