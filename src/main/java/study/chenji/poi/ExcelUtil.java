package study.chenji.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public  class ExcelUtil {

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";
    //列数
    private Integer totalCells = 0;
    /*
     * 1、文件类型的校验，并获取工workbook
     */
    public Workbook createWorkbook(MultipartFile file) throws Exception {
        if (file == null) {
            throw new RuntimeException("文件对象不能为空");
        }
        //获取文件的名字
        String originalFilename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (originalFilename.endsWith(SUFFIX_2003)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(SUFFIX_2007)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            throw new RuntimeException("格式错误");

        }
        return workbook;
    }

    /*
     * 2、workbook 中获取sheet列表
     */
    public List<Sheet> getSheets(Workbook workbook){
        if (workbook == null){
            return null;
        }
        //获取所有的工作表的的数量
        int numOfSheet = workbook.getNumberOfSheets();
        List<Sheet> list = new ArrayList<>();
        //遍历这个这些表
        for (int i = 0; i < numOfSheet; i++) {
            list.add(workbook.getSheetAt(i));
        }
        return list;

    }
    /*
     * 2、文件类型的校验 map<列名，没列的指>
     */
    public Map<Integer,List<String>> readByColumn(Sheet sheet){
        if (sheet == null){
            return null;
        }
        Map<Integer,List<String>> dataMap = new HashMap<>();
        //行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        //列数
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        //申明容器
        for(int lie = 0;lie < this.totalCells;lie++){
            List<String> list = new ArrayList<>();
            dataMap.put(lie, list);
        }
        //将sheet中的数据分装到 map 中
        for (int r = 0; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            // 循环Excel的列
            for (int c = 0; c < totalCells; c++){

                Cell cell = row.getCell(c);
                String cellValue = "";

                if (null != cell){
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                            cellValue = cell.getNumericCellValue() + "";
                            break;

                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;

                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;

                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;

                        case HSSFCell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;

                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            cellValue = "非法字符";
                            break;

                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }

                dataMap.get(c).add(cellValue);

            }

        }
        return dataMap;
    }

    /**
     * 3、组装成Excel对象
     */
    public Object buildExcelDTO(Map<Integer,List<String>> map,Class aclass) throws IllegalAccessException, InstantiationException {
        if (map == null || aclass == null){
            return null;
        }
        Field[] fields = aclass.getFields();
        Map<String,Field> mapFiel = new HashMap<>();
        if (fields != null){
            for (Field field : fields){
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                String value = annotation.value();
                mapFiel.put(value,field);
            }
        }
        List list = new ArrayList<>();

        Set<Integer> integers = map.keySet();
        for (Integer code :integers) {
            List<String> strings = map.get(code);
            if (CollectionUtils.isEmpty(strings)){
                continue;
            }
            String headValue = strings.get(0);
            Field flied = mapFiel.get(headValue);
            for (int lie = 1; lie < strings.size(); lie++) {
                flied.setAccessible(true);
                Object obj = null;
                try {
                    obj = list.get(lie);

                }catch (IndexOutOfBoundsException e){
                    obj = aclass.newInstance();
                }
                if (obj == null){
                    continue;
                }
                flied.set(obj,strings.get(lie));
            }
        }

        return list;
    }



}
