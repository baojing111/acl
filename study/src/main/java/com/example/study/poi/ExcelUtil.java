package com.example.study.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import java.lang.reflect.Field;
import java.util.*;

/**
 * excel 导入工具
 */
public  class ExcelUtil{

    private final String SUFFIX_2003 = ".xls";
    private final String SUFFIX_2007 = ".xlsx";
    //列数
    private Integer totalCells = 0;

    private List<Map<String,String>> listError = new ArrayList<>();

    /**
     * excel导入 模版方法
     * @param file
     * @param aclass 要转化的对象
     * @param <G>
     * @return
     * @throws Exception
     */
    public <G> Map<String,List<G>> tempalteMethod(MultipartFile file,Class<G> aclass) throws Exception {
        //创建Workbook
        Workbook workbook = createWorkbook(file);
        //获取工作表
        List<Sheet> sheets = getSheets(workbook);
        //将工作表转为对应的对象
        if (!CollectionUtils.isEmpty(sheets)){
            Map<String,List<G>> result = new HashMap<>();
            for (Sheet sheet : sheets){
                Map<Integer, List<String>> map = readByColumn(sheet);
                List<G> gs = buildExcelDTO(map, aclass);
                result.put(sheet.getSheetName(),gs);
            }
            return result;
        }
        return null;
    }

    /*
     * 1、文件类型的校验，并获取工workbook
     */
    public Workbook createWorkbook(MultipartFile file) throws Exception {
        if (file == null) {
            return null;
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
        int totalRows = sheet.getLastRowNum();
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
    public <T> List<T> buildExcelDTO(Map<Integer,List<String>> map,Class<T> aclass) throws Exception{
        if (CollectionUtils.isEmpty(map) || aclass == null){
            return null;
        }
        //获取转化对象的属性
        Field[] fields = aclass.getDeclaredFields();
        Map<String,Field> mapFiel = new HashMap<>();
        if(mapFiel == null){
            throw new RuntimeException("class 对象的属性为null");
        }
        //对属性进行缓存map key:注解的value值  value：field对象
        for (Field field : fields){
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            String value = annotation.value();
            mapFiel.put(value,field);
        }
        //定义返回的容器
        List list = new ArrayList<>();
        Set<Integer> integers = map.keySet();
        for (Integer code :integers) {
            //获取一列数据
            List<String> strings = map.get(code);
            if (CollectionUtils.isEmpty(strings)){
                continue;
            }
            //获取head值
            String headValue = strings.get(0);
            //mapFiel 中获取要赋值的属性
            Field flied = mapFiel.get(headValue);
            if (flied == null) {
                throw new RuntimeException("实体对象中没有" + headValue);
            }
            for (int hang = 1; hang < strings.size(); hang++) {
                //属性为private 设置属性为可访问的
                flied.setAccessible(true);
                Object obj = null;
                try {
                    obj = list.get(hang - 1);
                }catch (IndexOutOfBoundsException e){
                    //通过放射利用无参构造方法创建对象
                    obj = aclass.newInstance();
                    list.add(hang - 1,obj);
                }
                //属性赋值
                flied.set(obj,strings.get(hang));
            }
        }

        return list;
    }


}
