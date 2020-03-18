package com.pysbizz.pdfreader.pdfreader.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.pysbizz.pdfreader.pdfreader.PdfReaderController;
import com.pysbizz.pdfreader.pdfreader.model.VoterData;


@Service
public class PDFReader {

	public static void main(String[] args){
		
		try {
			new PDFReader().readPDFData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void readPDFData() throws IOException {
		


		try (PDDocument document = PDDocument.load(new File(PdfReaderController.UPLOADED_FOLDER))) {

            document.getClass();

            if (!document.isEncrypted()) {
			
				/*
				 * PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				 * stripper.setSortByPosition(true);
				 */
            	
            	
                PDFTextStripper tStripper = new PDFTextStripper();

				/*
				 * Matrix textMatrix = tStripper.getTextLineMatrix(); float scaleX =
				 * textMatrix.getScaleX(); float scaleY = textMatrix.getScaleY();
				 * System.out.println(" scaleX : "+scaleX+ "  scaleY:"+scaleY);
				 */ 

                
                
                String pdfFileInText = tStripper.getText(document);
                // System.out.println("Text:" + pdfFileInText);

				// split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                List<VoterData> voterDataList = new ArrayList<>();                 
                senario1(lines,voterDataList);
               // senario2(lines,voterDataList);
                	
                	try {
						WriteVoterDataToExcel.write(voterDataList);
					} catch (Exception e) {
						e.printStackTrace();
					}
                	
				/*
				 * for (String line : lines) { // System.out.println(line);
				 * 
				 * }
				 */

            }

        }
	}
	
	private void senario1(String lines[], List<VoterData> voterDataList) {

		for (int i = 0; i < lines.length; i++) {
			try {
				if (lines[i].contains("House")) {

					VoterData voterData = new VoterData();
					voterData.setSex(getSex(lines[i - 1]));
					voterData.setAge(getAge(lines[i - 1]));
					voterData.setSeqNumber(getSquenceNumber(lines[i + 2]));
					voterData.setVoterId(getVoterId(lines[i + 2]));
					voterData.setName(lines[i + 3]);
					voterData.setFatherName(lines[i + 6]);
					voterData.setHouseNumber(lines[i + 7]);
					voterDataList.add(voterData);
					// System.out.println(voterData.toString());
				}
			} catch (Exception e) {
				// System.out.println(lines[i]+" =========== Error");
				// e.printStackTrace();
			}
		}

	}
	
	
	private void senario2(String lines[], List<VoterData> voterDataList) {

		for (int i = 0; i < lines.length; i++) {
			try {
				if (lines[i].contains("House")) {

					VoterData voterData = new VoterData();
					voterData.setSex(lines[i + 4].trim().split("\\s+")[1]);
					voterData.setAge(lines[i + 4].trim().split("\\s+")[0]);
					voterData.setSeqNumber(lines[i - 3]);
					voterData.setVoterId(lines[i + 1]);
					voterData.setName(lines[i + 5]);
					voterData.setFatherName(lines[i + 6]);
					voterData.setHouseNumber(lines[i + 3]);

					voterDataList.add(voterData);
					// System.out.println(voterData.toString());
				}
			} catch (Exception e) {
				// System.out.println(lines[i]+" =========== Error");
				// e.printStackTrace();
			}
		}

	}
	
	private String getSex(String sexAndAge) {
		if (sexAndAge.contains("Male")) {
			return "Male";
		} else {
			return "FeMale";
		}
	}

	private String getSquenceNumber(String string) {
		return string.trim().split("\\s+")[0];
	}
	
	private String getVoterId(String string) {
		return string.trim().split("\\s+")[1];
	}
	
	private String getAge(String string) {
		return string.split(":")[2];
	}

}
