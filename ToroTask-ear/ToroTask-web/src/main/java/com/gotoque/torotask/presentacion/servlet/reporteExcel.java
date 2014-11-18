package com.gotoque.torotask.presentacion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray;
import com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject;
import com.gotoque.torotask.vo.TareaVO;
import java.util.Collection;


public class reporteExcel extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public reporteExcel() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			try {

				JSONObject object  = (JSONObject)request.getSession().getAttribute("reporteTarea");
				JSONArray array   = (JSONArray)object.getJSONArray("tareas");
				
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Reporte");
				
				int rowNow = 0;
				
				HSSFRow headerRow = sheet.createRow((short)rowNow);
				headerRow.createCell((short)0).setCellValue("ID");
				headerRow.createCell((short)1).setCellValue("Proyecto");
				headerRow.createCell((short)2).setCellValue("Tarea");
				headerRow.createCell((short)3).setCellValue("Fecha Inicio");
				headerRow.createCell((short)4).setCellValue("Fecha Estimada");
				headerRow.createCell((short)5).setCellValue("Fecha cierre");
				headerRow.createCell((short)6).setCellValue("Estado");
				headerRow.createCell((short)7).setCellValue("Descripcion");
				headerRow.createCell((short)8).setCellValue("Dias Cierre Estimado");
				headerRow.createCell((short)9).setCellValue("Dias Cierre Real");
				headerRow.createCell((short)10).setCellValue("Dias Total Estimado");
				headerRow.createCell((short)11).setCellValue("Dias Total Real");
				
				for (int i = 0; i < array.length(); i++) {
					rowNow  = rowNow + 1;
					JSONObject object2 = array.getJSONObject(i);
					
					String idtarea = object2.getString("idtarea");
					String proyecto = isNull(object2.getString("proyecto"));
					String tarea = object2.getString("tarea");
					String fecha_inicio = object2.getString("fecha_inicio");
					String fechaTerminoEstimada = object2.getString("fechaTerminoEstimada");
					String fechaTerminoReal = object2.getString("fechaTerminoReal");
					String estado = object2.getString("estado");
					String descripcion = object2.getString("descripcion");
					String cierreDiasEstimado = object2.getString("cierreDiasEstimada");
					String cierreDiasReal = object2.getString("cierreDiasReal");
					String totalDiasEstimado = object2.getString("totalDiasEstimada");
					String totalDiasReal = object2.getString("totalDiasReal");
					
					headerRow = sheet.createRow((short)rowNow);
					headerRow.createCell((short)0).setCellValue(idtarea);
					headerRow.createCell((short)1).setCellValue(proyecto);
					headerRow.createCell((short)2).setCellValue(tarea);
					headerRow.createCell((short)3).setCellValue(fecha_inicio);
					headerRow.createCell((short)4).setCellValue(fechaTerminoEstimada);
					headerRow.createCell((short)5).setCellValue(fechaTerminoReal);
					headerRow.createCell((short)6).setCellValue(estado);
					headerRow.createCell((short)7).setCellValue(descripcion);
					headerRow.createCell((short)8).setCellValue(cierreDiasEstimado);
					headerRow.createCell((short)9).setCellValue(cierreDiasReal);
					headerRow.createCell((short)10).setCellValue(totalDiasEstimado);
					headerRow.createCell((short)11).setCellValue(totalDiasReal);
				}
				
				String contentType	= "application/vnd.ms-excel";
				response.setContentType(contentType);
				 
				ServletOutputStream out = response.getOutputStream();
				workbook.write(out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private String isNull(String info){
		if(info == null){
			info = "";
		}
		return info;
	}
	
}
