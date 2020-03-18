package com.pysbizz.pdfreader.pdfreader.services;



import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pysbizz.pdfreader.pdfreader.model.VoterData;


public class WriteVoterDataToExcel {
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
      FileOutputStream out = new FileOutputStream(
         new File(Utils.outputFileLocation+"/Writesheet_"+System.currentTimeMillis()+".xlsx"));
      
      workbook.write(out);
      out.close();
      System.out.println("Writesheet.xlsx written successfully");
   }
}
