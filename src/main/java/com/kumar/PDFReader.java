package com.kumar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFReader {

	private static final String FILE_NAME = "C:/Users/IBM_ADMIN/Desktop/Knowledge_Transfer/test/target/output.pdf";

    public static void main(String[] args) throws DocumentException {

        PdfReader reader;
        
        Map<Integer, String> fieldTypes = createFeildType();
        try {

            reader = new PdfReader("C:/Users/IBM_ADMIN/Desktop/Knowledge_Transfer/test/form.pdf");
            PdfStamper stamp = new PdfStamper(reader,  new FileOutputStream(FILE_NAME));
            // pageNumber = 1
            AcroFields fields = stamp.getAcroFields();
            Set<String> fieldNames = fields.getFields().keySet();

            for (String fieldName : fieldNames) {
              System.out.println( "Field Type :"+ fieldTypes.get(fields.getFieldType(fieldName)) +", Name :" +fieldName + ", Value :" + fields.getField( fieldName ) );
              if(fields.getFieldType(fieldName) == 2) {
            	  fields.setField(fieldName, "Yes");
             }else if(fields.getFieldType(fieldName) == 4) {
            	 fields.setField(fieldName, "Done");
             }
            }
            stamp.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	private static Map<Integer, String> createFeildType() {
		Map<Integer, String> fieldType = new HashMap<Integer, String>();
		fieldType.put(0, "NONE");
		fieldType.put(1, "PUSHBUTTON");
		fieldType.put(2, "CHECKBOX");
		fieldType.put(3, "RADIOBUTTON");
		fieldType.put(4, "TEXT");
		fieldType.put(5, "LIST");
		fieldType.put(6, "COMBO");
		fieldType.put(7, "SIGNATURE");
		return fieldType;
	}
}
