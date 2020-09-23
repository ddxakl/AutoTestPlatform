package com.agree.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.agree.util.uid.UUID;

/**
 * 
 * @ClassName: FileUtil 
 * @author xrp09
 *
 */
public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);	
	/**
	 * 
	 * @Title：	BuffWrite
	 * @param	fileList
	 * @param	filePath
	 * @return	boolean
	 * @throws	Exception
	 */
    public static boolean BuffWrite(ArrayList<String> fileList,String filePath){
       try {
    		
    		FileOutputStream fw=new FileOutputStream(filePath);
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fw,"iso8859-1"));
	
	    	for (int i=0;i<fileList.size();i++){
	        	bw.write(fileList.get(i)+"");
	        	bw.write("\n");
	        }       
	        	bw.flush();    
	        	bw.close();  
	        	fw.close();
	        } catch (Exception e) {    
	          e.printStackTrace();    
	          return false;
	        } finally {  
	        
	        }    
        return true;
    }
    
    /**
     * 删除单个文件
     * @Title：	deleteFile
     * @param 	fileName 	要删除的文件的文件名
     * @return 	boolean		单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
    File file = new File(fileName);
    // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
    if(file.exists() && file.isFile()) {
    if(file.delete()) {
    	logger.debug("删除单个文件" + fileName + "成功！");
    return true;
    } else {
    	logger.debug("删除单个文件" + fileName + "失败！");
    return false;
    }
    } else {
    	logger.debug("删除单个文件失败：" + fileName + "不存在！");
    return false;
    }
    }

    /**
     * 复制文件
     * @Title：	copyFile
     * @param	from 	代表的是复制的源文件
     * @param	to		代表目标文件
     * @return 	void
     * @throws	Exception
     */
    public static void copyFile(String from, String to)  throws Exception {
      try{
        File file_from = new File(from);
        FileInputStream in = new FileInputStream(file_from);
        
        File file_to =new File(to);
        if(!file_to.exists() ){
          String ls_to_parent = file_to.getParent();
          File file_to_parent = new File(ls_to_parent);
          file_to_parent.mkdirs();
        }

        FileOutputStream out = new FileOutputStream(file_to);

        byte[] buf = new byte[128 * 1024];
        int c;

        while (true) {
          c = in.read(buf);
          if (c == -1)
            break;
            out.write(buf, 0, c);
        }
        in.close();
        out.close();
        logger.debug("复制单个文件成功："+file_to);
      }catch(Exception ex){
    	  logger.error("文件复制错误!");
        throw new Exception("文件复制错误!");
       
      }
    }

    /**
     * 移动文件
     * @Title：	moveFile
     * @param	from	代表要移动的源文件
     * @param	to		代表目标文件
     * @return	boolean	移动成功返回true
     * @throws	Exception
     */
    public static boolean moveFile(String from, String to) throws Exception {
      File file_from = new File(from);
      FileInputStream in = new FileInputStream(file_from);
      File file_to = new File(to);
      if (!file_to.exists()) {
        String ls_to_parent = file_to.getParent();
        File file_to_parent = new File(ls_to_parent);
        file_to_parent.mkdirs();
      }

      FileOutputStream out = new FileOutputStream(file_to); 

      byte[] buf = new byte[128 * 1024];
      int c;

      while (true) {
        c = in.read(buf);
        if (c == -1)
          break;
        out.write(buf, 0, c);
      }

      in.close();
      out.close();
      if (file_to.length() >= 0) {
        file_from.delete();
      }
      return true;
    }
     /** 
     * @Title: writeFile 
     * @Description: 写文件
     * @throws Exception     
     */ 
    public static void writeFile(String filename,String content) throws Exception{
    //	filename=java.net.URLDecoder.decode(FileUtil.class.getClassLoader().getResource("/temp").getPath(),"utf-8")+filename;
    	OutputStreamWriter osw = null;
    	FileOutputStream fos =null;
    	try { 
 		     fos = new FileOutputStream(filename); 
 			 osw = new OutputStreamWriter(fos, "UTF-8" ); 
 			osw.write(content); 
 			osw.flush(); 
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    	}finally{
    		//osw.close();
    		fos.close();
    	}
    	
    	
     }
    public static String readFile(String filename) throws Exception {
    	String result="";
    	char[] tempchars = new char[1];
    	//tempchars.
        Reader  reader = new InputStreamReader(new FileInputStream(filename),"utf-8");
        int tempchar;  
        while ((tempchar = reader.read()) != -1){  
	        //对于windows下，rn这两个字符在一起时，表示一个换行。  
	        //但如果这两个字符分开显示时，会换两次行。  
	        //因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。  
	      //  if (((char)tempchar) != 'r'){  
	        	tempchars[0]=(char)tempchar;  
	        	result=result.concat(new String(tempchars));
	       // }  
        }  
        reader.close();  
		return result;
    }
	/**
	 * 根据文件对象，将文件内容存储到字符串中
	 * @param file
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static  String readFile(File file, String encoding) throws Exception {
		String fileContent = "";
		FileInputStream fis = new FileInputStream(file);
		try {
			InputStreamReader isr = (encoding==null || "".equals(encoding.trim()))?new InputStreamReader(fis):new InputStreamReader(fis, encoding);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				fileContent += line;
				fileContent += "\r\n";
			}
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			fis.close();
		}
		return fileContent;
	}    
	/**
	 * 判断文件是否存在
	 * @param fileName 文件名
	 * @param dir 文件路径
	 * @return
	 */
	public static boolean isFileExist(String fileName, String dir) {
		File file = new File(dir + fileName);
		return (file.exists()) ? true : false;
	}
	
	/**
	 * 判断文件名是否已经存在，如果存在则在后面家(n)的形式返回新的文件名，否则返回原始文件名 例如：已经存在文件名 log4j.htm 则返回log4j(1).htm
	 * @param fileName  文件名不包含路径
	 * @return 判断后的文件名
	 */
	public static String checkFileName(String fileName, String dir) {
		boolean isDirectory = new File(dir + fileName).isDirectory();
		if (isFileExist(fileName, dir)) {
			int index = fileName.lastIndexOf(".");
			StringBuffer newFileName = new StringBuffer();
			String name = isDirectory ? fileName : fileName.substring(0, index);
			String exp = isDirectory ? "" : fileName.substring(index);
			int nameNum = 1;
			while (true) {
				newFileName.append(name).append("(").append(nameNum).append(")");
				if (!isDirectory) {
					newFileName.append(exp);
				}
				if (isFileExist(newFileName.toString(), dir)) {
					nameNum++;
					newFileName = new StringBuffer();
					continue;
				}
				return newFileName.toString();
			}
		}
		return fileName;
	}
	
	/**
	 * 根据路径创建一系列的目录
	 * @param path
	 */
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			File file1 =file.getAbsoluteFile();

			if (!file1.exists()) {
				file1.setWritable(true, false);
				return file1.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			file = null;
		}
		return false;
	}
	
	/**
	 * 根据指定字符集将内容写到文本中
	 * @param filePath 要写入到的目标文件路径
	 * @param fileContent 要写入的内容
	 * @param encoding 字符集
	 * @return
	 */
	public static boolean writeFileEncoding(String fileContent, String filePath, String encoding) {
		boolean flag = true;
		FileOutputStream os = null;
		try {
			filePath = filePath.trim().replace("\\", "/");
			mkDirectory(filePath.substring(0, filePath.lastIndexOf("/")));
			os = new FileOutputStream(filePath);
			byte[] b = (encoding!=null && !"".equals(encoding.trim()))?fileContent.getBytes(encoding):fileContent.getBytes();
			os.write(b, 0, b.length);
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		return flag;
	}
	
    public static void main(String[] args) throws Exception{
    	String file="E:\\bsms\\WebRoot\\temp\\admin_page.jsp";
    	System.out.println(FileUtil.readFile(file));
    }
	/**
	 * 重命名文件
	 * @param dir 文件路径
	 * @param oldfilename 原文件名称
	 * @param newfilename 新文件名称
	 * @return void
	 * @throws Exception
	 */
	public static void renameFile(String dir, String oldfilename, String newfilename) throws Exception {
		try {
			File oldfile = new File(dir+oldfilename);
			File onewfile = new File(dir+newfilename);
			oldfile.renameTo(onewfile);
		} catch (Exception e) {
			logger.error("文件重名名错误!", e);
			throw new Exception("文件重命名错误!");
		}
	}

	/**
	 * @Description 存储报文文件
	 * @param msg 报文内容
	 * @param project 工程信息，至少包含 rcid, arcid
	 * @param flowid 报文流水号
	 * @return 流水文件相对完整路径
	 * @throws Exception
	 */
//	public static String writeBaffleMsgFile(byte[] msg, AitProject project, String flowid) throws Exception{
//		if(flowid == null || "".equals(flowid))
//			return "";
//		String basePath =FileUtil.class.getClassLoader().getResource("").toString();
//		basePath = basePath.substring(basePath.indexOf("file:/")+6,basePath.indexOf("/WEB-INF")) + "/";
//		String filePath = Constants.ZICHAN + Constants.separator + "area_" + project.getAreaId() + Constants.separator + "P_" + project.getProjectCode().trim() +
//			Constants.separator + "FlowLog" + Constants.separator + DateUtil.getCurrentDateByFormat("yyyyMMdd") + Constants.separator;
//		//linux下，保存的文件路径会根据user.dir确定
//		System.setProperty("user.dir", "");
//		mkDirectory(basePath+filePath);
//		filePath += flowid + ".json";
//		FileOutputStream os = null;
//		try {
//			System.setProperty("user.dir", "");
//			File file2 = new File(basePath + filePath);
//			File file_a =file2.getAbsoluteFile();
//			os = new FileOutputStream(file_a);
////			os = new FileOutputStream(basePath + filePath);
//			os.write(msg, 0, msg.length);
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				if (os != null) {
//					os.flush();
//					os.close();
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw e;
//			}
//		}
//		return filePath;
//	}
	
	/**
	 * 解析XML
	 * @param filePath
	 * map<sheet页，页内容/每一行<map<列名称，每一个值>>>
	 */
	public static Map<String,List<Map<String,String>>> readEXCEL(String filePath)throws Exception{
		XSSFWorkbook wb =null;
		XSSFSheet sheet=null;
		XSSFRow row=null;
		Map<String,List<Map<String,String>>> allMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> sheetList = null; // sheet页中所有Map
		Map<String,String> listMap = null; //每个sheet页中数据
		try {
			//获取xml对象
			wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
			//excel文件的页数
			int sheetNum = wb.getNumberOfSheets();
			for(int i=0;i<sheetNum;i++){
				sheetList = new ArrayList<Map<String,String>>();
				sheet = wb.getSheetAt(i);
				if(sheet==null){
					continue;
				}
				int rowNum = sheet.getLastRowNum();
				if (rowNum == 0) {
					continue;
				}
				XSSFRow names = sheet.getRow(0); // 第0行名称
				//判断第0行是否为null
				if(names==null){
					continue;
				}
				String sheetName = sheet.getSheetName();
				for (int j=1;j<=rowNum;j++){
					listMap = new HashMap<String,String>();
					row = sheet.getRow(j);
					if (row == null) {
						continue;
					}
					int colNum = sheet.getRow(0).getLastCellNum();
					int k = 0;
					while (k < colNum) {
						XSSFCell cell = row.getCell(k);
						listMap.put(getCellFormatValue(names.getCell(k)).trim(), getCellFormatValue(cell).trim());
						k++;
					}
					sheetList.add(listMap);		
				}
				allMap.put(sheetName, sheetList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allMap;
	}
	
	/**
	 * 获取Cell格式值
	 * @param xssfCell
	 * @return
	 */

	private static String getCellFormatValue(XSSFCell xssfCell)throws Exception {
		String cellvalue = "";
		// String comment="";
		if (xssfCell != null) {
			// 判断当前Cell的Type
			switch (xssfCell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {

					Date date = xssfCell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					cellvalue = String.valueOf(xssfCell.getNumericCellValue());
				}
				break;
			}
			case XSSFCell.CELL_TYPE_STRING:
				cellvalue = xssfCell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	
	/**
	 *对上传文件进行判断
	 *@param fileInput
	 */
	public static int getExcelData(File fileInput)throws Exception{
		XSSFWorkbook wb =null;
		XSSFSheet sheet=null;
		XSSFRow names = null;
		List<XSSFRow> namesList = new ArrayList<XSSFRow>();
		//获取excel文件
		wb = new XSSFWorkbook(new FileInputStream(new File(fileInput.getAbsolutePath())));
		int sheetNum = wb.getNumberOfSheets();
		for (int sheetNo=0;sheetNo<sheetNum;sheetNo++){
			sheet = wb.getSheetAt(sheetNo);
			if (sheet == null) {
				continue;
			}
			int rowNum = sheet.getLastRowNum();
			if (rowNum == 0) {
				continue;
			}
			//获取第0行
			names = sheet.getRow(0);
			if(names==null){
				continue;
			}
			namesList.add(names);		
		}
		return namesList.size();
	}
	
	/**
	 * 获取sheet页名称的集合
	 * @param filePath
	 * @return
	 */
	public static List<String> getSheetNameList(String filePath){
		XSSFWorkbook wb =null;
		XSSFSheet sheet=null;
		String sheetName = null;
		XSSFRow names = null;
		List<String> sheetNameList = new ArrayList<String>();
		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		int sheetNum = wb.getNumberOfSheets();
		for(int i=0;i<sheetNum;i++){
			sheet = wb.getSheetAt(i);
			if(sheet==null){
				continue;
			}
			int rowNum = sheet.getLastRowNum();
			if (rowNum == 0) {
				continue;
			}
			names = sheet.getRow(0);
			if(names==null){
				continue;
			}
			sheetName = sheet.getSheetName();
			sheetNameList.add(sheetName);
		}
		return sheetNameList;
	}
	
	/**
	 * 获取sheet页中有数据列的名称集合
	 * @param filePath
	 * @return
	 */
	public static Map<String,List<String>> getSheetDataNameList(Map<String, List<Map<String,String>>> excelData,List<String> sheetNameList){
		String sheetName = null;
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for(int i=0;i<sheetNameList.size();i++){
			sheetName = sheetNameList.get(i);
			List<Map<String,String>> dataList = excelData.get(sheetName);
			List<String> listName  = new ArrayList<String>();
			for (Map<String, String> m : dataList){
				for(String k : m.keySet()){
					if(k==null||"".equals(k)){
						continue;
					}
					String value = m.get(k);
					if(value!=null&&!"".equals(value)&&!listName.contains(k)){
						listName.add(k);
					}
				}
			}
			map.put(sheetName, listName);
		}
		return map;
	}
	
	/**
	 * 把excel转化为html
	 * @param filePath,isWithStyle
	 * @return
	 */
	public static String readExcelToHtml(String filePath,boolean isWithStyle){
		 Sheet sheet = null;
		 InputStream is = null;
		 StringBuffer sb;
		 sb = new StringBuffer();File sourcefile = new File(filePath);
		try {			
			 is = new FileInputStream(sourcefile);
			 Workbook wb = WorkbookFactory.create(is);	 
			 for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++){
			     sheet = wb.getSheetAt(sheetIndex);// 获所有的sheet
			     if(sheet!=null){
			    	 Row row0 = sheet.getRow(0);
			    	 if(row0==null){
			    		 continue;
			    	 }
				     int lastRowNum = sheet.getLastRowNum();
				     //得到所有合并单元格
				     Map<String, String> map[] = getRowSpanColSpanMap(sheet);
				     sb.append("<table style=\'border-collapse:collapse;\' width=\'100%\'>");
				     Row row = null;        //兼容
				     Cell cell = null;    //兼容	     
				     for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
				         row = sheet.getRow(rowNum);
				         if (row == null) {
				             sb.append("<tr><td > &nbsp;</td></tr>");
				             continue;
				         }
				         sb.append("<tr>");
				         int lastColNum = sheet.getRow(0).getLastCellNum();
				         for (int colNum = 0; colNum < lastColNum; colNum++) {
				             cell = row.getCell(colNum);
				             if (cell == null) {//当遇到空白的单元格时
				            	 sb.append("<td style=\"align:center;valign:center;width:2048px;color:#000000;"
				            	 		+ "border-top:solid #d0d7e5 1px;border-right:solid #d0d7e5 1px;"
				            	 		+ "border-bottom:solid #d0d7e5 1px;border-left:solid #d0d7e5 1px;\">&nbsp;</td>");
				                     continue;
				                 }	 
			                 String stringValue = getCellValue(cell);
			                 //对有合并的单元格进行操作
			                 if (map[0].containsKey(rowNum + "," + colNum)) {
				                 String pointString = map[0].get(rowNum + "," + colNum);
				                 map[0].remove(rowNum + "," + colNum);
				                 int bottomeRow = Integer.valueOf(pointString.split(",")[0]);
				                 int bottomeCol = Integer.valueOf(pointString.split(",")[1]);
				                 int rowSpan = bottomeRow - rowNum + 1;
				                 int colSpan = bottomeCol - colNum + 1;
				                 sb.append("<td rowspan= '" + rowSpan + "' colspan= '"+ colSpan + "' ");
				             } else if (map[1].containsKey(rowNum + "," + colNum)) {
				                 map[1].remove(rowNum + "," + colNum);
				                 continue;
				             } else {
				                 sb.append("<td ");
				             }	             
				             //判断是否需要样式
				             if(isWithStyle){
				            	//为单元格设置样式
				                 dealExcelStyle(wb, sheet, cell, sb);
				             }	             
				             sb.append(">");
				            if (stringValue == null || "".equals(stringValue.trim())) {
				                 sb.append(" &nbsp; ");
				             } else {
				                 // 将ascii码为160的空格转换为html下的空格（&nbsp;）
				                 sb.append(stringValue.replace(String.valueOf((char) 160),"&nbsp;"));
				             }
				             sb.append("</td>");
				         }
				         sb.append("</tr>");
				     }	
				     sb.append("</table>");
			     }
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	     return sb.toString();		
	}
	/**
	 *得到所有合并单元格
	 *@param sheet
	 *@return
	 */
	private static Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {		 
     Map<String, String> map0 = new HashMap<String, String>();
     Map<String, String> map1 = new HashMap<String, String>();
     int mergedNum = sheet.getNumMergedRegions();
     CellRangeAddress range = null;
     for (int i = 0; i < mergedNum; i++) {
         range = sheet.getMergedRegion(i);
         int topRow = range.getFirstRow();
         int topCol = range.getFirstColumn();
         int bottomRow = range.getLastRow();
         int bottomCol = range.getLastColumn();
         map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);
         int tempRow = topRow;
         while (tempRow <= bottomRow) {
             int tempCol = topCol;
             while (tempCol <= bottomCol) {
                 map1.put(tempRow + "," + tempCol, "");
                 tempCol++;
             }
             tempRow++;
         }
         map1.remove(topRow + "," + topCol);
     }
     Map[] map = { map0, map1 };
     return map;
	}
	
/**
  * 获取表格单元格Cell内容
  * @param cell
  * @return
  */
 private static String getCellValue(Cell cell) {

     String result = new String();  
     switch (cell.getCellType()) {  
     case Cell.CELL_TYPE_NUMERIC:// 数字类型  
         if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
             SimpleDateFormat sdf = null;  
             if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
                 sdf = new SimpleDateFormat("HH:mm");  
             } else {// 日期  
                 sdf = new SimpleDateFormat("yyyy-MM-dd");  
             }  
             Date date = cell.getDateCellValue();  
             result = sdf.format(date);  
         } else if (cell.getCellStyle().getDataFormat() == 58) {  
             // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
             double value = cell.getNumericCellValue();  
             Date date = org.apache.poi.ss.usermodel.DateUtil  
                     .getJavaDate(value);  
             result = sdf.format(date);  
         } else {  
             double value = cell.getNumericCellValue();  
             CellStyle style = cell.getCellStyle();  
             DecimalFormat format = new DecimalFormat();  
             String temp = style.getDataFormatString();  
             // 单元格设置成常规  
             if (temp.equals("General")) {  
                 format.applyPattern("#");  
             }  
             result = format.format(value);  
         }  
         break;  
     case Cell.CELL_TYPE_STRING:// String类型  
         result = cell.getRichStringCellValue().toString();  
         break;  
     case Cell.CELL_TYPE_BLANK:  
         result = "";  
         break; 
     default:  
         result = "";  
         break;  
     }  
     return result;  
 }
	
 /**
   * 处理表格样式
   * @param wb
   * @param sheet
   * @param cell
   * @param sb
   */
  private static void dealExcelStyle(Workbook wb,Sheet sheet,Cell cell,StringBuffer sb){
      
      CellStyle cellStyle = cell.getCellStyle();
      if (cellStyle != null) {
          short alignment = cellStyle.getAlignment();
          sb.append("align='" + convertAlignToHtml(alignment) + "' ");//单元格内容的水平对齐方式
          short verticalAlignment = cellStyle.getVerticalAlignment();
          sb.append("valign='"+ convertVerticalAlignToHtml(verticalAlignment)+ "' ");//单元格中内容的垂直排列方式
          
          if (wb instanceof XSSFWorkbook) {
                          
              XSSFFont xf = ((XSSFCellStyle) cellStyle).getFont(); 
              short boldWeight = xf.getFontHeightInPoints();
              sb.append("style='");
              sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
              sb.append("font-size: " + xf.getFontHeight() / 3 + "%;"); // 字体大小
              int columnWidth = sheet.getColumnWidth(cell.getColumnIndex());
              
              sb.append("width:" + columnWidth + "px;");
              
              XSSFColor xc = xf.getXSSFColor();
              if (xc != null && !"".equals(xc)) {
                  sb.append("color:#" + xc.getARGBHex().substring(2) + ";"); // 字体颜色
              }
              
              XSSFColor bgColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
              if (bgColor != null && !"".equals(bgColor)) {
                  sb.append("background-color:#" + bgColor.getARGBHex().substring(2) + ";"); // 背景颜色
              }
              sb.append(getBorderStyle(0,cellStyle.getBorderTop(), ((XSSFCellStyle) cellStyle).getTopBorderXSSFColor()));
              sb.append(getBorderStyle(1,cellStyle.getBorderRight(), ((XSSFCellStyle) cellStyle).getRightBorderXSSFColor()));
              sb.append(getBorderStyle(2,cellStyle.getBorderBottom(), ((XSSFCellStyle) cellStyle).getBottomBorderXSSFColor()));
              sb.append(getBorderStyle(3,cellStyle.getBorderLeft(), ((XSSFCellStyle) cellStyle).getLeftBorderXSSFColor()));
                  
          }else if(wb instanceof HSSFWorkbook){
              
              HSSFFont hf = ((HSSFCellStyle) cellStyle).getFont(wb);
              short boldWeight = hf.getFontHeightInPoints();
              short fontColor = hf.getColor();
              sb.append("style='");
              HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette(); // 类HSSFPalette用于求的颜色的国际标准形式
              HSSFColor hc = palette.getColor(fontColor);
              sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
              sb.append("font-size: " + hf.getFontHeight() / 2 + "%;"); // 字体大小
              String fontColorStr = convertToStardColor(hc);
              if (fontColorStr != null && !"".equals(fontColorStr.trim())) {
                  sb.append("color:" + fontColorStr + ";"); // 字体颜色
              }
              int columnWidth = sheet.getColumnWidth(cell.getColumnIndex()) ;
              sb.append("width:" + columnWidth + "px;");
              short bgColor = cellStyle.getFillForegroundColor();
              hc = palette.getColor(bgColor);
              String bgColorStr = convertToStardColor(hc);
              if (bgColorStr != null && !"".equals(bgColorStr.trim())) {
                  sb.append("background-color:" + bgColorStr + ";"); // 背景颜色
              }
              sb.append( getBorderStyle(palette,0,cellStyle.getBorderTop(),cellStyle.getTopBorderColor()));
              sb.append( getBorderStyle(palette,1,cellStyle.getBorderRight(),cellStyle.getRightBorderColor()));
              sb.append( getBorderStyle(palette,3,cellStyle.getBorderLeft(),cellStyle.getLeftBorderColor()));
              sb.append( getBorderStyle(palette,2,cellStyle.getBorderBottom(),cellStyle.getBottomBorderColor()));
          }

          sb.append("' ");
      }
  }
  
  /**
    * 单元格内容的水平对齐方式
    * @param alignment
    * @return
    */
   private static String convertAlignToHtml(short alignment) {

       String align = "left";
//       switch (alignment) {
//       case CellStyle
//           align = "left";
//           break;
//       case CellStyle.ALIGN_CENTER:
//           align = "center";
//           break;
//       case CellStyle.ALIGN_RIGHT:
//           align = "right";
//           break;
//       default:
//           break;
//       }
       return align;
   }
   /**
     * 单元格中内容的垂直排列方式
     * @param verticalAlignment
     * @return
     */
    private static String convertVerticalAlignToHtml(short verticalAlignment) {

//        String valign = "middle";
//        switch (verticalAlignment) {
//        case CellStyle.VERTICAL_BOTTOM:
//            valign = "bottom";
//            break;
//        case CellStyle.VERTICAL_CENTER:
//            valign = "center";
//            break;
//        case CellStyle.VERTICAL_TOP:
//            valign = "top";
//            break;
//        default:
//            break;
//        }
        return "center";
    }
    
    private static String convertToStardColor(HSSFColor hc) {
        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            if (HSSFColor.AUTOMATIC.index == hc.getIndex()) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                sb.append(fillWithZero(Integer.toHexString(hc.getTriplet()[i])));
            }
        }
        return sb.toString();
    }
    
    private static String fillWithZero(String str) {
        if (str != null && str.length() < 2) {
            return "0" + str;
        }
        return str;
    }
    
    static String[] bordesr={"border-top:","border-right:","border-bottom:","border-left:"};
	static String[] borderStyles={"solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid","solid","solid","solid","solid"};
	 
	private static String getBorderStyle(  HSSFPalette palette ,int b,short s, short t){
	          
	    if(s==0)return  bordesr[b]+borderStyles[s]+"#d0d7e5 1px;";;
	    String borderColorStr = convertToStardColor( palette.getColor(t));
	    borderColorStr=borderColorStr==null|| borderColorStr.length()<1?"#000000":borderColorStr;
	    return bordesr[b]+borderStyles[s]+borderColorStr+" 1px;";	         
	}
	     
    private static String getBorderStyle(int b,short s, XSSFColor xc){        
         if(s==0)return  bordesr[b]+borderStyles[s]+"#d0d7e5 1px;";;
         if (xc != null && !"".equals(xc)) {
             String borderColorStr = xc.getARGBHex();//t.getARGBHex();
             borderColorStr=borderColorStr==null|| borderColorStr.length()<1?"#000000":borderColorStr.substring(2);
             return bordesr[b]+borderStyles[s]+borderColorStr+" 1px;";
         }         
         return "";
     }	
    
    public static String getUUID() throws Exception{
		String uuid = new UUID().toString();
		uuid = uuid.substring(1);
 	   	return uuid;
	}
    
	
}
