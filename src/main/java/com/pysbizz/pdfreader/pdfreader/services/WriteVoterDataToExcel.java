package com.pysbizz.pdfreader.pdfreader.services;



import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pysbizz.pdfreader.pdfreader.PdfReaderController;
import com.pysbizz.pdfreader.pdfreader.model.VoterData;


public class WriteVoterDataToExcel {
	public static String outputPath = "";
	public static File outputfile;
   public static void write(List<VoterData> voterData ) throws Exception {

      //Create blank workbook
      XSSFWorkbook workbook = new XSSFWorkbook();
      
      //Create a blank sheet
      XSSFSheet spreadsheet = workbook.createSheet( " Voter Info ");

      //Create row object
      XSSFRow row;

      //Iterate over data and write to sheet
      int rowid = 0;
      
      for(VoterData vd: voterData) {
    	  row = spreadsheet.createRow(rowid++);
    	  XSSFCell cell = row.createCell(0);
    	  cell.setCellValue(vd.getSeqNumber());
    	  
    	  cell = row.createCell(1);
    	  cell.setCellValue(vd.getVoterId());
    	  
    	  cell = row.createCell(2);
    	  cell.setCellValue(vd.getName());
    	  
    	  cell = row.createCell(3);
    	  cell.setCellValue(vd.getFatherName());
    	  
    	  cell = row.createCell(4);
    	  cell.setCellValue(vd.getHouseNumber());
    	  
    	  cell = row.createCell(5);
    	  cell.setCellValue(vd.getSex());
    	  
    	  cell = row.createCell(6);
    	  cell.setCellValue(vd.getAge());
    	  
      }
      
      //Write the workbook in file system
      outputfile = new File(PdfReaderController.UPLOADED_FOLDER+"/Writesheet_"+System.currentTimeMillis()+".xlsx");
      FileOutputStream out = new FileOutputStream(outputfile);
      workbook.write(out);
      outputPath = outputfile.getAbsolutePath();
      System.out.println("abslut "+outputfile.getAbsolutePath());
      out.close();
      System.out.println("Writesheet.xlsx written successfully");
   }
}
