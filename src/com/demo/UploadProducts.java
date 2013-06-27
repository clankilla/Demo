package com.demo;

/*
 	Ejemplo de como leer excell en el WEB-INF
 */

import java.io.IOException;
import javax.faces.context.FacesContext;
import jxl.*;
import jxl.read.biff.BiffException;

public class UploadProducts {
	public void leerExcell(){
		Cell cell;
		
		try{
			Workbook work = Workbook.getWorkbook(FacesContext.getCurrentInstance()
									.getExternalContext().
									getResourceAsStream("WEB-INF/Resources/prueba.xls"));
			Sheet workSheet = work.getSheet(0);
			
			for(int i = 0; i < workSheet.getRows(); i++)
				for(int j = 0; j < workSheet.getColumns(); j++){
					cell = workSheet.getCell(j, i);
					System.out.println(cell.getContents());
				}
			
		}catch(IOException e){
			
		}catch(BiffException e){
			
		}
	}
}
